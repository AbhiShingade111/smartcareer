package com.smartcareer.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartcareer.entities.User;
import com.smartcareer.repo.UserJpaRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserJpaRepository userJpa;
	
	@Override
	public User getUser(String email) {
		
		User theUser = userJpa.getUserByUsername(email);
		if(theUser==null) {
			throw new RuntimeException("User not found");
		}

		return theUser;
	}

}
