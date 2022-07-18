package com.github.d33d4y0.training.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {

	private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

	@Around("com.github.d33d4y0.training.aop.aspect.CommonJointPoint.loggerAroundAnnotation()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("executing at {} with {}", joinPoint.getSignature().getDeclaringType().getName(),
				OBJ_MAPPER.writeValueAsString(joinPoint.getArgs()));
		Object result = joinPoint.proceed();
		log.info("execute successfully at {} with result: {}", joinPoint.getSignature().getDeclaringType().getName(),
				OBJ_MAPPER.writeValueAsString(result));
		return result;
	}

	@Before("com.github.d33d4y0.training.aop.aspect.CommonJointPoint.loggerBeforeAnnotation()")
	public void logBefore(JoinPoint joinPoint) throws Throwable {
		log.info("executing at {} with {}", joinPoint.getSignature().getDeclaringType().getName(),
				OBJ_MAPPER.writeValueAsString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "com.github.d33d4y0.training.aop.aspect.CommonJointPoint.loggerAfterReturningAnnotation()", returning = "result")
	public void logAfterReturn(JoinPoint joinPoint, Object result) throws Throwable {
		log.info("execute successfully at {} with result: {}", joinPoint.getSignature().getDeclaringType().getName(),
				OBJ_MAPPER.writeValueAsString(result));
	}

	@AfterThrowing(pointcut = "com.github.d33d4y0.training.aop.aspect.CommonJointPoint.exceptionHandlerExecution()", throwing = "e")
	public void exceptionHandler(JoinPoint joinPoint, Exception e) {
		log.error(e.getMessage());
	}
	
	@After("com.github.d33d4y0.training.aop.aspect.CommonJointPoint.afterExecution()")
	public void logAfter(JoinPoint joinPoint) throws Throwable {
		log.info("execute successfully at {} with {}", joinPoint.getSignature().getDeclaringType().getName(),
				OBJ_MAPPER.writeValueAsString(joinPoint.getArgs()));
	}
}