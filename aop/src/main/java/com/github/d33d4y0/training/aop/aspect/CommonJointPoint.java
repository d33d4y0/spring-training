package com.github.d33d4y0.training.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJointPoint {

	@Pointcut("@annotation(com.github.d33d4y0.training.aop.annotation.LoggerAround)")
	public void loggerAroundAnnotation() {
	}

	@Pointcut("@annotation(com.github.d33d4y0.training.aop.annotation.LoggerBefore)")
	public void loggerBeforeAnnotation() {
	}

	@Pointcut("@annotation(com.github.d33d4y0.training.aop.annotation.LoggerAfterReturning)")
	public void loggerAfterReturningAnnotation() {
	}

//	"*" means any return type "(..)" means any arguments
	@Pointcut("execution(* com.github.d33d4y0.training.aop.service.DemoAopService.throwException(..))")
	public void exceptionHandlerExecution() {
	}

	@Pointcut("execution(* com.github.d33d4y0.training.aop.service.DemoAopService.logAfter(..))")
	public void afterExecution() {
	}

//	Advice at class layer
//	@Pointcut("execution(* com.github.d33d4y0.training.aop.DemoAopService.*.*(..))")
//	public void repositoryExecution() {
//	}

//	Specific bean names with specific class
//	@Pointcut("target(com.github.d33d4y0.training.aop.service.DemoAopService) && (bean(logTimeRestTemplate) || bean(logTimeSSLRestTemplate))")
//	public void restTemplateCall() {
//	}

}
