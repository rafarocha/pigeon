package br.eng.mosaic.pigeon.server.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.eng.mosaic.pigeon.infra.interfaces.IDao;
import br.eng.mosaic.pigeon.server.persistence.HibernateUtil;

/**
 * @author dhiego
 *
 */
public class DaoGeneric<Type, T> implements IDao<Type, T>{

	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public DaoGeneric(){
	        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getPersistentClass() {
        return persistentClass;
    }
	
	@Override
	public void add(T obj) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void remove(T obj) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}		
	}

	@Override
	public void update(T obj) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}	
	}

	@Override
	public T get(Type id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			T t = (T) session.get(getPersistentClass(), (Serializable) id);
			return t;
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public List<T> getByProperty(Object value, String propertyName) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(getPersistentClass());
			criteria.add(Restrictions.like(propertyName,value.toString(), MatchMode.END));
			
			List<T> list = (List<T>) criteria.list(); 
			
			return list;
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public List<T> list() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(getPersistentClass());			
			List<T> list = (List<T>) criteria.list(); 
			
			return list;
		} catch (Exception e) {
			throw e;
		}	
	}

}
