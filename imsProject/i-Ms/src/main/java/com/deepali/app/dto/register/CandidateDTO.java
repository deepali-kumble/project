package com.deepali.app.dto.register;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "candidate_register")
public class CandidateDTO implements Serializable {
	@Id
	private String email;
	private long mobile;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String jobCode;
	private Integer experience;
	private String password;
	private String userName;
	private Boolean newUser;
	private boolean active;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "candidateDTO",
			cascade = CascadeType.ALL)
	private List<LoginDateDTO> loginDate;

	@Transient
	private Boolean repeat;

	private static final Logger log = LoggerFactory.getLogger(CandidateDTO.class);

	public CandidateDTO() {
		log.info("Created----" + this.getClass().getSimpleName());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	// public static Logger getLog() {
	// return log;
	// }

	public String getPassword() {
		return password;
	}

	public String setPassword(String password) {
		return this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String setUserName(String userName) {
		return this.userName = userName;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public Boolean getNewUser() {
		return newUser;
	}

	public Boolean setNewUser(Boolean newUser) {
		return this.newUser = newUser;
	}

	public List<LoginDateDTO> getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(List<LoginDateDTO> loginDate) {
		this.loginDate = loginDate;
	}

	public Boolean getRepeat() {
		return repeat;
	}

	public void setRepeat(Boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "CandidateDTO [email=" + email + ", mobile=" + mobile + ", name="
				+ name + ", dateOfBirth=" + dateOfBirth + ", jobCode=" + jobCode
				+ ", experience=" + experience + ", password=" + password
				+ ", userName=" + userName + ", newUser=" + newUser + ", active="
				+ active + ", loginDate=" + loginDate + ", repeat=" + repeat + "]";
	}

}
