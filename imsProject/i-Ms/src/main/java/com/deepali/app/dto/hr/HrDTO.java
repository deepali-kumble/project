package com.deepali.app.dto.hr;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Hr_table")
public class HrDTO {
	private static final Logger log = LoggerFactory.getLogger(HrDTO.class);

	public HrDTO() {
		log.info("CREATED " + this.getClass().getSimpleName());
	}

	@Id
	private String hrId;
	private String password;
	private String hrName;

	public String getHrId() {
		return hrId;
	}

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public static Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "HrDTO [hrId=" + hrId + ", password=" + password + ", hrName="
				+ hrName + "]";
	}

}
