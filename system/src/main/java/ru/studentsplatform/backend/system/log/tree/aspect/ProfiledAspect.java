package ru.studentsplatform.backend.system.log.tree.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.studentsplatform.backend.system.log.tree.service.TreeLoggerService;

/**
 * Аспект который позволяет отслеживать вызовы методов классов помеченных аннотацией Profiled
 * и показывать время выполнения. В данный момент не может отследить вызов методов внутри одного класса и покажет только
 * первый вызов.
 */
@Aspect
@Component
public class ProfiledAspect {
	private final TreeLoggerService treeLoggerService;

	@Autowired
	public ProfiledAspect(TreeLoggerService treeLoggerService) {
		this.treeLoggerService = treeLoggerService;
	}

//	@Pointcut("@within(ru.studentsplatform.backend.system.log.tree.annotation.Profiled)" +
//			" || @annotation(ru.studentsplatform.backend.system.log.tree.annotation.Profiled)")
//	void profiled() {
//	}

//	@AfterThrowing(pointcut = "profiled()", throwing = "e")
//	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//		log.error("Exception in {}.{}()",
//				joinPoint.getSignature().getDeclaringTypeName(),
//				joinPoint.getSignature().getName());
//	}

	/**
	 * Аспект который позволяет замерить время выполнения и логгирует его с параметрами.
	 *
	 * @param joinPoint Точка входа
	 * @return Результат выполнения метода
	 * @throws Throwable возможное исключение
	 */
	@Around("@within(ru.studentsplatform.backend.system.log.tree.annotation.Profiled)" +
			" || @annotation(ru.studentsplatform.backend.system.log.tree.annotation.Profiled)")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		return treeLoggerService.profile(joinPoint);
	}
}