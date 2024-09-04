package org.mybatis.example;

public class Blog {
	private int id;
	private String title;
	private String content;
	
	public Blog() {
		this(0, "", "");
	}

	public Blog(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Blog(String title, String content) {
		this(0, title, content);
	}

	public Blog(int id) {
		this(id, null, null);
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
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
}