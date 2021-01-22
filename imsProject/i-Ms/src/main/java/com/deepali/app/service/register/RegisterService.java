package com.deepali.app.service.register;

import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

public interface RegisterService {
	// public CandidateDTO save(CandidateDTO candidateDTO, LoginDateDTO
	// dtoOneToMany)
	// throws Exception;

	public abstract CandidateDTO registerModuleService(CandidateDTO candidateDTO,
			LoginDateDTO loginDateDTO);

	public abstract void repeatSendMail(CandidateDTO dtoAfterSaveToDB);
}
