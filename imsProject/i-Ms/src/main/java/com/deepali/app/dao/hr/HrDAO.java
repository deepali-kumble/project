package com.deepali.app.dao.hr;

import java.util.List;

import com.deepali.app.dto.register.CandidateDTO;

public interface HrDAO 
{
	public List<CandidateDTO> viewCandidateDAO() throws Exception;
}
