package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.DonDatHang;
@Repository
public interface DonDatHangRepository extends CrudRepository<DonDatHang, Integer> {

}
