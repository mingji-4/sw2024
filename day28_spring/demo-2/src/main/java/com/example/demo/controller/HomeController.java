package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.GreetingService;

@Controller
public class HomeController {
	// DI는 생성자를 이용한 DI, setter를 이용한 DI로 2가지 방식이 있음
	@Autowired
	@Qualifier("greetingServiceKor")
	private GreetingService service;

	// 생성자를 이용한 DI
	/*
	public HomeController(GreetingService service) {
		this.service = service;
		System.out.print("HomeController 생성자");

		execute();
	}

	// sertter를 이용한 DI
	public void setService(GreetingService service) {
		this.service = service;
	}
	*/

	public void execute() {
		service.sayHello();
	}
	
	@GetMapping("/home")
	public void home(Model model) {
		model.addAttribute("greet", service.getGreeting());
	}
}