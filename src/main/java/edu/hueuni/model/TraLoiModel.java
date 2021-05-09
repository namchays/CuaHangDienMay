package edu.hueuni.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;

public class TraLoiModel {
	private int idCauTraLoi;
	private int luotThich;
	private String noiDung;
	private String urlImage;
	private String userName;
	private Date thoiGian;
	public int getIdCauTraLoi() {
		return idCauTraLoi;
	}
	public void setIdCauTraLoi(int idCauTraLoi) {
		this.idCauTraLoi = idCauTraLoi;
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
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	public TraLoiModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TraLoiModel(int idCauTraLoi, int luotThich, String noiDung, String urlImage, String userName,
			Date thoiGian) {
		super();
		this.idCauTraLoi = idCauTraLoi;
		this.luotThich = luotThich;
		this.noiDung = noiDung;
		this.urlImage = urlImage;
		this.userName = userName;
		this.thoiGian = thoiGian;
	}

	
}
