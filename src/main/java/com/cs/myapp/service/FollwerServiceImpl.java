package com.cs.myapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.myapp.controller.FollowerController;
import com.cs.myapp.entity.FollowerDtls;
import com.cs.myapp.exception.AppException;
import com.cs.myapp.repository.FollowerDtlsRepo;

@Service
public class FollwerServiceImpl implements FollowerService {
	private static final Logger log = LoggerFactory.getLogger(FollwerServiceImpl.class);
	
	FollowerDtls fdtls;
	
	@Autowired
	FollowerDtlsRepo repo;
	
	public FollwerServiceImpl() {
		super();
	}

	@Override
	public void save(int flrId, int flwId) throws AppException {
		try {
			fdtls = new FollowerDtls(flrId, flwId, true);
			repo.save(fdtls);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new AppException("follow action get failed - " + e.getMessage(), e.getCause());
		}
		

	}

	@Override
	public void update(int flrId, int fleId) throws AppException {
		try {
			repo.update(flrId, fleId);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new AppException("unfollow action get failed - " + e.getMessage());
		}
		
	}

}
