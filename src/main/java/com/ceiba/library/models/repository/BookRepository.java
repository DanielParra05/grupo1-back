
package com.ceiba.library.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.library.models.entity.Book;

/**
 *
 * @author Augusto
 */
public interface BookRepository extends JpaRepository<Book, Integer>{
    
}
