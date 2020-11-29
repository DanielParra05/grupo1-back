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

import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.mapper.LoanMapperImpl;
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

	@Autowired
	private LoanMapperImpl loanMapper;

	@Override
	public List<LoanDTO> getAll() {
		List<Loan> returnList = new ArrayList<>();
		loanRepository.findAll().forEach(obj -> returnList.add(obj));
		return loanMapper.entitiesToDtos(returnList);
	}

	@Override
	public LoanDTO getById(Long id) {
		Optional<Loan> obj = loanRepository.findById(id);
		if (obj.isPresent()) {
			return loanMapper.entityToDto(obj.get());
		}
		return null;
	}

	@Override
	public LoanDTO add(LoanDTO t) {
		Loan loanEntity = loanMapper.dtoToEntity(t);
		Loan loanSave = loanRepository.save(loanEntity);
		return loanMapper.entityToDto(loanSave);
	}

	@Override
	public LoanDTO edit(LoanDTO t) {
		return add(t);
	}

	@Override
	public void delete(Long id) {
		loanRepository.deleteById(id);

	}

	@Override
	public void lendBook(String user, String isbn) {
		
	}

}
