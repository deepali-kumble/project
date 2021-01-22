package com.deepali.app.dao.register;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

@Repository
public class RegisterDAOImpl implements RegisterDAO {
	@Autowired
	private SessionFactory factory;

	private static final Logger log = LoggerFactory.getLogger(RegisterDAOImpl.class);

	public RegisterDAOImpl() {
		log.info("Created " + this.getClass().getSimpleName());
	}
	/*
	 * public CandidateDTO save(CandidateDTO candidateDTO) throws Exception {
	 * Session session = null; Transaction transaction = null;
	 * 
	 * try { log.info("----------saveDAO Started-----------"); session =
	 * factory.openSession(); transaction = session.beginTransaction();
	 * session.save(candidateDTO); transaction.commit();
	 * log.info("Data saved into database successfully");
	 * 
	 * } catch (Exception e) { log.error("Exception  raised saving data" +
	 * e.getMessage()); transaction.rollback(); } finally { session.close();
	 * log.info("registerDAO started");
	 * 
	 * } return candidateDTO;
	 * 
	 * }
	 */

	@Override
	public CandidateDTO registerModuleSave(CandidateDTO candidateDTO,
			LoginDateDTO dtoOneToMany) throws Exception {
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			log.info("Entering save method in DAO");
			List<LoginDateDTO> list = new ArrayList<LoginDateDTO>();
			list.add(dtoOneToMany);
			dtoOneToMany.setLoginDate(Calendar.getInstance().getTime());
			dtoOneToMany.setCandidateDTO(candidateDTO);
			candidateDTO.setLoginDate(list);
			session.saveOrUpdate(candidateDTO);
			transaction.commit();
			log.info("out" + candidateDTO);
			log.info("Object successfully saved into DataBase");
			return candidateDTO;
		} catch (HibernateException e) {
			transaction.rollback();
			log.error("EXCEPTION in email dao : " + e.getMessage());
		}
		return candidateDTO;
	}

	public boolean validateEmailId(String emailId) throws Exception {
		try (Session session = factory.openSession()) {
			log.info("Entering the validateEmailId method");
			String hql = "from CandidateDTO cdreg where email=:emailid";
			Query query = session.createQuery(hql);
			query.setParameter("emailid", emailId);
			CandidateDTO dto = (CandidateDTO) query.uniqueResult();
			if (dto != null) {
				log.info("The emailId is already present");
				return false;
			}
		} catch (Exception e) {
			log.info("Unable to save to database due to exception  "
					+ e.getMessage());
		}
		return true;
	}
}
