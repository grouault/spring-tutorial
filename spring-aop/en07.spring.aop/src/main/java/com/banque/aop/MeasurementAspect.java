package com.banque.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasurementAspect {

	private static final Logger LOG = LogManager.getLogger();
	
	@Around("execution(* com.banque.service.impl.*Service.*(..))")
	public Object measureExecutionTime(ProceedingJoinPoint jp) throws Throwable {
		
		// before
		StopWatch watch = new StopWatch(jp.toShortString());
		watch.start("invoke");
		
		Object toReturn = jp.proceed();
		
		// after
		watch.stop();
		// LOG.info("test");
		LOG.info("Time Elapsed: target = {}, time={}", jp.getTarget(),  watch.getTotalTimeMillis());
		
		return toReturn;
		
	}
	
}
