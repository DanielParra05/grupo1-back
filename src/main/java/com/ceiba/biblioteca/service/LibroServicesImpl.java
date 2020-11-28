/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.models.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Augusto
 */
@Service
public class LibroServicesImpl implements LibroService {

    @Autowired
    public LibroRepository repository;

   

}
