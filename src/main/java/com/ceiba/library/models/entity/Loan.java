package com.ceiba.library.models.entity;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "loans")
public class Loan extends RegistryApp {
	
	@ManyToOne
	@JoinColumn(name="id_book", nullable = false)
	private Book book;
	
	@Column(name = "date_request", nullable = false)
	private LocalDate dateRequest;
	
	@Column(name = "date_delivery", nullable = false)
	private LocalDate dateDelivery;
	
	@Column(name = "user", length = 30, nullable = false)
	private String user;
	
}
