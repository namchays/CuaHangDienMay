package edu.hueuni.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;


/**
 * The persistent class for the tra_loi database table.
 * 
 */
@Entity
@Table(name="tra_loi")
@NamedQuery(name="TraLoi.findAll", query="SELECT t FROM TraLoi t")
public class TraLoi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cau_tra_loi")
	private int idCauTraLoi;

	@Column(name="luot_thich")
	private int luotThich;

	@Column(name="noi_dung")
	@Nationalized
	private String noiDung;

	@Column(name="url_image")
	@Nationalized
	private String urlImage;

	@Column(name="user_name",length = 50)
	private String userName;
	
	@Column(name="thoi_gian")
	@Temporal(TemporalType.TIMESTAMP)
	private Date thoiGian;
	//bi-directional many-to-one association to BinhLuan
	@ManyToOne
	@JoinColumn(name="id_binh_luan")
	private BinhLuan binhLuan;

	public TraLoi() {
	}
	

	public TraLoi(int luotThich, String noiDung, String urlImage, String userName) {
		super();
		this.luotThich = luotThich;
		this.noiDung = noiDung;
		this.urlImage = urlImage;
		this.userName = userName;
	}


	public int getIdCauTraLoi() {
		return this.idCauTraLoi;
	}

	public void setIdCauTraLoi(int idCauTraLoi) {
		this.idCauTraLoi = idCauTraLoi;
	}

	public int getLuotThich() {
		return this.luotThich;
	}

	public Date getThoiGian() {
		return thoiGian;
	}


	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
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

	public BinhLuan getBinhLuan() {
		return this.binhLuan;
	}

	public void setBinhLuan(BinhLuan binhLuan) {
		this.binhLuan = binhLuan;
	}

}