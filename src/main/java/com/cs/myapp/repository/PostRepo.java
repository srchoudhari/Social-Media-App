package com.cs.myapp.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cs.myapp.entity.Post;

@Repository
@Transactional
public class PostRepo {
	
	@Autowired
	EntityManager em;
	
	public void save(Post post) {
		em.persist(post);
	}
	
	public List<Integer> getPostIdsbyUserId(List<Integer> ids){
		Query query =  em.createQuery("select p.postId from Post p where p.user IN ?1 order by timestamp desc");
		query.setParameter(1, ids);
		query.setMaxResults(20);
		return query.getResultList();	
	}
	
	public Post getPostByPostId(int id) {
		return em.find(Post.class, id);
	}

}
