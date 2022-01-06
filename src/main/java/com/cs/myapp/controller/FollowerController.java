package com.cs.myapp.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.myapp.exception.AppException;
import com.cs.myapp.service.FollowerService;
import com.cs.myapp.service.FollwerServiceImpl;


@RestController
@Validated
public class FollowerController {
	private static final Logger log = LoggerFactory.getLogger(FollowerController.class);
	
	FollowerService service;
	
	public FollowerController() {
		super();
	}

	@Autowired
	public FollowerController(FollwerServiceImpl serviceImpl) {
		this.service = serviceImpl;
	}

	@PostMapping("/follow")
	public ResponseEntity<?> follow(@RequestParam(name = "flrid") @NotNull int followerId,
										@RequestParam(name = "fleid") @NotNull int followeeId) throws AppException {
		try {
			this.service.save(followerId,followeeId);
			
		} catch (AppException e) {
			throw new AppException("Error while completing this action :" + e.getMessage(), e.getCause());
		}
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@PutMapping("/unfollow")
	public ResponseEntity<?> unfollow(@RequestParam(name = "flrid") int followerId,
			@RequestParam(name = "fleid") int followeeId) throws AppException {
		try {
			this.service.update(followerId, followeeId);
			
		} catch (AppException e) {
			log.error(e.getMessage());
			throw new AppException("Error while completing this action :" + e.getMessage(), e.getCause());
		}
			return ResponseEntity.ok(HttpStatus.CREATED);
	}

}
