package com.ceiba.library.service;

import com.ceiba.library.common.service.CommonService;
import com.ceiba.library.models.entity.Loan;

public interface LoanService extends CommonService<Loan, Long> {
	
	void lendBook(String user, String isbn);

}
