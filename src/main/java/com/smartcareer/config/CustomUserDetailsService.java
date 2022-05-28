package com.smartcareer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smartcareer.entities.User;
import com.smartcareer.repo.UserJpaRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserJpaRepository userJpaRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User theUser = userJpaRepo.getUserByUsername(username);
		if(theUser==null) {
			throw new UsernameNotFoundException("User not found");
		}
		CustomUserDetails cust = new CustomUserDetails(theUser);
		return cust;
	}

}
