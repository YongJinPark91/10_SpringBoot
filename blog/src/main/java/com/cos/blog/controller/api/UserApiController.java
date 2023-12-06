package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.cos.blog.vo.ResponseVO;
import com.mysql.cj.Session;

@RestController // 데이터만을 응답해주기 위해서 만듬, 앱/웹 모두 사용가능
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired //DI가 되서 주입이 가능해 진다.
	private BCryptPasswordEncoder encode;
	
	@PostMapping("/auth/joinProc")
	public ResponseVO<Integer> save(@RequestBody User user) { // user -> username, password, email 은 받아오지만 Role은 받아오지 않는다.
		System.out.println("save 호출됨");
		// 실제로 DB에 insert를 하고, 아래에서 return이 되면 된다.
		userService.회원가입(user);
		return new ResponseVO<Integer>(HttpStatus.OK.value(), 1);
		// ok.value를 하면 정상적으로 처리될 경우 200의 숫자를 넘겨 준다.
	}
	
	// 로그인 요청을 해당부분에서 작성하지는 않는다. 그 이유는 스프링 시큐리티가 가로채어서 로그인을 하게 만들 것이기 때문에 별도로
	// Mapping을 활용할 필요가 없는 것이다.
	
	/*
	// 스프링 시큐리티를 이용해서 로그인하게 되면 더이상 활용하지 않는다.
	@PostMapping("/api/user/login")
	public ResponseVO<Integer> login(@RequestBody User user, HttpSession session){
		System.out.println("login이 정상적으로 호출됨");
		User principal = userService.로그인(user); // principal (접근주체)
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		
		return new ResponseVO<Integer>(HttpStatus.OK.value(), 1);
	}
	*/
}
