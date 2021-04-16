package edu.hueuni.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.LoaiHang;
@Repository
public interface LoaiHangRepository extends CrudRepository<LoaiHang, Integer>{
	public Optional<LoaiHang> findByTenLoaiHang(String tenLoaiHang);
}
