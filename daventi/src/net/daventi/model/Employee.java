package net.daventi.model;

public class Employee {
	private String id_employee;
	private String name_employee;
	private String email_employee;
	private String password_employee;
	private String gender;
	private String phone_num_employee;
	private String role;
	
	public String getId_employee() {
		return id_employee;
	}
	public void setId_employee(String id_employee) {
		this.id_employee = id_employee;
	}
	public String getName_employee() {
		return name_employee;
	}
	public void setName_employee(String name_employee) {
		this.name_employee = name_employee;
	}
	public String getEmail_employee() {
		return email_employee;
	}
	public void setEmail_employee(String email_employee) {
		this.email_employee = email_employee;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone_num_employee() {
		return phone_num_employee;
	}
	public void setPhone_num_employee(String phone_num_employee) {
		this.phone_num_employee = phone_num_employee;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword_employee() {
		return password_employee;
	}
	public void setPassword_employee(String password_employee) {
		this.password_employee = password_employee;
	}
	
	public static void main(String[] args) {
		int a = 1;
		String test = "EMP00";
		String idemp = test + a;
		System.out.println(idemp);
	}
}
