package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.MatHang;
@Repository
public interface MatHangRepository extends CrudRepository<MatHang, Integer>{

}
