package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.ChiTietDatHang;
import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.MatHang;
@Repository
public interface ChiTietDatHangRepository extends CrudRepository<ChiTietDatHang, Integer>{
	public List<ChiTietDatHang> findAll();
	public List<ChiTietDatHang> findByMatHang(MatHang matHang);
	public void deleteByDonDatHang(DonDatHang donDatHang);
}
