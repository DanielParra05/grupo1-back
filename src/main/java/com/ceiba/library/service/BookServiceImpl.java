/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ceiba.library.common.service.impl.CommonServiceImpl;
import com.ceiba.library.models.entity.Book;
import com.ceiba.library.models.repository.BookRepository;

/**
 *
 * @author Augusto
 */
@Service
public class BookServiceImpl extends CommonServiceImpl<Book, Long> {

    @Autowired
    public BookRepository bookRepository;

	@Override
	public CrudRepository<Book, Long> getCrudRepository() {
		return bookRepository;
	}
	

}
