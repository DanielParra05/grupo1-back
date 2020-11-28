package com.ceiba.library.models.entity;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "prestamo")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_LIBRO",referencedColumnName="id")
	private Book libro;
	
	private Date fechaSolicitud;
	
	private Date fechaEntregaMaxima;
	
	private String nombreUsuario;
	
}
