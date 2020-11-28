package com.ceiba.library.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Augusto
 */
@Data
@Entity
@Table(name = "books")
@EqualsAndHashCode(callSuper=false)
public class Book extends RegistryApp {
	
	@Column(nullable = false)
	private String title;
	
	@NotEmpty
	@Column(nullable = false)
	private String isbn;
	
	@Column(name = "stock")
	private Integer stock;	
}
