package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.BaiDang;
import edu.hueuni.entity.BinhLuan;
import edu.hueuni.entity.MatHang;
import edu.hueuni.repository.BaiDangRepository;

@Service
public class BaiDangService {
	@Autowired
	private BaiDangRepository baiDangRepository;
	@Autowired
	private BinhLuanService binhLuanService;
	public List<BaiDang> findAll() {
		return baiDangRepository.findAll();
	}
	public void  save(BaiDang baiDang) {
		 baiDangRepository.save(baiDang);
	}
	public List<BaiDang> findByTieuDe(String tieuDe) {
		return baiDangRepository.findByTieuDe(tieuDe);
	}
	public List<BaiDang> findByMatHang(MatHang matHang) {
		return baiDangRepository.findByMatHang(matHang);
	}
	public void deleteById(int id) {
//		List<BaiDang> listBaiDang = baiDangService.findByMatHang(matHangFound.get());
//		if(listBaiDang!=null) {
//			if(listBaiDang.size()>0) {
//				listBaiDang.forEach(x->{
//					baiDangService.deleteById(x.getIdBaiDang());
//				});
//			}
//		}
		Optional<BaiDang> baiDangFound = baiDangRepository.findById(id);
		if(baiDangFound.isPresent()) {
			List<BinhLuan> listBinhLuan = binhLuanService.findByBaiDang(baiDangFound.get());	
			if(listBinhLuan!=null) {
				if(listBinhLuan.size()>0) {
					listBinhLuan.forEach(x->{
						binhLuanService.deleteById(x.getIdBinhLuan());
					});
				}
			}
		}
		
		
		
		baiDangRepository.deleteById(id);
	}
}
