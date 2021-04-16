package edu.hueuni.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.NhanVien;
@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, String>{
	public Optional<NhanVien> findByUserName(String UserName);
	public Optional<NhanVien> findByUserNameAndPassword(String userName, String password);
}
