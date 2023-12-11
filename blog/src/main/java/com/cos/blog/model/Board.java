package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 가장 아래에 있는것이 가장 좋다.
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment, 올라클(시퀀스)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터인 경우 사용한다.
	private String content; // 내용은 많이 길어질 수 있다. 섬머노트 라이브러리를 활용할 것인데 이때 글에 디자인 된다, 그리고 HTML 태그도 섞여서 디자인 되서 용량이 커진다.
	
	private int count; // 조회수

	// [FK가 만들어지는 과정]
	@ManyToOne(fetch = FetchType.EAGER) // Many : Board, User : One => 한명의 유저는 여러개의 게시물을 쓸 수 있다.
	@JoinColumn(name="userId") // User 테이블에서 PK를 받아서 FK를 만들게 되고, 컬럼명은 userId로 만들게 된다.
	private User user;
	// FK로 찾아서 넣어주는것 이 아니라
	// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	// 자바프로그램에서 DB의 자료형에 맞춰서 테이블을 만들어야 한다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)//한개의 게시물은 여러개의 댓글을 가질수 있다.
	// 여기서 mappedBy가 작성되어 있으면, 연관관계의 주인이 아니라는 의미와 FK가 아니라는 의미를 동시에 내포한다.
	// 또한 DB에 컬럼을 만들지 않으라는 의미이다.
	// OTM은 기본적으로 fetch가 LAZY이고,
	// MTO는 기본적으로 fetch가 EAGER 이다.
	
	//@JoinColumn 올수 없다. 만약 오게 된다면, DB의 제1정규화 및 원자성에 위배된다. 즉 OneToMany 한개의 게시물에는 여러개의 댓글이 들어가려면
	// 	여러개의 댓글 번호들이 들어가야 하는데 그것이 안된다는 뜻과 동일 한다.
	private List<Reply> reply;
	
	@CreationTimestamp // insert, update 될때 자동으로 들어가 준다.
	private Timestamp createDate;
	
}
