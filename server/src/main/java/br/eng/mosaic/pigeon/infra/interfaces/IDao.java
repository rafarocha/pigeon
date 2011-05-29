package br.eng.mosaic.pigeon.infra.interfaces;

import java.util.List;

public interface IDao<Type, T> {
	public void add(T obj) throws Exception;
	public void remove(T obj) throws Exception;
	public void update(T obj) throws Exception;	
	public T get(Type id) throws Exception;
	public List<T> getByProperty(Object value, String propertyName) throws Exception;
	public List<T> list() throws Exception;	
}