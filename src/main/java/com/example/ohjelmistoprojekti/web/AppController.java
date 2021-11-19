package com.example.ohjelmistoprojekti.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ohjelmistoprojekti.domain.Answer;
import com.example.ohjelmistoprojekti.domain.AnswerRepository;
import com.example.ohjelmistoprojekti.domain.Question;
import com.example.ohjelmistoprojekti.domain.QuestionRepository;
import com.example.ohjelmistoprojekti.domain.Survey;
import com.example.ohjelmistoprojekti.domain.SurveyRepository;
import com.example.ohjelmistoprojekti.domain.UserAnswer;
import com.example.ohjelmistoprojekti.domain.UserAnswerRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
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
	

//Methods for SURVEYS
	
	@RequestMapping(value="/apisurveys", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Survey> surveyListRest() {
		return (List<Survey>) sRepo.findAll();
	}
		
	@RequestMapping(value="/apisurveys/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Survey> findSurveyRest(@PathVariable("id") Long surveyID) {
		return sRepo.findById(surveyID);
	}
	
    @RequestMapping(value="/apisurveys", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Survey saveSurveyRest(@RequestBody Survey survey) {	
    	return sRepo.save(survey);
    }
    
    //if no survey with such id found, the new empty survey with given id is created
    @RequestMapping(value="/apisurveys/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:3000")
    public Survey updateSurveyRest(@RequestBody Survey newSurvey, @PathVariable Long id) {	
    	return sRepo.findById(id)
    			.map(survey-> {
    				survey.setSurveyDesc(newSurvey.getSurveyDesc());
    				return sRepo.save(survey);
    			})
    			.orElseGet(()->{
    				newSurvey.setSurveyID(id);
    				return sRepo.save(newSurvey);
    			});
    			
    }
    
    @RequestMapping(value="/apisurveys/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteSurveyRest(@PathVariable("id") Long surveyID) {
		sRepo.deleteById(surveyID);
		return "Survey " + surveyID + " deleted";
	}

	
//Methods for QUESTIONS
	
	@RequestMapping(value="/apiquestions", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Question> questionListRest() {
		return (List<Question>) qRepo.findAll();
	}
	
	@RequestMapping(value="/apiquestions/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Question> findQuestionRest(@PathVariable("id") Long questionID) {
		return qRepo.findById(questionID);
	}
	
    @RequestMapping(value="/apiquestions", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Question saveQuestionRest(@RequestBody Question question) {	
    	return qRepo.save(question);
    }
    
    @RequestMapping(value="/saveallquestions", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Question> saveAllQuestionsRest(@RequestBody List<Question> questions) {	
    	List<Question> questionResponse=(List<Question>) qRepo.saveAll(questions);
    	return questionResponse;
    }
    
    //if no question with such id found, the new question with given id is created
    @RequestMapping(value="/apiquestions/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:3000")
    public Question updateQustionRest(@RequestBody Question newQuestion, @PathVariable Long id) {	
    	return qRepo.findById(id)
    			.map(question-> {
    				question.setQuestion(newQuestion.getQuestion());
    				question.setQuestionType(newQuestion.getQuestionType());
    				question.setSurvey(newQuestion.getSurvey());
    				return qRepo.save(question);
    			})
    			.orElseGet(()->{
    				newQuestion.setQuestionID(id);
    				return qRepo.save(newQuestion);
    			});
    }
        

    @RequestMapping(value="/apiquestions/{id}", method = RequestMethod.DELETE)
    public String deleteQuestionRest(@PathVariable Long id) {	
    	qRepo.deleteById(id);
    	return "Question " + id + " deleted";
    }   
    
//Methods for ANSWERS
	
	@RequestMapping(value="/apianswers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Answer> answerListRest() {
		return (List<Answer>) aRepo.findAll();
	}
	
	@RequestMapping(value="/apianswers/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Answer> findAnswerRest(@PathVariable("id") Long answerID) {
		return aRepo.findById(answerID);
	}
	
    @RequestMapping(value="/apianswers", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Answer saveAnswerRest(@RequestBody Answer answer) {	
    	return aRepo.save(answer);
    }
    
    @RequestMapping(value="/saveallanswers", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Answer> saveAllAnswersRest(@RequestBody List<Answer> answers) {	
    	List<Answer> answerResponse=(List<Answer>) aRepo.saveAll(answers);
    	return answerResponse;
    }
    
    //if no answer with such id found, the new answer with given id is created
    @RequestMapping(value="/apianswers/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:3000")
    public Answer updateANswerRest(@RequestBody Answer newAnswer, @PathVariable Long id) {	
    	return aRepo.findById(id)
    			.map(answer-> {
    				answer.setAnswer(newAnswer.getAnswer());
    				answer.setQuestion(newAnswer.getQuestion());
    				return aRepo.save(answer);
    			})
    			.orElseGet(()->{
    				newAnswer.setAnswerID(id);
    				return aRepo.save(newAnswer);
    			});
    }
    
    @RequestMapping(value="/apianswers/{id}", method = RequestMethod.DELETE)
    public String deleteAnswerRest(@PathVariable Long id) {	
    	aRepo.deleteById(id);
    	return "Answer " + id + " deleted";
    }
    
	
//Methods for USERANSWERS
	@RequestMapping(value="/apiuseranswers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<UserAnswer> uAnswerListRest() {
		return (List<UserAnswer>) uaRepo.findAll();
	}
	
	@RequestMapping(value="/apiuseranswers/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<UserAnswer> findUserAnswerRest(@PathVariable("id") Long uanswerID) {
		return uaRepo.findById(uanswerID);
	}
	
    @RequestMapping(value="/apiuseranswers", method = RequestMethod.POST)
    public UserAnswer saveUAnswerRest(@RequestBody UserAnswer uAnswer) {	
    	return uaRepo.save(uAnswer);
    }
    
    @RequestMapping(value="/apiuseranswers/", method = RequestMethod.PUT)
    public UserAnswer updateUAnswerRest(@RequestBody UserAnswer uAnswer) {	
    	return uaRepo.save(uAnswer);
    }
    
    @RequestMapping(value="/apiuseranswers/{id}", method = RequestMethod.DELETE)
    public String deleteUAnswerRest(@PathVariable Long id) {	
    	uaRepo.deleteById(id);
    	return "UserAnswer " + id + " deleted";
    }   
	
}


