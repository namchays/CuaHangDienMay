package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.repository.LoaiHangRepository;
import edu.hueuni.repository.NhomHangRepository;

@Service
public class LoaiHangService {
	@Autowired
	private LoaiHangRepository loaiHangRepository;
	@Autowired
	private NhomHangService nhomHangService;
	
	public void save(LoaiHang loaiHang) {
		loaiHangRepository.save(loaiHang);
	}
	public Optional<LoaiHang> findByTenLoaiHang(String tenLoaiHang) {
		return loaiHangRepository.findByTenLoaiHang(tenLoaiHang);
	}
	public List<LoaiHang> findAll() {
		return loaiHangRepository.findAll();
	}
	public Optional<LoaiHang> findById(int id){
		return loaiHangRepository.findById(id);
	}
	public void deleteById(int id) {
		Optional<LoaiHang> LoaiHangFound = loaiHangRepository.findById(id);
		if(LoaiHangFound.isPresent()) {
			List<NhomHang> ListNhomHang = nhomHangService.findByLoaiHang(LoaiHangFound.get());
			if(ListNhomHang!=null) {
				if(ListNhomHang.size()>0) {
					ListNhomHang.forEach(x->{
						nhomHangService.deleteById(x.getIdNhomHang());
					});
				}
			}
		}
		loaiHangRepository.deleteById(id);
	}
	
//	public void deleteByDeThi(DeThi deThi) {
//		List<CauHoi> listCauHoi = cauHoiRepository.findByDeThi(deThi);
//		if(listCauHoi!=null) {
//			if(listCauHoi.size()>0) {
//				listCauHoi.forEach(x->{
//					cauHoiService.deleteCauHoiByIdCauHoi(x.getIdCauHoi());
//				});
//			}
//		}
//		List<ThamGiaThi> listThamGiaThi = thamGiaThiService.findByDeThi(deThi);
//		if(listThamGiaThi!=null) {
//			if(listThamGiaThi.size()>0) {
//				listThamGiaThi.forEach(x->{
//					thamGiaThiService.DeleteById(x.getIdThamGiaThi());
//				});
//			}
//		}
//		
//		deThiRepository.delete(deThi);
//	}
	
	
	
}
