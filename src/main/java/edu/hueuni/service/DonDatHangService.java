package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.DonDatHang;
import edu.hueuni.repository.DonDatHangRepository;

@Service
public class DonDatHangService {
	@Autowired
	private DonDatHangRepository donDatHangRepository;
	
	public void save(DonDatHang donDatHang) {
		donDatHangRepository.save(donDatHang);
	}
	public List<DonDatHang> findAll() {
		return donDatHangRepository.findAll();
	}
	public List<DonDatHang> findByNoiGiaoHang(String noiGiaoHang) {
		return donDatHangRepository.findByNoiGiaoHang(noiGiaoHang);
	}
}
