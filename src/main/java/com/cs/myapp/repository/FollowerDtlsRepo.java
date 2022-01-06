package com.cs.myapp.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cs.myapp.entity.FollowerDtls;

@Repository
@Transactional
public class FollowerDtlsRepo {
	
	@Autowired
	EntityManager em;
	
	public void save(FollowerDtls fdtls) {
		em.persist(fdtls);		
	}
	
	public void update(int followerId,int followeeId) throws Exception {
		FollowerDtls dtls = getFollowerStatus(followerId, followeeId);
		if(dtls.isStatus()) {
			Query query = em.createQuery("update FollowerDtls set status = false where followerId = ?1 AND followeeId = ?2");
			query.setParameter(1, followerId);
			query.setParameter(2, followeeId);
			query.executeUpdate();
		}
	}
	
	public FollowerDtls getFollowerStatus(int followerId, int followeeId) throws Exception {
		try {
			TypedQuery<FollowerDtls> query = em.createQuery("select f from FollowerDtls f where f.followerId = ?1 AND f.followeeId = ?2",FollowerDtls.class);
			query.setParameter(1, followerId);
			query.setParameter(2, followeeId);
			Optional<Query> result = Optional.ofNullable(query);
			if (result.isPresent())
				return query.getSingleResult();	
			throw new Exception("No entity found for query");
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
	public List<Integer> getFolloweeIds(int followerId) {
		try {
			Query query = em.createQuery("select f.followerId from FollowerDtls f where f.followeeId = ?1 AND f.status = true");
			query.setParameter(1, followerId);
			return query.getResultList();
		} catch (Exception e) {
			throw e;
		}
		
	}

}
