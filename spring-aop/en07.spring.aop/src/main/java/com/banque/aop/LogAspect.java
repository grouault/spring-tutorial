package com.banque.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	  private final static Logger LOG = LogManager.getLogger();

	  /*
	  @Before("execution(* com.banque.service.impl.*Service.*(..))")
	  public void logBefore(JoinPoint jp) {
		  LOG.info("Passage avant target = {}, signature = {}", jp.getTarget().getClass().getName(), jp.getSignature().getName());
	  }
	  
	  @Before("execution(* com.banque.service.impl.*Service.*(..)) && args(unChiffre)")
	  public void logBefore(JoinPoint jp, int unChiffre) {
		  LOG.info("Passage avant target = {}, unChiffre = {}", jp.getTarget(), unChiffre);
	  }
	  
	  @After("execution(* com.banque.service.impl.*Service.*(..))")
	  public void logAfter(JoinPoint jp) {
		  LOG.info("Passage après target = {}", jp.getTarget());
	  }
	  */
	  
	  @Around("execution(* com.banque.service.impl.*Service.*(..))")
	  public Object logAround(ProceedingJoinPoint jp) throws Throwable {
		  
		  // log before
		  LOG.info("Around - before : target = {}", jp.getTarget());
		  
		  // proceed
		  Object toReturn = jp.proceed();
		  
		  // log after
		  LOG.info("Around - after : target = {}", jp.getTarget());
		  
		  return toReturn;
	  }

	
}
