package org.comstudy.myapp.entity;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속을 받으므로 @Repository 어노테이션 생략 가능
public interface BoardRepository extends JpaRepository<Board, Long>{
	// select, insert, update, delete 관련 메서드가 자동 생성됨
	// CRUD관련 메서드는 자동 생성
	// 저장: save()
	// 검색: findBy필드명()
	// 필요하다면 직접 메서드를 구현해 줄 수 있음
	// 인터페이스의 메서드 형식을 갖추고 JPQL 사용
}