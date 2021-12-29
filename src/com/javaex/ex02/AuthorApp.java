package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		List<AuthorVo> list;//List 타입의 클래스 선언
		AuthorDao authorDao = new AuthorDao();//AuthorDao 클래스 타입의 객체 생성
		
		//작가 등록
		authorDao.authorInsert("이문열","경북 영양");
		authorDao.authorInsert("박경리","경상남도 통영");
		authorDao.authorInsert("유시민","17대 국회의원");
		
		list = authorDao.authorSelect();//authorSelect() 메소드가 authorList 주소값을 리턴, list와 연결
		for (int i = 0; i < list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(
					vo.getAuthorId() + " , " + vo.getAuthorName() + " , " + vo.getAuthorDesc());
		}
		System.out.println("--------------------------------------------------------------");
		
		

		//작가 수정
		authorDao.authorUpdate(1,"김문열","경상북도 영양");
		
		list = authorDao.authorSelect();//데이터에 변화가 있었으므로 다시 메소드 호출, 변화된 데이터 값을 가진 authorList 주소값 리턴 받음
		for (int i = 0; i < list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(
					vo.getAuthorId() + " , " + vo.getAuthorName() + " , " + vo.getAuthorDesc());
		}
		System.out.println("--------------------------------------------------------------");
		
		
		//작가 삭제
		authorDao.authorDelete(2);
		
		list = authorDao.authorSelect();
		for (int i = 0; i < list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(
					vo.getAuthorId() + " , " + vo.getAuthorName() + " , " + vo.getAuthorDesc());
		}
		
	}

}
