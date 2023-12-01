package com.cos.blog.hander;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.vo.ResponseVO;

/*
 * exception이 발생되었을때 해당 클래스로 오게 하기 위해서는
 * ControllerAdvice 어노테이션이 붙어야 한다.
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseVO<String> handleArgumentException(IllegalArgumentException e) {
		return new ResponseVO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
	
	// 다른 exception을 받고 싶다면 Illegal~Exception을 변경해주면 된다.
	// 혹은 모든 Exception을 받고 싶다면 Exception을 받아주면 된다. 왜냐하면 모든 오류의 부모이기 때문이다.
}
