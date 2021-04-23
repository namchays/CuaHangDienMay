package edu.hueuni.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import java.util.List;


/**
 * The persistent class for the quyen database table.
 * 
 */
@Entity
@Table(name="quyen")
@NamedQuery(name="Quyen.findAll", query="SELECT q FROM Quyen q")
public class Quyen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_quyen")
	private int idQuyen;

	@Column(name="ten_quyen",length = 50)
	@Nationalized
	private String tenQuyen;

	//bi-directional many-to-one association to NhanVien
	@OneToMany(mappedBy="quyen")
	private List<NhanVien> nhanViens;

	public Quyen() {
	}
	

	public Quyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}


	public int getIdQuyen() {
		return this.idQuyen;
	}

	public void setIdQuyen(int idQuyen) {
		this.idQuyen = idQuyen;
	}

	public String getTenQuyen() {
		return this.tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	public List<NhanVien> getNhanViens() {
		return this.nhanViens;
	}

	public void setNhanViens(List<NhanVien> nhanViens) {
		this.nhanViens = nhanViens;
	}

	public NhanVien addNhanVien(NhanVien nhanVien) {
		getNhanViens().add(nhanVien);
		nhanVien.setQuyen(this);

		return nhanVien;
	}

	public NhanVien removeNhanVien(NhanVien nhanVien) {
		getNhanViens().remove(nhanVien);
		nhanVien.setQuyen(null);

		return nhanVien;
	}

}