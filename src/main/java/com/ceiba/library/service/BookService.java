package com.ceiba.library.service;

import java.util.List;

import com.ceiba.library.common.service.CommonService;
import com.ceiba.library.dto.BookDTO;

public interface BookService extends CommonService<BookDTO, Long> {

	List<BookDTO> getAvailableBooks(boolean available);
}
