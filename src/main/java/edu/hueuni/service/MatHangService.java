package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.ChiTietMatHang;
import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.repository.AnhMatHangRepository;
import edu.hueuni.repository.ChiTietMatHangRepository;
import edu.hueuni.repository.CuaHangRepository;
import edu.hueuni.repository.MatHangRepository;


@Service
public class MatHangService {
	@Autowired 
	private MatHangRepository matHangRepository;
	@Autowired 
	private CuaHangRepository cuaHangRepository;
	@Autowired
	private ChiTietMatHangRepository chiTietMatHangRepository;
	@Autowired
	private AnhMatHangRepository anhMatHangRepository;
	
	public void save(MatHang matHang) {
		matHangRepository.save(matHang);
	}
	public List<MatHang> findByNhomHang(NhomHang nhomHang) {
		return matHangRepository.findByNhomHang(nhomHang);
	}
	
	public Optional<MatHang> findById(int id) {
		return matHangRepository.findById(id);
	}
	
	
	public List<MatHang> findAll() {
		return matHangRepository.findAll();
	}
	
	public void deleteById(int id) {
		List<CuaHang> listCuaHang = cuaHangRepository.findAll();
		for (CuaHang cuaHang : listCuaHang) {
			List<MatHang> listMatHang = cuaHang.getMatHangs();
			// nếu trong danh sách cửa hàng này có mặt hàng kia thì remove
			for (MatHang matHang : listMatHang) {
				if(matHang.getMaHang() == id) {
					listMatHang.remove(matHang);
					break;
				}
			}
			cuaHang.setMatHangs(listMatHang);
			cuaHangRepository.save(cuaHang);
		}
		
		
		
		Optional<MatHang> matHangFound = matHangRepository.findById(id);
		if(matHangFound.isPresent()) {
			///Xóa chi tiêt mặt hàng
			List<ChiTietMatHang> listChiTietMatHang = chiTietMatHangRepository.findByMatHang(matHangFound.get());
			if(listChiTietMatHang!=null) {
				if(listChiTietMatHang.size()>0) {
					listChiTietMatHang.forEach(x->{
						chiTietMatHangRepository.deleteById(x.getMaChiTiet());
					});
				}
			}
			
			//xóa ảnh mặt hàng
			List<AnhMatHang> listAnhMatHang = anhMatHangRepository.findByMatHang(matHangFound.get());
			if(listAnhMatHang!=null) {
				if(listAnhMatHang.size()>0) {
					listAnhMatHang.forEach(x->{
						anhMatHangRepository.deleteById(x.getIdAnh());
					});
				}
			}
		}
		
		
		matHangRepository.deleteById(id);
	}
	public List<MatHang> findByTenHang(String tenHang) {
		return matHangRepository.findByTenHang(tenHang);
	}
}
