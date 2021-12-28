package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		
		//작가 데이터 가져오기
		
		//작가 데이터 저장할 list 생성
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		/*오라클에 저장된 작가 데이터 select로 전부 꺼내오기. 데이터를 가져올 때 덩어리 상태로 가져오기 때문에
		 java의 jdbc class의 ResultSet이 우리가 원하는 형태로 1차 가공해준다.*/
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

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
			query += " select author_id as id, ";//컬럼명에 별명을 붙여준 경우
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from author ";
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 --> 생략 (? 기호 없음)
			
			//실행
			rs = pstmt.executeQuery();//select는 Query! Update가 아님. 주의.
			
		    // 4.결과처리
			while(rs.next()) {//next():동작 실행 후 cusor(시선)가 다음 항목으로 옮겨감
				int authorId = rs.getInt("id");//해당 컬럼명에 별명을 지어줬을 경우 그 별명으로만 호출 가능.
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				/*
				int authorId = rs.getInt(1); //컬럼 순서로 컬럼 호출
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				*/
				
				//작가 데이터를 Author class에 저장
				AuthorVo vo = new AuthorVo(authorId,authorName,authorDesc);
				authorList.add(vo); //authorList에 작가 데이터 저장
				
				//데이터 셀프 2차 가공
				//System.out.println(authorId+"  "+authorName+"  "+authorDesc);
			}
			
			
			//authorList에 저장된 작가 데이터 출력
			for(int i = 0; i<authorList.size();i++) {
				AuthorVo authorVo = authorList.get(i);
				System.out.println(authorVo.getAuthorId()+" , "+authorVo.getAuthorName()+" , "+authorVo.getAuthorDesc());
			}
			
			//첫 번째 작가 재출력
			AuthorVo authorVo = authorList.get(0);//리스트 안 0번째 작가 데이터를 호출하기 위해 이름 정의
			System.out.println(authorVo.getAuthorName());
			
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
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
