package com.xdomain.user.aop;

import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	@Pointcut("within(com.xdomain.user..*)")
	public void applicationClassPath() {}
	
	
	@Around("applicationClassPath()")
	public Object executionLogging(ProceedingJoinPoint pjp) throws Throwable {
	   
		Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
		
		log.debug(">>> Executing: {}",pjp.getSignature().toShortString());
		Stream.of(pjp.getArgs()).forEachOrdered(x -> log.trace("- {}",x.toString()));;
		Object ret;
		try {
			ret= pjp.proceed();
		}catch (Throwable t) {
			log.warn("<<< Exception: {} ", t.getClass());
			log.trace("- {} ", t);
			throw t;
		}
		 
		
		log.debug("<<< Returning: {}", pjp.getSignature().toShortString());
		log.trace("- {}", ret);
		
		return ret;
	}
	
}
