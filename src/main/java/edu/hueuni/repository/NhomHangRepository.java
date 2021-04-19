package edu.hueuni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.NhomHang;
@Repository
public interface NhomHangRepository extends CrudRepository<NhomHang, Integer>{
	public List<NhomHang> findByTenNhomHang(String tenNhomHang);
	public List<NhomHang> findAll();
	public List<NhomHang> findByLoaiHang(LoaiHang loaiHang);
}
