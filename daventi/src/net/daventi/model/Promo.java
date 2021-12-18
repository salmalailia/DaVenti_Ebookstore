package net.daventi.model;

public class Promo {
	private String id_promo;
	private String name_promo;
	private float discount;
	private String start_discount;
	private String end_discount;
	
	public Promo(String id_promo, String name_promo, float discount, String start_discount, String end_discount) {
		super();
		this.id_promo = id_promo;
		this.name_promo = name_promo;
		this.discount = discount;
		this.start_discount = start_discount;
		this.end_discount = end_discount;
	}
	public Promo(String name_promo, float discount, String start_discount, String end_discount) {
		super();
		this.name_promo = name_promo;
		this.discount = discount;
		this.start_discount = start_discount;
		this.end_discount = end_discount;
	}
	public Promo() {
		super();
	}
	public String getId_promo() {
		return id_promo;
	}
	public void setId_promo(String id_promo) {
		this.id_promo = id_promo;
	}
	public String getName_promo() {
		return name_promo;
	}
	public void setName_promo(String name_promo) {
		this.name_promo = name_promo;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getStart_discount() {
		return start_discount;
	}
	public void setStart_discount(String start_discount) {
		this.start_discount = start_discount;
	}
	public String getEnd_discount() {
		return end_discount;
	}
	public void setEnd_discount(String end_discount) {
		this.end_discount = end_discount;
	}
	
	

}
