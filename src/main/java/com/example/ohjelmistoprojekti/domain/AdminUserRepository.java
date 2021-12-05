package com.example.ohjelmistoprojekti.domain;

import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {

	AdminUser findByAdminUsername(String adminUsername);

}
