package com.ceiba.library.dto;

import lombok.Data;

/**
 * Base class DTO, for entity registration
 * @author Jefferson Rios
 *
 */
@Data
public abstract class RegistryAppDTO {

	/**
	 * Corresponds to the primary key of the object
	 */
	private Long id;
}
