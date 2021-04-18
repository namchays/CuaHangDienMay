package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the cua_hang database table.
 * 
 */
@Entity
@Table(name="cua_hang")
@NamedQuery(name="CuaHang.findAll", query="SELECT c FROM CuaHang c")
public class CuaHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cua_hang")
	private int idCuaHang;

	@Column(name="dia_chi_cua_hang")
	@Nationalized
	private String diaChiCuaHang;
	
	@Column(length = 1000)
	@Nationalized
	private String iframe;

	@Column(name="so_dien_thoai")
	private String soDienThoai;

	@Column(name="ten_cua_hang")
	@Nationalized
	private String tenCuaHang;

	//bi-directional many-to-many association to MatHang
	@ManyToMany
	@JoinTable(
		name="cua_hang_mat_hang"
		, joinColumns={
			@JoinColumn(name="id_cua_hang")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ma_hang")
			}
		)
	private List<MatHang> matHangs;

	public CuaHang() {
	}
	
	public CuaHang(String diaChiCuaHang, String iframe, String soDienThoai, String tenCuaHang) {
		super();
		this.diaChiCuaHang = diaChiCuaHang;
		this.iframe = iframe;
		this.soDienThoai = soDienThoai;
		this.tenCuaHang = tenCuaHang;
	}

	public int getIdCuaHang() {
		return this.idCuaHang;
	}

	public void setIdCuaHang(int idCuaHang) {
		this.idCuaHang = idCuaHang;
	}

	public String getDiaChiCuaHang() {
		return this.diaChiCuaHang;
	}

	public void setDiaChiCuaHang(String diaChiCuaHang) {
		this.diaChiCuaHang = diaChiCuaHang;
	}

	public String getIframe() {
		return this.iframe;
	}

	public void setIframe(String iframe) {
		this.iframe = iframe;
	}

	public String getSoDienThoai() {
		return this.soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getTenCuaHang() {
		return this.tenCuaHang;
	}

	public void setTenCuaHang(String tenCuaHang) {
		this.tenCuaHang = tenCuaHang;
	}

	public List<MatHang> getMatHangs() {
		return this.matHangs;
	}

	public void setMatHangs(List<MatHang> matHangs) {
		this.matHangs = matHangs;
	}
	

}