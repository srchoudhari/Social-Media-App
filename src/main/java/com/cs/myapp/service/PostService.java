package com.cs.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cs.myapp.entity.Post;
import com.cs.myapp.exception.AppException;

@Service
public interface PostService {
	
	public void savePost(int userId, int postId, String content) throws AppException;
	
	public List<Integer> getPostsByUserId(int userId) throws AppException;

}
