package edu.hueuni.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.ChiTietMatHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.repository.ChiTietMatHangRepository;

@Service
public class ChiTietMatHangService {
	@Autowired
	private ChiTietMatHangRepository chiTietMatHangRepository;
	
	public List<ChiTietMatHang> findAll() {
		return chiTietMatHangRepository.findAll();
	}
	public void save(ChiTietMatHang chiTietMatHang) {
		chiTietMatHangRepository.save(chiTietMatHang);
	}
	
	 @Transactional
	public void deleteByMatHang(MatHang matHang) {
		chiTietMatHangRepository.deleteByMatHang(matHang);
	}
}
