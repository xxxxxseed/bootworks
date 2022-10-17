package com.boot.service;

import com.boot.entity.Member;

public interface MemberService {

	void signup(Member member);			//회원 가입
	
	Member view(String userid);			//나의 정보
	
	void update(Member member);			//회원 정보 수정
	
	void delete(Member member);			//회원 탈퇴
	
	int checkID(String userid);	//ID 중복 확인
}
