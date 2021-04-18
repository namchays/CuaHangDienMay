package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.ChiTietMatHang;
import edu.hueuni.entity.MatHang;
@Repository
public interface ChiTietMatHangRepository extends CrudRepository<ChiTietMatHang, Integer> {
	public List<ChiTietMatHang> findAll();
	public void deleteByMatHang(MatHang matHang);
	public List<ChiTietMatHang> findByMatHang(MatHang matHang);
}
