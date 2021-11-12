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
public class Survey {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long surveyID;
	
	private String surveyDesc;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="survey")
	@JsonIgnoreProperties("survey")
	private List<Question> questions;
	
	public Survey() {
		super();
		this.surveyDesc=null;
	}
	
	public Survey(String surveyDesc) {
		super();
		this.surveyDesc=surveyDesc;
	}

	public Long getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(Long surveyID) {
		this.surveyID = surveyID;
	}

	public String getSurveyDesc() {
		return surveyDesc;
	}

	public void setSurveyDesc(String surveyDesc) {
		this.surveyDesc = surveyDesc;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
