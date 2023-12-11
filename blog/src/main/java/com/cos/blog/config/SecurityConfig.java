package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

// Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 이렇게 하면 bean 등록 어노테이션 
@EnableWebSecurity // 필터링을 걸어주는 어노테이션 => 스프링 시큐리티가 활성화 되어 있는데 어떤 설정을 해당 파일(SecurityConfig)에서 하겠다는 의미
									// 시큐리티 필터가 등록이 된다. 그 설정은 아래의 클래스에 한다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하먄 권한 및 인증을 미리 체크 하겠다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;
	// 만든 service를 가져와 autowired를 하여 사용할 수 있게 만들어 준다.
	
	@Bean // IoC가 되어서 Spring이 관리하게 된다. 이렇게 하면 필요할 때 마다 사용하면 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해 주는데 password를 가로채기 하여 실행해 주는데
	// 해당 password가 무엇으로 해쉬가 되어 회원가입이 되었는지 알아야 하고,
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬와 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		// 위의 코드에서 principalDetailService을 통해 로그인할 때 password를 encode해서 비밀번호를 비교해 준다.
	}
	// 실제 로그인을 실행하는 service를 userDetailsService()에 넣어 주어야 한다.
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/image/**", "/css/**", "/dummy/**") // /auth로 들어오는 못든것을 (**을 붙여주면 auth/~~~ 뭐가 붙던지 모든것을 포함한다는 의미임)
				.permitAll() // 모두 받아준다는 의미임
				.anyRequest() // 이게아닌 다른 모든 요청은
				.authenticated() // 인증이 되어야 한다. 라는 의미
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				// 인증이 필요한 곳이 생기면 /auth/loginForm으로 보내어서 인증을 하게되는 과정을 보여주는 것이다.
				.loginProcessingUrl("/auth/loginProc") // 이렇게 작성해 두면 해당 주소로 요청되는 로그인을 가로채간다.
																			      // 가로채서 대신 로그인을 해준다.
				.defaultSuccessUrl("/"); // 로그인을 성공하게 되면 보낼 URL을 ContextPath로 지정해서 메인페이지로 이동시켜 준다.
	}
}
