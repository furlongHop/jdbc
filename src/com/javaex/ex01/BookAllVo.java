package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAllVo {

	public static void main(String[] args) {
		
		//책 데이터 불러온 뒤 리스트에 저장
		
		//book 데이터 저장용 리스트 생성
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;//객체 선언 시 기본값 null
		PreparedStatement pstmt = null;//쿼리를 미리 컴파일하여 값을 나중에 지정하는 방식. 재사용, 동적 쿼리에 유리
		ResultSet rs = null;//select문(결과 처리)

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");			
						
			// 2. Connection 얻어오기(DBMS와 연결<Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";//길이가 길어서 따로 빼준 것
			conn = DriverManager.getConnection(url, "webdb", "webdb");//static에 있는 클래스 메소드 사용

		    // 3. SQL문 준비 / 바인딩 / 실행
			
			//문자열 만들기
			String query = "";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        to_char(pub_date,'YYYY-MM-DD') pub_date, ";
			query += "        author_id ";
			query += " from book ";
			
		    //문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 생략(? 기호 없음)
			
			//실행
			rs = pstmt.executeQuery();//preparedStatement에 있는 메소드(executeQuery) 실행>ResultSet 객체 리턴 
		    
		    // 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				BookVo bookVo = new BookVo(bookId,title,pubs,pubDate,authorId);//BookVo 객체 생성, 생성자 지정
				bookList.add(bookVo);//만들어진 객체 주소를 bookList에 추가 및 저장
			}
			
			//book 데이터 출력
			for(int i = 0; i<bookList.size(); i++) {
				BookVo bookVo = bookList.get(i);
				System.out.println(bookVo.getBookId()+" "+bookVo.getTitle()+" "+bookVo.getPubs()+" "+bookVo.getPubDate()+" "+bookVo.getAuthorId());
				
			}

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
