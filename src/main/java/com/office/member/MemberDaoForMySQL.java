package com.office.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

public class MemberDaoForMySQL implements IMemberDao{
	
	
	final private String DRIVER = "com.mysql.cj.jdbc.Driver"; 
	
	final private String URL = "jdbc:mysql://127.0.0.1:3306/db_diary";
	final private String USERNAME = "root";
	final private String PASSWORD = "1234";
	

	@Override
	public boolean isMember(String memId) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이버 로드 in 메모리
			Class.forName(DRIVER);
			
			
			// 2. 다리건설 : Connection 생성 by 인증(Authentication)
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//3. SQL 작성 (작업지시서) 쿼리?
			String sql = "select count(*) cnt from tbl_member where memId = ?";
			
			// 4. 일꾼 선택(생성) 일꾼 은 crud 중 하나
			pstmt = conn.prepareStatement(sql);
			
			//5. 데이터주입
			pstmt.setString(1, memId);
			
			//6. 작업지시
			rs = pstmt.executeQuery();
			
			/*
			 * executeQuery(): select, 반환형(ResulSet) -> while문으로 사용하자
			 * executeupdate():insert, update, delete , 무조건 int로 반환 반환형(int) -> 영향을 받는 행의수
			 */
			
			// 7. 가져온 데이터 분석하기
			while (rs.next()) {
				result = rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			//8. 외부자원해제
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
			
		}
		
		
		
		return result == 0 ? false : true;
	}

	@Override
	public int insertNewMember(MemberDto memberDto) {
		
        int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			//1. 드라이버 로드 in 메모리
			Class.forName(DRIVER);
			
			
			// 2. 다리건설 : Connection 생성 by 인증(Authentication)
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//3. SQL 작성 (작업지시서) 쿼리?
			String sql = "insert into tbl_member(memId, memPw, memMail, memPhone values(?, ?, ?, ?)";
			
			// 4. 일꾼 선택(생성) 일꾼 은 crud 중 하나
			pstmt = conn.prepareStatement(sql);
			
			//5. 데이터주입
			pstmt.setString(1, memberDto.getMemId());
			pstmt.setString(2, memberDto.getMemPw());
			pstmt.setString(3, memberDto.getMemMail());
			pstmt.setString(4, memberDto.getMemPhone());
			
			//6. 작업지시
			result = pstmt.executeUpdate();
			
			/*
			 * executeQuery(): select, 반환형(ResulSet) -> while문으로 사용하자
			 * executeupdate():insert, update, delete , 무조건 int로 반환 반환형(int) -> 영향을 받는 행의수
			 */
			
			// 7. 가져온 데이터 분석하기
//			while (rs.next()) {
//				result = rs.getInt("cnt");
//				
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			//8. 외부자원해제
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
			
		}
		
		
		
		return result;
	}

	@Override
	public MemberDto selectMemberByMemId(String memId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
