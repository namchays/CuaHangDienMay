package edu.hueuni.model;

public class NhomHangModel {

	private	int id;
	private String tenNhomHang;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenNhomHang() {
		return tenNhomHang;
	}
	public void setTenNhomHang(String tenNhomHang) {
		this.tenNhomHang = tenNhomHang;
	}
	public NhomHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhomHangModel(int id, String tenNhomHang) {
		super();
		this.id = id;
		this.tenNhomHang = tenNhomHang;
	}
	
}