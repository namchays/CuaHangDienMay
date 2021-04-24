package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.BaiDang;
import edu.hueuni.entity.MatHang;

@Repository
public interface BaiDangRepository extends CrudRepository<BaiDang, Integer>{
	public List<BaiDang> findAll();
	public List<BaiDang> findByTieuDe(String tieuDe);
	public List<BaiDang> findByMatHang(MatHang matHang);
}	
