package edu.hueuni.model;

import java.util.Date;



public class MatHangModel {
	private int maHang;
	private String donViTinh;
	private int giaHang;
	private Date ngayNhap;
	private int soLuong;
	private String tenHang;
	private int mucGiamGia;
	private int trangThai;
	private String xuatXu;
	private int idNhomHang;
	private String tenNhomHang;
	private int idLoaiHang;
	private String tenLoaiHang;
	private String urlImg;

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public MatHangModel(int maHang, String donViTinh, int giaHang, Date ngayNhap, int soLuong, String tenHang,
			int mucGiamGia, int trangThai, String xuatXu, int idNhomHang, String tenNhomHang, int idLoaiHang,
			String tenLoaiHang, String urlImg) {
		super();
		this.maHang = maHang;
		this.donViTinh = donViTinh;
		this.giaHang = giaHang;
		this.ngayNhap = ngayNhap;
		this.soLuong = soLuong;
		this.tenHang = tenHang;
		this.mucGiamGia = mucGiamGia;
		this.trangThai = trangThai;
		this.xuatXu = xuatXu;
		this.idNhomHang = idNhomHang;
		this.tenNhomHang = tenNhomHang;
		this.idLoaiHang = idLoaiHang;
		this.tenLoaiHang = tenLoaiHang;
		this.urlImg = urlImg;
	}

	public int getMaHang() {
		return maHang;
	}

	public void setMaHang(int maHang) {
		this.maHang = maHang;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public int getGiaHang() {
		return giaHang;
	}

	public void setGiaHang(int giaHang) {
		this.giaHang = giaHang;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public int getMucGiamGia() {
		return mucGiamGia;
	}

	public void setMucGiamGia(int mucGiamGia) {
		this.mucGiamGia = mucGiamGia;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getXuatXu() {
		return xuatXu;
	}

	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}

	public int getIdNhomHang() {
		return idNhomHang;
	}

	public void setIdNhomHang(int idNhomHang) {
		this.idNhomHang = idNhomHang;
	}

	public int getIdLoaiHang() {
		return idLoaiHang;
	}

	public void setIdLoaiHang(int idLoaiHang) {
		this.idLoaiHang = idLoaiHang;
	}

	public MatHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatHangModel(int maHang, String donViTinh, int giaHang, Date ngayNhap, int soLuong, String tenHang,
			int mucGiamGia, int trangThai, String xuatXu, int idNhomHang, int idLoaiHang) {
		super();
		this.maHang = maHang;
		this.donViTinh = donViTinh;
		this.giaHang = giaHang;
		this.ngayNhap = ngayNhap;
		this.soLuong = soLuong;
		this.tenHang = tenHang;
		this.mucGiamGia = mucGiamGia;
		this.trangThai = trangThai;
		this.xuatXu = xuatXu;
		this.idNhomHang = idNhomHang;
		this.idLoaiHang = idLoaiHang;
	}

	public MatHangModel(int maHang, String donViTinh, int giaHang, Date ngayNhap, int soLuong, String tenHang,
			int mucGiamGia, int trangThai, String xuatXu, int idNhomHang, String tenNhomHang, int idLoaiHang,
			String tenLoaiHang) {
		super();
		this.maHang = maHang;
		this.donViTinh = donViTinh;
		this.giaHang = giaHang;
		this.ngayNhap = ngayNhap;
		this.soLuong = soLuong;
		this.tenHang = tenHang;
		this.mucGiamGia = mucGiamGia;
		this.trangThai = trangThai;
		this.xuatXu = xuatXu;
		this.idNhomHang = idNhomHang;
		this.tenNhomHang = tenNhomHang;
		this.idLoaiHang = idLoaiHang;
		this.tenLoaiHang = tenLoaiHang;
	}

	public String getTenNhomHang() {
		return tenNhomHang;
	}

	public void setTenNhomHang(String tenNhomHang) {
		this.tenNhomHang = tenNhomHang;
	}

	public String getTenLoaiHang() {
		return tenLoaiHang;
	}

	public void setTenLoaiHang(String tenLoaiHang) {
		this.tenLoaiHang = tenLoaiHang;
	}
	
	

}
