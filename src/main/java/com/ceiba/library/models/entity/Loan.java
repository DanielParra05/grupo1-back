package com.ceiba.library.models.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

/**
 * This class is related to the loans entity and contains the fields of the entity in the database
 * @author Equipo1
 *
 */
@Data
@Entity
@Table(name = "loans")
@EqualsAndHashCode(callSuper=false)
public class Loan extends RegistryApp {
	
	/**
	 * This field stores the identifier of the book
	 */
	@ManyToOne
	@JoinColumn(name="id_book", nullable = false)
	private Book book;
	
	/**
	 * 	This field saves the loan application date
	 */
	@Column(name = "date_request", nullable = false)
	private LocalDate dateRequest;
	
	/**
	 * This field stores the maximum delivery date
	 */
	@Column(name = "date_delivery")
	private LocalDate dateDelivery;
	
	/**
	 * This field stores the user's name
	 */
	@Column(name = "user", length = 30, nullable = false)
	private String user;
	
}
