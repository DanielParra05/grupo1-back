package com.ceiba.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.mapper.LoanMapper;
import com.ceiba.library.models.entity.Loan;
import com.ceiba.library.models.repository.LoanRepository;
import com.ceiba.library.service.LoanService;

/**
 * Interface that allows access to transactions made within the application
 * 
 * @author Brian Gomez
 * @author Jefferson Rios
 */
@Service
public class LoanServiceImpl implements LoanService {

	/**
	 * Injection of the related repository
	 */
	@Autowired
	private LoanRepository loanRepository;

	/**
	 * Injection of the related mapper
	 */
	@Autowired
	private LoanMapper loanMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<LoanDTO> getAll() {
		List<Loan> returnList = new ArrayList<>();
		loanRepository.findAll().forEach(obj -> returnList.add(obj));
		return loanMapper.entitiesToDtos(returnList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoanDTO getById(Long id) {
		Optional<Loan> obj = loanRepository.findById(id);
		if (obj.isPresent()) {
			return loanMapper.entityToDto(obj.get());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoanDTO add(LoanDTO loan) {
		Loan loanEntity = loanMapper.dtoToEntity(loan);
		Loan loanSave = loanRepository.save(loanEntity);
		return loanMapper.entityToDto(loanSave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoanDTO edit(LoanDTO loan) {
		return add(loan);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {
		loanRepository.deleteById(id);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void lendBook(String user, String isbn) {

	}

}
