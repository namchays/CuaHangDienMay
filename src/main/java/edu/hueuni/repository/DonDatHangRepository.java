package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.DonDatHang;
@Repository
public interface DonDatHangRepository extends CrudRepository<DonDatHang, Integer> {
	public List<DonDatHang> findAll();
	public List<DonDatHang> findByNoiGiaoHang(String noiGiaoHang);
	
}	
