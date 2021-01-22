package com.deepali.app.service.login;

import com.deepali.app.dto.hr.HrDTO;
import com.deepali.app.dto.login.ChangePasswordDTO;
import com.deepali.app.dto.login.LoginDTO;
import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

public interface LoginService {

	// dtofromjsp ,registration data from db ,One to many to fetch date and time
	CandidateDTO loginModuleServiceSave(LoginDTO dtoFromJSP, CandidateDTO dtoFromDB,
			LoginDateDTO dtoOneToMany) throws Exception;

	// dao loginModuleServiceUpdate
	ChangePasswordDTO loginModuleServiceUpdate(ChangePasswordDTO dtoFromJsp,
			CandidateDTO dtoFromDb) throws Exception;

	// change password Confirmation mail
	Boolean loginServicePasswordChange(String username) throws Exception;

	// to fetch date from the db
	String loginServiceFetchDate(CandidateDTO dtoAfterValidation,
			LoginDateDTO dtoOneToMany) throws Exception;

	void updateService(CandidateDTO candidateDTO) throws Exception;

	void disableService(CandidateDTO dto) throws Exception;

	HrDTO hrLoginService(LoginDTO dtoFromJsp);

}
