package org.comstudy.myapp.controller;

import java.util.List;

import org.comstudy.myapp.entity.Board;
import org.comstudy.myapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/Board")
	public List<Board> boardList() {
		return boardService.selectAll();
	}
	
	@PostMapping("/Board")
	public String inserBoards(@RequestBody(required = true) Board board) {
		boardService.insert(board);
		return "입력 완료 >>> " + board;
	}
}