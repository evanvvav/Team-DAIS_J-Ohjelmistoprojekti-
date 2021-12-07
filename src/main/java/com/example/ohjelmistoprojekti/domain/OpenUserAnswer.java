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
	@JsonIgnoreProperties({"respondent", "openUserAnswers"})
	@JoinColumn(name = "respondentID")
	private Respondent respondent;

	public OpenUserAnswer() {
		super();
		this.question = null;
		this.respondent = null;
		this.answerText = null;
	}

	public OpenUserAnswer(String answerText, Respondent respondent, Question question) {
		super();
		this.question = question;
		this.respondent = respondent;
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

	public Respondent getRespondent() {
		return respondent;
	}

	public void getRespondent(Respondent respondent) {
		this.respondent = respondent;
	}

}
