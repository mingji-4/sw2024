package com.example.demo;

import java.util.Date;

import com.example.demo.entity.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAClient {
	
	public static void main(String[] args) {
		insert();
		
		// insert() 실해 후 persistence.xml에 update로 수정 후 실행 
		select();
	}

	public static void insert() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BoardWeb");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 테스트 내용");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			em.persist(board);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void select() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BoardWeb");
		EntityManager em = emf.createEntityManager();
		try {
			Board board = em.find(Board.class, 1L);
			System.out.println(board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}