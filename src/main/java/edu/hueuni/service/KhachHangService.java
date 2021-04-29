package edu.hueuni.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.KhachHang;
import edu.hueuni.repository.KhachHangRepository;

@Service
public class KhachHangService {
	@Autowired
	private KhachHangRepository khachHangRepository;
	@Autowired
	private NhanVienService nhanVienService;
	
	public void save(KhachHang khachHang) throws NoSuchAlgorithmException {
		Optional<KhachHang> khachHangFound = khachHangRepository.findById(khachHang.getUserName());
		if(khachHangFound.isPresent()) {
			throw new DuplicateKeyException("Username đã tồn tại");
		}else {
			String password = khachHang.getPassword();
			String encryptedPassword = nhanVienService.md5("hueunisalt", password);
			khachHang.setPassword(encryptedPassword);
			khachHangRepository.save(khachHang);
		}
		
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
