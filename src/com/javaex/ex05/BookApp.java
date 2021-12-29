package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		List<BookVo> list;
		BookDao bookDao = new BookDao();
		
		//책 등록
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert("토지", "마로니에북스", "2012-08-15", 2);
		
		System.out.println("------------------------------------------------------------");
		list = bookDao.bookSelect();
		for(int i=0;i<list.size();i++) {
			BookVo vo = list.get(i);
			
			System.out.println(vo.getBookId()+" "+vo.getTitle()+" "+vo.getPubs()+" "+vo.getPubDate()+" "+vo.getAuthorId());
		}
		System.out.println("------------------------------------------------------------");
		
		
		//책 수정
		bookDao.bookUpdate(1, "초한지", "RHK", "2020-11-05", 1);
		
		System.out.println("------------------------------------------------------------");
		list = bookDao.bookSelect();
		for(int i=0;i<list.size();i++) {
			BookVo vo = list.get(i);
			
			System.out.println(vo.getBookId()+" "+vo.getTitle()+" "+vo.getPubs()+" "+vo.getPubDate()+" "+vo.getAuthorId());
		}
		System.out.println("------------------------------------------------------------");
		
		
		//책 삭제
		bookDao.bookDelete(2);
		
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
