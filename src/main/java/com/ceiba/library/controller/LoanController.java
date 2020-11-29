package com.ceiba.library.controller;

import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.service.LoanService;
import java.util.HashMap;
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
 *
 * @author Augusto
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({ "/rest/service/loan" })
public class LoanController {

	@Autowired
	private LoanService loanService;

	@GetMapping
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(loanService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable Long id) {
		LoanDTO loanObtained = loanService.getById(id);
		if (loanObtained == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(loanObtained);

	}

	/**
	 * validated y BindinResult para validar los campos que vienen del Json
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
		LoanDTO loanId = loanService.add(loan);
		return ResponseEntity.status(HttpStatus.CREATED).body(loanId);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editLoan(@Validated @RequestBody LoanDTO loan, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return validated(result);
		}
		LoanDTO loanObtained = loanService.getById(id);
		if (loanObtained == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(loanService.add(loan));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLoan(@PathVariable Long id) {
		loanService.delete(id);
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
