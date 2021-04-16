package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.TraLoi;
@Repository
public interface TraLoiRepository extends CrudRepository<TraLoi, Integer>{

}
