package com.example.demo.service;

public class GreetingServiceEng implements GreetingService {
	
	public GreetingServiceEng() {
		System.out.println(">>> GreetingServiceEng constructor");
	}

	@Override
	public void sayHello() {
		System.out.println(">>> Hello world");
	}

}