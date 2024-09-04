package org.comstudy.myweb.model;

public class Memo {
	/*
	CREATE TABLE MEMO(
		ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
		TITLE VARCHAR(50) NOT NULL,
		CONTENT VARCHAR(50)
	)
	 */
	private int id;
	private String title;
	private String content;
	
	public Memo() {
		this(0, "", "");
	}

	public Memo(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Memo(String title2, String content2) {
		this(0, title2, content2);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Memo [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
}