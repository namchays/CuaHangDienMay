package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.QuaTang;
@Repository
public interface QuaTangRepository extends CrudRepository<QuaTang, Integer> {

}
