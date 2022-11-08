package com.boot.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.repository.BoardRepository;
import com.boot.repository.ReplyRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private ReplyRepository replyRepo;

	//게시글 등록
	@Override
	public Long register(BoardDto dto) {
		Board board = dtoToEntity(dto);
		boardRepo.save(board);
		
		return board.getBno();
	}

	@Override
	public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
		//entityToDto 호출
		Function<Object[], BoardDto> fn = (en -> entityToDto((Board) en[0],
				(Member) en[1], (Long) en[2]));
		
		//Pageable pageable = pageRequestDto.getPageable(Sort.by("bno").descending());
		//Page<Object[]> result = boardRepo.getBoardWithReplyCount(pageable);
		
		Page<Object[]> result = boardRepo.searchPage(
				pageRequestDto.getType(), 
				pageRequestDto.getKeyword(), 
				pageRequestDto.getPageable(Sort.by("bno").descending()));
		
		return new PageResultDto<>(result, fn);
	}

	//게시글 상세 보기
	@Override
	public BoardDto get(Long bno) {
		Object result = boardRepo.getBoardByBno(bno);
		Object[] arr = (Object[]) result;
		return entityToDto((Board) arr[0],
				(Member) arr[1], (Long) arr[2]);
	}

	//게시글 삭제
	@Transactional
	@Override
	public void remove(Long bno) {
		//댓글 먼저 삭제
		replyRepo.deleteByBno(bno);
		
		boardRepo.deleteById(bno);
	}

	//조회수
	@Transactional
	@Override
	public void updateCount(Long bno) {
		boardRepo.updateCount(bno);
	}

	@Override
	public void modify(BoardDto dto) {
		//수정할 게시글 가져오기
		Board board = boardRepo.findById(dto.getBno()).get();
		//게시글 수정
		board.changeTitle(dto.getTitle());
		board.changeContent(dto.getContent());
		
		//수정 저장
		boardRepo.save(board);
	}

	

}
