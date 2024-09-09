package org.comstudy.myapp.service;

import java.util.List;

import org.comstudy.myapp.entity.Board;
import org.comstudy.myapp.entity.BoardRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	public void insert(Board board) {
		boardRepository.save(board);
	}
	
	public Board selectOne(Long id) {
		return boardRepository.findById(id).get();
	}
	
	public List<Board> selectAll() {
		return boardRepository.findAll();
	}
}