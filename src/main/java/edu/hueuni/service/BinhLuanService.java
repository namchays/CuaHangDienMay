package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.BinhLuan;
import edu.hueuni.repository.BinhLuanRepository;

@Service
public class BinhLuanService {
	@Autowired
	private BinhLuanRepository binhLuanRepository;
	
	public void save(BinhLuan binhLuan) {
		binhLuanRepository.save(binhLuan);
	}
	
	public List<BinhLuan> findAll() {
		return binhLuanRepository.findAll();
	}
}
