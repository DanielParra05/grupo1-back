package com.ceiba.library.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ceiba.library.dto.LoanDTO;
import com.ceiba.library.models.entity.Loan;

/**
 * Object type converter for Loan class
 * 
 * @author Jefferson Rios
 *
 */
@Mapper(componentModel = "spring", uses = { BookMapper.class })
public interface LoanMapper {

	/**
	 * Attribute Mapper, from DTO to Entity
	 * 
	 * @param loanDTO, loan to convert @return, the loan in DTO format
	 */
	@Mapping(source = "loanDTO.id", target = "id")
	@Mapping(source = "loanDTO.book", target = "book")
	@Mapping(source = "loanDTO.dateRequest", target = "dateRequest")
	@Mapping(source = "loanDTO.dateDelivery", target = "dateDelivery")
	@Mapping(source = "loanDTO.user", target = "user")
	public Loan dtoToEntity(LoanDTO loanDTO);

	/**
	 * Attribute Mapper, from Entity to DTO
	 * 
	 * @param loan, loan to convert @return, the loan in Entity format
	 */
	@Mapping(source = "loan.id", target = "id")
	@Mapping(source = "loan.book", target = "book")
	@Mapping(source = "loan.dateRequest", target = "dateRequest")
	@Mapping(source = "loan.dateDelivery", target = "dateDelivery")
	@Mapping(source = "loan.user", target = "user")
	public LoanDTO entityToDto(Loan loan);

	/**
	 * convert entity list to DTOS
	 * 
	 * @param listLoanDTO,list to convert @return, list of DTO
	 */
	public List<LoanDTO> entitiesToDtos(List<Loan> listLoanDTO);

	/**
	 * convert DTO list to entity
	 * 
	 * @param listLoan,list to convert @return, list of DTO
	 */
	public List<Loan> dtosToEntities(List<LoanDTO> listLoan);
}
