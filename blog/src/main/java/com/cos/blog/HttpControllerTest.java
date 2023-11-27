package com.cos.blog;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


// 사용자가 요청 -> 응답(Data) => @RestController
// 사용자가 요청 -> 응답(HTML 파일) => @Controller
@RestController
public class HttpControllerTest {
	
	// 인터넷 브라우저 요청은 무조건 get 요청만 할 수 있다.
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest() {
		return "get 요청";
	}

	// get요청을 제외한 나머지 요청은 Post맨을 활용한 요청 테스트를 해볼 수 있다.
	// PostMan에서도 요청방법에 따른 요청카테고리를 변경하면서 사용할 수 있다.
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
	
}