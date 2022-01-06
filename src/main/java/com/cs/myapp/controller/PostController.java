package com.cs.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.myapp.exception.AppException;
import com.cs.myapp.service.PostService;
import com.cs.myapp.service.PostServiceImpl;

@RestController
@Validated
public class PostController {
	private static final Logger log = LoggerFactory.getLogger(PostController.class);
	PostService postService;
	
	public PostController() {
		super();
	}

	@Autowired
	public PostController(PostServiceImpl postServiceImpl) {
		super();
		this.postService = postServiceImpl;
	}

	@PostMapping("/posts")
	public ResponseEntity<Object> createPost(@RequestParam(name = "userId") @NotNull int userId,
										@RequestParam(name = "postId") @NotNull int postId,
										@RequestParam(name = "content") @Size(max = 100) String content) throws AppException{
		try {
			postService.savePost(userId,postId,content);
			
		} catch (AppException e) {
			log.error(e.getMessage());
			throw new AppException("Post not created : " + e.getMessage(), e.getCause());
		}
		
		return ResponseEntity.ok(HttpStatus.CREATED);		
	}
	
	@GetMapping("/{userId}/posts")
	public ResponseEntity<List<Integer>> getNewsFeed(@PathVariable int userId) throws AppException{
		List<Integer> postIds = new ArrayList<>();
		try {
			postIds = postService.getPostsByUserId(userId);		
		} catch (AppException e) {
			log.error(e.getMessage());
			 throw new AppException("No posts found for user: "+ e.getMessage(), e.getCause());
		}
		return ResponseEntity.ok(postIds);
	}

}
