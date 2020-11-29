package com.ceiba.library.common.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Brian Gomez
 *
 * @param <T> object for generic use of services
 * @param <ID> data type of principal entity identifier
 */
public interface CommonService <T, ID extends Serializable> {

	List<T> getAll();
	
	T getById(ID id);
	
	T add(T t);
	
	T edit(T t);
	
	void delete(ID id);
	
}
