package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.KhachHang;
import edu.hueuni.repository.KhachHangRepository;

@Service
public class KhachHangService {
	@Autowired
	private KhachHangRepository khachHangRepository;
	
	public void save(KhachHang khachHang) {
		khachHangRepository.save(khachHang);
	}
	public Optional<KhachHang> findByUserNameAndPassword(String userName, String password){
		return khachHangRepository.findByUserNameAndPassword(userName, password);
	}
	public Optional<KhachHang> findByUserName(String userName){
		return khachHangRepository.findByUserName(userName);
	}
	public List<KhachHang> findAll() {
		return khachHangRepository.findAll();
	}
}
