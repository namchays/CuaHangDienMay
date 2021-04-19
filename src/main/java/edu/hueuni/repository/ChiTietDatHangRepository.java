package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.ChiTietDatHang;
@Repository
public interface ChiTietDatHangRepository extends CrudRepository<ChiTietDatHang, Integer>{
	public List<ChiTietDatHang> findAll();
}
