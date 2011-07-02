/**
 * 
 */
package br.eng.mosaic.pigeon.server.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
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
			Criteria c = session.createCriteria(User.class);
			c.setMaxResults(5);
			c.addOrder(Order.desc("score"));
			
			List<User> users = c.list();
			return users;
		} catch (Exception e) {
			throw e;
		}finally{
			session.close();
		}
	}
	
}
