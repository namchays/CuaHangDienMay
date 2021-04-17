package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.QuaTang;
import edu.hueuni.repository.QuaTangRepository;

@Service
public class QuaTangService {
	@Autowired
	private QuaTangRepository quaTangRepository;
	
	public List<QuaTang> findAll() {
		return quaTangRepository.findAll();
	}
	public void save(QuaTang quaTang) {
		quaTangRepository.save(quaTang);
	}
	public Optional<QuaTang> findByTenQuaTang(String tenQuaTang) {
		return quaTangRepository.findByTenQuaTang(tenQuaTang);
	}
	
}
