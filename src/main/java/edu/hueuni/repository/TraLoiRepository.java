package edu.hueuni.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hueuni.entity.BinhLuan;
import edu.hueuni.entity.TraLoi;
@Repository
public interface TraLoiRepository extends CrudRepository<TraLoi, Integer>{
	public List<TraLoi> findAll();

	public List<TraLoi> findByNoiDung(String noiDung);
	public List<TraLoi> findByBinhLuan(BinhLuan binhLuan);
}
