package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size=3, sort="id", direction = Direction.DESC) Pageable pageable) { // 컨트롤러에서 세션을 어떻게 찾는지?
		// /WEB-INF/views/index.jsp
		
		/*
		 * 메인페이지에서 데이터를 보기 위해서는 Model이 필요하다.
		 *    "/"를 요청하면 boards의 이름으로 글목록을 모두 가져온다. 
		 *    가져오는 방법은 boardrespoitory의 findall이 모두 갖고 있기 때문에 모든 데이터를 가져올 수 있다.
		 */
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index"; // viewResolver 작동된다.
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
