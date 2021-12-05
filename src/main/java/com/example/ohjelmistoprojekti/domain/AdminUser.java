package com.example.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userID;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnoreProperties("user")
	private List<UserAnswer> userAnswers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnoreProperties("user")
	private List<OpenUserAnswer> openUserAnswers;
	
	private String userName;
	
	public User() {
		super();
	}
	
	public User(String name) {
		super();
		this.userName = name;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<OpenUserAnswer> getOpenUserAnswers() {
		return openUserAnswers;
	}

	public void setOpenUserAnswers(List<OpenUserAnswer> openUserAnswers) {
		this.openUserAnswers = openUserAnswers;
	}



	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public List<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}
	
	
	

}
