package com.corgiking.service;

import org.springframework.stereotype.Service;

import com.corgiking.aop.PerformInterfac;

@Service
public class PerformService implements PerformInterfac{

	@Override
	public String perform() {
		return "Corgi King!";
	}

}
