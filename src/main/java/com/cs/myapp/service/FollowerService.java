package com.cs.myapp.service;

import org.springframework.stereotype.Service;
import com.cs.myapp.entity.FollowerDtls;
import com.cs.myapp.exception.AppException;

@Service
public interface FollowerService {
	
	public void save(int followerId, int followeeId) throws AppException;
	
	public void update(int flrId, int fleId) throws AppException;

}
