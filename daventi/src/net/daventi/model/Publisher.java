package net.daventi.model;

public class Publisher {
	private String id_publisher;
	private String name_publisher;
	private String email_publisher;

	public Publisher(String name_publisher, String email_publisher) {
		super();
		this.name_publisher = name_publisher;
		this.email_publisher = email_publisher;
	}
	public Publisher(String id_publisher, String name_publisher, String email_publisher) {
		super();
		this.id_publisher = id_publisher;
		this.name_publisher = name_publisher;
		this.email_publisher = email_publisher;
	}
	public Publisher() {
		super();
	}
	public String getId_publisher() {
		return id_publisher;
	}
	public void setId_publisher(String id_publisher) {
		this.id_publisher = id_publisher;
	}
	public String getName_publisher() {
		return name_publisher;
	}
	public void setName_publisher(String name_publisher) {
		this.name_publisher = name_publisher;
	}
	public String getEmail_publisher() {
		return email_publisher;
	}
	public void setEmail_publisher(String email_publisher) {
		this.email_publisher = email_publisher;
	}
	
	
}
