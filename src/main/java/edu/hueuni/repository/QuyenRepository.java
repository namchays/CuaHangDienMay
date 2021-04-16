package edu.hueuni.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.Quyen;
@Repository
public interface QuyenRepository extends CrudRepository<Quyen, Integer>{
	Optional<Quyen> findByTenQuyen(String TenQuyen);
}
