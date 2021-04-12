package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the loai_hang database table.
 * 
 */
@Entity
@Table(name="loai_hang")
@NamedQuery(name="LoaiHang.findAll", query="SELECT l FROM LoaiHang l")
public class LoaiHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_loai_hang")
	private int idLoaiHang;

	@Column(name="ten_loai_hang")
	@Nationalized
	private String tenLoaiHang;

	//bi-directional many-to-one association to NhomHang
	@OneToMany(mappedBy="loaiHang")
	private List<NhomHang> nhomHangs;

	public LoaiHang() {
	}

	public int getIdLoaiHang() {
		return this.idLoaiHang;
	}

	public void setIdLoaiHang(int idLoaiHang) {
		this.idLoaiHang = idLoaiHang;
	}

	public String getTenLoaiHang() {
		return this.tenLoaiHang;
	}

	public void setTenLoaiHang(String tenLoaiHang) {
		this.tenLoaiHang = tenLoaiHang;
	}

	public List<NhomHang> getNhomHangs() {
		return this.nhomHangs;
	}

	public void setNhomHangs(List<NhomHang> nhomHangs) {
		this.nhomHangs = nhomHangs;
	}

	public NhomHang addNhomHang(NhomHang nhomHang) {
		getNhomHangs().add(nhomHang);
		nhomHang.setLoaiHang(this);

		return nhomHang;
	}

	public NhomHang removeNhomHang(NhomHang nhomHang) {
		getNhomHangs().remove(nhomHang);
		nhomHang.setLoaiHang(null);

		return nhomHang;
	}

}