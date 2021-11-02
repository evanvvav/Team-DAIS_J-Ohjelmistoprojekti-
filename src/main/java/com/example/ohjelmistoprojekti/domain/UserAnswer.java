package com.example.ohjelmistoprojekti.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UserAnswer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userAnswerID;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="answerID")
	private Answer answer;
	
	private UserAnswer() {
		super();
		this.answer=null;
	}
	
	private UserAnswer(Answer answer) {
		super();
		this.answer=answer;
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
