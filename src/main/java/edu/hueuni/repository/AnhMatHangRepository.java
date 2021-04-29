package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.MatHang;
@Repository
public interface AnhMatHangRepository extends CrudRepository<AnhMatHang, Integer>{
	public List<AnhMatHang> findAll();
	public List<AnhMatHang> findByMatHang(MatHang matHang);
	public List<AnhMatHang> findByUrl(String url);
	public void deleteByMatHang(MatHang matHang);

}
