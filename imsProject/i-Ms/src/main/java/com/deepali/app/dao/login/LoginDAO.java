package com.deepali.app.dao.login;

import java.util.Date;

import com.deepali.app.dto.hr.HrDTO;
import com.deepali.app.dto.login.ChangePasswordDTO;
import com.deepali.app.dto.login.LoginDTO;
import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

public interface LoginDAO {

	CandidateDTO login(LoginDTO dto) throws Exception;

	CandidateDTO loginModuleDAOFetchByUserName(String username) throws Exception;

	Boolean loginModuleDAOUpdatePassword(ChangePasswordDTO dtoFromJSP,
			CandidateDTO dtoFromDB) throws Exception;

	CandidateDTO loginModuleDAOFetchByPassword(String oldPassword) throws Exception;

	CandidateDTO loginDAOUpdate(CandidateDTO candidateDTO) throws Exception;

	Date loginModuleDAOFetchDateFromDB(CandidateDTO dtoAfterValidation,
			LoginDateDTO dtoOneToMany) throws Exception;

	CandidateDTO updateDAO(CandidateDTO candidateDTO) throws Exception;

	void disableDAO(CandidateDTO dto);
	
	HrDTO HrLoginDAO(HrDTO hrDtoFromDb) throws Exception;

}
