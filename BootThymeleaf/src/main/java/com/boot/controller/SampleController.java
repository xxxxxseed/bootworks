package com.boot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.dto.ItemDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/sample/")
@Controller
public class SampleController {

	@GetMapping("/ex1")
	public void ex1() {}	//sample/ex1.html
	
	@GetMapping("/ex2")
	public String ex2(Model model) {
		ItemDto itemDto = new ItemDto();
		itemDto.setId(1L);			//1번
		itemDto.setItemNm("상품1");
		itemDto.setPrice(20000);
		itemDto.setItemDetail("상품1 상세 설명");
		itemDto.setRegTime(LocalDateTime.now());	//현재 시간
		
		model.addAttribute("itemDto", itemDto);
		
		return "sample/ex2";
	}
	
	@GetMapping("/ex3")
	public String ex3(Model model) {
		List<ItemDto> itemList = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setId((long) i);			//1번
			itemDto.setItemNm("상품" + i);
			itemDto.setPrice(2000*i);
			itemDto.setItemDetail("상품" + i +"상세 설명");
			itemDto.setRegTime(LocalDateTime.now());
			itemList.add(itemDto);				//리스트에 상품 저장
		}
		model.addAttribute("itemList", itemList);
		return "sample/ex3";
	}
	
	//타임리프 레이아웃
	@GetMapping({"/exLayout1", "/exTemplate"})
	public void exLayout1() {
		log.info("exLayout......");
	}
}
