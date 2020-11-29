package com.ceiba.library.service;

import com.ceiba.library.common.service.CommonService;
import com.ceiba.library.dto.LoanDTO;

/**
 * Interface that allows remote access to transactional operations
 * 
 * @author Brian Gomez
 * @author Jefferson Rios
 *
 */
public interface LoanService extends CommonService<LoanDTO, Long> {

	/**
	 * Allows you to make a loan within the application
	 * 
	 * @param user, user applying for the loan
	 * @param isbn, isbn book code
	 */
	void lendBook(String user, String isbn);
}
