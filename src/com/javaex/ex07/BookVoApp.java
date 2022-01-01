package com.javaex.ex07;

import java.util.List;

public class BookVoApp {

	public static void main(String[] args) {
		
		List<BookVo> list;
		BookDao bookDao = new BookDao();
		
		//책 추가
		BookVo vo01 = new BookVo("우리들의 일그러진 영웅","다림","1998-02-22",1);
		bookDao.bookInsert(vo01);
		
		BookVo vo02 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(vo02);
		
		BookVo vo03 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert(vo03);
		
		System.out.println("------------------------------------------------------------");
		list = bookDao.bookSelect();
		for(int i=0;i<list.size();i++) {
			BookVo vo = list.get(i);
			
			System.out.println(vo.getBookId()+" "+vo.getTitle()+" "+vo.getPubs()+" "+vo.getPubDate()+" "+vo.getAuthorId());
		}
		System.out.println("------------------------------------------------------------");
		
		//책 수정
		BookVo bookUp = new BookVo(1, "초한지", "RHK", "2020-11-05", 1);
		bookDao.bookUpdate(bookUp);
		
		System.out.println("------------------------------------------------------------");
		list = bookDao.bookSelect();
		for(int i=0;i<list.size();i++) {
			BookVo vo = list.get(i);
			
			System.out.println(vo.getBookId()+" "+vo.getTitle()+" "+vo.getPubs()+" "+vo.getPubDate()+" "+vo.getAuthorId());
		}
		System.out.println("------------------------------------------------------------");
		
		//책 삭제
		bookDao.bookDelete(1);
		
		System.out.println("------------------------------------------------------------");
		list = bookDao.bookSelect();
		for(int i=0;i<list.size();i++) {
			BookVo vo = list.get(i);
			
			System.out.println(vo.getBookId()+" "+vo.getTitle()+" "+vo.getPubs()+" "+vo.getPubDate()+" "+vo.getAuthorId());
		}
		System.out.println("------------------------------------------------------------");
		
		

		//최종 출력
		list = bookDao.bookAllSelect();
		for(int i=0;i<list.size();i++) {
			BookVo vo = list.get(i);
			
			System.out.println(vo.getBookId() + " " + vo.getTitle() + " " + vo.getPubs() + " " + vo.getPubDate() + " " + vo.getAuthorId() + " " + vo.getAuthorName()
					+ " " + vo.getAuthorDesc());
		}
	}

}
