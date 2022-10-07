package com.boot.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude="boardList")
@Setter
@Getter
@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String userid;
	
	private String password;
	private String name;
	
	@Column(updatable = false,
			columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime regDate;		//가입일
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
	
	//연관 매핑(1 : 다)
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<Board> boardList = new ArrayList<>();
}
