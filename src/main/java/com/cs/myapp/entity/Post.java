package com.cs.myapp.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
public class Post {
	
	@Id
	private int postId;
	
	@Column
	private String content;
	
	@Column(name="timestamp",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime timestamp;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId")*/
	@Column
	private int user;

	public Post() {
		super();
	}

	public Post(int postId, String content, LocalDateTime timestamp, int user) {
		super();
		this.postId = postId;
		this.content = content;
		this.timestamp = timestamp;
		this.user = user;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimestamp() {
		return LocalDateTime.now();
	}

	public void setTimestamp() {
		this.timestamp = getTimestamp();
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
	
}
