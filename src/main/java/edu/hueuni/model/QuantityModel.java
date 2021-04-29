package edu.hueuni.model;

public class QuantityModel {
	private int id;
	private int quantity;
	private int thanhTien;
	private int tongTien;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	public QuantityModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuantityModel(int id, int quantity, int thanhTien, int tongTien) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.thanhTien = thanhTien;
		this.tongTien = tongTien;
	}
	
	
}
