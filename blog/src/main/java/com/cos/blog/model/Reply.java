package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// [연관관계 생성]
	@ManyToOne // 여러개의 답변은 한개의 게시글에 존재할 수 있다.
	//@OneToOne 하나의 답변은 한아ㅢ 게시글만에 존재할 수 있다. (X)
	//@OneToMany 하나의 답변은 여러개의 게시글에 존재할 수 있다. (X)
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 여러개의 답변은 한명의 유저가 쓸수 있다.
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
