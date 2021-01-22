package com.deepali.app.dao.register;

import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

public interface RegisterDAO {
	// public CandidateDTO save(CandidateDTO candidateDTO) throws Exception;

	public CandidateDTO registerModuleSave(CandidateDTO candidateDTO,
			LoginDateDTO dtoOneToMany) throws Exception;

	public boolean validateEmailId(String emailId) throws Exception;
}
