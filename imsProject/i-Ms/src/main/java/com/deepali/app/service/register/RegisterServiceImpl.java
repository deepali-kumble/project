package com.deepali.app.service.register;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.deepali.app.dao.register.RegisterDAO;
import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

@Service
public class RegisterServiceImpl implements RegisterService {
	private static final Logger log = LoggerFactory
			.getLogger(RegisterServiceImpl.class);

	@Autowired
	private MailSender mailSender;

	@Autowired
	private RegisterDAO registerDAO;

	final static Random random = new Random();

	public RegisterServiceImpl() {
		log.info("Created " + this.getClass().getSimpleName());
	}

	public CandidateDTO registerModuleService(CandidateDTO candidateDTO,
			LoginDateDTO dtoOneToMany) {
		try {
			if (!StringUtils.isEmpty(candidateDTO.getEmail())
					&& !StringUtils.isEmpty(candidateDTO.getName())
					&& !StringUtils.isEmpty(candidateDTO.getJobCode())) {
				Boolean repeat = registerDAO
						.validateEmailId(candidateDTO.getEmail());
				candidateDTO.setRepeat(repeat);
				if (Objects.nonNull(candidateDTO.getMobile())
						&& Objects.nonNull(candidateDTO.getExperience())
						&& Objects.nonNull(candidateDTO.getDateOfBirth())) {
					System.out.println("----Validation success---");
					log.info("Email " + candidateDTO.getEmail());

					log.info("Name " + candidateDTO.getName());
					String userName = candidateDTO.setUserName(userId(candidateDTO));
					log.info("Username : " + candidateDTO.getUserName());
					String password = candidateDTO.setPassword(password());
					log.info("Password : " + candidateDTO.getPassword());
					log.info("Mobile " + candidateDTO.getMobile());
					log.info("DOB " + candidateDTO.getDateOfBirth());
					log.info("job code " + candidateDTO.getJobCode());
					log.info("Experience " + candidateDTO.getExperience());
					Boolean newUser = candidateDTO.setNewUser(true);
					log.info("NewUser : " + candidateDTO.getPassword());
					candidateDTO.setActive(true);

					CandidateDTO dtoAfterSaveToDB = registerDAO
							.registerModuleSave(candidateDTO, dtoOneToMany);
					if (dtoAfterSaveToDB != null) {
						successSendMail(dtoAfterSaveToDB);
						return dtoAfterSaveToDB;
					}
				}
			}
		} catch (Exception e) {
			log.error("Exception in RegisterServiceImpl : " + e.getMessage());
		}
		return candidateDTO;
	}

	static String userId(CandidateDTO candidateDTO) {
		// fetch 3 lettersfrom username
		String uname = " ";
		uname = candidateDTO.getName().substring(0, 3);
		log.info("uname1 : " + uname);

		// 3 from date of birth
		Date date = candidateDTO.getDateOfBirth();
		Integer year = LocalDate
				.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).getYear();
		final String yy = year.toString().substring(2);
		uname = uname + yy;
		log.info("uname2 : " + uname);
		String[] email = candidateDTO.getEmail().split("[@._]", 3);

		final String first = email[0];
		final String middle = email[1];
		final String last = email[2];
		String arr = first + middle + last;
		int charLength = 2;
		StringBuilder token = new StringBuilder(charLength);
		for (int i = 0; i < charLength; i++) {
			if (arr.charAt(i) >= 'a' && arr.charAt(i) <= 'z') {
				token = token.append(Character
						.toUpperCase(arr.charAt(random.nextInt(arr.length()))));
			} else {
				token = token.append(arr.charAt(random.nextInt(arr.length())));
			}
		}
		uname = uname + token;
		log.info("fname 3: " + uname);
		return uname;

	}

	static String password() {
		final String pas = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
		int len = 8;
		String password = " ";
		StringBuilder token1 = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			token1 = token1.append(pas.charAt(random.nextInt(pas.length())));

		}
		password = password + token1;

		return password;
	}

	private void successSendMail(CandidateDTO dtoAfterSaveToDB) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		try {
			log.info("Entering to send mail");
			mailMessage.setTo(dtoAfterSaveToDB.getEmail());
			mailMessage.setSubject("CandidateRegistration Confirmation mail");
			mailMessage.setText("Hi " + dtoAfterSaveToDB.getName() + ""
					+ "you have successfully registered for "
					+ dtoAfterSaveToDB.getJobCode() + " \n with user name: "
					+ dtoAfterSaveToDB.getUserName() + " \n password:"
					+ dtoAfterSaveToDB.getPassword()
					+ "\n Please use this for future communication. http://localhost:8080/i-Ms/changePassword.do?userName="
					+ dtoAfterSaveToDB.getUserName());
			mailSender.send(mailMessage);
			log.info("Mail successfully sent");
		} catch (MailException e) {

			log.error("EXCEPTION in successSendMail : " + e.getMessage());
		}

	}

	public void repeatSendMail(CandidateDTO dtoAfterSaveToDB) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(dtoAfterSaveToDB.getEmail());
			mailMessage.setSubject("Registration Repeated");
			mailMessage.setText("Hi " + dtoAfterSaveToDB.getName() + ""
					+ "you have already successfully registered for "
					+ dtoAfterSaveToDB.getJobCode() + "."
					+ "So you cannot register again");
			mailSender.send(mailMessage);
			log.info("Mail repeation message successfully sent");
		} catch (Exception e) {
			log.error("EXCEPTION in successful mail sending : " + e.getMessage());
		}
	}
}
