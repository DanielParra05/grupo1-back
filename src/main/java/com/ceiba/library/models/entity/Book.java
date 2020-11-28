
package com.ceiba.library.models.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Augusto
 */
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book extends RegistryApp {

}
