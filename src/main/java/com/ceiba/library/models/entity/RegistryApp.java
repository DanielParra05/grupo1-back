package com.ceiba.library.models.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Base class, for entity registration
 * @author Jefferson Rios
 *
 */
@Data
@MappedSuperclass
public abstract class RegistryApp {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
