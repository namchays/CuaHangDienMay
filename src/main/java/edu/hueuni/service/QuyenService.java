package edu.hueuni.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.Quyen;
import edu.hueuni.repository.QuyenRepository;

@Service
public class QuyenService {
	@Autowired 
	private QuyenRepository quyenRepository;
	
	public Optional<Quyen> findByTenQuyen(String TenQuyen) {
		return quyenRepository.findByTenQuyen(TenQuyen);
	}
	public void save(Quyen quyen) {
		quyenRepository.save(quyen);
	}
	
}
