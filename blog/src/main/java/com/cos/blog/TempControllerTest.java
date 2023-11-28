package com.cos.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		// 파일리턴 기본경로 : src/main/resources/static
		// 위 경로에 있는 파일을 찾아야 한다.
		//return "home.html"; 이렇게 작성해주면 찾을 수가 없다.
		return "/home.html";
		// 이렇게 해주면 src/main/resource/static/home.html 로 경로가 설정되어진다.
		
		/*
		 * RestController는 return에 있는 문자 혹은 데이터에 대해서 그대로 리턴을 해주지만
		 * Controller는 경로를 찾아서 리턴을 해주게 된다.
		 * 
		 * jsp를 리턴해야하지만 spring boot에서는 지원하지 않아서 jsp 템플릿 엔진을 활용해야 인식을 할 수 있다.
		 */
	}
	
	/*
	 * 기본경로가 static이기 때문에 jsp파일을 제대로 읽을 수 없다.
	 * 			- 정적파일들을 넣어놓는다. (즉, 브라우져가 읽을 수 있는(인식가능한파일) 파일 예) IMG 파일)
	 */
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.jpg";
	}
	
	
	/*
	 * jsp는 읽어낼 수 없다.
	 * 		왜냐면 컴파일을 통한 동적인 파일이기 때문이다.
	 */
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		/*
		 * yml을 통해 경로 변경 후
		 * 		prefix : /WEB-INF/views/
		 * 		seffix : .jsp
		 * 		리턴값 앞뒤로 자동으로 붙게 된다. 우리가 레거시를 할때와 동일하게 셋팅하는 모습이다.
		 * 		풀네인 : /WEB-INF/views/~~~
		 * 
		 * Java 파일이기 때문에 아파치가 아닌 톰켓이 파일을 컴파일 해서 html 파일로 만들어서 아파치에게
		 * 넘겨주어 파일이 동작되는 원리이다.
		 */
		return "test";
	}
}
