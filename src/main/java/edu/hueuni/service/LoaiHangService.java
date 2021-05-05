package edu.hueuni.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.repository.LoaiHangRepository;
import edu.hueuni.repository.NhomHangRepository;

@Service
public class loaiHangService {
	@Autowired
	private LoaiHangRepository loaiHangRepository;
	@Autowired
	private NhomHangService nhomHangService;
	
	public void save(LoaiHang loaiHang) {
		loaiHangRepository.save(loaiHang);
	}
	public List<LoaiHang> findByTenLoaiHang(String tenLoaiHang) {
		return loaiHangRepository.findByTenLoaiHang(tenLoaiHang);
	}
	public List<LoaiHang> findAll() {
		return loaiHangRepository.findAll();
	}
	public LoaiHang findById(int id){
		Optional<LoaiHang> loaiHangFound = loaiHangRepository.findById(id);
		if(loaiHangFound.isPresent()) {
			return loaiHangFound.get();
		}
		return null;
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
	public List<MatHang> getAllMatHangByNhomHang(int id) {
		Optional<LoaiHang> loaiHangFound = loaiHangRepository.findById(id);
		List<MatHang> listMatHang = new ArrayList<MatHang>();
		if(loaiHangFound.isPresent()) {
			LoaiHang loaiHang = loaiHangFound.get();
			List<NhomHang> listNhomHang = loaiHang.getNhomHangs();
			if(listNhomHang!=null) {
				if(listNhomHang.size()>0) {
					for (NhomHang nhomHang : listNhomHang) {
						List<MatHang> matHangs = nhomHang.getMatHangs();
						if(matHangs!=null) {
							if(matHangs.size()>0) {
								matHangs.forEach(x->{
									listMatHang.add(x);
								});
							}
						}
					}
				}
			}
			
		}
		return listMatHang;
	}
	
	
}
