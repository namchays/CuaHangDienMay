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

			String password = khachHang.getPassword();
			String encryptedPassword = nhanVienService.md5("hueunisalt", password);
			khachHang.setPassword(encryptedPassword);
			khachHangRepository.save(khachHang);
		
		
	}
	public Optional<KhachHang> findByUserNameAndPassword(String userName, String password){
		return khachHangRepository.findByUserNameAndPassword(userName, password);
	}
	public KhachHang findByUserName(String userName){
		
		 Optional<KhachHang> khachHangFound = khachHangRepository.findByUserName(userName);
		 if(khachHangFound.isPresent()) {
			 return khachHangFound.get();
		 }
		 return null;
	}
	public List<KhachHang> findAll() {
		return khachHangRepository.findAll();
	}
}
