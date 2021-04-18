package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the qua_tang database table.
 * 
 */
@Entity
@Table(name="qua_tang")
@NamedQuery(name="QuaTang.findAll", query="SELECT q FROM QuaTang q")
public class QuaTang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_qua_tang")
	private int idQuaTang;

	private int gia;

//	@Column(name="ma_hang")
//	private int maHang;

	@Column(name="so_luong")
	private int soLuong;

	@Column(name="ten_qua_tang")
	@Nationalized
	private String tenQuaTang;

	@Column(name="url_image")
	@Nationalized
	private String urlImage;

	//bi-directional many-to-many association to MatHang
	@ManyToMany(mappedBy="quaTangs")
	private List<MatHang> matHangs;

	public QuaTang() {
	}
	

	public QuaTang(int gia, int soLuong, String tenQuaTang, String urlImage) {
		super();
		this.gia = gia;
		this.soLuong = soLuong;
		this.tenQuaTang = tenQuaTang;
		this.urlImage = urlImage;
	}


	public int getIdQuaTang() {
		return this.idQuaTang;
	}

	public void setIdQuaTang(int idQuaTang) {
		this.idQuaTang = idQuaTang;
	}

	public int getGia() {
		return this.gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

//	public int getMaHang() {
//		return this.maHang;
//	}
//
//	public void setMaHang(int maHang) {
//		this.maHang = maHang;
//	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTenQuaTang() {
		return this.tenQuaTang;
	}

	public void setTenQuaTang(String tenQuaTang) {
		this.tenQuaTang = tenQuaTang;
	}

	public String getUrlImage() {
		return this.urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<MatHang> getMatHangs() {
		return this.matHangs;
	}

	public void setMatHangs(List<MatHang> matHangs) {
		this.matHangs = matHangs;
	}

}