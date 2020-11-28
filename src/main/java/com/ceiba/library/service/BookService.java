package com.ceiba.library.service;

import java.util.List;

import com.ceiba.library.common.service.CommonService;
import com.ceiba.library.dto.BookDTO;
import com.ceiba.library.models.entity.Book;

public interface BookService extends CommonService<Book, Long> {

	List<BookDTO> getAvailableBooks(boolean available);
}
