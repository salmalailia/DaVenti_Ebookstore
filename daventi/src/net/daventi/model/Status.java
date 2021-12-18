package net.daventi.model;

public class Status {
	private String id_status;
	private String name_status;
	
	public Status(String id_status, String name_status) {
		super();
		this.id_status = id_status;
		this.name_status = name_status;
	}
	public Status(String name_status) {
		super();
		this.name_status = name_status;
	}
	public Status() {
		super();
	}
	public String getId_status() {
		return id_status;
	}
	public void setId_status(String id_status) {
		this.id_status = id_status;
	}
	public String getName_status() {
		return name_status;
	}
	public void setName_status(String name_status) {
		this.name_status = name_status;
	}
	
	
}
