/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.library.controller;

import com.ceiba.library.models.entity.Loan;
import com.ceiba.library.service.LoanServiceImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Augusto
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/rest/service/loan"})
public class LoanController {

    private LoanServiceImpl loanServiceImpl;

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(loanServiceImpl.getCrudRepository().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Loan> o = loanServiceImpl.getCrudRepository().findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(o.get());

    }

    /**
     * validated y BindinResult para validar los campos que vienen del Json
     *
     * @param loan
     * @param result
     * @return
     */
    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Loan loan, BindingResult result) {
        if (result.hasErrors()) {
            return validated(result);
        }
        Loan loanId = loanServiceImpl.getCrudRepository().save(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Validated @RequestBody Loan loan, BindingResult result,
            @PathVariable Long id) {
        if (result.hasErrors()) {
            return validated(result);
        }
        Optional<Loan> o = loanServiceImpl.getCrudRepository().findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Loan loanbd = o.get();
        loanbd.setUser(loan.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(loanServiceImpl.getCrudRepository().save(loanbd));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        loanServiceImpl.getCrudRepository().deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método para validar los errores
     *
     * @param result
     * @return
     */
    protected ResponseEntity<?> validated(BindingResult result) {
        Map<String, Object> errores = new HashMap<String, Object>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

}
