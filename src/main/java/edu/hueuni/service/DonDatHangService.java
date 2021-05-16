package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.repository.DonDatHangRepository;

@Service
public class DonDatHangService {
	@Autowired
	private DonDatHangRepository donDatHangRepository;
	
	@Autowired
	private ChiTietDatHangService chiTietDatHangService;
	
	public void save(DonDatHang donDatHang) {
		donDatHangRepository.save(donDatHang);
	}
	public List<DonDatHang> findAll() {
		return donDatHangRepository.findAll();
	}
	public List<DonDatHang> findByNoiGiaoHang(String noiGiaoHang) {
		return donDatHangRepository.findByNoiGiaoHang(noiGiaoHang);
	}
	public List<DonDatHang> findByNhanVien(NhanVien nhanVien) {
		return donDatHangRepository.findByNhanVien(nhanVien);
	}
	public List<DonDatHang> findByKhachHang(KhachHang khachHang) {
		return donDatHangRepository.findByKhachHang(khachHang);
	}
	public DonDatHang findById(int id) {
		Optional<DonDatHang> donHangFound = donDatHangRepository.findById(id);
		if(donHangFound.isPresent()) {
			return donHangFound.get();
		}
		return null;
	}
	@Transactional
	public void deletById(int id) {
		DonDatHang donDatHang = this.findById(id);
		chiTietDatHangService.deleteByDonDatHang(donDatHang);
		donDatHangRepository.deleteById(id);
		
	}
}
