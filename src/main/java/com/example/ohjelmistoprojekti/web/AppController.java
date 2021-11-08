package com.example.ohjelmistoprojekti.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ohjelmistoprojekti.domain.Answer;
import com.example.ohjelmistoprojekti.domain.AnswerRepository;
import com.example.ohjelmistoprojekti.domain.Question;
import com.example.ohjelmistoprojekti.domain.QuestionRepository;
import com.example.ohjelmistoprojekti.domain.Survey;
import com.example.ohjelmistoprojekti.domain.SurveyRepository;
import com.example.ohjelmistoprojekti.domain.UserAnswer;
import com.example.ohjelmistoprojekti.domain.UserAnswerRepository;

@Controller
public class AppController {
	
	@Autowired
	private AnswerRepository aRepo;
	@Autowired
	private SurveyRepository sRepo;
	@Autowired
	private QuestionRepository qRepo;
	@Autowired
	private UserAnswerRepository uaRepo;
	
	//get all surveys and list them as links
	@GetMapping(value={"", "/", "surveys"})
	public String getAllSurveys(Model model) {
		model.addAttribute("surveys", sRepo.findAll());
		return "surveys";
	}
	
	//retrieving survey's main page. It could contain button "start" 
	@GetMapping(value="survey/{id}")
	public String getSurvey(@PathVariable("id") Long surveyID, Model model) {
		model.addAttribute("survey", sRepo.findById(surveyID).get());
		Question firstQuestion=sRepo.findById(surveyID).get().getQuestions().get(0);
		model.addAttribute("firstQuestion", firstQuestion);
		return "survey";
	}
	
	//retrieving question page: question and suggested answers
	@GetMapping (value="question/{id}") 
	public String getQuestion(@PathVariable("id") Long questionID, Model model) {
		Question question = qRepo.findById(questionID).get();
		model.addAttribute("question", question);
		model.addAttribute("answers", question.getAnswers());
		model.addAttribute("uAnswer", new UserAnswer());
		return "question";
	}
	
	@PostMapping (value="/saveresponse")
	public String saveResp(UserAnswer uAnswer) {
		uaRepo.save(uAnswer);
		Answer answer = uAnswer.getAnswer();
		Question question = answer.getQuestion();
		List<Question> qList = question.getSurvey().getQuestions();

		int i = 0;
		for (Question q : qList) {
			if (q.getQuestion() == question.getQuestion()) {
				i = qList.indexOf(q) + 1;				
			}
		}
		
		return "question" + Long.toString(qList.get(i).getQuestionID());
		
		
	}
	
	
}


