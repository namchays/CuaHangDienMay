package edu.hueuni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.QuaTang;
@Repository
public interface QuaTangRepository extends CrudRepository<QuaTang, Integer> {
	 public List<QuaTang> findAll();
	 public List<QuaTang> findByTenQuaTang(String tenQuaTang);
}
