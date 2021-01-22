package com.deepali.app.dto.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangePasswordDTO {
	private static final Logger log = LoggerFactory
			.getLogger(ChangePasswordDTO.class);

	public ChangePasswordDTO() {
		log.info("CREATED " + this.getClass().getSimpleName());
	}

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public static Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "ChangePasswordDTO [oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + ", confirmPassword=" + confirmPassword + "]";
	}

}
