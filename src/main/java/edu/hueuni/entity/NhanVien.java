package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the nhan_vien database table.
 * 
 */
@Entity
@Table(name="nhan_vien")
@NamedQuery(name="NhanVien.findAll", query="SELECT n FROM NhanVien n")
public class NhanVien implements Serializable {
	private static final long serialVersionUID = 1L;



	@Id
	@Column(name="user_name",length = 50)
	private String userName;

	@Column(name="dia_chi")
	@Nationalized
	private String diaChi;

	@Column(name="dien_thoai",length = 11)
	@Nationalized
	private String dienThoai;

	@Column(name="gioi_tinh")
	private int gioiTinh;

	@Column(name="luong_co_ban")
	private int luongCoBan;

	@Temporal(TemporalType.DATE)
	@Column(name="ngay_lam_viec")
	private Date ngayLamViec;

	@Temporal(TemporalType.DATE)
	@Column(name="ngay_sinh")
	private Date ngaySinh;

	@Column(length = 50)
	private String password;

	@Column(name="phu_cap")
	private int phuCap;

	@Column(name="ten_nhan_vien")
	@Nationalized
	private String tenNhanVien;
	
	@Column(name="url_avatar")
	@Nationalized
	private String urlAvatar;
	
	//bi-directional many-to-one association to DonDatHang
	@OneToMany(mappedBy="nhanVien")
	private List<DonDatHang> donDatHangs;

	//bi-directional many-to-one association to Quyen
	@ManyToOne
	@JoinColumn(name="id_quyen")
	private Quyen quyen;

	public NhanVien() {
	}
	



	public NhanVien(String userName, String password, String tenNhanVien) {
		super();
		this.userName = userName;
		this.password = password;
		this.tenNhanVien = tenNhanVien;
	}


	public String getUrlAvatar() {
		return urlAvatar;
	}
	
	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
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

	public String getDienThoai() {
		return this.dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public int getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public int getLuongCoBan() {
		return this.luongCoBan;
	}

	public void setLuongCoBan(int luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

	public Date getNgayLamViec() {
		return this.ngayLamViec;
	}

	public void setNgayLamViec(Date ngayLamViec) {
		this.ngayLamViec = ngayLamViec;
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

	public int getPhuCap() {
		return this.phuCap;
	}

	public void setPhuCap(int phuCap) {
		this.phuCap = phuCap;
	}

	public String getTenNhanVien() {
		return this.tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public List<DonDatHang> getDonDatHangs() {
		return this.donDatHangs;
	}

	public void setDonDatHangs(List<DonDatHang> donDatHangs) {
		this.donDatHangs = donDatHangs;
	}

	public DonDatHang addDonDatHang(DonDatHang donDatHang) {
		getDonDatHangs().add(donDatHang);
		donDatHang.setNhanVien(this);

		return donDatHang;
	}

	public DonDatHang removeDonDatHang(DonDatHang donDatHang) {
		getDonDatHangs().remove(donDatHang);
		donDatHang.setNhanVien(null);

		return donDatHang;
	}

	public Quyen getQuyen() {
		return this.quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

}