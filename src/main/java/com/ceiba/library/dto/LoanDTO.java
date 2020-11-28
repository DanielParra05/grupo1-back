package com.ceiba.library.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class representing the Loan object and its characterization
 * 
 * @author Jefferson Rios
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoanDTO extends RegistryAppDTO {

	/**
	 * corresponds to the book related to that loan
	 */
	private BookDTO book;

	/**
	 * corresponds to the date of the loan application
	 */
	private LocalDate dateRequest;

	/**
	 * Corresponds to the delivery date of the book
	 */
	private LocalDate dateDelivery;

	/**
	 * Corresponds to the name of the user requesting the book
	 */
	private String user;
}
