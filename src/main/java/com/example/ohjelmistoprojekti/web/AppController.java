package com.example.ohjelmistoprojekti.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ohjelmistoprojekti.OhjelmistoprojektiTeamDaisJApplication;
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
	
	private static final Logger log = LoggerFactory.getLogger(AppController.class);
	
	@Autowired
	private AnswerRepository aRepo;
	@Autowired
	private SurveyRepository sRepo;
	@Autowired
	private QuestionRepository qRepo;
	@Autowired
	private UserAnswerRepository uaRepo;
	
	
	
	@RequestMapping(value="/apiquestions", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody List<Question> questionListRest() {
		return (List<Question>) qRepo.findAll();
	}
	
	@RequestMapping(value="/apiquestions/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody Optional<Question> findQuestionRest(@PathVariable("id") Long questionID) {
		return qRepo.findById(questionID);
	}
	
	@RequestMapping(value="/apisurveys", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody List<Survey> surveyListRest() {
		return (List<Survey>) sRepo.findAll();
	}
	
	@RequestMapping(value="/apisurveys/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody Optional<Survey> findSurveyRest(@PathVariable("id") Long surveyID) {
		return sRepo.findById(surveyID);
	}
	
	@RequestMapping(value="/apianswers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody List<Answer> answerListRest() {
		return (List<Answer>) aRepo.findAll();
	}
	
	@RequestMapping(value="/apianswers/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody Optional<Answer> findAnswerRest(@PathVariable("id") Long answerID) {
		return aRepo.findById(answerID);
	}
	
	@RequestMapping(value="/apiuseranswers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody List<UserAnswer> uAnswerListRest() {
		return (List<UserAnswer>) uaRepo.findAll();
	}
	
	@RequestMapping(value="/apiuseranswers/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody Optional<UserAnswer> findUserAnswerRest(@PathVariable("id") Long uanswerID) {
		return uaRepo.findById(uanswerID);
	}
	
    @RequestMapping(value="/apiuseranswers", method = RequestMethod.POST)
    public @ResponseBody UserAnswer saveUAnswerRest(@RequestBody UserAnswer uAnswer) {	
    	return uaRepo.save(uAnswer);
    }
    @RequestMapping(value="/apisurveys", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Survey saveSurveyRest(@RequestBody Survey survey) {	
    	return sRepo.save(survey);
    }
    
    @RequestMapping(value="/apiquestions", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Question saveQuestionRest(@RequestBody Question question) {	
    	return qRepo.save(question);
    }
	
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


