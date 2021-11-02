package com.example.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionID;
	
	@ManyToOne
	@JsonIgnoreProperties("questions")
	@JoinColumn(name="surveyID")
	private Survey survey;
	private String questionType;
	private String question;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="question")
	@JsonIgnoreProperties("question")
	private List<Answer> answers;
	
	public Question() {
		super();
		this.survey=null;
		this.question=null;
		this.questionType=null;
	}
	
	public Question(String question, String questionType, Survey survey) {
		super();
		this.question=question;
		this.questionType=questionType;
		this.survey=survey;
	}

	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
	

}