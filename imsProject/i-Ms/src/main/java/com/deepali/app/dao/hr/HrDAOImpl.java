/*package com.deepali.app.dao.hr;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deepali.app.dto.register.CandidateDTO;

@Repository
public class HrDAOImpl implements HrDAO {
	public HrDAOImpl() {
		System.out.println("CREATED " + this.getClass().getSimpleName());
	}

	@Autowired
	private SessionFactory  factory;

	@Override
	public List<CandidateDTO> viewCandidateDAO() throws Exception {
		Session session = factory.openSession();
		String hql = "From CandidateDTO";
		Query query = session.createQuery(hql);
		List<CandidateDTO> list = query.list();
		return list;
	}

}*/
