/**
 * 
 */
package br.eng.mosaic.pigeon.server.persistence.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.ParseConversionEvent;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;

import br.eng.mosaic.pigeon.common.domain.User;
import br.eng.mosaic.pigeon.server.persistence.HibernateUtil;

/**
 * @author dhiego
 *
 */
public class DaoUser extends DaoGeneric<String, User> {

	public DaoUser(){
		
	}
	
	public List<User> getTopFive() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			SQLQuery sqlQuery = session.createSQLQuery("select u.email, u.name, u.score, u.facebook_id from User u order by u.score desc");
			sqlQuery.setMaxResults(5);

			List<?> list = sqlQuery.list();
			List<User> result = new LinkedList<User>();
			
			for (Object element : list) {
				Object[] o = (Object[]) element;
				User u = new User();
				u.setEmail(o[0].toString());
				u.setName(o[1].toString());
				u.setScore(Double.parseDouble(o[2].toString()));
				u.setFacebook_id(o[3].toString() );
				result.add(u);
			}
			return result;
		} catch (Exception e) {
			throw e;
		}finally{
			session.close();
		}
	}
	
	public void updateScore(String email, double score) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			SQLQuery sqlQuery = session.createSQLQuery("update user u set u.score=:score where u.email=:email");
			sqlQuery.setDouble("score", score);
			sqlQuery.setString("email", email);
			
			sqlQuery.executeUpdate();
			
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	public double getScore(String email) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			SQLQuery sqlQuery = session.createSQLQuery("select u.score from User u where u.email=:email");
			sqlQuery.setString("email", email);

			Object o = sqlQuery.uniqueResult();
			double result = 0;
			if(o != null)
				result =  Double.parseDouble(sqlQuery.uniqueResult().toString());
			return result;
		} catch (Exception e) {
			throw e;
		}finally{
			session.close();
		}
	}
	
	public void insertUser(String email, String name, double score) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			
			session.getTransaction().begin();
			SQLQuery sqlQuery = session.createSQLQuery("insert into user (email, name, score) select u.email, u.name, u.score from User u");
			sqlQuery.setString("email", email);
			sqlQuery.setString("name", name);
			sqlQuery.setDouble("score", score);
			
			sqlQuery.executeUpdate();
			
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}
	
}
