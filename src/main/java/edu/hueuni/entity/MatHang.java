package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the mat_hang database table.
 * 
 */
@Entity
@Table(name="mat_hang")
@NamedQuery(name="MatHang.findAll", query="SELECT m FROM MatHang m")
public class MatHang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ma_hang")
	private int maHang;

	@Column(name="don_vi_tinh")
	private String donViTinh;

	@Column(name="gia_hang")
	private int giaHang;

	@Temporal(TemporalType.DATE)
	@Column(name="ngay_nhap")
	private Date ngayNhap;

	private int rating;
	private int count_rating;

	@Column(name="so_luong")
	private int soLuong;

	@Column(name="ten_hang")
	@Nationalized
	private String tenHang;

	@Column(name="trang_thai")
	private int trangThai;

	@Column(name="xuat_xu")
	@Nationalized
	private String xuatXu;

	//bi-directional many-to-one association to AnhMatHang
	@OneToMany(mappedBy="matHang")
	private List<AnhMatHang> anhMatHangs;

	//bi-directional many-to-one association to BaiDang
	@OneToMany(mappedBy="matHang")
	private List<BaiDang> baiDangs;

	//bi-directional many-to-one association to ChiTietDatHang
	@OneToMany(mappedBy="matHang")
	private List<ChiTietDatHang> chiTietDatHangs;

	//bi-directional many-to-one association to ChiTietMatHang
	@OneToMany(mappedBy="matHang")
	private List<ChiTietMatHang> chiTietMatHangs;

	//bi-directional many-to-many association to CuaHang
	@ManyToMany(mappedBy="matHangs")
	private List<CuaHang> cuaHangs;

	//bi-directional many-to-one association to NhomHang
	@ManyToOne
	@JoinColumn(name="id_nhom_hang")
	private NhomHang nhomHang;

	//bi-directional many-to-many association to QuaTang
	@ManyToMany
	@JoinTable(
		name="mat_hang_qua_tang"
		, joinColumns={
			@JoinColumn(name="ma_hang")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_qua_tang")
			}
		)
	private List<QuaTang> quaTangs;

	public MatHang() {
	}
	

	public int getCount_rating() {
		return count_rating;
	}


	public void setCount_rating(int count_rating) {
		this.count_rating = count_rating;
	}


	public int getMaHang() {
		return this.maHang;
	}

	public void setMaHang(int maHang) {
		this.maHang = maHang;
	}

	public String getDonViTinh() {
		return this.donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public int getGiaHang() {
		return this.giaHang;
	}

	public void setGiaHang(int giaHang) {
		this.giaHang = giaHang;
	}

	public Date getNgayNhap() {
		return this.ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTenHang() {
		return this.tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getXuatXu() {
		return this.xuatXu;
	}

	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}

	public List<AnhMatHang> getAnhMatHangs() {
		return this.anhMatHangs;
	}

	public void setAnhMatHangs(List<AnhMatHang> anhMatHangs) {
		this.anhMatHangs = anhMatHangs;
	}

	public AnhMatHang addAnhMatHang(AnhMatHang anhMatHang) {
		getAnhMatHangs().add(anhMatHang);
		anhMatHang.setMatHang(this);

		return anhMatHang;
	}

	public AnhMatHang removeAnhMatHang(AnhMatHang anhMatHang) {
		getAnhMatHangs().remove(anhMatHang);
		anhMatHang.setMatHang(null);

		return anhMatHang;
	}

	public List<BaiDang> getBaiDangs() {
		return this.baiDangs;
	}

	public void setBaiDangs(List<BaiDang> baiDangs) {
		this.baiDangs = baiDangs;
	}

	public BaiDang addBaiDang(BaiDang baiDang) {
		getBaiDangs().add(baiDang);
		baiDang.setMatHang(this);

		return baiDang;
	}

	public BaiDang removeBaiDang(BaiDang baiDang) {
		getBaiDangs().remove(baiDang);
		baiDang.setMatHang(null);

		return baiDang;
	}

	public List<ChiTietDatHang> getChiTietDatHangs() {
		return this.chiTietDatHangs;
	}

	public void setChiTietDatHangs(List<ChiTietDatHang> chiTietDatHangs) {
		this.chiTietDatHangs = chiTietDatHangs;
	}

	public ChiTietDatHang addChiTietDatHang(ChiTietDatHang chiTietDatHang) {
		getChiTietDatHangs().add(chiTietDatHang);
		chiTietDatHang.setMatHang(this);

		return chiTietDatHang;
	}

	public ChiTietDatHang removeChiTietDatHang(ChiTietDatHang chiTietDatHang) {
		getChiTietDatHangs().remove(chiTietDatHang);
		chiTietDatHang.setMatHang(null);

		return chiTietDatHang;
	}

	public List<ChiTietMatHang> getChiTietMatHangs() {
		return this.chiTietMatHangs;
	}

	public void setChiTietMatHangs(List<ChiTietMatHang> chiTietMatHangs) {
		this.chiTietMatHangs = chiTietMatHangs;
	}

	public ChiTietMatHang addChiTietMatHang(ChiTietMatHang chiTietMatHang) {
		getChiTietMatHangs().add(chiTietMatHang);
		chiTietMatHang.setMatHang(this);

		return chiTietMatHang;
	}

	public ChiTietMatHang removeChiTietMatHang(ChiTietMatHang chiTietMatHang) {
		getChiTietMatHangs().remove(chiTietMatHang);
		chiTietMatHang.setMatHang(null);

		return chiTietMatHang;
	}

	public List<CuaHang> getCuaHangs() {
		return this.cuaHangs;
	}

	public void setCuaHangs(List<CuaHang> cuaHangs) {
		this.cuaHangs = cuaHangs;
	}

	public NhomHang getNhomHang() {
		return this.nhomHang;
	}

	public void setNhomHang(NhomHang nhomHang) {
		this.nhomHang = nhomHang;
	}

	public List<QuaTang> getQuaTangs() {
		return this.quaTangs;
	}

	public void setQuaTangs(List<QuaTang> quaTangs) {
		this.quaTangs = quaTangs;
	}

}