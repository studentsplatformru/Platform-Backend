package ru.studentsplatform.backend.system.log.tree.domain;

/**
 * @author Krylov Sergey (30.07.2020)
 */
public class TreeMethodCall {
	private boolean isRoot;
	private int layer;
	private JoinPointHelper joinPointHelper;
	private long start;
	private long end;
	private String returnValue;

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean root) {
		isRoot = root;
	}

	public JoinPointHelper getJoinPointHelper() {
		return joinPointHelper;
	}

	public void setJoinPointHelper(JoinPointHelper joinPointHelper) {
		this.joinPointHelper = joinPointHelper;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	@Override
	public String toString() {
		return "TreeMethodCall{" +
				"isRoot=" + isRoot +
				", layer=" + layer +
				", joinPointHelper=" + joinPointHelper +
				", start=" + start +
				", end=" + end +
				", returnValue='" + returnValue + '\'' +
				'}';
	}
}
