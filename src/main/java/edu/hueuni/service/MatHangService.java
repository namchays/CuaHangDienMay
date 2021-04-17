package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.MatHang;
import edu.hueuni.repository.MatHangRepository;


@Service
public class MatHangService {
	@Autowired 
	private MatHangRepository matHangRepository;
	
	public void save(MatHang matHang) {
		matHangRepository.save(matHang);
	}
	
	public List<MatHang> findAll() {
		return matHangRepository.findAll();
	}
}
