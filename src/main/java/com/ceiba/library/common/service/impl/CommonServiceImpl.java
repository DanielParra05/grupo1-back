package com.ceiba.library.common.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.library.common.service.CommonService;

/**
 * @author Brian Gomez
 *
 * @param <T> entity for generic use of services
 * @param <ID> data type of principal entity identifier
 */
public abstract class CommonServiceImpl<T, ID extends Serializable> implements CommonService<T, ID> {

	@Override
	public List<T> getAll() {
		List<T> returnList = new ArrayList<>();
		getCrudRepository().findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}

	@Override
	public T getById(ID id) {
		Optional<T> obj = getCrudRepository().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public T add(T t) {
		return getCrudRepository().save(t);
	}

	@Override
	public T edit(T t) {
		return getCrudRepository().save(t);
	}

	@Override
	public void delete(ID id) {
		getCrudRepository().deleteById(id);

	}
	
	@Override
	public void lendBook(String user, String isbn){
		
	}

	public abstract CrudRepository<T, ID> getCrudRepository();

}