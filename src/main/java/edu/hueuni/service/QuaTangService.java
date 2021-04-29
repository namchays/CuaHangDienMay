package edu.hueuni.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.QuaTang;
import edu.hueuni.repository.QuaTangRepository;

@Service
public class QuaTangService {
	@Autowired
	private QuaTangRepository quaTangRepository;
	@Autowired
	private MatHangService matHangService;
	public List<QuaTang> findAll() {
		return quaTangRepository.findAll();
	}
	public void save(QuaTang quaTang) {
		quaTangRepository.save(quaTang);
	}
	public List<QuaTang> findByTenQuaTang(String tenQuaTang) {
		return quaTangRepository.findByTenQuaTang(tenQuaTang);
	}
	public Optional<QuaTang> findById(int id) {
		return quaTangRepository.findById(id);
	}
	public void deleteById(int id) {
		Optional<QuaTang> quaTangFound = quaTangRepository.findById(id);
		if(quaTangFound.isPresent()) {
			QuaTang quaTang = quaTangFound.get();
			List<MatHang> listMatHang = quaTangFound.get().getMatHangs();
			for (MatHang matHang : listMatHang) {
				List<QuaTang> listQuaTang = matHang.getQuaTangs();
				for(QuaTang quaTangItem : listQuaTang) {
					if(quaTangItem.getIdQuaTang() == id) {
						listQuaTang.remove(quaTang);
						break;
					}
					matHang.setQuaTangs(listQuaTang);
				}
			
				matHangService.save(matHang);
			}
			quaTangRepository.deleteById(id);	
		}
		
	}
	
}
