package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConnectionMgr;

public class Main {

	public static void main(String[] args) {
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// Java와 DB를 연결함.
			con = pool.getConnection();
			
			// 실행할 쿼리문 작성
			String sql = "select * from user_tb";
			
			// 작성한 쿼리문을 가공
			pstmt = con.prepareStatement(sql);
			
			// 가공한 쿼리문을 실행 -> 결과를 ResultSet으로 변환
			rs = pstmt.executeQuery();
			
			// 결과가 담긴 ResultSet을 반복 작업을 통해 데이터 조회
			System.out.println("번호\t|아이디\t|비밀번호");
			while(rs.next()) {	//한 행씩 가져옴
				// getInt -> 정수
				// getString -> 문자열
				System.out.println(rs.getInt(1) + "\t|" + rs.getString(2) + "\t|" + rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 생성된 rs, pstmt, con 객체 소멸(데이터베이스 연결 해제
			pool.freeConnection(con, pstmt, rs);
		}
		
//===================================================================================================
		
		try {
			// 데이터베이스 연결
			con = pool.getConnection();
			
			// 쿼리문 작성
			String sql = "insert into user_tb values(0, ?, ?)";
			
			// 쿼리문 가공 준비
			pstmt = con.prepareStatement(sql);
			
			// 쿼리문 가공
			pstmt.setString(1, "bbb");
			pstmt.setString(2, "1234");
			
			// 쿼리문 실행
			int successCount = pstmt.executeUpdate();
			System.out.println("\ninsert 성공 횟수: " + successCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
//===================================================================================================
		
		try {
			// Java와 DB를 연결함.
			con = pool.getConnection();
			
			// 실행할 쿼리문 작성
			String sql = "select * from user_tb";
			
			// 작성한 쿼리문을 가공
			pstmt = con.prepareStatement(sql);
			
			// 가공한 쿼리문을 실행 -> 결과를 ResultSet으로 변환
			rs = pstmt.executeQuery();
			
			// 결과가 담긴 ResultSet을 반복 작업을 통해 데이터 조회
			System.out.println("\n번호\t|아이디\t|비밀번호");
			while(rs.next()) {	//한 행씩 가져옴
				// getInt -> 정수
				// getString -> 문자열
				System.out.println(rs.getInt(1) + "\t|" + rs.getString(2) + "\t|" + rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 생성된 rs, pstmt, con 객체 소멸(데이터베이스 연결 해제
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
}
