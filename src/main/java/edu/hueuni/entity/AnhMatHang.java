package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;


/**
 * The persistent class for the anh_mat_hang database table.
 * 
 */
@Entity
@Table(name="anh_mat_hang")
@NamedQuery(name="AnhMatHang.findAll", query="SELECT a FROM AnhMatHang a")
public class AnhMatHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_anh")
	private int idAnh;

	@Nationalized
	private String url;

	//bi-directional many-to-one association to MatHang
	@ManyToOne
	@JoinColumn(name="ma_hang")
	private MatHang matHang;

	public AnhMatHang() {
	}

	public int getIdAnh() {
		return this.idAnh;
	}

	public void setIdAnh(int idAnh) {
		this.idAnh = idAnh;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MatHang getMatHang() {
		return this.matHang;
	}

	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}

}