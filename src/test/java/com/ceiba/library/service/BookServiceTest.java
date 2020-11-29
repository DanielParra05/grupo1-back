package com.ceiba.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.mapper.BookMapper;
import com.ceiba.library.models.entity.Book;
import com.ceiba.library.models.repository.BookRepository;
import com.ceiba.library.service.impl.BookServiceImpl;
import com.ceiba.library.useful.UsefulConstants;

/**
 * Class allowing for test cases for the book service
 * 
 * @author Jefferson Rios
 *
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	BookServiceImpl bookService;

	@Mock
	BookRepository bookRepository;

	@Mock
	BookMapper bookMapper;

	@Test
	public void getAllTest() {
		List<Book> listBook = generateBookList();
		when(bookRepository.findAll()).thenReturn(listBook);
		when(bookMapper.entitiesToDtos(listBook)).thenReturn(generateBookDTOList());
		Long idBookResult = bookService.getAll().get(0).getId();
		Long idBookExpected = listBook.get(0).getId();

		assertEquals(idBookExpected, idBookResult);
	}

	@Test
	public void getByIdTest() {
		Optional<Book> opt = Optional.of(generateBook());
		BookDTO bookDTO = generateBookDTO();
		when(bookRepository.findById(UsefulConstants.ID_TEST)).thenReturn(opt);
		when(bookMapper.entityToDto(opt.get())).thenReturn(bookDTO);
		BookDTO testBook = bookService.getById(UsefulConstants.ID_TEST);
		assertEquals(UsefulConstants.ID_TEST, testBook.getId());
	}

	@Test
	public void addWithStockTest() {
		Optional<Book> opt = Optional.of(generateBook());
		BookDTO bookDTO = generateBookDTO();
		when(bookRepository.findByIsbn(UsefulConstants.ISBN_TEST)).thenReturn(opt);
		when(bookMapper.entityToDto(opt.get())).thenReturn(bookDTO);
		when(bookRepository.save(opt.get())).thenReturn(opt.get());
		when(bookMapper.dtoToEntity(bookDTO)).thenReturn(opt.get());

		BookDTO result = bookService.add(bookDTO);

		assertEquals(UsefulConstants.STOCK_TEST + 1, result.getStock());
	}

	@Test
	public void addNoStockTest() {
		Optional<Book> opt = Optional.empty();
		BookDTO bookDTO = generateBookDTO();
		when(bookRepository.findByIsbn(UsefulConstants.ISBN_TEST)).thenReturn(opt);
		when(bookRepository.save(generateBook())).thenReturn(generateBook());
		when(bookMapper.dtoToEntity(bookDTO)).thenReturn(generateBook());
		when(bookMapper.entityToDto(generateBook())).thenReturn(bookDTO);

		BookDTO result = bookService.add(bookDTO);

		assertEquals(1, result.getStock());
	}

	@Test
	public void availableBooksTrueTest() {
		when(bookRepository.findByState(true)).thenReturn(generateBookList());
		when(bookMapper.entitiesToDtos(generateBookList())).thenReturn(generateBookDTOList());

		List<BookDTO> listDTOS = bookService.getAvailableBooks(true);

		assertEquals(1, listDTOS.size());
	}
	
	@Test
	public void availableBooksFalseTest() {
		when(bookRepository.findByState(false)).thenReturn(new ArrayList<>());
		when(bookMapper.entitiesToDtos(new ArrayList<>())).thenReturn(new ArrayList<>());

		List<BookDTO> listDTOS = bookService.getAvailableBooks(false);

		assertEquals(0, listDTOS.size());
	}

	private List<Book> generateBookList() {
		List<Book> listBook = new ArrayList<>();
		Book book = generateBook();
		listBook.add(book);
		return listBook;
	}

	private List<BookDTO> generateBookDTOList() {
		List<BookDTO> listBook = new ArrayList<>();
		BookDTO book = generateBookDTO();
		listBook.add(book);
		return listBook;
	}

	private Book generateBook() {
		Book book = new Book();
		book.setId(UsefulConstants.ID_TEST);
		book.setIsbn(UsefulConstants.ISBN_TEST);
		book.setState(true);
		book.setStock(UsefulConstants.STOCK_TEST);
		book.setTitle(UsefulConstants.TITLE_BOOK_TEST);
		return book;
	}

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
