package com.deepali.app.service.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepali.app.dao.hr.HrDAO;
import com.deepali.app.dto.register.CandidateDTO;

@Service
public class HrServiceImpl implements HrService{
	public HrServiceImpl() {
		System.out.println("CREATED " + this.getClass().getSimpleName());
	}

	@Autowired
	private HrDAO hrDAO;

	@Override
	public List<CandidateDTO> viewCandidateService() throws Exception
	{
		List<CandidateDTO> list=hrDAO.viewCandidateDAO();
		return list;
	}

}
