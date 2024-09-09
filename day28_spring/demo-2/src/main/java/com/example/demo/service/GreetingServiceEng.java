package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceEng implements GreetingService {
	
	public GreetingServiceEng() {
		System.out.println(">>> GreetingServiceEng constructor");
	}

	@Override
	public void sayHello() {
		System.out.println(">>> Hello world");
	}

	@Override
	public Object getGreeting() {
		return ">>> Hello world";
	}

}