package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.repository.NhomHangRepository;

@Service
public class NhomHangService {
	@Autowired
	private NhomHangRepository nhomHangRepository;
	
	public Optional<NhomHang> findByTenNhomHang(String tenNhomHang) {
		return nhomHangRepository.findByTenNhomHang(tenNhomHang);
	}
	public void save(NhomHang nhomHang) {
		nhomHangRepository.save(nhomHang);
	}
	public List<NhomHang> findAll(){
		return nhomHangRepository.findAll();
	}
	public void deleteById(int id) {
		nhomHangRepository.deleteById(id);
	}
	public Optional<NhomHang> findById(int id) {
		return nhomHangRepository.findById(id);	
	}
	public List<NhomHang> findByLoaiHang(LoaiHang loaiHang) {
		return nhomHangRepository.findByLoaiHang(loaiHang);
	}
}
