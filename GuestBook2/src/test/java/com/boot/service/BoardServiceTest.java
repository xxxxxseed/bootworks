package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;
	
	/*@Test
	public void testRegister() {
		BoardDto dto =
				BoardDto.builder()
						.title("한글 테스트")
						.content("테스트 내용입니다.")
						.writerEmail("user50@aaa.com")
						.build();
		
		boardService.register(dto);
	}*/
	
	//게시글 목록 보기
	/*@Test
	public void testList() {
		
		PageRequestDto pageRequestDto = new PageRequestDto();
		
		PageResultDto<BoardDto, Object[]> result = boardService.getList(pageRequestDto);
		
		for(BoardDto boardDto : result.getDtoList()) {
			System.out.println(boardDto);
		}
	}*/
	
	//게시글 상세 보기
	@Test
	public void testGet() {
		Long bno = 90L;
		BoardDto boardDto = boardService.get(bno);
		
		System.out.println(boardDto);
	}
}
