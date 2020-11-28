package com.ceiba.library.models.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Base class, for entity registration
 * @author Jefferson Rios
 *
 */
public class RegistryApp {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
