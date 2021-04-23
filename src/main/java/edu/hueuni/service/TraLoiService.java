package edu.hueuni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.TraLoi;
import edu.hueuni.repository.TraLoiRepository;

@Service
public class TraLoiService {
	@Autowired
	private TraLoiRepository traLoiRepository;
	
	public void save(TraLoi traLoi) {
		traLoiRepository.save(traLoi);
	}
	public List<TraLoi> findAll() {
		return traLoiRepository.findAll();
	}
	public List<TraLoi> findByNoiDung(String noiDung) {
		return traLoiRepository.findByNoiDung(noiDung);
	}
}
