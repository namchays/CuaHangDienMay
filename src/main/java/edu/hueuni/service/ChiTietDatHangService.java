package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.ChiTietDatHang;
import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.repository.ChiTietDatHangRepository;

@Service
public class ChiTietDatHangService {
	@Autowired
	private ChiTietDatHangRepository chiTietDatHangRepository;
	
	public List<ChiTietDatHang> findAll() {
		return chiTietDatHangRepository.findAll();
	}
	public void save(ChiTietDatHang chiTietDatHang) {
		chiTietDatHangRepository.save(chiTietDatHang);
	}
	public List<ChiTietDatHang> findByMatHang(MatHang matHang) {
		return chiTietDatHangRepository.findByMatHang(matHang);
	}
	public void deleteById(int id) {
		chiTietDatHangRepository.deleteById(id);
	}
	public void deleteByDonDatHang(DonDatHang donDatHang) {
		chiTietDatHangRepository.deleteByDonDatHang(donDatHang);
	}
}
