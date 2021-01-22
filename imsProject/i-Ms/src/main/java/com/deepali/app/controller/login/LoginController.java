package com.deepali.app.controller.login;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deepali.app.dto.login.ChangePasswordDTO;
import com.deepali.app.dto.login.LoginDTO;
import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;
import com.deepali.app.service.login.LoginService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService service;
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	public LoginController() {
		log.info("CREATED " + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView loginControllerToSave(LoginDTO dtoFromJsp,
			CandidateDTO dtoFromdb, LoginDateDTO dtoOneToMany,
			HttpServletRequest request) throws Exception {
		log.info("--------login controller Started-------");
		try {
			CandidateDTO dtoFromService = service.loginModuleServiceSave(dtoFromJsp,
					dtoFromdb, dtoOneToMany);
			if (dtoFromService != null) {
				if (dtoFromService.isActive()) {
					log.info(
							"Object saved in Database and now navigating to home.jsp");
					HttpSession session = request.getSession(true);
					session.setMaxInactiveInterval(45);
					System.out.println("dtofromdb" + dtoFromService);
					session.setAttribute("user", dtoFromService);
					String loginDate = service.loginServiceFetchDate(dtoFromService,
							dtoOneToMany);
					log.info(loginDate);
					if (loginDate != null)
						return new ModelAndView("index.jsp")
								.addObject("name", dtoFromService.getName())
								.addObject("loginTime", loginDate);
				} else {
					/*service.hrLoginService(dtoFromJsp);
					if(hrDtoFromDb!=null)
					{
						return new ModelAndView("hrlogin.jsp","msg","Hr login successfull");
					}
					else
					{
						return new ModelAndView("hrlogin.jsp","msg","Hr login failed");
					}*/
					return new ModelAndView("login.jsp", "msg",
							"your Account is Disabled \n Please Contact HR to Enable the Account ");
				}
			} else {
				log.error("Login unsuccessfull");
				log.error("loginController ended");
				return new ModelAndView("login.jsp", "msg", "login failed");
			}
		} catch (Exception e) {
			log.error("EXCEPTION has occured in loginController");
		}
		return new ModelAndView("login.jsp", "logs",
				"username and password does not  match");
	}

	@RequestMapping(value = "changePassword.do", method = RequestMethod.POST)
	public ModelAndView loginControllerUpdatePassword(ChangePasswordDTO dtoFromJSP,
			CandidateDTO dtoFromDB) {
		try {
			log.info("Entering controller method");
			log.info("Binding data from JSP to Controller");
			ChangePasswordDTO dtoAfterUpdate = service
					.loginModuleServiceUpdate(dtoFromJSP, dtoFromDB);
			if (dtoAfterUpdate != null) {
				log.info("Password successfully updated to DB");
				return new ModelAndView("home.jsp", "pwdUpdateSuccess",
						"Password Upadated Successfully");
			}
		} catch (Exception e) {
			log.error("Some EXCEPTION has occured in controller loginRepeat method");
		}
		return new ModelAndView("changePassword.jsp", "msg",
				"Please enter valid credentials");
	}

	@RequestMapping(value = "changePassword.do")
	public ModelAndView loginControllerChangepassword(HttpServletRequest request,
			@RequestParam(value = "userName") String userName) {
		try {
			log.info("Entering changePassword method");
			Boolean isPresent = service.loginServicePasswordChange(userName);
			if (isPresent == true) {
				HttpSession httpsession = request.getSession(false);
				httpsession.setAttribute("username", userName);
				return new ModelAndView("changePassword.jsp", "userName", userName);
			}
		} catch (Exception e) {
			log.info(
					"Some Exception has occured in loginControllerchangePassword method");
		}
		return null;
	}

	@RequestMapping(value = "update.do", method = RequestMethod.GET)
	private ModelAndView profileController(HttpServletRequest request) {
		try {
			log.info("Entering Profile Controller");
			HttpSession session = request.getSession(false);
			if (session == null) {
				return new ModelAndView("login.jsp", "msg",
						"your session expired , Please login again");
			} else {
				CandidateDTO candidateDTO = (CandidateDTO) session
						.getAttribute("user");
				if (candidateDTO == null) {
					return new ModelAndView("login.jsp", "msg",
							"your session expired ,Please Login again");
				} else {
					log.info("Session object " + candidateDTO);
					return new ModelAndView("profile", "user", candidateDTO);
				}
			}
		} catch (Exception e) {
			log.error("Exception in profile controller : " + e.getMessage());
		}
		return new ModelAndView("profile.jsp", "error", "enter valid details");
	}

	@RequestMapping(value = "updateProfile.do", method = RequestMethod.POST)
	public ModelAndView updateController(CandidateDTO candidateDTO,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			if (session == null) {
				return new ModelAndView("index.jsp", "msg",
						"your session expired.please login again");
			} else {
				CandidateDTO existingUser = (CandidateDTO) session
						.getAttribute("user");
				if (existingUser == null) {
					return new ModelAndView("index.jsp", "msg",
							"your session expired ,Please Login again");
				} else {
					Boolean nUser = existingUser.getNewUser();
					candidateDTO.setNewUser(nUser);
					candidateDTO.setActive(existingUser.isActive());
					log.info("Profile updated successfully");
					service.updateService(candidateDTO);
				}
			}
		} catch (Exception e) {
			log.info("Exception occurred in updateController " + e.getMessage());
		}
		return new ModelAndView("index.jsp", "updated", "update successful");

	}

	@RequestMapping(value = "disable.do", method = RequestMethod.GET)
	public ModelAndView disableController(HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return new ModelAndView("login.jsp", "msg",
					"your session expired.please login again");
		} else {
			CandidateDTO dto = (CandidateDTO) session.getAttribute("user");
			if (dto == null) {
				return new ModelAndView("login.jsp", "msg",
						"your session expired please login again");
			} else {
				dto.setActive(false);
				service.disableService(dto);
				return new ModelAndView("login.jsp", "msg",
						"your account is disabled");
			}
		}

	}

	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public ModelAndView logoutController(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return new ModelAndView("login.jsp");
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class,
				new CustomDateEditor(dateFormat, false));

	}
}
