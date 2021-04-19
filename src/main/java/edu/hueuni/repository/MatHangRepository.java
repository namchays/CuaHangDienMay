package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhomHang;
@Repository
public interface MatHangRepository extends CrudRepository<MatHang, Integer>{
	public List<MatHang> findAll();
	public List<MatHang> findByNhomHang(NhomHang nhomHang);
	public List<MatHang> findByTenHang(String tenHang);

}	
