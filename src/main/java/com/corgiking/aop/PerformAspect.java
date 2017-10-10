package com.corgiking.aop;

import javax.annotation.PostConstruct;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class PerformAspect {

	@Pointcut("execution(** com.corgiking.aop.PerformInterfac.perform(..))")
	public void pointPerform(){}
	
	@PostConstruct
	public void Init(){
		System.out.println("init");
	}
	
	@After("pointPerform()")
	public void after(){
		System.out.println(criticismEngine.getCriticism());
	}
	
	@Autowired
	private CriticismEngine criticismEngine;

	public void setCriticismEngine(CriticismEngine criticismEngine) {
		this.criticismEngine = criticismEngine;
	}
	
}
