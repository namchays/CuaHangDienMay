package edu.hueuni.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;

public class NhanVienModel {
	private String userName;

	private String diaChi;

	private String dienThoai;

	private int gioiTinh;

	private int luongCoBan;

	private String ngayLamViec;

	private String ngaySinh;

	private String password;

	private int phuCap;

	private String tenNhanVien;

	public NhanVienModel() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public int getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public int getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(int luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

	public String getNgayLamViec() {
		return ngayLamViec;
	}

	public void setNgayLamViec(String ngayLamViec) {
		this.ngayLamViec = ngayLamViec;
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

	public int getPhuCap() {
		return phuCap;
	}

	public void setPhuCap(int phuCap) {
		this.phuCap = phuCap;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	

	public NhanVienModel(String userName, String diaChi, String dienThoai, int gioiTinh, int luongCoBan,
			String ngayLamViec, String ngaySinh, String password, int phuCap, String tenNhanVien) {
		super();
		this.userName = userName;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.gioiTinh = gioiTinh;
		this.luongCoBan = luongCoBan;
		this.ngayLamViec = ngayLamViec;
		this.ngaySinh = ngaySinh;
		this.password = password;
		this.phuCap = phuCap;
		this.tenNhanVien = tenNhanVien;
	}

	
	
	
	
	
}
