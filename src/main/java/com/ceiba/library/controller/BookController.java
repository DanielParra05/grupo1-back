package com.ceiba.library.controller;
import com.ceiba.library.models.entity.Book;
import com.ceiba.library.service.BookService;
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
@RequestMapping({"/rest/service/book"})
public class BookController {

	@Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable Long id) {
    	Book o = bookService.getById(id);
        if (o == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(o);

    }

    /**
     * validated y BindinResult para validar los campos que vienen del Json
     *
     * @param book
     * @param result
     * @return
     */
    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Book book, BindingResult result) {
        if (result.hasErrors()) {
            return validated(result);
        }
        Book bookId = bookService.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Validated @RequestBody Book book, BindingResult result,
            @PathVariable Long id) {
        if (result.hasErrors()) {
            return validated(result);
        }
        Book o = bookService.getById(id);
        if (o == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.add(book));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
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

}
