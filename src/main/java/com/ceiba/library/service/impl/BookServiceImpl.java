package com.ceiba.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.mapper.BookMapper;
import com.ceiba.library.models.entity.Book;
import com.ceiba.library.models.repository.BookRepository;
import com.ceiba.library.service.BookService;

/**
 * Class that allows the development of transactional operations within the application
 * @author Brian Gomez
 * @author Jefferson Rios
 */
@Service
public class BookServiceImpl implements BookService {

	/**
	 * Injection of the related repository
	 */
	@Autowired
	private BookRepository bookRepository;

	/**
	 * Injection of the related mapper
	 */
	@Autowired
	private BookMapper bookMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BookDTO> getAll() {
		List<Book> returnList = new ArrayList<>();
		bookRepository.findAll().forEach(obj -> returnList.add(obj));
		return bookMapper.entitiesToDtos(returnList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookDTO getById(Long id) {
		Optional<Book> obj = bookRepository.findById(id);
		if (obj.isPresent()) {
			return bookMapper.entityToDto(obj.get());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookDTO add(BookDTO book) {
		Optional<Book> optBook = bookRepository.findByIsbn(book.getIsbn());
		if(optBook.isPresent()) {
			BookDTO bookDTO = bookMapper.entityToDto(optBook.get());
			bookDTO.setStock(bookDTO.getStock() + 1);
			return edit(bookDTO);
		} else {
			book.setStock(Integer.valueOf(1));
			book.setState(Boolean.TRUE);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BookDTO edit(BookDTO book) {
		Book bookEntity = bookMapper.dtoToEntity(book);
		Book bookSave = bookRepository.save(bookEntity);
		return bookMapper.entityToDto(bookSave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BookDTO> getAvailableBooks(boolean available) {
		List<Book> listEntities = bookRepository.findByState(available);
		return bookMapper.entitiesToDtos(listEntities);
	}
}
