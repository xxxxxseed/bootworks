package com.boot.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Search {	//파라미터 전달 - DTO
	private String searchCondition;		//검색 조건(제목, 내용)
	private String searchKeyword;		//검색어
}
