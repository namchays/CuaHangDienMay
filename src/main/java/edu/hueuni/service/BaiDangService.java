package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.BaiDang;
import edu.hueuni.repository.BaiDangRepository;

@Service
public class BaiDangService {
	@Autowired
	private BaiDangRepository baiDangRepository;
	
	public List<BaiDang> findAll() {
		return baiDangRepository.findAll();
	}
	public void  save(BaiDang baiDang) {
		 baiDangRepository.save(baiDang);
	}
	public List<BaiDang> findByTieuDe(String tieuDe) {
		return baiDangRepository.findByTieuDe(tieuDe);
	}
}
