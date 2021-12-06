package com.example.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OpenUserAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long openUserAnswerID;

	private String answerText;

	@ManyToOne
	@JsonIgnoreProperties("openUserAnswers")
	@JoinColumn(name = "questionID")
	private Question question;

	// link answers to open questions to users
	@ManyToOne
	@JsonIgnoreProperties("openUserAnswers")
	@JoinColumn(name = "userID")
	private User user;

	public OpenUserAnswer() {
		super();
		this.question = null;
		this.user = null;
		this.answerText = null;
	}

	public OpenUserAnswer(String answerText, User user, Question question) {
		super();
		this.question = question;
		this.user = user;
		this.answerText = answerText;
	}

	public Long getOpenUserAnswerID() {
		return openUserAnswerID;
	}

	public void setOpenUserAnswerID(Long openUserAnswerID) {
		this.openUserAnswerID = openUserAnswerID;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
