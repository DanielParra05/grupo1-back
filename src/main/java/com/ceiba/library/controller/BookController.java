package com.ceiba.library.controller;

import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.service.BookService;
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
 *
 * @author Augusto
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({ "/rest/service/book" })
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<?> getAllBooks() {
		return ResponseEntity.ok().body(bookService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		BookDTO bookObtained = bookService.getById(id);
		if (bookObtained == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(bookObtained);

	}

	/**
	 * validated y BindinResult para validar los campos que vienen del Json
	 *
	 * @param book
	 * @param result
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveBook(@Validated @RequestBody BookDTO book, BindingResult result) {
		if (result.hasErrors()) {
			return validated(result);
		}
		BookDTO bookId = bookService.add(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookId);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editBook(@Validated @RequestBody BookDTO book, BindingResult result,
			@PathVariable Long id) {
		if (result.hasErrors()) {
			return validated(result);
		}
		BookDTO bookObtained = bookService.getById(id);
		if (bookObtained == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.add(book));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		bookService.delete(id);
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

	@GetMapping("/{available}")
	public ResponseEntity<List<BookDTO>> getAvailableBooks(@PathVariable boolean available) {

		List<BookDTO> listBook = bookService.getAvailableBooks(available);
		return ResponseEntity.ok().body(listBook);
	}
}
