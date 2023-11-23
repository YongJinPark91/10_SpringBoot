package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 패키지를 만들때 유의사항
 * 		- 스프링은 IoC를 한다. (제어의 역전)
 * 		- new를 해서 메모리에 띄우는 것은 Spring이 하기 때문이다.
 *           -> 싱글톤패턴 사용 및 레퍼런스변수를 Spring이 관리해주겠다는 의미
 *     - 프로젝트를 만들때 com.cos.blog로 만들어 해당 아래에 있는것들만 스캔하기 때문에
 *        com.cos.test라 만들게 되면 Spring이 스캔할 수 없다.
 *   1) 패키지를 생성할 때 기준을 잡아준 패키지 아래에 추가적인 패키지를 만들어 주어야 한다.
 */

@RestController
public class BlogControllerTest {
	/*
	 * 스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 "new" 해주는 것이 아닌
	 * 특정 어노테이션(@)을 붙인 클래스 파일들을 new해서 (IoC) 스프링 컨테이너에 관리해 준다.
	 */
	
	// 확인 주소 : http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>Hello Srping Boot</h1>";
	}
}
