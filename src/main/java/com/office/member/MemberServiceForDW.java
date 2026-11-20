package com.office.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberServiceForDW implements IMemberService {

	final private IMemberDao memberDao;
	
	
	public MemberServiceForDW(IMemberDao memberDao) {
		this.memberDao = memberDao;
		
	}

	@Override
	public int signup(HttpServletRequest request, HttpServletResponse response) {
		
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		String memMail = request.getParameter("memMail");
		String memPhone = request.getParameter("memPhone");
		
		MemberDto memberDto = new MemberDto(memId, memPw, memMail, memPhone);
		
		boolean isMember = memberDao.isMember(memId);
		int result = 0;
		if (!isMember) {
			result = memberDao.insertNewMember(memberDto);
		}
		
		
		return result;
		
	}

	@Override
	public MemberDto signin(HttpServletRequest request, HttpServletResponse response) {
		
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		
		MemberDto selectedmemberDto = memberDao.selectMemberByMemId(memId);
		
		if (selectedmemberDto != null) {
			System.out.println("[Memberserbice] selectedmemberDto is not null");
			
			if (selectedmemberDto.getMemPw().equals(memPw)) {
				System.out.println("[Memberserbice] signin SUCCESS!!");
				
				return selectedmemberDto;
				
			} else {
				System.out.println("[Memberserbice] signin FAIL!!");
				
				return null;
				
			}
			
		} else {
			System.out.println("[Memberserbice] selectedmemberDto is null");
			System.out.println("[Memberserbice] signin FAIL!!");
			
			return null;
			
		}
		
		
	}
	
	
	
}