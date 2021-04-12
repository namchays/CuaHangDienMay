package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the don_dat_hang database table.
 * 
 */
@Entity
@Table(name="don_dat_hang")
@NamedQuery(name="DonDatHang.findAll", query="SELECT d FROM DonDatHang d")
public class DonDatHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="so_hoa_don")
	private int soHoaDon;

	@Temporal(TemporalType.DATE)
	@Column(name="ngay_dat_hang")
	private Date ngayDatHang;

	@Temporal(TemporalType.DATE)
	@Column(name="ngay_giao_hang")
	private Date ngayGiaoHang;

	@Column(name="noi_giao_hang")
	@Nationalized
	private String noiGiaoHang;

	//bi-directional many-to-one association to ChiTietDatHang
	@OneToMany(mappedBy="donDatHang")
	private List<ChiTietDatHang> chiTietDatHangs;

	//bi-directional many-to-one association to KhachHang
	@ManyToOne
	@JoinColumn(name="user_name_khach_hang")
	private KhachHang khachHang;

	//bi-directional many-to-one association to NhanVien
	@ManyToOne
	@JoinColumn(name="user_name_nhan_vien")
	private NhanVien nhanVien;

	public DonDatHang() {
	}

	public int getSoHoaDon() {
		return this.soHoaDon;
	}

	public void setSoHoaDon(int soHoaDon) {
		this.soHoaDon = soHoaDon;
	}

	public Date getNgayDatHang() {
		return this.ngayDatHang;
	}

	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public Date getNgayGiaoHang() {
		return this.ngayGiaoHang;
	}

	public void setNgayGiaoHang(Date ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}

	public String getNoiGiaoHang() {
		return this.noiGiaoHang;
	}

	public void setNoiGiaoHang(String noiGiaoHang) {
		this.noiGiaoHang = noiGiaoHang;
	}

	public List<ChiTietDatHang> getChiTietDatHangs() {
		return this.chiTietDatHangs;
	}

	public void setChiTietDatHangs(List<ChiTietDatHang> chiTietDatHangs) {
		this.chiTietDatHangs = chiTietDatHangs;
	}

	public ChiTietDatHang addChiTietDatHang(ChiTietDatHang chiTietDatHang) {
		getChiTietDatHangs().add(chiTietDatHang);
		chiTietDatHang.setDonDatHang(this);

		return chiTietDatHang;
	}

	public ChiTietDatHang removeChiTietDatHang(ChiTietDatHang chiTietDatHang) {
		getChiTietDatHangs().remove(chiTietDatHang);
		chiTietDatHang.setDonDatHang(null);

		return chiTietDatHang;
	}

	public KhachHang getKhachHang() {
		return this.khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return this.nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

}