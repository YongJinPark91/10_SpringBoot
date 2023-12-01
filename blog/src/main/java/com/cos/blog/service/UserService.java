package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepositroy;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. 
// Bean에 등록을 하는것은 IoC를 해준다는 의미임.
@Service
public class UserService {

	@Autowired
	private UserRepositroy userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	/*
	 * Service가 필요한 이유
	 * 		1. 트렌젝션을 관리하기 위함
	 * 			- 
	 * 		2. 서비스의 의미
	 * 			예) 송금 서비스 :  홍길동 -------(송금)-------> 임꺽정
	 * 									50000             30000               20000
	 *             가. 홍길동 금액 Update() - commit
	 *             나. 임꺽정 금액 Update() - commit
	 *              
	 *          예) 입금 서비스 : 홍길동 20000 ---(10000입금)---> 홍길동 30000 
	 *          
	 *          만약 송금을 할때 홍길동은 정상적으로 송금이 3만원 되었지만, 임꺽정이 받지 못할 경우
	 *          두번의 커밋이 일어나기 때문에 홍길동은 다시 Update를 해야만 하는 상황이거나,
	 *          commit으로 인해 돌려받지 못하는 상황이 생길 수 있다.
	 *          
	 *          트렌젝션은 한번의 커밋이 일어나는 것을 의미하는데 송금 서비스와 같이 2번의 커밋이 일어나는
	 *          경우 2개를 하나의 트렌젝션으로 묶어서 정상적으로 이루어 졌을때만 commit이 일어 날 수 있게 
	 *          하는 것을 서비스라고 한다.
	 */
	
	@Transactional(readOnly = true) // select 할때 트랜젝션 시작, 서비스 종료시에 트랜젝션 종료 (정합성을 유지시킬 수 있음)
	public User 로그인(User user) {
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}
}
