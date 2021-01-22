package com.deepali.app.service.hr;

import java.util.List;

import com.deepali.app.dto.register.CandidateDTO;

public interface HrService 
{
	List<CandidateDTO> viewCandidateService() throws Exception;
}
