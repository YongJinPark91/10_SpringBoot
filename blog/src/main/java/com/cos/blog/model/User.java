package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 테이블화 하기위해서 @Entity를 붙여준다.
 * 		User 클래스가 MySQL에 테이블이 생성된다.
 * 
 * ORM -> Java(다른언어) Object => 테이블로 매핑해주는 기술
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
//@DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id // PK가 된다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// 여기서 넘버링의 전략을 따라간다는 의미는 오라클에서는 시퀀스를, MySQL에서는 Auto_Increment를 사용한다는 의미임.
	private int id; // 오라클 : 시퀀스, MySQL : auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 넉넉하게 잡아주는 이유는 해쉬로 변경해서 암호화 할 경우 길이가 길어질 수 있기 때문이다.
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("user")
	// DB는 RoleType이라는 것이 없기 때문에 어노테이션을 붙여줘야 한다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. (Enum을 쓰면 데이터의 도메인을(어떤 범위) 만들어 줄 수 있다.
									// admin, user, manager와 같이 다른 형태의 사용자에 따라서 권한을 부여해 줄 수 있다.
									// 도메인 : 성별(남, 녀), 초등학생(1~6학년) 이렇게 지정된 것 제외에는 값을 넣을수 없게 되어 정확한 값을 넣을 수 있다.
									// ColumnDefault를 줄때 글자를 넣고 싶으면 ' ' 를 붙여 주어야 한다.
	
	@CreationTimestamp // 현재시간이 자동으로 등록된다.
	private Timestamp createDate;
	
}
