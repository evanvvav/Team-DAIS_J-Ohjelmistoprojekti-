package com.example.ohjelmistoprojekti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adminId", nullable = false, updatable = false)
	private Long adminId;
// Username with unique constraint
	@Column(name = "adminUsername", nullable = false, unique = true)
	private String adminUsername;
	@Column(name = "password", nullable = false)
	private String passwordHash;
	@Column(name = "role", nullable = false)
	private String role;

	public AdminUser() {
		super();
	}

	public AdminUser(String adminUsername, String passwordHash, String role) {
		super();
		this.adminUsername = adminUsername;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
