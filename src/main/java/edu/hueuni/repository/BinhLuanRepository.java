package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.BinhLuan;
@Repository
public interface BinhLuanRepository extends CrudRepository<BinhLuan, Integer>{
	public List<BinhLuan> findAll();

	public List<BinhLuan> findByNoiDung(String noiDung);
}
