package org.comstudy.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.comstudy.myapp.model.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private static List<Memo> memoList = new ArrayList<>();
	static {
		for(int i=0; i<20; i++) {
			memoList.add(new Memo(101+i, "메모 연습"+i, "메모 연습 내용"+i));
		}
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		
		model.addAttribute("memoList", memoList);
		return "home";
	}
	
	@GetMapping("/test")
	public String test(Model model) {
		
		// Memo memo1 = new Memo(101, "메모 연습", "메모 연습 내용");
		Memo memo1 = Memo.builder()
				.id(0)
				.title("test memo")
				.content("test memo content")
				.build();
		System.out.println(memo1);
		
		// 바인딩 	
		model.addAttribute("memo", memo1);
		
		return "home";
	}
}
