package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.BaiDang;

@Repository
public interface BaiDangRepository extends CrudRepository<BaiDang, Integer>{

}
