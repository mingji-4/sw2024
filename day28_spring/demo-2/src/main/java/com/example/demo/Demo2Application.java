package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.demo.controller.HomeController;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Demo2Application.class, args);
		
		// 빈을 DL(룩업)을 통해서 사용 가능.
		HomeController homeController = context.getBean(HomeController.class);
		homeController.execute();
	}
}