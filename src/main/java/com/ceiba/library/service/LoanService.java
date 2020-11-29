package com.ceiba.library.service;

import com.ceiba.library.common.service.CommonService;
import com.ceiba.library.dto.LoanDTO;

public interface LoanService extends CommonService<LoanDTO, Long> {
	
	void lendBook(LoanDTO loanDTO);

}
