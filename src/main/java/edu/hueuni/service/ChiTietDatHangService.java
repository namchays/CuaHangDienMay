package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.ChiTietDatHang;
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
}
