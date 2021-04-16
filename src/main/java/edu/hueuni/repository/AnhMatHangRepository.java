package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.AnhMatHang;
@Repository
public interface AnhMatHangRepository extends CrudRepository<AnhMatHang, Integer>{

}
