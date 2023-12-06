package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepositroy;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController // 응답만 해줄 수 있게 RestController로 어노테이션
// html 파일이 아니라 data를 리턴해주는 Controller => @RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepositroy userRepositroy;
	/*
	 * Autowired를 붙여주면 DummyControllerTest가 메모리에 뜰때
	 * Autowired를 어노테이션으로 갖고있는 userRepositroy도 같이 메모리에 뜨게 된다.
	 * 
	 * 왜냐하면 UserRepositroy타입으로 Spring이 관리하고 있는 객체가 있다면 userRepositroy에 넣어주기 때문이다.
	 */
	
	@DeleteMapping("/dummy/delete/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepositroy.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 존재하지 않습니다.";
		}
		return "삭제가 정상적으로 실행되었습니다. id : " + id;
	}
	
	/*
	 * save 함수는 id를 전달하지 않으면 insert를 해주고
	 * save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	 * save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
	 */
	@Transactional // 함수종료시 자동 commit을 하게 된다.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepositroy.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		requestUser.setId(id);
		requestUser.setUsername("ssar");
		//userRepositroy.save(requestUser);
		// 이렇게만 하게 되면 DB에 넣지 않은 값인 createDate, role 과 같은 값은 null로 값이 들어가게 된다.
		// save는 id값이 있으면, 그것을 업데이트 해준다. 그러나 없는 값에 대해서는 null로 입력을 하게 된다.
		
		// @Transactional 이라는 어노테이션을 사용하면
		// save를 활용하지 않아도 업데이트가 된다. 이것을 더티 체킹이라고 한다.
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepositroy.findAll();
		// findAll을 하게되면 전체가 리턴 됨을 알 수 있다.
	}
	
	// 한페이지당 2건의 데이터를 리턴받겠다.
	// http://localhost:8000/blog/dummy/user/page => 마지막 페이지를 보여줌
	// http://localhost:8000/blog/dummy/user/page?page=0 => 첫 페이지를 보여줌 / 0자리에 원하는 페이지 숫자를 넣으면 원하는 위치로 이동함
	// 시작은 0부터 한다. 
	@GetMapping("/dummy/user/page")
	public Page<User> pageList(@PageableDefault(size=2, sort="id") Pageable pageable) {
		Page<User> users = userRepositroy.findAll(pageable);
		return users;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3 		=>		이렇게 작성하면 id자리에 3이 들어간다.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 DB에서 못찾아 올경우 user가 null이 되는 것 아닌가?
		// 그럼 return null이 되면 프로그램 상에 문제가 발생하는 것은 아닐까?
		// Optional로 너의 User 객체를 감싸서 가져올테니 null인지 판단해서 return 을 하면 되지.
		
		//User user = userRepositroy.findById(id).orElseGet(new Supplier<User>() {
			/*
			 * 이 코드는 id가 있을 경우 정상적으로 작동을 해서 DB를 가져오지만,
			 * 없을 경우 new Supplier 구문이 실행되어서 아래의 메서드가 실행된다.
			 * 그렇게 되면 return 값으로 기본생성자 User를 반환 하기 때문에
			 * null 값이 아닌 기본생성자가 user에 들어가게 되고,
			 * detail 메서드에서는 return 값으로 기본 생성자가 반환되게 된다.
			 */
		/*
			@Override
			public User get() {
				// TODO Auto-generated method stub
				return new User();
			}
		});
		*/
		
		User user = userRepositroy.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹 브라우져
		// user 객체 = 자바 오브젝트
		// 변환( 웹브라우져가 이해할 수 있는 데이터 ) => JSON
		// 스프링부트 = MessageConverter 라는 것이 응답시에 자동 작동
		// 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오프젝트를 JSON으로 변환해서 브라우져에게 전달해준다.
		// 그래서 데이터를 요청하면 JSON 형태의 데이터로 보이는 것을 알 수 있다. (Headers에 Content-Type을 확이해도 application/json임을 알 수 있다.)
		return user;
	}

	@PostMapping("/dummy/join")
	//public String join(String username, String password, String email) { // Body의 키값과 동일하게 보내면 파라미터로 받아 진다.
		// 변수명을 key 값으로 적기만하면 value의 값을 파라미터로 받을 수 있다는 뜻이다.
		// 테스트를 할때 사용자로부터 3가지(아이디, 비밀번호, 이메일)을 받아야 한다.
		//System.out.println("username : " + username);
		//System.out.println("password : " + password);
		//System.out.println("email : " + email);
		//return "회원가입이 완료 되었습니다.";
	public String join(User user) { // 오브젝트로 받아서 처리
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword()); // 만약 password2로 body에 key값을 주면 null이 온다.
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepositroy.save(user);
		/*
		 * 이렇게 해주면 실제로 DB에 들어가는 모습을 볼 수 있다.
		 * 		그러나 role은 데이터가 들어가지 않았다.
		 * 
		 * 하지만 id 부분과, createDate 부분을 비워놔도 자동으로 들어갈 것이다.
		 *     insert 
			    into
			        User
			        (createDate, email, password, role, username) 
			    values
			        (?, ?, ?, ?, ?)
			    이렇게 쿼리가 실행되는데, Default 값이 들어갈려면 role을 빼고 insert 해야한다.
			    
			    이렇게 빼고 싶으면 @DynamicInsert를 활용하면 null인 필드는 제외하고 insert 하게 된다.
			    
		 */

		return "회원가입이 완료 되었습니다.";
	}
}
