package com.boot.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.boot.entity.Board;

public interface SearchBoardRepository {
	
	Board search1();
	
	Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
