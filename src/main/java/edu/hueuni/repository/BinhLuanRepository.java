package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.BinhLuan;
@Repository
public interface BinhLuanRepository extends CrudRepository<BinhLuan, Integer>{

}
