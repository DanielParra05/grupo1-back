package com.ceiba.library.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.mapper.LoanMapper;
import com.ceiba.library.exception.LoadException;
import com.ceiba.library.models.entity.Loan;
import com.ceiba.library.models.repository.BookRepository;
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
	 * Injection of the related repository
	 */
	@SuppressWarnings("unused")
	@Autowired
	private BookRepository bookRepository;

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
	 * This method allows you to register the loan in the database
	 */
	@SuppressWarnings("unused")
	@Override
	public void lendBook(LoanDTO loanDTO) {

		LocalDate dateRequest = null;

		if (!this.valideExistence(loanDTO.getBook().getIsbn())) {
			throw new LoadException("There is no stock of the book to borrow");
		}

		if (this.validePallndrome(loanDTO.getBook().getIsbn())) {
			throw new LoadException("palindromic books only they can use in the library");
		}

		if (this.countDigits(loanDTO.getBook().getIsbn()) > 30) {
			dateRequest = this.getDateRequest();
		}

	}

	private boolean valideExistence(String isbn) {

		return true;
	}

	/**
	 * This method allows to validate the words pallndrome
	 * 
	 * @param isbn
	 * @return
	 */
	private boolean validePallndrome(String isbn) {

		int inc = 0;
		int des = isbn.length() - 1;
		boolean sw = true;

		while ((inc < des) && (sw)) {

			if (isbn.charAt(inc) == isbn.charAt(des)) {
				inc++;
				des--;
			} else {
				sw = false;
			}
		}

		return sw;
	}

	/**
	 * This method allows you to count the digits of the isbn
	 * 
	 * @param isbn It is the string that stores the identifier of the book
	 * @return The sum of the digit characters
	 */
	private int countDigits(String isbn) {

		char vectorIsbn[] = isbn.toCharArray();
		char digit;
		int cDigit = 0;

		for (int i = 0; i < vectorIsbn.length; i++) {
			digit = vectorIsbn[i];
			if (Character.isDigit(digit)) {
				cDigit += Character.getNumericValue(digit);
			}
		}
		return cDigit;
	}

	private LocalDate getDateRequest() {

		Date dateRequest;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int i = 1;

		while (i < 15) {
			if (cal.get(Calendar.DAY_OF_WEEK) != 1) {
				i++;
			}

			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		}

		dateRequest = cal.getTime();

		return dateRequest.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
