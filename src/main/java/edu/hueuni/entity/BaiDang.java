package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the bai_dang database table.
 * 
 */
@Entity
@Table(name="bai_dang")
@NamedQuery(name="BaiDang.findAll", query="SELECT b FROM BaiDang b")
public class BaiDang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bai_dang")
	private int idBaiDang;

	@Column(name="noi_dung",length = 5000)
	@Nationalized
	private String noiDung;

	@Column(name="tieu_de",length = 500)
	@Nationalized
	private String tieuDe;

	@Column(name="user_name",length = 50)
	private String userName;
	
	@Column(name="url_img")
	@Nationalized
	private String urlImg;

	//bi-directional many-to-one association to Anh

	//bi-directional many-to-one association to MatHang
	@ManyToOne
	@JoinColumn(name="ma_hang")
	private MatHang matHang;

	//bi-directional many-to-one association to BinhLuan
	@OneToMany(mappedBy="baiDang")
	private List<BinhLuan> binhLuans;

	public BaiDang() {
	}
	

	public BaiDang(String noiDung, String tieuDe, String userName, MatHang matHang) {
		super();
		this.noiDung = noiDung;
		this.tieuDe = tieuDe;
		this.userName = userName;
		this.matHang = matHang;
	}


	public int getIdBaiDang() {
		return this.idBaiDang;
	}

	public void setIdBaiDang(int idBaiDang) {
		this.idBaiDang = idBaiDang;
	}

	public String getNoiDung() {
		return this.noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getTieuDe() {
		return this.tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public MatHang getMatHang() {
		return this.matHang;
	}

	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}

	public List<BinhLuan> getBinhLuans() {
		return this.binhLuans;
	}

	public void setBinhLuans(List<BinhLuan> binhLuans) {
		this.binhLuans = binhLuans;
	}

	public BinhLuan addBinhLuan(BinhLuan binhLuan) {
		getBinhLuans().add(binhLuan);
		binhLuan.setBaiDang(this);

		return binhLuan;
	}

	public BinhLuan removeBinhLuan(BinhLuan binhLuan) {
		getBinhLuans().remove(binhLuan);
		binhLuan.setBaiDang(null);

		return binhLuan;
	}

}