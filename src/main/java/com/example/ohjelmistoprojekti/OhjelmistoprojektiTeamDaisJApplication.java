package com.example.ohjelmistoprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ohjelmistoprojekti.domain.AnswerRepository;
import com.example.ohjelmistoprojekti.domain.SurveyRepository;
import com.example.ohjelmistoprojekti.domain.QuestionRepository;
import com.example.ohjelmistoprojekti.domain.Survey;
import com.example.ohjelmistoprojekti.domain.Answer;
import com.example.ohjelmistoprojekti.domain.Question;



@SpringBootApplication
public class OhjelmistoprojektiTeamDaisJApplication {
	private static final Logger log = LoggerFactory.getLogger(OhjelmistoprojektiTeamDaisJApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OhjelmistoprojektiTeamDaisJApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner surveyDemo(AnswerRepository aRepo, SurveyRepository sRepo, QuestionRepository qRepo) {
		return (args) -> {
			log.info("saving sample data");
			Survey survey1 = new Survey("First survey");
			sRepo.save(survey1);
			log.info("survey created" + survey1.getSurveyDesc());
			
			Question q1 = new Question("Is Java difficult?", "radio-button question", survey1);
			qRepo.save(q1);
			Question q2 = new Question("Do you like bootstrap?", "radio-button question", survey1);
			qRepo.save(q2);
			log.info("questions created: 1)" + q1.getQuestion() + " 2)" + q2.getQuestion());
			
			Answer a1 = new Answer("very difficult", q1);
			aRepo.save(a1);
			Answer a2 = new Answer("somewhat difficult", q1);
			aRepo.save(a2);
			Answer a3 = new Answer("easy", q1);
			aRepo.save(a3);
			log.info("for question " + q1.getQuestion() + " answers created: 1)" + a1.getAnswer() + " 2)" + a2.getAnswer() + " 3)" + a3.getAnswer());
			
			Answer a4 = new Answer("very much", q2);
			aRepo.save(a4);
			Answer a5 = new Answer("no opinion", q2);
			aRepo.save(a5);
			Answer a6 = new Answer("don't like at all", q2);
			aRepo.save(a6);
			log.info("for question " + q2.getQuestion() + " answers created: 1)" + a4.getAnswer() + " 2)" + a5.getAnswer() + " 3)" + a6.getAnswer());
			
			
		
	};

	}
}
