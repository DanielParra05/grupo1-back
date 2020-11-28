package com.ceiba.library.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author Augusto
 */
@Data
@Entity
@Table(name = "books")
public class Book extends RegistryApp {

}
