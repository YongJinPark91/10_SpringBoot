package com.cos.blog.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepositroy;

@Service // bean  등록
public class PrincipalDetailService implements UserDetailsService {

	private UserRepositroy userRepositroy;
	/*
	 * 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	 * password 부분 처리는 알아서 해준다.
	 * username이 DB에 있는지만 확인을 해주면 된다.
	 * 위에서 말하는 확인을 아래의 함수에서 실행을 해준다.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepositroy.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다. :" + username);
				});
		return new PrincipalDetail(principal); // 이때 시큐리티 세션에 유저 정보가 저장이 된다. 이때 타입이 UserDetails 타입이 되어야 한다.
	}

}
