package com.example.ohjelmistoprojekti.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ohjelmistoprojekti.domain.Answer;
import com.example.ohjelmistoprojekti.domain.AnswerRepository;
import com.example.ohjelmistoprojekti.domain.OpenUserAnswer;
import com.example.ohjelmistoprojekti.domain.OpenUserAnswerRepository;
import com.example.ohjelmistoprojekti.domain.Question;
import com.example.ohjelmistoprojekti.domain.QuestionRepository;
import com.example.ohjelmistoprojekti.domain.Survey;
import com.example.ohjelmistoprojekti.domain.SurveyRepository;
import com.example.ohjelmistoprojekti.domain.Respondent;
import com.example.ohjelmistoprojekti.domain.UserAnswer;
import com.example.ohjelmistoprojekti.domain.UserAnswerRepository;
import com.example.ohjelmistoprojekti.domain.RespondentRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AppController {

	@Autowired
	private AnswerRepository aRepo;
	@Autowired
	private SurveyRepository sRepo;
	@Autowired
	private QuestionRepository qRepo;
	@Autowired
	private UserAnswerRepository uaRepo;
	@Autowired
	private OpenUserAnswerRepository oUaRepo;
	@Autowired
	private RespondentRepository respRepo;


//Methods for SURVEYS

	@RequestMapping(value = "/apisurveys", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Survey> surveyListRest() {
		return (List<Survey>) sRepo.findAll();
	}

	@RequestMapping(value = "/apisurveys/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Survey> findSurveyRest(@PathVariable("id") Long surveyID) {
		return sRepo.findById(surveyID);
	}

	@RequestMapping(value = "/apisurveys", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public Survey saveSurveyRest(@RequestBody Survey survey) {
		return sRepo.save(survey);
	}

	// if no survey with such id found, the new empty survey with given id is
	// created
	@RequestMapping(value = "/apisurveys/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	public Survey updateSurveyRest(@RequestBody Survey newSurvey, @PathVariable Long id) {
		return sRepo.findById(id).map(survey -> {
			survey.setSurveyDesc(newSurvey.getSurveyDesc());
			return sRepo.save(survey);
		}).orElseGet(() -> {
			newSurvey.setSurveyID(id);
			return sRepo.save(newSurvey);
		});
	}

	@RequestMapping(value = "/apisurveys/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteSurveyRest(@PathVariable("id") Long surveyID) {
		sRepo.deleteById(surveyID);
		return "Survey " + surveyID + " deleted";
	}

//Methods for QUESTIONS

	@RequestMapping(value = "/apiquestions", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Question> questionListRest() {
		return (List<Question>) qRepo.findAll();
	}

	@RequestMapping(value = "/apiquestions/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Question> findQuestionRest(@PathVariable("id") Long questionID) {
		return qRepo.findById(questionID);
	}

	@RequestMapping(value = "/apiquestions", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public Question saveQuestionRest(@RequestBody Question question) {
		return qRepo.save(question);
	}

	@RequestMapping(value = "/saveallquestions", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Question> saveAllQuestionsRest(@RequestBody List<Question> questions) {
		List<Question> questionResponse = (List<Question>) qRepo.saveAll(questions);
		return questionResponse;
	}

	// if no question with such id found, the new question with given id is created
	@RequestMapping(value = "/apiquestions/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	public Question updateQustionRest(@RequestBody Question newQuestion, @PathVariable Long id) {
		return qRepo.findById(id).map(question -> {
			question.setQuestion(newQuestion.getQuestion());
			question.setQuestionType(newQuestion.getQuestionType());
			question.setSurvey(newQuestion.getSurvey());
			return qRepo.save(question);
		}).orElseGet(() -> {
			newQuestion.setQuestionID(id);
			return qRepo.save(newQuestion);
		});
	}

	@RequestMapping(value = "/apiquestions/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteQuestionRest(@PathVariable Long id) {
		qRepo.deleteById(id);
		return "Question " + id + " deleted";
	}

//Methods for ANSWERS
	// list all answers
	@RequestMapping(value = "/apianswers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Answer> answerListRest() {
		return (List<Answer>) aRepo.findAll();
	}

	// find answer by id
	@RequestMapping(value = "/apianswers/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Answer> findAnswerRest(@PathVariable("id") Long answerID) {
		return aRepo.findById(answerID);
	}

	// save one answer
	@RequestMapping(value = "/apianswers", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public Answer saveAnswerRest(@RequestBody Answer answer) {
		return aRepo.save(answer);
	}

	// save all answers into a list
	@RequestMapping(value = "/saveallanswers", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Answer> saveAllAnswersRest(@RequestBody List<Answer> answers) {
		List<Answer> answerResponse = (List<Answer>) aRepo.saveAll(answers);
		return answerResponse;
	}

	// if no answer with such id found, the new answer with given id is created
	@RequestMapping(value = "/apianswers/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	public Answer updateANswerRest(@RequestBody Answer newAnswer, @PathVariable Long id) {
		return aRepo.findById(id).map(answer -> {
			answer.setAnswer(newAnswer.getAnswer());
			answer.setQuestion(newAnswer.getQuestion());
			return aRepo.save(answer);
		}).orElseGet(() -> {
			newAnswer.setAnswerID(id);
			return aRepo.save(newAnswer);
		});
	}

	// delete answer by id
	@RequestMapping(value = "/apianswers/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteAnswerRest(@PathVariable Long id) {
		aRepo.deleteById(id);
		return "Answer " + id + " deleted";
	}

//Methods for USERANSWERS
	@RequestMapping(value = "/apiuseranswers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<UserAnswer> uAnswerListRest() {
		return (List<UserAnswer>) uaRepo.findAll();
	}

	@RequestMapping(value = "/apiuseranswers/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<UserAnswer> findUserAnswerRest(@PathVariable("id") Long uanswerID) {
		return uaRepo.findById(uanswerID);
	}

	@RequestMapping(value = "/apiuseranswers", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public UserAnswer saveUAnswerRest(@RequestBody UserAnswer uAnswer) {
		return uaRepo.save(uAnswer);
	}

	@RequestMapping(value = "/apiuseranswers/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteUAnswerRest(@PathVariable Long id) {
		uaRepo.deleteById(id);
		return "UserAnswer " + id + " deleted";
	}

	@RequestMapping(value = "/savealluseranswers", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<UserAnswer> saveAllUserAnswersRest(@RequestBody List<UserAnswer> userAnswers) {
		List<UserAnswer> uanswerResponse = (List<UserAnswer>) uaRepo.saveAll(userAnswers);
		return uanswerResponse;
	}

	// Methods for RESPONDENTS
	@RequestMapping(value = "/apirespondents", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Respondent> respListRest() {
		return (List<Respondent>) respRepo.findAll();
	}

	@RequestMapping(value = "/apirespondents/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<Respondent> findRespRest(@PathVariable("id") Long id) {
		return respRepo.findById(id);
	}

	@RequestMapping(value = "/apirespondents", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public Respondent saveRespRest(@RequestBody Respondent respondent) {
		return respRepo.save(respondent);
	}

	@RequestMapping(value = "/apirespondents/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteRespRest(@PathVariable Long id) {
		respRepo.deleteById(id);
		return "Respondent " + id + " deleted";
	}

	@RequestMapping(value = "/apirespondents/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	public Respondent updateRespRest(@RequestBody Respondent newResp, @PathVariable Long id) {
		return respRepo.findById(id).map(respondent -> {
			respondent.setRespondentName(newResp.getRespondentName());
			return respRepo.save(respondent);
		}).orElseGet(() -> {
			newResp.setUserID(id);
			return respRepo.save(newResp);
		});
	}

	// Methods for ADMINUSERS
	// so far only these
	// not sure what we needed
	// for any method that is ONLY FOR ADMINUSERS
	// the endpoint needs to start with /apiadmin/x
	// can replace x with whatever and only admin users can access it
	// if try to access it via url, redirected to login page
	// after successful login, can access whatever you please until logout

//	@PreAuthorize("hasAuthority('ADMIN')")
//	@RequestMapping(value = "/apiadmin/adusers", method = RequestMethod.GET)
//	@CrossOrigin(origins = "http://localhost:3000")
//	public List<AdminUser> adminUsersListRest() {
//		return (List<AdminUser>) adminRepo.findAll();
//	}
//
//	@PreAuthorize("hasAuthority('ADMIN')")
//	@RequestMapping(value = "/apiadmin/aduser/{id}", method = RequestMethod.GET)
//	@CrossOrigin(origins = "http://localhost:3000")
//	public Optional<AdminUser> findAdminUserRest(@PathVariable("id") Long id) {
//		return adminRepo.findById(id);
//	}

	// methods to authenticate an adminuser

//	@RequestMapping(value = "/login")
//	public String login() {
//		return "login";
//	}

	// THE ENDPOINT FOR LOGGING OUT IS JUST /LOGOUT

	// Methods for OPEN USER ANSWERS

	// list all openuseranswers
	@RequestMapping(value = "/apiouanswers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<OpenUserAnswer> ouaListRest() {
		return (List<OpenUserAnswer>) oUaRepo.findAll();
	}

	// find openuseranswer by id
	@RequestMapping(value = "/apiouanswers/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public Optional<OpenUserAnswer> findOUARest(@PathVariable("id") Long id) {
		return oUaRepo.findById(id);
	}

	// save open user answer
	@RequestMapping(value = "/apiouanswers", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public OpenUserAnswer saveOUARest(@RequestBody OpenUserAnswer ouanswer) {
		return oUaRepo.save(ouanswer);
	}

	// save LIST of OUAnswers
	@RequestMapping(value = "/saveallouanswers", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<OpenUserAnswer> saveAllOUAnswersRest(@RequestBody List<OpenUserAnswer> ouAnswers) {
		List<OpenUserAnswer> ouanswerResponse = (List<OpenUserAnswer>) oUaRepo.saveAll(ouAnswers);
		return ouanswerResponse;
	}

	// delete openuseranswer
	@RequestMapping(value = "/apiouanswers/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteOUARest(@PathVariable Long id) {
		oUaRepo.deleteById(id);
		return "OpenUserAnswer " + id + " deleted";
	}
	
	

}
