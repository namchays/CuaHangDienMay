package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.repository.AnhMatHangRepository;

@Service
public class AnhMatHangService {
	@Autowired 
	private AnhMatHangRepository anhMatHangRepository;
	
	public void save(AnhMatHang anhMatHang) {
		anhMatHangRepository.save(anhMatHang);
	}
	public List<AnhMatHang> findAll() {
		return anhMatHangRepository.findAll();
	}
	public void deleteByMatHang(MatHang matHang) {
		anhMatHangRepository.deleteByMatHang(matHang);
	}
	public List<AnhMatHang> findByUrl(String url) {
		return anhMatHangRepository.findByUrl(url);
	}
	
}
