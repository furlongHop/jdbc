//Dao 만들기, AuthorVo 사용하기, 공통 변수 빼기

package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDao {

	// 필드(공통 변수를 묶기 위한 필드)
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자
	public AuthorDao() {// 해당 생성자 이외에 다른 생성자가 없을 경우 생략 가능

	}

	// 메소드 g/s

	// 메소드 일반
	public void authorInsert(AuthorVo authorVo) {//AuthorVo 타입 변수를 받는 메소드

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행

			// 문자열 만들기
			String query = "";
			query += " insert into author ";
			query += " values(seq_author_id.nextval,?,?) ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, authorVo.getAuthorName());//AuthorVo 클래스 타입인 authorVo 안 필드값 authorName 읽어오기
			pstmt.setString(2, authorVo.getAuthorDesc());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.[작가]");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {

				/*
				 * if (rs != null) { rs.close(); }
				 */
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

	public void authorUpdate(AuthorVo authorVo) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id , pw);

			// 3. SQL문 준비 / 바인딩 / 실행

			// 문자열 만들기
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";

			// 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 수정되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) {//select문 rs.close(); }
				 */
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

	public void authorDelete(int index) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id , pw);

			// 3. SQL문 준비 / 바인딩 / 실행

			// 문자열 만들기
			String query = "";
			query += " delete from author ";
			query += "  where author_id = ? ";

			// 문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩(? 자리와 연결)
			pstmt.setInt(1, index);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 삭제되었습니다.");

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
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

	public List<AuthorVo> authorSelect() {//따라가면 List<AuthorVo>가 있다.(주소)
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();//authorSelect 괄호가 닫히면 사라진다.
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id , pw); 

			// 3. SQL문 준비 / 바인딩 / 실행

			// 문자열 만들기
			String query = "";
			query += " select author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from author ";
			query += " order by author_id asc ";//순서가 중요할 경우 정렬하기
			

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩 --> 생략 (? 기호 없음)

			// 실행
			rs = pstmt.executeQuery();// select는 Query! Update가 아님. 주의.

			// 4.결과처리
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				// 작가 데이터를 Author class에 저장
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo); // authorList에 작가 데이터 저장

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

		return authorList;
		
	}

}
