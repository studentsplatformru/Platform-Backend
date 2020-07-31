package ru.studentsplatform.backend.system.log.tree.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.system.log.tree.domain.JoinPointHelper;
import ru.studentsplatform.backend.system.log.tree.domain.TreeMethodCall;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Krylov Sergey (30.07.2020)
 */
@Service
public class TreeMethodCallService {
	public TreeMethodCall beginCall(JoinPointHelper joinPointHelper, int index) {
		var treeMethodCall = new TreeMethodCall();
		treeMethodCall.setJoinPointHelper(joinPointHelper);
		treeMethodCall.setStart(System.currentTimeMillis());
		treeMethodCall.setRoot(false);
		treeMethodCall.setLayer(index);
		return treeMethodCall;
	}

	public void endCall(TreeMethodCall treeMethodCall, String returnValue) {
		treeMethodCall.setEnd(System.currentTimeMillis());
		treeMethodCall.setReturnValue(returnValue);
	}

	public TreeMethodCall beginRootCall(JoinPointHelper joinPointHelper, int index) {
		var treeMethodCall = new TreeMethodCall();
		treeMethodCall.setJoinPointHelper(joinPointHelper);
		treeMethodCall.setStart(System.currentTimeMillis());
		treeMethodCall.setRoot(true);
		treeMethodCall.setLayer(index);
		return treeMethodCall;
	}

	public String createTree(ArrayList<TreeMethodCall> dataStorage) {
		var tree = new StringBuilder();
		var totalTime = dataStorage.get(0).getEnd() - dataStorage.get(0).getStart();
		tree.append("\n└──ROOT " );
		tree.append(totalTime + "ms\n");
		var size = dataStorage.size();
		for (var i = 0; i < size; i++) {
			var call = dataStorage.get(i);
			if (i == 0) {
				var layerFuture = dataStorage.get(1).getLayer();
				var line = createTab(call.getLayer()) + createBranch(-1, 0, layerFuture) + createLine(call);
				tree.append(line);
				tree.append("\n");
			} else if (i == size - 1) {
				var layerCurrent = dataStorage.get(i).getLayer();
				var layerBefore = dataStorage.get(i - 1).getLayer();
				var line = createTab(call.getLayer()) + createBranch(layerBefore, layerCurrent, 999) + createLine(call);
				tree.append(line);
				tree.append("\n");
			} else {
				var layerCurrent = dataStorage.get(i).getLayer();
				var layerBefore = dataStorage.get(i - 1).getLayer();
				var layerFuture = dataStorage.get(i + 1).getLayer();
				var line = createTab(call.getLayer()) + createBranch(layerBefore, layerCurrent, layerFuture) + createLine(call);
				tree.append(line);
				tree.append("\n");
			}
		}
		return tree.toString();
	}

	private String createLine(TreeMethodCall call) {
		var point = call.getJoinPointHelper();
		return String.format("%s.%s(%s) | %s | %d ms",
				point.getClassName(),
				point.getMethodName(),
				Arrays.toString(point.getParameters()),
				call.getReturnValue(),
				call.getEnd() - call.getStart());
	}

	private String createTab(int layer) {
		return "	".repeat(layer + 1);
	}

	private String createBranch(int before, int current, int future) {
		if (before <= current) {
			if (future > current) {
				return "└──";
			}
			if (future < current) {
				return "│   └──";
			}
			if (future == current) {
				return "├──";
			}
		}
		if (before > current) {
			return "?";
		}
		return "not found";
	}
}
