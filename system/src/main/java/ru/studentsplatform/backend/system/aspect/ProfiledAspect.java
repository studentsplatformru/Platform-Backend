package ru.studentsplatform.backend.system.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Аспект который позволяет отслеживать вызовы методов классов помеченных аннотацией Profiled
 * и показывать время выполнения. В данный момент не может отследить вызов методов внутри одного класса и покажет только
 * первый вызов.
 */
@Aspect
@Component
public class ProfiledAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String tabs = " ".repeat(5);

	@Pointcut("@within(ru.studentsplatform.backend.system.annotation.Profiled)" +
			" || @annotation(ru.studentsplatform.backend.system.annotation.Profiled)")
	public void profiled() {
	}

	@AfterThrowing(pointcut = "profiled()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}()",
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName());
	}

	/**
	 * Аспект который позволяет замерить время выполнения и логгирует его с параметрами.
	 *
	 * @param joinPoint Точка входа
	 * @return Результат выполнения метода
	 * @throws Throwable возможное исключение
	 */
	@Around("@within(ru.studentsplatform.backend.system.annotation.Profiled)" +
			" || @annotation(ru.studentsplatform.backend.system.annotation.Profiled)")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		}
		try {
			long start = System.currentTimeMillis();
			Object result = joinPoint.proceed();
			if (log.isDebugEnabled()) {
				log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);
			} else {
				String time = String.valueOf(System.currentTimeMillis() - start);
				log.info("{}.{}() \n{}| argument: {}\n{}| result: {}\n{}| time [{} msec]",
						joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(),
						tabs,
						Arrays.toString(joinPoint.getArgs()),
						tabs,
						result,
						tabs,
						time);
			}
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
	}
}