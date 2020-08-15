package ru.studentsplatform.backend.system.log.tree.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.system.helper.CollectionUtils;
import ru.studentsplatform.backend.system.log.tree.domain.JoinPointHelper;
import ru.studentsplatform.backend.system.log.tree.domain.TreeMethodCall;
import ru.studentsplatform.backend.system.log.tree.storage.RequestStorage;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервис отвечающий за дерево логов.
 *
 * @author Krylov Sergey (30.07.2020)
 */
@Service
public class TreeLoggerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final TreeMethodCallService treeMethodCallService;

	@Autowired
	public TreeLoggerService(TreeMethodCallService treeMethodCallService) {
		this.treeMethodCallService = treeMethodCallService;
	}

	/**
	 * Метод профилирования, реализует логику логгирования.
	 *
	 * @param joinPoint Точка входа
	 * @return Результат
	 */
	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
		var joinPointHelper = getJoinPointHelper(joinPoint);
		var dataStorage = RequestStorage.getRequestStorage();
		var indexStorage = RequestStorage.getIndexStorage();
		Object result = null;
		TreeMethodCall treeMethodCall = null;
		try {
			treeMethodCall = before(dataStorage, indexStorage, joinPointHelper);
			result = joinPoint.proceed();
		} catch (Throwable throwable) {
			after(treeMethodCall, dataStorage, indexStorage, throwable.getMessage());
			throw throwable;
		} finally {
			after(treeMethodCall, dataStorage, indexStorage, result);
		}
		return result;
	}


	private JoinPointHelper getJoinPointHelper(ProceedingJoinPoint joinPoint) {
		var className = joinPoint.getSignature().getDeclaringTypeName();
		var methodName = joinPoint.getSignature().getName();
		var args = joinPoint.getArgs();
		return new JoinPointHelper(className, methodName, args);
	}

	private TreeMethodCall before(List<TreeMethodCall> dataStorage,
								  AtomicInteger indexStorage,
								  JoinPointHelper joinPointHelper) {
		TreeMethodCall treeMethodCall;
		var index = indexStorage.get();
		if (CollectionUtils.empty(dataStorage)) {
			treeMethodCall = treeMethodCallService.beginRootCall(joinPointHelper, index);
		} else {
			treeMethodCall = treeMethodCallService.beginCall(joinPointHelper, index);
		}
		dataStorage.add(treeMethodCall);
		indexStorage.incrementAndGet();
		return treeMethodCall;
	}

	private void after(TreeMethodCall treeMethodCall,
					   List<TreeMethodCall> dataStorage,
					   AtomicInteger indexStorage,
					   Object result) {
		var ind = indexStorage.decrementAndGet();
		treeMethodCallService.endCall(treeMethodCall, Objects.toString(result));
		if (ind == 0) {
			logger.info(treeMethodCallService.createTree(dataStorage));
			dataStorage.clear();
		}
	}
}
