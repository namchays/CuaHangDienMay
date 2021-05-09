package edu.hueuni.model;

import java.util.Date;

public class BinhLuanModel {

	private int idBinhLuan;
	private int luotThich;
	private String noiDung;
	private String urlImage;
	private String userName;
	private String thoiGian;
	

	public String getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}
	public BinhLuanModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BinhLuanModel(int idBinhLuan, int luotThich, String noiDung, String urlImage, String userName) {
		super();
		this.idBinhLuan = idBinhLuan;
		this.luotThich = luotThich;
		this.noiDung = noiDung;
		this.urlImage = urlImage;
		this.userName = userName;
	}
	public int getIdBinhLuan() {
		return idBinhLuan;
	}
	public void setIdBinhLuan(int idBinhLuan) {
		this.idBinhLuan = idBinhLuan;
	}
	public int getLuotThich() {
		return luotThich;
	}
	public void setLuotThich(int luotThich) {
		this.luotThich = luotThich;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
