package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다. 그때 저장되는 것이 UserDetails 타입의 PrincipalDetail이 저장된다.
// principal~이 저장될때 당연히 기본정보인 User의 정보가 들어가 있어야 한다.
@Getter
public class PrincipalDetail implements UserDetails{

	private User user; // 기본정보를 들고 있는 User를 가져온다.
	// 위과 같이 하는 것을 콤포지션이라고 한다. (객체를 품고 있음)
	// extends로 가져오는 것을 상속, 품고있는것을 콤포지션이라고 한다.

	public PrincipalDetail(User user) {
		this.user = user;
		// 이게 없으면, 우리가 입력하는 user의 정보가 아닌 최초 인즈을 할때 쓰는 id : user, pw : console에서 제공해주는 pw 를 이용해서 로그인을 할 수 있다.
		// 그래서 인증 정보를 우리가 입력하는 User 클래스의 정보로 입력을 하게 만들어 주는 부분이다.
		
		// 반드시 User를 컴포지션 하여 PrincipalDetail에 파라미터로 값을 넘겨서 우리가 입력하는 user의 데이터로 넣어주어야 원하는데로 동작하게 된다.
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴을 한다. (true:만료안됨/false:만료됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않았는지 리턴을 한다. (true:잠기지 않음 / false:잠김)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비밀번호가 만료되었는지 리턴을 해준다. (true:만료안됨/false:만료됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정의 활성화(사용가능)인지 리턴을 한다. (true : 활성화 / false : 비활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 계정이 어떠한 권한을 갖고 있는지 확인을 한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList();
		/*
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRole();
				// 스프링에서 role을 받을 때 "ROLE_"을 반드시 붙여주어야 한다.
				// ROLE_USER와 같이 return 되어야 확인이 된다.
				// 만약 USER로 return을 하게되면 인식을 하지 못하게 된다.
			}
		});
		*/
		
		// 위와 같이 작성된 부분을 람다식으로 아래와 같이 작성 할 수 있다.
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}
	
}
