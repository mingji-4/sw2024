package com.example.demo.controller;

import com.example.demo.service.GreetingService;

public class HomeController {
	// DI는 생성자를 이용한 DI, setter를 이용한 DI로 2가지 방식이 있다.
	private GreetingService service;
	
	// 생성자를 이용한 DI
	public HomeController(GreetingService service) {
		this.service = service;
		System.out.println("HomeController 생성자");
		
		execute();
	}
	
	// setter를 이용한 DI
	public void setService(GreetingService service) {
		this.service = service;
	}

	public void execute() {
		service.sayHello();
	}
}