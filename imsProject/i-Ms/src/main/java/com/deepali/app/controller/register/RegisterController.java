package com.deepali.app.controller.register;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;
import com.deepali.app.service.register.RegisterService;
import com.deepali.app.service.register.RegisterServiceImpl;

@Controller
@RequestMapping("/")
public class RegisterController {

	@Autowired
	private RegisterServiceImpl registerService;

	private static final Logger log = LoggerFactory
			.getLogger(RegisterController.class);

	public RegisterController() {
		log.info("Created----" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "register.do", method = RequestMethod.POST)
	public ModelAndView saveToService(CandidateDTO candidateDTO,
			LoginDateDTO dtoOneToMany) {
		log.info("Entering Controller : " + candidateDTO);
		log.info("Entering into saveToService method");
		log.info("Binding data from html and dto");
		try {
			log.info("Travelling to service");
			CandidateDTO dtoAfterSaveToDB = registerService
					.registerModuleService(candidateDTO, dtoOneToMany);
			if (dtoAfterSaveToDB != null) {
				log.info("data validated");
				if (dtoAfterSaveToDB.getRepeat()) {

					log.info("email registered for first time");
					return new ModelAndView("register.jsp", "success",
							"Register successful");
				} else {
					log.info(
							"email already registered, try to login with your valid credentials");
					registerService.repeatSendMail(dtoAfterSaveToDB);
					return new ModelAndView("register.jsp", "present",
							"User with this credentials already present");
				}
			}
		} catch (Exception e) {
			log.error("Exception in controller : " + e.getMessage());
		}
		return new ModelAndView("register.jsp", "error",
				"registration failed, fill all the required data");
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class,
				new CustomDateEditor(dateFormat, false));

	}
}
