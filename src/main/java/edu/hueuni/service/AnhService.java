package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.Anh;
import edu.hueuni.repository.AnhRepository;

@Service
public class AnhService {
	@Autowired
	private AnhRepository anhRepository;
	
	public void save(Anh anh) {
		anhRepository.save(anh);
	}
	public List<Anh> findAll() {
		return anhRepository.findAll();
	}
}
