package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAdvice {
	private Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	public void beforeMethod(JoinPoint joinPoint) {
		logger.debug("loggingAdvice before"+"@@@@@@@@@@@@@@@@@@@@@@@@");

	}
	public void afterMethod(JoinPoint joinPoint) {
		
		String className=joinPoint.getTarget().getClass().getName();
		String methodName=joinPoint.getSignature().getName();
		logger.debug("loggingAdvice afterMethod  {},{}",className,methodName);

	}
	
	public Object aroundMethod(ProceedingJoinPoint joinPoin) throws Throwable {
		//핵심로직 호출전
		long startTime = System.currentTimeMillis();
		//핵심로직 호출
		Object[]args=joinPoin.getArgs();
		Object returnObj=joinPoin.proceed(args);
		//핵심로직 호출후
		long endTime = System.currentTimeMillis();
		logger.debug("profilinfTime : {}",startTime-endTime );
		return returnObj;
	}
}
