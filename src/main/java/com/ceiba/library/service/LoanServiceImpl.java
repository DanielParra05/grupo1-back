/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ceiba.library.common.service.impl.CommonServiceImpl;
import com.ceiba.library.models.entity.Loan;
import com.ceiba.library.models.repository.LoanRepository;

/**
 *
 * @author Augusto
 */
@Service
public class LoanServiceImpl extends CommonServiceImpl<Loan, Long> {

    @Autowired
    public LoanRepository loanRepository;

	@Override
	public CrudRepository<Loan, Long> getCrudRepository() {
		return loanRepository;
	}
	

}
