package com.example.ohjelmistoprojekti.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ohjelmistoprojekti.domain.AdminUser;
import com.example.ohjelmistoprojekti.domain.AdminUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final AdminUserRepository repository;

	@Autowired
	public UserDetailServiceImpl(AdminUserRepository adminUserRepository) {
		this.repository = adminUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String adminUsername) throws UsernameNotFoundException {
		AdminUser curruser = repository.findByAdminUsername(adminUsername);
		UserDetails user = new org.springframework.security.core.userdetails.User(adminUsername,
				curruser.getPasswordHash(), AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
