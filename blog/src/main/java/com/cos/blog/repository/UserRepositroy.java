package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

public interface UserRepositroy extends JpaRepository<User, Integer> {
	// Jpa 라이브러리 내에 find All이 있어서 모든 데이터를 받을 수 있다.
	// 또한 insert, update도 할 수 있다.
	// JSP로 비교하자면 DAO라 볼 수 있다.
	// 또한 자동으로 Bean으로 등록 할 수 있다.
	// @Repository를 생략할 수 있다.

	//로그인을 위한 함수 생성
	//JPA Naming 쿼리
	// SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?
	User findByUserNameAndPassword(String userName, String password);
	
	// 위 아래의 쿼리 동작은 동일하게 작동을 한다.
	/*
	@Query(value="SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?", nativeQuery = true)
	User login(String userName, String password);
	*/
}
