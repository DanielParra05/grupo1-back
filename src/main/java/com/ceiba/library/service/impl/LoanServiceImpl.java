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

import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.mapper.BookMapper;
import com.ceiba.library.mapper.LoanMapper;
import com.ceiba.library.exception.ApplicationException;
import com.ceiba.library.models.entity.Book;
import com.ceiba.library.models.entity.Loan;
import com.ceiba.library.models.repository.BookRepository;
import com.ceiba.library.models.repository.LoanRepository;
import com.ceiba.library.service.BookService;
import com.ceiba.library.service.LoanService;
import com.ceiba.library.useful.UsefulConstants;

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
	 * Injection of the related mapper
	 */
	@Autowired
	private BookMapper bookMapper;

	/**
	 * Injection of the related repository
	 */
	@Autowired
	private BookRepository bookRepository;

	/**
	 * Injection of the related service
	 */
	@Autowired
	private BookService bookService;

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
	 * 
	 * @throws ApplicationException
	 */
	@Override
	public LoanDTO lendBook(LoanDTO loanDTO) throws ApplicationException {

		if (!this.valideExistence(loanDTO)) {
			throw new ApplicationException(UsefulConstants.MSJ_BOOK_WITHOUT_UNITS);
		}

		if (this.validePallndrome(loanDTO.getBook().getIsbn())) {
			throw new ApplicationException(UsefulConstants.MSJ_PALINDROMIC_ONLY);
		}

		if (this.countDigits(loanDTO.getBook().getIsbn()) > 30) {
			loanDTO.setDateDelivery(getDateDelivery());
		}
		setStock(loanDTO.getBook());
		loanDTO.setDateRequest(LocalDate.now());
		return add(loanDTO);
	}

	private void setStock(BookDTO bookDTO) {
		bookService.setStock(bookDTO.getIsbn());
	}

	/**
	 * This method validates the existence of the book and its stock
	 * 
	 * @param isbn This parameter saves the identification string of the book
	 * @return Returns true if the book exists and has stock greater than 0
	 */
	private boolean valideExistence(LoanDTO loanDTO) {

		boolean sw = false;
		Optional<Book> optBook = this.bookRepository.findByIsbn(loanDTO.getBook().getIsbn());

		if (optBook.isPresent()) {
			BookDTO bookDTO = bookMapper.entityToDto(optBook.get());
			if (bookDTO.getStock() > 0) {
				loanDTO.setBook(bookDTO);
				sw = true;
			}
		}
		return sw;
	}

	/**
	 * This method allows to validate the words pallndrome
	 * 
	 * @param isbn
	 * @return Return true if the word is pallndrome
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

	/**
	 * This method allows obtaining the maximum date for the delivery of the book
	 * 
	 * @return The maximum date the user must be delivered
	 */
	private LocalDate getDateDelivery() {

		Date dateDelivery;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int i = 1;

		while (i < 15) {
			if (cal.get(Calendar.DAY_OF_WEEK) != 1) {
				i++;
			}

			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		}

		dateDelivery = cal.getTime();

		return dateDelivery.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	}
}
