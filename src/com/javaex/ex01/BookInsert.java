package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		//insert문
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb"); //위치, 아이디, 비밀번호
			System.out.println("접속에 성공했습니다.");
							
			
			
			
			// 3. SQL문 준비 / 바인딩 / 실행
							
			//문자열 만들기
			String query = ""; 
			//query = query + "문자열" : 저장할 때 띄어쓰기 오류 방지를 위해 문자열의 양 옆을 띄워준다.
			query += " insert into book "; 
			query += " values(seq_book_id.nextval,?,?,?,?) ";// ? 기호: (데이터가 들어갈 자리)
			System.out.println(query);
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
							
			//바인딩(순서 주의)
			pstmt.setString(1,"우리들의 일그러진 영웅");//첫 번째 ? 자리에 들어올 데이터
			pstmt.setString(2,"다림");//두 번째 ? 자리에 들어올 데이터
			pstmt.setString(3,"1998/02/22");
			pstmt.setInt(4,1);
							
			//실행
			int count = pstmt.executeUpdate();//쿼리문 실행
							
			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.");
			
			
							
			//바인딩부터 반복(jdbc 클래스를 메모리에 올린 채 새 데이터만 전송-기존 자원 이용)
			pstmt.setString(1,"오직 두 사람");//첫 번째 ? 자리에 들어올 데이터
			pstmt.setString(2,"문학동네");//두 번째 ? 자리에 들어올 데이터
			pstmt.setString(3,"2017-05-04");
			pstmt.setInt(4,6);
							
			//실행-이름 중복
			int count2 = pstmt.executeUpdate();//쿼리문 실행
							
			// 4.결과처리
			System.out.println(count2 + " 건이 저장되었습니다.");
			
			
							
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
					   
					// 5. 자원정리: 프로그램 간 상호 작용 관리(연결선)
					try {            
						if (pstmt != null) {
							pstmt.close();
						 	}
						if (conn != null) {
						    conn.close();
						    }
						} catch (SQLException e) {
						    System.out.println("error:" + e);
						}
				}
	}

}
