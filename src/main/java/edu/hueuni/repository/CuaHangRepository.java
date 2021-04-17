package edu.hueuni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.CuaHang;
@Repository
public interface CuaHangRepository extends CrudRepository<CuaHang, Integer> {
	public List<CuaHang> findAll();
	public Optional<CuaHang> findBySoDienThoai(String soDienThoai);


	
}
