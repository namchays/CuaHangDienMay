package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.ChiTietDatHang;
@Repository
public interface ChiTietDatHangRepository extends CrudRepository<ChiTietDatHang, Integer>{

}
