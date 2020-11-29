package com.ceiba.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.mapper.LoanMapper;
import com.ceiba.library.models.entity.Book;
import com.ceiba.library.models.entity.Loan;
import com.ceiba.library.models.repository.LoanRepository;
import com.ceiba.library.service.impl.LoanServiceImpl;
import com.ceiba.library.useful.UsefulConstants;

/**
 * Class allowing for test cases for the loan service
 * 
 * @author Jefferson Rios
 *
 */
@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

	/**
	 * Injection of the class to be simulated
	 */
	@InjectMocks
	LoanServiceImpl loanService;

	/**
	 * Injection of the mock used in the service
	 */
	@Mock
	LoanRepository loanRepository;

	/**
	 * Injection of the mock used in the service
	 */
	@Mock
	LoanMapper loanMapper;

	/**
	 * Single test to obtain all the loans
	 */
	@Test
	public void getAllTest() {
		List<Loan> listLoan = generateLoanList();
		when(loanRepository.findAll()).thenReturn(listLoan);
		when(loanMapper.entitiesToDtos(listLoan)).thenReturn(generateLoanDTOList());
		Long idLoanResult = loanService.getAll().get(0).getId();
		Long idLoanExpected = listLoan.get(0).getId();

		assertEquals(idLoanExpected, idLoanResult);
	}

	/**
	 * Unit test to validate the method of obtaining by id
	 */
	@Test
	public void getByIdTest() {
		Optional<Loan> opt = Optional.of(generateLoan());
		LoanDTO loanDTO = generateLoanDTO();
		when(loanRepository.findById(UsefulConstants.ID_TEST)).thenReturn(opt);
		when(loanMapper.entityToDto(opt.get())).thenReturn(loanDTO);
		LoanDTO testLoan = loanService.getById(UsefulConstants.ID_TEST);
		assertEquals(UsefulConstants.ID_TEST, testLoan.getId());
	}

	/**
	 * Unit test to validate the method of adding loans
	 */
	@Test
	public void addTest() {
		Loan loan = generateLoan();
		LoanDTO loanDTO = generateLoanDTO();
		when(loanMapper.dtoToEntity(loanDTO)).thenReturn(loan);
		when(loanRepository.save(loan)).thenReturn(loan);
		when(loanMapper.entityToDto(loan)).thenReturn(loanDTO);
		
		LoanDTO result = loanService.add(loanDTO);

		assertEquals(loanDTO, result);
	}

	/**
	 * Get a list of loans for testing purposes
	 * 
	 * @return
	 */
	private List<Loan> generateLoanList() {
		List<Loan> listLoan = new ArrayList<>();
		Loan loan = generateLoan();
		listLoan.add(loan);
		return listLoan;
	}

	/**
	 * Get a list of loanDTO for testing purposes
	 * 
	 * @return
	 */
	private List<LoanDTO> generateLoanDTOList() {
		List<LoanDTO> listLoan = new ArrayList<>();
		LoanDTO loan = generateLoanDTO();
		listLoan.add(loan);
		return listLoan;
	}

	/**
	 * Generate a book for testing purposes
	 * 
	 * @return
	 */
	private Book generateBook() {
		Book book = new Book();
		book.setId(UsefulConstants.ID_TEST);
		book.setIsbn(UsefulConstants.ISBN_TEST);
		book.setState(true);
		book.setStock(UsefulConstants.STOCK_TEST);
		book.setTitle(UsefulConstants.TITLE_BOOK_TEST);
		return book;
	}

	/**
	 * Generate a loan for testing purposes
	 * @return
	 */
	private Loan generateLoan() {
		Loan loan = new Loan();
		loan.setBook(generateBook());
		loan.setDateDelivery(LocalDate.now());
		loan.setDateRequest(LocalDate.now());
		loan.setId(UsefulConstants.ID_TEST);
		loan.setUser(UsefulConstants.USER_TEST);
		return loan;
	}

	/**
	 * Generate a loanDTO for testing purposes
	 * @return
	 */
	private LoanDTO generateLoanDTO() {
		LoanDTO loan = new LoanDTO();
		loan.setBook(generateBookDTO());
		loan.setDateDelivery(LocalDate.now());
		loan.setDateRequest(LocalDate.now());
		loan.setId(UsefulConstants.ID_TEST);
		loan.setUser(UsefulConstants.USER_TEST);
		return loan;
	}

	/**
	 * Generate a bookDTO for testing purposes
	 * 
	 * @return
	 */
	private BookDTO generateBookDTO() {
		BookDTO book = new BookDTO();
		book.setId(UsefulConstants.ID_TEST);
		book.setIsbn(UsefulConstants.ISBN_TEST);
		book.setState(true);
		book.setStock(UsefulConstants.STOCK_TEST);
		book.setTitle(UsefulConstants.TITLE_BOOK_TEST);
		return book;
	}
}
