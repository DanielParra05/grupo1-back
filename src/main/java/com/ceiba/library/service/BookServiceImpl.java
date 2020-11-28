/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.library.models.repository.BookRepository;

/**
 *
 * @author Augusto
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookRepository repository;

   

}
