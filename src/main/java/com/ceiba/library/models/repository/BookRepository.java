
package com.ceiba.library.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.library.models.entity.Book;

/**
 *
 * @author Augusto
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    
}
