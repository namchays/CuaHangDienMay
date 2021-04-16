package edu.hueuni.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.NhanVien;
import edu.hueuni.repository.NhanVienRepository;
@Service
public class NhanVienService {
	@Autowired 
	private NhanVienRepository nhanVienRepository;
	
	public void save(NhanVien nhanVien) {
		nhanVienRepository.save(nhanVien);
	}
	public Optional<NhanVien> findByUserName(String userName) {
		return nhanVienRepository.findByUserName(userName);
	}
	public Optional<NhanVien> findByUserNameAndPassword(String userName, String password){
		return nhanVienRepository.findByUserNameAndPassword(userName, password);
	}
}
