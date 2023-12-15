package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepositroy;
import com.cos.blog.repository.UserRepositroy;

@Service
public class BoardService {

	@Autowired
	private BoardRepositroy boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable); 
		/*
		 * 페이징 처리를 하기 위해서는 Controller에서 Pageable을 인자로 받아오게 되고,
		 * 그 값을 Service에서도 마찬가지로 인자로 받아와 findAll로 pageable을 넘기면 페이징처리가 된다.
		 * 그렇지만 최초 List<Board>형태에서 => Page<Board> 형태로 변환되기 때문에 자료형을 변환해주어야 한다.
		 */
	}

}
