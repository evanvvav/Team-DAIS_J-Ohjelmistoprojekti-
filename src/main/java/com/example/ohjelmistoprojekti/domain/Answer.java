package com.example.ohjelmistoprojekti.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long answerID;
	private String answer;
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@ManyToOne
	@JsonIgnoreProperties("answers")
	@JoinColumn(name="questionID")
	private Question question;
	
	//for radiobutton questions: 1 possible answer
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userAnswerID")
	private UserAnswer userAnswer;
	
	public Answer() {
		super();
		this.question=null;
		this.userAnswer=null;
		this.answer=null;
	}
	
	public Answer(String answer, Question question) {
		super();
		this.question=question;
		this.answer=answer;
	}

	public Long getAnswerID() {
		return answerID;
	}

	public void setAnswerID(Long answerID) {
		this.answerID = answerID;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public UserAnswer getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(UserAnswer userAnswer) {
		this.userAnswer = userAnswer;
	}	

}
