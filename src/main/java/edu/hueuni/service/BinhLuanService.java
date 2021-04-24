package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.BaiDang;
import edu.hueuni.entity.BinhLuan;
import edu.hueuni.entity.TraLoi;
import edu.hueuni.repository.BinhLuanRepository;

@Service
public class BinhLuanService {
	@Autowired
	private BinhLuanRepository binhLuanRepository;
	
	@Autowired
	private TraLoiService traLoiService;
	
	public void save(BinhLuan binhLuan) {
		binhLuanRepository.save(binhLuan);
	}
	
	public List<BinhLuan> findAll() {
		return binhLuanRepository.findAll();
	}
	public List<BinhLuan> findByNoiDung(String noiDung) {
		return binhLuanRepository.findByNoiDung(noiDung);
	}
	public void deleteById(int id) {
		Optional<BinhLuan> BinhLuanFound = binhLuanRepository.findById(id);
		if(BinhLuanFound.isPresent()) {
			List<TraLoi> listTraLoi = traLoiService.findByBinhLuan(BinhLuanFound.get());
			if(listTraLoi!=null) {
				if(listTraLoi.size()>0) {
					listTraLoi.forEach(x->{
						traLoiService.deleteById(x.getIdCauTraLoi());
					});
				}
			}
			
		}
		 
		 binhLuanRepository.deleteById(id);
	}
	public List<BinhLuan> findByBaiDang(BaiDang baiDang) {
		return binhLuanRepository.findByBaiDang(baiDang);
	}
}
