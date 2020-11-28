package com.ceiba.library.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class representing the book object and its characterization
 * 
 * @author Jefferson Rios
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BookDTO extends RegistryAppDTO {

	/**
	 * Corresponds to the title of the book
	 */
	@NotEmpty
	private String title;

	/**
	 * Corresponds to the title of the ISBN in the book
	 */
	@NotEmpty
	private String isbn;

	/**
	 * Corresponds to the number of items available per book
	 */
	@NotEmpty
	private Integer stock;
}
