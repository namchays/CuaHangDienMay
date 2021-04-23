package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the binh_luan database table.
 * 
 */
@Entity
@Table(name="binh_luan")
@NamedQuery(name="BinhLuan.findAll", query="SELECT b FROM BinhLuan b")
public class BinhLuan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_binh_luan")
	private int idBinhLuan;

	@Column(name="luot_thich")
	@Nationalized
	private int luotThich;

	@Column(name="noi_dung")
	@Nationalized
	private String noiDung;

	@Column(name="url_image")
	@Nationalized
	private String urlImage;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to BaiDang
	@ManyToOne
	@JoinColumn(name="id_bai_dang")
	private BaiDang baiDang;

	//bi-directional many-to-one association to TraLoi
	@OneToMany(mappedBy="binhLuan")
	private List<TraLoi> traLois;

	public BinhLuan() {
	}
	
	public BinhLuan(int luotThich, String noiDung, String userName, BaiDang baiDang) {
		super();
		this.luotThich = luotThich;
		this.noiDung = noiDung;
		this.userName = userName;
		this.baiDang = baiDang;
	}

	public int getIdBinhLuan() {
		return this.idBinhLuan;
	}

	public void setIdBinhLuan(int idBinhLuan) {
		this.idBinhLuan = idBinhLuan;
	}

	public int getLuotThich() {
		return this.luotThich;
	}

	public void setLuotThich(int luotThich) {
		this.luotThich = luotThich;
	}

	public String getNoiDung() {
		return this.noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getUrlImage() {
		return this.urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BaiDang getBaiDang() {
		return this.baiDang;
	}

	public void setBaiDang(BaiDang baiDang) {
		this.baiDang = baiDang;
	}

	public List<TraLoi> getTraLois() {
		return this.traLois;
	}

	public void setTraLois(List<TraLoi> traLois) {
		this.traLois = traLois;
	}

	public TraLoi addTraLoi(TraLoi traLoi) {
		getTraLois().add(traLoi);
		traLoi.setBinhLuan(this);

		return traLoi;
	}

	public TraLoi removeTraLoi(TraLoi traLoi) {
		getTraLois().remove(traLoi);
		traLoi.setBinhLuan(null);

		return traLoi;
	}

}