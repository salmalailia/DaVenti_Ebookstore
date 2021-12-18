package net.daventi.model;

public class Author {
	private String id_author;
	private String name_author;
	
	public Author(String name_author) {
		super();
		this.name_author = name_author;
	}
	
	public Author(String id_author, String name_author) {
		super();
		this.id_author = id_author;
		this.name_author = name_author;
	}
	
	public Author() {
		super();
	}
	
	public String getId_author() {
		return id_author;
	}
	
	public void setId_author(String id_author) {
		this.id_author = id_author;
	}
	
	public String getName_author() {
		return name_author;
	}
	
	public void setName_author(String name_author) {
		this.name_author = name_author;
	}
}
