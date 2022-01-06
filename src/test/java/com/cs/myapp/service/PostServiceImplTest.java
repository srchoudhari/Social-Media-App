package com.cs.myapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cs.myapp.entity.Post;
import com.cs.myapp.exception.AppException;
import com.cs.myapp.repository.PostRepo;

@SpringBootTest
public class PostServiceImplTest {
	
	@Autowired
	PostRepo repo;
	
	@Autowired
	EntityManager em;
	
	@Autowired
	PostServiceImpl service;
	
	@Test
	public void testSavePost() {
		try {
		  service.savePost(10, 1, "Test");
		  Post post = em.find(Post.class, 1);
		  assertEquals(10, post.getUser());
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPostsByUserId() {
		try {
			repo.save(new Post(2, "test1", LocalDateTime.now(), 10));
			repo.save(new Post(3, "test2", LocalDateTime.now(), 10));
			repo.save(new Post(4, "test3", LocalDateTime.now(), 10));
			List<Integer> list = service.getPostsByUserId(10);
			assertEquals(4, list.get(0).intValue());
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
	
	

}
