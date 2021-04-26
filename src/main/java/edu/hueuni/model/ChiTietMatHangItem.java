package edu.hueuni.model;

public class ChiTietMatHangItem {
	private String tenChiTiet;
	private String noiDungChiTiet;
	public String getTenChiTiet() {
		return tenChiTiet;
	}
	public void setTenChiTiet(String tenChiTiet) {
		this.tenChiTiet = tenChiTiet;
	}
	public String getNoiDungChiTiet() {
		return noiDungChiTiet;
	}
	public void setNoiDungChiTiet(String noiDungChiTiet) {
		this.noiDungChiTiet = noiDungChiTiet;
	}
	public ChiTietMatHangItem(String tenChiTiet, String noiDungChiTiet) {
		super();
		this.tenChiTiet = tenChiTiet;
		this.noiDungChiTiet = noiDungChiTiet;
	}
	public ChiTietMatHangItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
