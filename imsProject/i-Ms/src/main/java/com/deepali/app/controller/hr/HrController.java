package com.deepali.app.controller.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.service.hr.HrService;

public class HrController {
	public HrController() {
		System.out.println("CREATED " + this.getClass().getSimpleName());
	}

	@Autowired
	private HrService hrService;

	public ModelAndView viewCandidateController() throws Exception {
		List<CandidateDTO> list = hrService.viewCandidateService();
		return new ModelAndView("viewCandidatejsp", "list", list);

	}
}
