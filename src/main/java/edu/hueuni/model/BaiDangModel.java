package edu.hueuni.model;

public class BaiDangModel {
	private int idBaiDang;
	private String noiDung;
	private String tieuDe;
	private String userName;
	private String urlImg;
	public int getIdBaiDang() {
		return idBaiDang;
	}
	public void setIdBaiDang(int idBaiDang) {
		this.idBaiDang = idBaiDang;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	public BaiDangModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaiDangModel(int idBaiDang, String noiDung, String tieuDe, String userName, String urlImg) {
		super();
		this.idBaiDang = idBaiDang;
		this.noiDung = noiDung;
		this.tieuDe = tieuDe;
		this.userName = userName;
		this.urlImg = urlImg;
	}
	
}
