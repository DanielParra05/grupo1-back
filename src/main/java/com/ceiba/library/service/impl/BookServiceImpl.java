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
import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.mapper.BookMapperImpl;
import com.ceiba.library.models.entity.Book;
import com.ceiba.library.models.repository.BookRepository;
import com.ceiba.library.service.BookService;

/**
 *
 * @author Augusto
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookMapperImpl bookMapper;

	@Override
	public List<Book> getAll() {
		List<Book> returnList = new ArrayList<>();
		bookRepository.findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}

	@Override
	public Book getById(Long id) {
		Optional<Book> obj = bookRepository.findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public Book add(Book t) {
		Book bookConsulting = getById(t.getId());
		if (bookConsulting != null) {
			if (t.getStock() != null) {
				t.setStock(t.getStock() + 1);
				return edit(t);
			} else {
				t.setStock(Integer.valueOf(1));
				t.setState(Boolean.TRUE);
			}
			return bookRepository.save(t);
		}
		return null;
	}

	@Override
	public Book edit(Book t) {
		return bookRepository.save(t);
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
