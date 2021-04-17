package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.CuaHang;
import edu.hueuni.repository.CuaHangRepository;

@Service
public class CuaHangService {
	@Autowired
	private CuaHangRepository cuaHangRepository;
	
	public List<CuaHang> findAll() {
		return cuaHangRepository.findAll();
	}
	
	public void save(CuaHang cuaHang) {
		cuaHangRepository.save(cuaHang);
	}
	public Optional<CuaHang> findBySoDienThoai(String soDienThoai) {
		return cuaHangRepository.findBySoDienThoai(soDienThoai);
	}
	
	public Optional<CuaHang> findById(int id) {
		return cuaHangRepository.findById(id);
	}
	public void DeleteById(int id) {
		cuaHangRepository.deleteById(id);
	}
	
	
}
