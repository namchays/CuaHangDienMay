package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the khach_hang database table.
 * 
 */
@Entity
@Table(name="khach_hang")
@NamedQuery(name="KhachHang.findAll", query="SELECT k FROM KhachHang k")
public class KhachHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_name",length = 50)
	private String userName;

	@Column(name="dia_chi")
	@Nationalized
	private String diaChi;

	private boolean enable;

	@Column(name="gioi_tinh")
	private int gioiTinh;

	@Temporal(TemporalType.DATE)
	@Column(name="ngay_sinh")
	private Date ngaySinh;

	@Column(length = 50)
	private String password;

	@Column(name="so_dien_thoai",length = 13)
	private String soDienThoai;

	@Column(name="ten_khach_hang")
	@Nationalized
	private String tenKhachHang;

	@Column(name="url_avatar")
	@Nationalized
	private String urlAvatar;

	//bi-directional many-to-one association to DonDatHang
	@OneToMany(mappedBy="khachHang")
	private List<DonDatHang> donDatHangs;

	public KhachHang() {
	}
	

	public KhachHang(String userName, String password,boolean enable) {
		super();
		this.userName = userName;
		this.password = password;
		this.enable = enable;
	}
	


	public KhachHang(String userName, String diaChi, int gioiTinh, Date ngaySinh, String password,
			String soDienThoai, String tenKhachHang) {
		super();
		this.userName = userName;
		this.diaChi = diaChi;
		this.enable = true;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.password = password;
		this.soDienThoai = soDienThoai;
		this.tenKhachHang = tenKhachHang;
	}


	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public boolean getEnable() {
		return this.enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return this.ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSoDienThoai() {
		return this.soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getTenKhachHang() {
		return this.tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getUrlAvatar() {
		return this.urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}

	public List<DonDatHang> getDonDatHangs() {
		return this.donDatHangs;
	}

	public void setDonDatHangs(List<DonDatHang> donDatHangs) {
		this.donDatHangs = donDatHangs;
	}

	public DonDatHang addDonDatHang(DonDatHang donDatHang) {
		getDonDatHangs().add(donDatHang);
		donDatHang.setKhachHang(this);

		return donDatHang;
	}

	public DonDatHang removeDonDatHang(DonDatHang donDatHang) {
		getDonDatHangs().remove(donDatHang);
		donDatHang.setKhachHang(null);

		return donDatHang;
	}



}