package com.ceiba.library.controller;

import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.exception.ApplicationException;
import com.ceiba.library.service.LoanService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Controller that allows access to the services methods, through web requests
 * 
 * @author Augusto
 * @author Jefferson Rios
 * @author Brian Gomez
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({ "/rest/service/loan" })
public class LoanController {

	/**
	 * Injection of the related service
	 */
	@Autowired
	private LoanService loanService;

	/**
	 * It allows to obtain the list of loans made within the application
	 * 
	 * @return, the list of loans
	 */
	@GetMapping
	public ResponseEntity<List<LoanDTO>> getAllLoans() {
		return ResponseEntity.ok().body(loanService.getAll());
	}

	/**
	 * Allow Obtaining a book based on id
	 * 
	 * @param id, id of the book to look for @return, the book found
	 */
	@GetMapping("/{id}")
	public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
		LoanDTO loanObtained = loanService.getById(id);
		if (loanObtained == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(loanObtained);
	}

	/**
	 * validated y BindinResult to validate JSON fields
	 *
	 * @param loan
	 * @param result
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveLoan(@Validated @RequestBody LoanDTO loan, BindingResult result) {
		if (result.hasErrors()) {
			return validated(result);
		}
		LoanDTO loanId;
		try {
			loanId = loanService.lendBook(loan);
			return ResponseEntity.status(HttpStatus.CREATED).body(loanId);
		} catch (ApplicationException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Allows the edition of a loan
	 * 
	 * @param loan,  loan to be edited
	 * @param result
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> editLoan(@Validated @RequestBody LoanDTO loan, BindingResult result,
			@PathVariable Long id) {
		if (result.hasErrors()) {
			return validated(result);
		}
		LoanDTO loanObtained = loanService.getById(id);
		if (loanObtained == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(loanService.add(loan));
	}

	/**
	 * Allows deleting loan from id
	 * 
	 * @param id, id of the loan to be eliminated
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLoan(@PathVariable Long id) {
		loanService.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * method to validate errors
	 *
	 * @param result
	 * @return
	 */
	protected ResponseEntity<?> validated(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
	}
}
