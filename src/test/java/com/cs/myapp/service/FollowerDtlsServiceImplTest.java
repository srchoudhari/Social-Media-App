package com.cs.myapp.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cs.myapp.entity.FollowerDtls;
import com.cs.myapp.exception.AppException;
import com.cs.myapp.repository.FollowerDtlsRepo;

@SpringBootTest
public class FollowerDtlsServiceImplTest {
	
	@Autowired
	EntityManager em;
	
	@Autowired
	FollowerDtlsRepo repo;
	
	@Autowired
	FollwerServiceImpl service;
	
	@Test
	public void testSaveForFollowUser() {
		try {
			int flrId = 20;
			int flwId  = 10;
			service.save(flrId, flwId);
			FollowerDtls result = em.find(FollowerDtls.class, new FollowerDtls(flrId, flwId, true));
			assertTrue(result.isStatus());
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateForUnfollowUser() {
		int flrId = 20;
		int flwId  = 10;
		try {
			service.update(flrId, flwId);
			FollowerDtls result = em.find(FollowerDtls.class, new FollowerDtls(flrId, flwId, true));
			assertFalse(result.isStatus());
		} catch (AppException e) {
			e.printStackTrace();
		}
		
	}

}
