package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.ChiTietMatHang;
@Repository
public interface ChiTietMatHangRepository extends CrudRepository<ChiTietMatHang, Integer> {

}
