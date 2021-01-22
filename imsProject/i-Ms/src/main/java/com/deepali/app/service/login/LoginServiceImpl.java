package com.deepali.app.service.login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.deepali.app.dao.login.LoginDAO;
import com.deepali.app.dao.register.RegisterDAO;
import com.deepali.app.dto.hr.HrDTO;
import com.deepali.app.dto.login.ChangePasswordDTO;
import com.deepali.app.dto.login.LoginDTO;
import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private RegisterDAO registerDAO;

	@Autowired
	private MailSender mailsender;

	private static final Logger log = LoggerFactory
			.getLogger(LoginServiceImpl.class);

	public LoginServiceImpl() {
		log.info("CREATED " + this.getClass().getSimpleName());
	}

	public CandidateDTO login(LoginDTO dto) throws Exception {
		System.out.println("Object created for login service");
		return loginDAO.login(dto);
	}

	public CandidateDTO loginModuleServiceSave(LoginDTO dtoFromJSP,
			CandidateDTO dtoFromDB, LoginDateDTO dtoOneToMany) throws Exception {
		try {
			log.info(
					"-----------------Entering loginModuleServiceSave in Service Class--------------");
			if (!StringUtils.isEmpty(dtoFromJSP.getUserName())
					&& !StringUtils.isEmpty(dtoFromJSP.getPassword())) {
				log.info("Completed Validation for entering the values in JSP");
				if (dtoFromJSP.getUserName().equals(dtoFromDB.getUserName())
						&& dtoFromJSP.getPassword()
								.equals(dtoFromDB.getPassword())) {
					log.info(
							"-----------------Compleated Validation for login page with DB-------------------");
					CandidateDTO dtoFromDb = loginDAO
							.loginModuleDAOFetchByUserName(dtoFromJSP.getUserName());
					CandidateDTO dtoAfterSave = registerDAO
							.registerModuleSave(dtoFromDb, dtoOneToMany);
					if (dtoAfterSave != null) {
						log.info(
								"---------------Object returned to controller-------------");
						return dtoAfterSave;
					}
				}
			}
		} catch (Exception e) {
			log.error("EXCEPTION IN loginModuleServiceSave : " + e.getMessage());
			log.error("EXCEPTION class loginModuleServiceSave : " + e.getClass());
		}
		return null;
	}

	public ChangePasswordDTO loginModuleServiceUpdate(ChangePasswordDTO dtoFromJsp,
			CandidateDTO dtoFromDb) {
		try {
			log.info(
					"---------Entering loginModuleServiceUpdate method in service class-----------");
			log.info("Data from Jsp is : " + dtoFromJsp);
			if (!StringUtils.isEmpty(dtoFromJsp.getOldPassword())
					&& !StringUtils.isEmpty(dtoFromJsp.getNewPassword())
					&& !StringUtils.isEmpty(dtoFromJsp.getConfirmPassword())) {
				log.info("Completed validation for entering values in JSP page");
				CandidateDTO dtoAfterValidationFromDB = loginDAO
						.loginModuleDAOFetchByPassword(dtoFromJsp.getOldPassword());
				if (dtoFromJsp.getOldPassword()
						.equals(dtoAfterValidationFromDB.getPassword())) {
					log.info("Completed Validation for login page with Db");
					if (dtoFromJsp.getNewPassword()
							.equals(dtoFromJsp.getConfirmPassword())) {
						log.info(
								"Validation Completed for all three fields of jsp page");
						if (dtoAfterValidationFromDB.getNewUser() == true) {
							dtoAfterValidationFromDB.setNewUser(false);
							Boolean updatedPassword = loginDAO
									.loginModuleDAOUpdatePassword(dtoFromJsp,
											dtoAfterValidationFromDB);
							if (updatedPassword == true) {
								log.info("Password Successfully updated");
								updatedPasswordMail(dtoAfterValidationFromDB);
								return dtoFromJsp;
							}
						}
					}

				}

			}
		} catch (Exception e) {
			log.error("EXCEPTION has occurred in loginModuleServiceUpdate method "
					+ e.getMessage());
		}
		return null;
	}

	private void updatedPasswordMail(CandidateDTO dtoFromDb) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		try {
			log.info("Entering to send email");
			mailMessage.setTo(dtoFromDb.getEmail());
			mailMessage.setSubject("Password Reset");
			mailMessage.setText("Your Password was Reset." + "\n"
					+ " Please contact us if it was not done by you contact at 9980274233 or deepalikumble@gmail.com");
			mailsender.send(mailMessage);
			log.info("Mail sent successfully");
		} catch (Exception e) {
			log.error("unable to send mail,EXCEPTION IN updatedPasswordMail:-"
					+ e.getMessage());
		}
	}

	public Boolean loginServicePasswordChange(String username) throws Exception {
		try {
			log.info("Entering loginServicePasswordChange method in Service");
			if (username != null) {
				CandidateDTO dtoFromDb = loginDAO
						.loginModuleDAOFetchByUserName(username);
				if (dtoFromDb != null) {
					log.info("Data found for username");
					return true;
				}
			}
		} catch (Exception e) {
			log.error("EXCEPTION IN loginServicePasswordChange " + e.getMessage());
		}
		log.info("Object not found for username ");
		return false;
	}

	public String loginServiceFetchDate(CandidateDTO dtoAfterValidation,
			LoginDateDTO dtoOneToMany) throws Exception {
		try {
			log.info("Entering to loginServiceFetchDate");
			Date logindate = loginDAO
					.loginModuleDAOFetchDateFromDB(dtoAfterValidation, dtoOneToMany);
			if (logindate != null) {
				log.info("Entering as object is found");

				log.info("Login date is" + logindate);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				return dateFormat.format(logindate);
			}
		} catch (Exception e) {
			log.error("Some exception in service");
		}
		return null;
	}

	public void updateService(CandidateDTO candidateDTO) {
		try {
			CandidateDTO dtoFromJsp = loginDAO.updateDAO(candidateDTO);
			log.info("dtoFromJsp in updateService : " + dtoFromJsp);
		} catch (Exception e) {
			log.error("Exception in update service : " + e.getMessage());
		}
	}

	@Override
	public void disableService(CandidateDTO dto) throws Exception {
		try {
			loginDAO.disableDAO(dto);
		} catch (Exception e) {
			log.error("exception in disableService  ");
		}
	}

	@Override
	public HrDTO hrLoginService(LoginDTO dtoFromJsp) 
	{
		
		return null;
	}

}
