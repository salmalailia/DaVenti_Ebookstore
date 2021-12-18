package net.daventi.model;

public class Method {
	private String id_method;
	private String name_method;
	private String dest_bank;
	
	public Method(String id_method, String name_method, String dest_bank) {
		super();
		this.id_method = id_method;
		this.name_method = name_method;
		this.dest_bank = dest_bank;
	}

	public Method(String name_method, String dest_bank) {
		super();
		this.name_method = name_method;
		this.dest_bank = dest_bank;
	}

	public Method() {
		super();
	}

	public String getId_method() {
		return id_method;
	}

	public void setId_method(String id_method) {
		this.id_method = id_method;
	}

	public String getName_method() {
		return name_method;
	}

	public void setName_method(String name_method) {
		this.name_method = name_method;
	}

	public String getDest_bank() {
		return dest_bank;
	}

	public void setDest_bank(String dest_bank) {
		this.dest_bank = dest_bank;
	}
}
