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
public class Respondent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long respondentID;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "respondent")
	@JsonIgnoreProperties({"respondent", "userAnswers"})
	private List<UserAnswer> userAnswers;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "respondent")
	@JsonIgnoreProperties({"respondent", "openUserAnswers"})
	private List<OpenUserAnswer> openUserAnswers;

	private String respondentName;

	public Respondent() {
		super();
	}

	public Respondent(String name) {
		super();
		this.respondentName = name;
	}

	public String getRespondentName() {
		return respondentName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}

	public List<OpenUserAnswer> getOpenUserAnswers() {
		return openUserAnswers;
	}

	public void setOpenUserAnswers(List<OpenUserAnswer> openUserAnswers) {
		this.openUserAnswers = openUserAnswers;
	}

	public Long getRespondentID() {
		return respondentID;
	}

	public void setUserID(Long respondentID) {
		this.respondentID = respondentID;
	}

	public List<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

}
