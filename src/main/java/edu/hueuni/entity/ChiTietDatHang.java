package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chi_tiet_dat_hang database table.
 * 
 */
@Entity
@Table(name="chi_tiet_dat_hang")
@NamedQuery(name="ChiTietDatHang.findAll", query="SELECT c FROM ChiTietDatHang c")
public class ChiTietDatHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_chi_tiet_dat_hang")
	private int idChiTietDatHang;

	@Column(name="gia_ban")
	private int giaBan;

	@Column(name="muc_giam_gia")
	private int mucGiamGia;

	@Column(name="so_luong")
	private int soLuong;


	//bi-directional many-to-one association to DonDatHang
	@ManyToOne
	@JoinColumn(name="so_hoa_don")
	private DonDatHang donDatHang;

	//bi-directional many-to-one association to MatHang
	@ManyToOne
	@JoinColumn(name="ma_hang")
	private MatHang matHang;

	public ChiTietDatHang() {
	}

	public ChiTietDatHang(int giaBan, int mucGiamGia, int soLuong) {
		super();
		this.giaBan = giaBan;
		this.mucGiamGia = mucGiamGia;
		this.soLuong = soLuong;

	}

	public int getIdChiTietDatHang() {
		return this.idChiTietDatHang;
	}

	public void setIdChiTietDatHang(int idChiTietDatHang) {
		this.idChiTietDatHang = idChiTietDatHang;
	}

	public int getGiaBan() {
		return this.giaBan;
	}

	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}

	public int getMucGiamGia() {
		return this.mucGiamGia;
	}

	public void setMucGiamGia(int mucGiamGia) {
		this.mucGiamGia = mucGiamGia;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}



	public DonDatHang getDonDatHang() {
		return this.donDatHang;
	}

	public void setDonDatHang(DonDatHang donDatHang) {
		this.donDatHang = donDatHang;
	}

	public MatHang getMatHang() {
		return this.matHang;
	}

	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}

}