
package com.ceiba.library.models.entity;
import javax.persistence.Entity;
import javax.persistence.Table;
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

	@Column(nullable = false)
	private String isbn;
	
	@Column(name = "stock")
	private Integer stock;	

}
