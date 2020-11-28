
package com.ceiba.library.models.repository;

import com.ceiba.library.models.entity.Loan;
import org.springframework.data.repository.CrudRepository;



/**
 *
 * @author Augusto
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {
    
}
