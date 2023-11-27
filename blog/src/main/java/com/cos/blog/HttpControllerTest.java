package com.cos.blog;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// 사용자가 요청 -> 응답(Data) => @RestController
// 사용자가 요청 -> 응답(HTML 파일) => @Controller
@RestController
public class HttpControllerTest {
	
	// 인터넷 브라우저 요청은 무조건 get 요청만 할 수 있다.
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	//public String getTest(@RequestParam int id, @RequestParam String userName) {
	// 위와 같이 하나씩 받을수도 있지만, 한개의 VO에 값이 같이 들어가 있다면, 쿼리스트링으로 전달된 값은 객체로 받을 수도 있다.
	public String getTest(Member m) {
		return "get 요청 : " +m.getId()+ "," +m.getUserName()+","+m.getPassword()+","+m.getEmail();
	}

	// get요청을 제외한 나머지 요청은 Post맨을 활용한 요청 테스트를 해볼 수 있다.
	// PostMan에서도 요청방법에 따른 요청카테고리를 변경하면서 사용할 수 있다.
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	// #1. x.www.from.urlencoded 요청방식
	//public String postTest(Member m) {
		//return "post 요청 : " +m.getId()+ "," +m.getUserName()+","+m.getPassword()+","+m.getEmail();
	// #2. raw 요청방식
	//public String postTest(@RequestBody String text) {
		//return "post 요청  : " + text;
	public String postTest(@RequestBody Member m) { // JSON방식으롤 요청시
		return "post 요청 : " +m.getId()+ "," +m.getUserName()+","+m.getPassword()+","+m.getEmail();
		
		/*
		 * post요청은 다양한 방법으로 보낼 수 있다. 
		 * 		1. Body에 담아서 보내야함
		 * 			방법 1 : x.www.from.urlencoded (과거 우리가 porm태그 내에 값을 넣어 보냈던 방법)
		 * 					-> 해당방법은 쿼리스트링의 형태와 동일하게 바디에 데이터를 넣어서 보내는 방법
		 * 			방법 2 : raw 요청방식 :  
		 * 					-> 해당방법으로 보낼때는 반드시 @RequestBody 어노테이션을 붙여야 한다.
		 * 					-> raw데이터는 text/plain으로 평문을 보냈다는 의미
		 * 					-> application/JSON형태로 받을 경우 @RequestBody에 받을 타입을 작성해주면 JSON으로 데이터를 받을 수 있다.
		 * 						(해당 방식은 MessageConverter라는 Class가 자동으로 매칭해 준다)
		 */
	} 
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " +m.getId()+ "," +m.getUserName()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
	
}
