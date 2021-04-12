package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the nhom_hang database table.
 * 
 */
@Entity
@Table(name="nhom_hang")
@NamedQuery(name="NhomHang.findAll", query="SELECT n FROM NhomHang n")
public class NhomHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_nhom_hang")
	private int idNhomHang;

	@Column(name="ten_nhom_hang")
	@Nationalized
	private String tenNhomHang;

	//bi-directional many-to-one association to MatHang
	@OneToMany(mappedBy="nhomHang")
	private List<MatHang> matHangs;

	//bi-directional many-to-one association to LoaiHang
	@ManyToOne
	@JoinColumn(name="id_loai_hang")
	private LoaiHang loaiHang;

	public NhomHang() {
	}

	public int getIdNhomHang() {
		return this.idNhomHang;
	}

	public void setIdNhomHang(int idNhomHang) {
		this.idNhomHang = idNhomHang;
	}

	public String getTenNhomHang() {
		return this.tenNhomHang;
	}

	public void setTenNhomHang(String tenNhomHang) {
		this.tenNhomHang = tenNhomHang;
	}

	public List<MatHang> getMatHangs() {
		return this.matHangs;
	}

	public void setMatHangs(List<MatHang> matHangs) {
		this.matHangs = matHangs;
	}

	public MatHang addMatHang(MatHang matHang) {
		getMatHangs().add(matHang);
		matHang.setNhomHang(this);

		return matHang;
	}

	public MatHang removeMatHang(MatHang matHang) {
		getMatHangs().remove(matHang);
		matHang.setNhomHang(null);

		return matHang;
	}

	public LoaiHang getLoaiHang() {
		return this.loaiHang;
	}

	public void setLoaiHang(LoaiHang loaiHang) {
		this.loaiHang = loaiHang;
	}

}