/*package com.deepali.app.dao.login;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deepali.app.dto.hr.HrDTO;
import com.deepali.app.dto.login.ChangePasswordDTO;
import com.deepali.app.dto.login.LoginDTO;
import com.deepali.app.dto.register.CandidateDTO;
import com.deepali.app.dto.register.LoginDateDTO;

@Repository
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	private SessionFactory factory;
	private static final Logger log = LoggerFactory.getLogger(LoginDAOImpl.class);

	public LoginDAOImpl() {
		log.info("CREATED " + this.getClass().getSimpleName());
	}

	// @SuppressWarnings("rawtypes")
	@Override
	public CandidateDTO login(LoginDTO dto) throws Exception {
		log.info("" + dto);
		CandidateDTO fromDb = null;
		try (Session session = factory.openSession()) {
			String hql = "from CandidateDTO cdreg where userName=:user and password=:pwd";
			Query query = session.createQuery(hql);
			query.setParameter("user", dto.getUserName());
			query.setParameter("pwd", dto.getPassword());
			fromDb = (CandidateDTO) query.uniqueResult();
		} catch (Exception e) {
			log.error("EXCEPTION in logging : " + e.getMessage());
		}
		return fromDb;
	}

	@Override
	public CandidateDTO loginDAOUpdate(CandidateDTO candidateDTO) throws Exception {
		CandidateDTO dtoFromDb = null;
		try (Session session = factory.openSession()) {
			String hql = "update CandidateDTO cdreg set newUser=:nuser where userName=:ruser";
			Query query = session.createQuery(hql);
			if (candidateDTO != null) {
				Boolean newUser = candidateDTO.setNewUser(false);
				query.setParameter("nuser", newUser);
				query.setParameter("ruser", candidateDTO.getUserName());
				query.executeUpdate();
				dtoFromDb = (CandidateDTO) query.uniqueResult();
				return dtoFromDb;
			} else {
				log.info("dto object not found");
			}
		} catch (Exception e) {
			log.error("failed to update the dto in the database");
		}
		return dtoFromDb;
	}

	// @SuppressWarnings("unchecked")
	@Override
	public CandidateDTO loginModuleDAOFetchByUserName(String username)
			throws Exception {
		try (Session session = factory.openSession()) {
			String hql = "from CandidateDTO cdreg where userName=:userName";
			Query query = session.createQuery(hql);
			query.setParameter("userName", username);
			CandidateDTO dtoFromDB = (CandidateDTO) query.uniqueResult();
			if (dtoFromDB != null) {
				log.info("user data is found in db");
				return dtoFromDB;
			}
			log.info("", dtoFromDB);
		} catch (Exception e) {
			log.info("EXCEPTION in loginModuleDAOFetchByUserName : "
					+ e.getMessage());
		}
		log.info("user data is not found in db");
		return null;
	}

	// @SuppressWarnings("unchecked")
	@Override
	public Boolean loginModuleDAOUpdatePassword(ChangePasswordDTO dtoFromJSP,
			CandidateDTO dtoFromDB) throws Exception {

		Transaction transaction = null;
		String hql = "update CandidateDTO cdreg set password=:password,newUser=:newUser where userName=:userName";
		try (Session session = factory.openSession()) {
			log.info("Entering update method of LOGINDAO");
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("password", dtoFromJSP.getNewPassword());
			query.setParameter("newUser", dtoFromDB.getNewUser());
			query.setParameter("userName", dtoFromDB.getUserName());
			query.executeUpdate();
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			log.info("Exception in login DAO  updatePassword : " + e.getMessage());
			transaction.rollback();
		}
		return false;
	}

	// @SuppressWarnings("unchecked")
	@Override
	public CandidateDTO loginModuleDAOFetchByPassword(String oldPassword)
			throws Exception {
		log.info("Entering loginModuleDAOFetchByPassword ");
		String hql = "from CandidateDTO cdreg where password=:password";
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("password", oldPassword);
			CandidateDTO dtoFromDB = (CandidateDTO) query.uniqueResult();
			if (dtoFromDB != null) {
				log.info("Password data found in DB");
				return dtoFromDB;
			}
			// transaction.commit();
		} catch (Exception e) {
			log.info(
					"Exception in loginModuleDAOFetchByPassword: " + e.getMessage());
			transaction.rollback();
		}
		log.info("Password data not found in DB");
		return null;
	}

	@Override
	public Date loginModuleDAOFetchDateFromDB(CandidateDTO dtoAfterValidation,
			LoginDateDTO dtoOneToMany) {
		Date logindate = null;
		try (Session session = factory.openSession()) {
			String hql = "from LoginDateDTO ldto where loginID=(SELECT MAX(loginID) FROM LoginDateDTO loginDate\r\n"
					+ "  WHERE loginID NOT IN (SELECT MAX(loginID) FROM LoginDateDTO loginDate ))";
			Query query = session.createQuery(hql);
			LoginDateDTO dtofromdb = (LoginDateDTO) query.uniqueResult();
			logindate = dtofromdb.getLoginDate();
			log.info("Date is" + logindate);
			if (logindate != null) {
				log.info("Object found in DB" + dtofromdb.getLoginID());
				return logindate;
			}
		} catch (Exception e) {
			log.info("EXCEPTION loginModuleDAOFetchDateFromDB : " + e.getMessage());
		}
		return null;
	}

	@Override
	public CandidateDTO updateDAO(CandidateDTO candidateDTO) throws Exception {
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			String hql = "select password from CandidateDTO cdreg where userName=:uname";
			Query query = session.createQuery(hql);
			query.setParameter("uname", candidateDTO.getUserName());
			String password = (String) query.uniqueResult();
			candidateDTO.setPassword(password);
			log.info("" + candidateDTO);
			session.saveOrUpdate(candidateDTO);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Exception in updateDAO : " + e.getMessage());
			transaction.rollback();
		}
		return candidateDTO;
	}

	public void disableDAO(CandidateDTO dto) {
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(dto);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Exception in disableDAO : " + e.getMessage());
			transaction.rollback();
		}

	}

	@Override
	public HrDTO HrLoginDAO(HrDTO hrDtoFromDb) throws Exception {
		try (Session session = factory.openSession()) {
			String hql = "from HrDTO hr where userName=:user and password=:pwd";
			Query query = session.createQuery(hql);
			query.setParameter("user", hrDtoFromDb.getHrName());
			query.setParameter("pwd", hrDtoFromDb.getPassword());
			hrDtoFromDb = (HrDTO) query.uniqueResult();
		} catch (Exception e) {
			log.error("EXCEPTION in logging : " + e.getMessage());
		}
		return hrDtoFromDb;
	}

}
*/