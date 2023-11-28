package com.cos.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Getter
//@Setter
@Data
//getter, setter를 한번에 작성할 경우
@NoArgsConstructor
public class Member {
	private int id;
	private String userName;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	

}
