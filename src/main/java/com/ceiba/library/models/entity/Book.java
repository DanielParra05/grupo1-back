package com.ceiba.library.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * This class is related to the books entity and contains the fields of the entity 
 * in the database
 * @author Equipo1
 */
@Data
@Entity
@Table(name = "books")
public class Book extends RegistryApp {
	
	/**
	 * This field stores the book title
	 */
	@NotEmpty
	@Column(nullable = false)
	private String title;

	/**
	 * This field stores the book title
	 */
	@NotEmpty
	@Column(nullable = false)
	private String isbn;
	
	/**
	 * This field stores the number of existing books
	 */
	@Column(name = "stock")
	private Integer stock;	
	
	/**
	 * This field allows you to identify if there are books to loan
	 */
	@Column(name = "state")
	private boolean state;

}
