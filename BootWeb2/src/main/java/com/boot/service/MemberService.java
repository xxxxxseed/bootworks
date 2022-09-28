package com.boot.service;

import java.util.List;

import com.boot.domain.Member;

public interface MemberService {
	//로그인 처리
	Member getMember(Member member);
	
	//회원가입
	void signup(Member member);
	
	//회원 정보
	Member getOne(String id);
	
	//회원 탈퇴
	void delete(Member member);
	
	//회원 수정
	void update(Member member);
}
