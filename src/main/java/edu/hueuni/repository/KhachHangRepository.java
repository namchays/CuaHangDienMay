package edu.hueuni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.KhachHang;
@Repository
public interface KhachHangRepository extends CrudRepository<KhachHang, String>{
	public Optional<KhachHang> findByUserNameAndPassword(String userName, String password);
	public Optional<KhachHang> findByUserName(String userName);
	public List<KhachHang> findAll();
	}
