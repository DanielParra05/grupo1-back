
package com.ceiba.library.models.repository;

import com.ceiba.library.models.entity.Book;
import com.ceiba.library.models.entity.Loan;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



/**
 *
 * @author Augusto
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {
    
	List<Loan> findByBook(Book book);
}
