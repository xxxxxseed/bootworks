package com.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.config.SecurityUser;
import com.boot.dto.FileDto;
import com.boot.domain.Board;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	//게시글 목록 보기
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";	//templates/board/getBoardList.html
	}
	
	//게시글 상세 보기
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		boardService.updateCount(seq);		//조회수
		Board board = boardService.getBoard(seq);
		model.addAttribute("board", board);
		return "board/getBoard";
	}
	
	//글 쓰기 폼 페이지 요청
	@GetMapping("/insertBoard")
	public void insertBoard() {}
	
	//글쓰기 처리 요청
	@PostMapping("insertBoard")
	public String insertBoard(Board board, @RequestParam MultipartFile[] uploadFile,
			@AuthenticationPrincipal SecurityUser principal) throws IllegalStateException, IOException {
		for(MultipartFile file : uploadFile) {
			if(!file.isEmpty()) {
				//FileDto 객체 생성
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
						file.getOriginalFilename(), file.getContentType());
				
				//파일 생성
				File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
				//실제 물리적인 파일로 전달해서 저장
				file.transferTo(newFileName);
			}
		}
		
		board.setMember(principal.getMember());		//인증된 회원이 글씀
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
