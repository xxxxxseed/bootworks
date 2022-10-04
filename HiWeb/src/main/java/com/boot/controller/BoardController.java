package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.domain.Search;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	//게시글 목록
	@GetMapping("/getBoardList")
	public String getBoardList(Search search, Model model) {
		//초기값 null 처리
		if(search.getSearchCondition() == null) {
			search.setSearchCondition("TITLE");
		}
		if(search.getSearchKeyword() == null) {
			search.setSearchKeyword("");
		}
		//List<Board> boardList = service.getBoardList();
		Page<Board> boardList = service.getBoardList(search);
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";		//board/getBoardList.html
	}
	
	//상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCount(seq);				//조회수
		Board board = service.getBoard(seq);	//상세보기
		model.addAttribute("board", board);
		return "board/getBoard";			//board/getBoard.html
	}
	
	//글쓰기 폼 요청
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글쓰기 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board,
			@AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		service.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
