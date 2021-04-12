package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;


/**
 * The persistent class for the chi_tiet_mat_hang database table.
 * 
 */
@Entity
@Table(name="chi_tiet_mat_hang")
@NamedQuery(name="ChiTietMatHang.findAll", query="SELECT c FROM ChiTietMatHang c")
public class ChiTietMatHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ma_chi_tiet")
	private int maChiTiet;

	@Column(name="ten_chi_tiet")
	@Nationalized
	private String tenChiTiet;

	@Column(name="thong_tin_chi_tiet")
	@Nationalized
	private String thongTinChiTiet;

	//bi-directional many-to-one association to MatHang
	@ManyToOne
	@JoinColumn(name="ma_hang")
	private MatHang matHang;

	public ChiTietMatHang() {
	}

	public int getMaChiTiet() {
		return this.maChiTiet;
	}

	public void setMaChiTiet(int maChiTiet) {
		this.maChiTiet = maChiTiet;
	}

	public String getTenChiTiet() {
		return this.tenChiTiet;
	}

	public void setTenChiTiet(String tenChiTiet) {
		this.tenChiTiet = tenChiTiet;
	}

	public String getThongTinChiTiet() {
		return this.thongTinChiTiet;
	}

	public void setThongTinChiTiet(String thongTinChiTiet) {
		this.thongTinChiTiet = thongTinChiTiet;
	}

	public MatHang getMatHang() {
		return this.matHang;
	}

	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}

}