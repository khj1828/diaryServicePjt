package com.office.member;

public interface IMemberDao {
	//회원가입
	public boolean isMember(String memId);
	
	public int insertNewMember(MemberDto memberDto);
	
	//로그인
	public MemberDto selectMemberByMemId(String memId);

}
