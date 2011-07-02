/**
 * 
 */
package br.eng.mosaic.pigeon.server.persistence.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
			SQLQuery sqlQuery = session.createSQLQuery("select u.email, u.name, u.score from User u order by u.score desc");
			sqlQuery.setMaxResults(5);

			List<?> list = sqlQuery.list();
			List<User> result = new LinkedList<User>();
			
			for (Object element : list) {
				Object[] o = (Object[]) element;
				User u = new User();
				u.setEmail(o[0].toString());
				u.setName(o[1].toString());
				u.setScore(Double.parseDouble(o[2].toString()));
				result.add(u);
			}
			return result;
		} catch (Exception e) {
			throw e;
		}finally{
			session.close();
		}
	}
	
}
