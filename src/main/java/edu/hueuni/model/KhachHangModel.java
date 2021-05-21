package edu.hueuni.model;

import java.util.Date;

public class KhachHangModel {
	private String userName;
	private String diaChi;
	private boolean enable;
	private int gioiTinh;
	private String ngaySinh;
	private String password;
	private String soDienThoai;
	private String tenKhachHang;
	private String urlAvatar;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public int getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getUrlAvatar() {
		return urlAvatar;
	}
	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}
	public KhachHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhachHangModel(String userName, String diaChi, boolean enable, int gioiTinh, String ngaySinh,
			String password, String soDienThoai, String tenKhachHang, String urlAvatar) {
		super();
		this.userName = userName;
		this.diaChi = diaChi;
		this.enable = enable;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.password = password;
		this.soDienThoai = soDienThoai;
		this.tenKhachHang = tenKhachHang;
		this.urlAvatar = urlAvatar;
	}
	
	
}
