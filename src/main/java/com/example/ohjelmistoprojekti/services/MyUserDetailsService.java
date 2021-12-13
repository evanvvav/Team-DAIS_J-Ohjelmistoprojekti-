package com.example.ohjelmistoprojekti.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ohjelmistoprojekti.domain.UserRepository;
import com.example.ohjelmistoprojekti.domain.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private final UserRepository repository;

	@Autowired
	public MyUserDetailsService(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), new ArrayList<>());
        return user;
	}

	
	
//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//		return new User("admin", "admin", new ArrayList<>());
//	}
	

}
