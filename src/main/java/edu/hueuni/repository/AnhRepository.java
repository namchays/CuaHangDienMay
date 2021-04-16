package edu.hueuni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.Anh;


@Repository
public interface AnhRepository extends CrudRepository<Anh, Integer> {
	
}