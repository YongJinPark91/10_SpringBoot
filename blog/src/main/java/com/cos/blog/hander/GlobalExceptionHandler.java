package com.cos.blog.hander;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/*
 * exception이 발생되었을때 해당 클래스로 오게 하기 위해서는
 * ControllerAdvice 어노테이션이 붙어야 한다.
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
	// 다른 exception을 받고 싶다면 Illegal~Exception을 변경해주면 된다.
	// 혹은 모든 Exception을 받고 싶다면 Exception을 받아주면 된다. 왜냐하면 모든 오류의 부모이기 때문이다.
}
