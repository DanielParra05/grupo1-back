package com.ceiba.library.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 *
 * @author Augusto
 */
@Data
@Entity
@Table(name = "books")
public class Book extends RegistryApp {
	
	@NotEmpty
	@Column(nullable = false)
	private String title;

	@NotEmpty
	@Column(nullable = false)
	private String isbn;
	
	@Column(name = "stock")
	private Integer stock;	

}
