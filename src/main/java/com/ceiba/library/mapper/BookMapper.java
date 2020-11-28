package com.ceiba.library.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.models.entity.Book;

/**
 * Object type converter for Book class
 * @author Jefferson Rios
 *
 */
@Mapper(componentModel = "spring")
public interface BookMapper {
	
	/**
	 * Attribute Mapper, from DTO to Entity
	 * @param bookDTO, book to convert
	 * @return, the book in DTO format
	 */
	@Mapping(source = "bookDTO.id", target = "id")
	@Mapping(source = "bookDTO.title", target = "title")
	@Mapping(source = "bookDTO.isbn", target = "isbn")
	@Mapping(source = "bookDTO.stock", target = "stock")
	@Mapping(source = "bookDTO.state", target = "state")
	public Book dtoToEntity(BookDTO bookDTO);
	
	/**
	 * Attribute Mapper, from Entity to DTO
	 * @param book, book to convert
	 * @return, the book in Entity format
	 */
	@Mapping(source = "book.id", target = "id")
	@Mapping(source = "book.title", target = "title")
	@Mapping(source = "book.isbn", target = "isbn")
	@Mapping(source = "book.stock", target = "stock")
	@Mapping(source = "book.state", target = "state")
	public BookDTO entityToDto(Book book);
	
	/**
	 * convert entity list to DTOS
	 * @param listBookDTO,list to convert
	 * @return, list of DTO
	 */
	public List<BookDTO> entitiesToDtos(List<Book> listBookDTO);
	
	/**
	 * convert DTO list to entity
	 * @param listBook,list to convert
	 * @return, list of DTO
	 */
	public List<Book> dtosToEntities(List<BookDTO> listBook);
}
