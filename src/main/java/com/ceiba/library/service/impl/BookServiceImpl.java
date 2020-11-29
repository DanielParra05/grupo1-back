package com.ceiba.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.mapper.BookMapperImpl;
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
	private BookMapperImpl bookMapper;

	/**
	 * {@inheritDoc}} 
	 */
	@Override
	public List<BookDTO> getAll() {
		List<Book> returnList = new ArrayList<>();
		bookRepository.findAll().forEach(obj -> returnList.add(obj));
		return bookMapper.entitiesToDtos(returnList);
	}

	@Override
	public BookDTO getById(Long id) {
		Optional<Book> obj = bookRepository.findById(id);
		if (obj.isPresent()) {
			return bookMapper.entityToDto(obj.get());
		}
		return null;
	}

	@Override
	public Book add(Book t) {
		Optional<Book> optBook = bookRepository.findByIsbn(t.getIsbn());
		if(optBook.isPresent()) {
			Book book = optBook.get();
			book.setStock(book.getStock() + 1);
			return edit(book);
		} else {
			t.setStock(Integer.valueOf(1));
			t.setState(Boolean.TRUE);
		}
		return null;
	}

	@Override
	public BookDTO edit(BookDTO t) {
		Book bookEntity = bookMapper.dtoToEntity(t);
		Book bookSave = bookRepository.save(bookEntity);
		return bookMapper.entityToDto(bookSave);
	}

	@Override
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<BookDTO> getAvailableBooks(boolean available) {
		List<Book> listEntities = bookRepository.findByState(available);
		return bookMapper.entitiesToDtos(listEntities);
	}
}
