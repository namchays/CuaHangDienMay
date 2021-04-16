package edu.hueuni.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.repository.CuaHangRepository;
import edu.hueuni.repository.LoaiHangRepository;

@Service
public class LoaiHangService {
	@Autowired
	private LoaiHangRepository loaiHangRepository;
	
	public void save(LoaiHang loaiHang) {
		loaiHangRepository.save(loaiHang);
	}
	public Optional<LoaiHang> findByTenLoaiHang(String tenLoaiHang) {
		return loaiHangRepository.findByTenLoaiHang(tenLoaiHang);
	}
	
}
