package com.cs.myapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.myapp.entity.Post;
import com.cs.myapp.exception.AppException;
import com.cs.myapp.repository.FollowerDtlsRepo;
import com.cs.myapp.repository.PostRepo;

@Service
public class PostServiceImpl  implements PostService {	
	private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

	Post post;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	FollowerDtlsRepo flrRepo;
	
	@Override
	public void savePost(int userId, int postId, String content) throws AppException {	
		try {
			if (checkPostId(postId)) {
				throw new AppException("PostId already present");
			}
			post = new Post(postId, content, LocalDateTime.now(), userId);
			postRepo.save(post);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new AppException("post not created - " + e.getMessage(), e.getCause());
		}
		
	}

	private boolean checkPostId(int postId) {
		Optional<Post> post = Optional.ofNullable(postRepo.getPostByPostId(postId));
		return post.isPresent();		
	}

	@Override
	public List<Integer> getPostsByUserId(int userId) throws AppException {
		try {
			Optional<Integer> opt = Optional.of(userId);
			List<Integer> ids = new ArrayList<>();
			
			if(opt.isPresent()) {
				ids = flrRepo.getFolloweeIds(userId);
			}
			ids.add(userId);	
			return postRepo.getPostIdsbyUserId(ids);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new AppException("Not able to get user post - " + e.getMessage(), e.getCause());
		}
			
	}

}
