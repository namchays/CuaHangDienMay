package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;


/**
 * The persistent class for the anh database table.
 * 
 */
@Entity
@Table(name="anh")
@NamedQuery(name="Anh.findAll", query="SELECT a FROM Anh a")
public class Anh implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_anh")
	private int idAnh;

	@Nationalized
	private String url;

	//bi-directional many-to-one association to BaiDang
	@ManyToOne
	@JoinColumn(name="id_bai_dang")
	private BaiDang baiDang;

	public Anh() {
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

	public BaiDang getBaiDang() {
		return this.baiDang;
	}

	public void setBaiDang(BaiDang baiDang) {
		this.baiDang = baiDang;
	}

}