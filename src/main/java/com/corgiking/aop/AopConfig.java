package com.corgiking.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy//开启AspectJ自动代理
public class AopConfig {
	
	@Bean
	public PerformAspect performAspect(){
		return new PerformAspect();
	}
	
	@Bean
	public CriticismEngine criticismEngineImpl(){
		CriticismEngineImpl impl = new CriticismEngineImpl();
		String[] criticismPool = {"Java Config"};
		impl.setCriticismPool(criticismPool);
		return impl;
	}
}
