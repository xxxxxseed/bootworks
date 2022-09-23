package com.boot;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryMethodTest {

	@Autowired
	private BoardRepository boardRepo;
	
	//테이터 200개 저장
	//@BeforeEach는 테스트 메소드가 실행되기 전에 동작
	/*@BeforeEach
	public void dataPrepare() {
		for(int i=1; i<=200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스트");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			boardRepo.save(board);
		}
	}*/
	
	/*@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("테스트 제목 20");
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("17");
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList =
				boardRepo.findByTitleContainingOrContentContaining("17", "18");
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}*/
	
	@Test
	public void testFindByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = 
				boardRepo.findByTitleContainingOrderBySeqDesc("17");
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}
}
