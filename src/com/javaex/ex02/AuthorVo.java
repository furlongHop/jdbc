package com.javaex.ex02;

public class AuthorVo {//~Vo: 담는 그릇
	
	// 필드: 보통 소문자로 시작한다. 단어와 단어 사이는 대문자로 표기한다.(낙타 표기)
	private int authorId; // author_id: 단어와 단어 사이를 _로 구분(스네이크 표기)
	private String authorName;
	private String authorDesc;

	// 생성자
	public AuthorVo() {

	}

	public AuthorVo(int authorId, String authorName, String authorDesc) {
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}

	//메소드 g/s
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	
	//메소드 일반
	@Override
	public String toString() {
		return "AuthorVo [authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}

	
}
