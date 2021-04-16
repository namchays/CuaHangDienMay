package edu.hueuni.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.NhomHang;
@Repository
public interface NhomHangRepository extends CrudRepository<NhomHang, Integer>{
	public Optional<NhomHang> findByTenNhomHang(String tenNhomHang);
}
