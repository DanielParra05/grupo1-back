package com.ceiba.library.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class is related to the books entity and contains the fields of the
 * entity in the database
 * 
 * @author Augusto
 */
@Data
@Entity
@Table(name = "books")
@EqualsAndHashCode(callSuper = false)
public class Book extends RegistryApp {

	/**
	 * This field stores the book title
	 */
	@Column(name = "title", nullable = false)
	private String title;

	/**
	 * This field stores the book isbn
	 */
	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;

	/**
	 * This field stores the number of existing books
	 */
	@Column(name = "stock", nullable = false)
	private Integer stock;

	/**
	 * This field allows you to identify if there are books to loan
	 */
	@Column(name = "state")
	private boolean state;
}
