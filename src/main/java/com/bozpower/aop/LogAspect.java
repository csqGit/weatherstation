package com.bozpower.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	@Pointcut("execution(* com.bozpower.service.impl.*.*(..))")
	public void pc() {}
	
	@Pointcut("execution(* com.bozpower.service.impl.*.select*(..))")
	public void pcSelect() {}
	
	@Pointcut("execution(* com.bozpower.service.impl.*.delete*(..))")
	public void pcDelete() {}
	
	@Pointcut("execution(* com.bozpower.service.impl.*.delete*(..))")
	public void pcUpdate() {}
	
	@Before(value = "pc()")
	public void myBefore(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.err.println(name + "() start");
	}
	
	@After(value = "pc()")
	public void myAfter(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.err.println(name + "() end");
	}
	
	
	@AfterThrowing(value = "pcSelect()", throwing = "e")
	public void myAfterThrowingBySelect(JoinPoint joinPoint, Exception e) {
		String name = joinPoint.getSignature().getName();
		System.err.println(name + "()查询执行异常，异常信息为：" + e.getMessage());
	}
	
	
	@AfterThrowing(value = "pcDelete()", throwing = "e")
	public void myAfterThrowingByDelete(JoinPoint joinPoint, Exception e) {
		String name = joinPoint.getSignature().getName();
		System.err.println(name + "() 删除执行异常， 异常信息为：" + e.getMessage());
	}
	
	
	@AfterThrowing(value = "pcUpdate()", throwing = "e")
	public void myAfterThrowingByUpdate(JoinPoint joinPoint, Exception e) {
		String name = joinPoint.getSignature().getName();
		System.err.println(name + "() 更新执行异常， 异常信息为：" + e.getMessage());
	}

}
