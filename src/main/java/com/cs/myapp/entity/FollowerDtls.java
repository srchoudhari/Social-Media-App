package com.cs.myapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
public class FollowerDtls implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int followeeId;
	
	@Id
	private int followerId;
	
	@Column
	private boolean status;
	
	public FollowerDtls() {
		super();
	}

	public FollowerDtls(int followerId, int followeeId, boolean status) {
		super();
		this.followerId = followerId;
		this.followeeId = followeeId;
		this.status = status;
	}

	public int getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(int followeeId) {
		this.followeeId = followeeId;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
