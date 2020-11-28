/*
 * Clase que implementa los libros
 */
package com.ceiba.biblioteca.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Augusto
 */
@Entity()
@Table(name = "libros")
public class Libro implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
