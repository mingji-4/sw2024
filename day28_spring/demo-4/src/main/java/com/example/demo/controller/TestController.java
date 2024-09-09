package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TestDTO;
import com.example.demo.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test")
public class TestController {
	@GetMapping
	public String testController() {
		return "GET - /test";
	}
	
	@PostMapping
	public String postController() {
		return "POST - /test";
	}
	
//	@GetMapping("/{id}")
//	public String pathVariabls(@PathVariable(required = true) int id) {
//		return "Hello World! Path Variables ID : " + id;
//	}

	@GetMapping("/requestParam")
	public String requestParm(@RequestParam(required = true) int id) {
		return "Hello World! Request Param ID : " + id;
	}

	@GetMapping("/requestBody")
	public String requestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}

	@GetMapping("/selectAll")
    public List<TestDTO> selectAll() {
        TestDTO obj1 = new TestDTO(1, "Message 1");
        TestDTO obj2 = new TestDTO(2, "Message 2");
        TestDTO obj3 = new TestDTO(3, "Message 3");

        List<TestDTO> list = Arrays.asList(obj1, obj2, obj3);

        return list;
    }


}