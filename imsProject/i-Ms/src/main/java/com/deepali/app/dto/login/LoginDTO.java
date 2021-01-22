package com.deepali.app.dto.login;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginDTO implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(LoginDTO.class);

	public LoginDTO() {
		log.info("CREATED : " + this.getClass().getSimpleName());
	}

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [userName=" + userName + ", password=" + password + "]";
	}

}