package ru.studentsplatform.backend.system.log.tree.storage;

import ru.studentsplatform.backend.system.log.tree.domain.TreeMethodCall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Krylov Sergey (30.07.2020)
 */
public class RequestStorage {
	private static final ThreadLocal<ArrayList<TreeMethodCall>> REQUEST_STORAGE = new ThreadLocal();
	private static final ThreadLocal<AtomicInteger> INDEX_STORAGE = new ThreadLocal();

	/**
	 * Метод получает список всех веток созданных при вызове данного запроса.
	 *
	 * @return Список "веток"
	 */
	public static List<TreeMethodCall> getRequestStorage() {
		var treeMethodCalls = REQUEST_STORAGE.get();
		if (treeMethodCalls == null) {
			treeMethodCalls = new ArrayList<>();
			REQUEST_STORAGE.set(treeMethodCalls);

			INDEX_STORAGE.set(new AtomicInteger(0));
		}
		return treeMethodCalls;
	}

	/**
	 * Получение индекса вложенности вызова метода.
	 *
	 * @return Индекс
	 */
	public static AtomicInteger getIndexStorage() {
		var index = INDEX_STORAGE.get();
		if (index == null) {
			index = new AtomicInteger(0);
			INDEX_STORAGE.set(index);
		}
		return index;
	}
}
