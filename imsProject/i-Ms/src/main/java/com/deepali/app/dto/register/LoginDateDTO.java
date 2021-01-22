package com.deepali.app.dto.register;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "logindate")
public class LoginDateDTO implements Serializable {
	private static Logger log = LoggerFactory.getLogger(LoginDateDTO.class);

	public LoginDateDTO() {
		log.info("CREATED-----" + this.getClass().getSimpleName());
	}

	@Id
	@GenericGenerator(name = "yes", strategy = "increment")
	@GeneratedValue(generator = "yes")
	private Integer loginID;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date loginDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "email", nullable = false)
	private CandidateDTO candidateDTO;

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		LoginDateDTO.log = log;
	}

	public Integer getLoginID() {
		return loginID;
	}

	public void setLoginID(Integer loginID) {
		this.loginID = loginID;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public CandidateDTO getCandidateDTO() {
		return candidateDTO;
	}

	public void setCandidateDTO(CandidateDTO candidateDTO) {
		this.candidateDTO = candidateDTO;
	}

	/*
	 * @Override public String toString() { return "LoginDateDTO [loginID=" +
	 * loginID + ", loginDate=" + loginDate + ", candidateDTO=" + candidateDTO +
	 * "]"; }
	 */

}
