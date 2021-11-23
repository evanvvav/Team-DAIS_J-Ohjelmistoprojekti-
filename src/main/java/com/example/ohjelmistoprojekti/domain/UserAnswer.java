package com.example.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class UserAnswer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userAnswerID;
	
	@ManyToOne
	@JsonIgnoreProperties("userAnswers")
	@JoinColumn(name="answerID")
	private Answer answer;	
	
	@ManyToOne
	@JsonIgnoreProperties("userAnswers")
	@JoinColumn(name="userID")
	private User user;	
	
	public UserAnswer(Answer answer) {
		super();
		this.answer=answer;
	}

	public UserAnswer() {
		super();
		this.answer=null;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	public Long getUserAnswerID() {
		return userAnswerID;
	}

	public void setUserAnswerID(Long userAnswerID) {
		this.userAnswerID = userAnswerID;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

}
