package com.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepo;
	
	//목록 보기
	@Override
	public List<Board> getBoardList() {
		//findAll() 사용 : select * from board 내장됨
		//return boardRepo.findAll();
		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "seq"));
	}

	//새글 등록
	@Override
	public void insertBoard(Board board) {
		//save() - insert into - values
		boardRepo.save(board);
	}

	//글 상세 보기
	@Override
	public Board getBoard(Long seq) {
		//findById().get() - select * from board where seq=?
		return boardRepo.findById(seq).get();
	}

	//글 삭제
	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
	}

	//글 수정
	@Override
	public void updateBoard(Board board) {			//파라미터에서 커맨드 객체(get/set 작동)
		/*Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());		//입력 폼의 제목을 세팅
		findBoard.setContent(board.getContent());	//입력 폼의 내용을 세팅*/
		
		boardRepo.save(board);					//다시 저장(수정)
	}

	@Transactional	//트랜잭션 처리
	@Override
	public void updateCount(Long seq) {
		boardRepo.updateCount(seq);
	}

}
