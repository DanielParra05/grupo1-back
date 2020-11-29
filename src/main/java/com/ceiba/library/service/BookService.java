package com.ceiba.library.service;

import java.util.List;

import com.ceiba.library.common.service.CommonService;
import com.ceiba.library.dto.BookDTO;

/**
 * Interface that allows remote access to transactional operations
 * 
 * @author Brian Gomez
 * @author Jefferson Rios
 *
 */
public interface BookService extends CommonService<BookDTO, Long> {

	/**
	 * Allows to obtain the available or unavailable books of the application
	 * 
	 * @param available, parameter that indicates if we are going to obtain the
	 *                   available books or not @return, a list of DTOS books
	 */
	List<BookDTO> getAvailableBooks(boolean available);
}
