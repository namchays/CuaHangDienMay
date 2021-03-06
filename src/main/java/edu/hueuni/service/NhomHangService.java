package edu.hueuni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.repository.NhomHangRepository;

@Service
public class NhomHangService {
	@Autowired
	private NhomHangRepository nhomHangRepository;
	@Autowired
	private MatHangService matHangService;
	
	public List<NhomHang> findByTenNhomHang(String tenNhomHang) {
		return nhomHangRepository.findByTenNhomHang(tenNhomHang);
	}
	public void save(NhomHang nhomHang) {
		nhomHangRepository.save(nhomHang);
	}
	public List<NhomHang> findAll(){
		return nhomHangRepository.findAll();
	}
	public void deleteById(int id) {
		Optional<NhomHang> nhomHangFound = nhomHangRepository.findById(id);
		if(nhomHangFound.isPresent()) {
			List<MatHang> listMatHang = matHangService.findByNhomHang(nhomHangFound.get());
			if(listMatHang!=null) {
				if(listMatHang.size()>0) {
					listMatHang.forEach(x->{
						matHangService.deleteById(x.getMaHang());
					});
				}
			}
		}
		nhomHangRepository.deleteById(id);
	}
	public NhomHang findById(int id) {
		Optional<NhomHang> nhomHangFound = nhomHangRepository.findById(id);
		if(nhomHangFound.isPresent()) {
			return nhomHangFound.get();
		}
		return null;
	}
	public List<NhomHang> findByLoaiHang(LoaiHang loaiHang) {
		return nhomHangRepository.findByLoaiHang(loaiHang);
	}
}
