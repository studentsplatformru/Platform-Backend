package ru.studentsplatform.backend.system.log.tree.storage;

import ru.studentsplatform.backend.system.log.tree.domain.TreeMethodCall;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Krylov Sergey (30.07.2020)
 */
public class RequestStorage {
	private static final ThreadLocal<ArrayList<TreeMethodCall>> requestStorage = new ThreadLocal();
	private static final ThreadLocal<AtomicInteger> indexStorage = new ThreadLocal();

	public static ArrayList<TreeMethodCall> getRequestStorage() {
		var treeMethodCalls = requestStorage.get();
		if (treeMethodCalls == null) {
			treeMethodCalls = new ArrayList<>();
			requestStorage.set(treeMethodCalls);

			indexStorage.set(new AtomicInteger(0));
		}
		return treeMethodCalls;
	}

	public static AtomicInteger getIndexStorage() {
		var index = indexStorage.get();
		if (index == null) {
			index = new AtomicInteger(0);
			indexStorage.set(index);
		}
		return index;
	}
}
