/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceiba.library.models.entity.Loan;
import com.ceiba.library.models.repository.LoanRepository;
import com.ceiba.library.service.LoanService;

/**
 *
 * @author Augusto
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;


    @Override
	public List<Loan> getAll() {
		List<Loan> returnList = new ArrayList<>();
		loanRepository.findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}

	@Override
	public Loan getById(Long id) {
		Optional<Loan> obj = loanRepository.findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public Loan add(Loan t) {
		return loanRepository.save(t);
	}

	@Override
	public Loan edit(Loan t) {
		return loanRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		loanRepository.deleteById(id);

	}
	
	@Override
	public void lendBook(String user, String isbn){
		
	}
	

}
