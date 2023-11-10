package com.ssafy.maytrip.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.ssafy.maytrip.controller.*.*(..)) || execution(* com.ssafy.maytrip.service.*.*(..))")
	public void allMethod() {}
	
	@Before("allMethod()")
	public void beforeLogging(JoinPoint jp) {
		Signature s = jp.getSignature();
		logger.debug("호출 : {}, 파라미터:{}", 
				s.getDeclaringType().getSimpleName()+"."+
						s.getName(),
				Arrays.toString( jp.getArgs() )
		);
	}
	
	@AfterReturning(pointcut = "allMethod()", returning = "result")
	public void afterReturningLogging(JoinPoint jp, Object result) {
		Signature s = jp.getSignature();
//		logger.debug("리턴 : {}, 반환값: {}", 
//				s.getDeclaringType().getSimpleName()+"."+
//				s.getName(), 
//				result);
		logger.debug("리턴 : {}", 
				s.getDeclaringType().getSimpleName()+"."+
				s.getName() 
				);
		
	}
}
