package edu.hueuni.model;

public class GioHangItem {
	private int maHang;
	private String tenHang;
	private int soLuong;
	private int gia;
	private int thanhTien;
	private String imgUrl;
	
	
	public GioHangItem(int maHang, String tenHang, int soLuong, int gia, String imgUrl) {
		super();
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.soLuong = soLuong;
		this.gia = gia;
		this.thanhTien = soLuong * gia;
		this.imgUrl = imgUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getMaHang() {
		return maHang;
	}
	public void setMaHang(int maHang) {
		this.maHang = maHang;
	}
	public String getTenHang() {
		return tenHang;
	}
	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		
		this.soLuong = soLuong;
		this.thanhTien = soLuong*gia;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public int getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public GioHangItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
