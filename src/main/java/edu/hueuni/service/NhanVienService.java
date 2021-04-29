package edu.hueuni.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import edu.hueuni.entity.NhanVien;
import edu.hueuni.repository.NhanVienRepository;
@Service
public class NhanVienService {
	@Autowired 
	private NhanVienRepository nhanVienRepository;
	
	public void save(NhanVien nhanVien) throws NoSuchAlgorithmException {
		Optional<NhanVien> o = nhanVienRepository.findById(nhanVien.getUserName());
		if(o.isPresent()) {
			throw new DuplicateKeyException("Usename đã tồn tại");
		}
		else {
			String password = nhanVien.getPassword();
			String encryptedPassword = md5("hueunisalt", password);
			nhanVien.setPassword(encryptedPassword);
//			taiKhoan.setUrlAvatar("/image/defaultAvatar.png");
			nhanVienRepository.save(nhanVien);
		}
	}
	public Optional<NhanVien> findByUserName(String userName) {
		return nhanVienRepository.findByUserName(userName);
	}
	public Optional<NhanVien> findByUserNameAndPassword(String userName, String password){
		return nhanVienRepository.findByUserNameAndPassword(userName, password);
	}
	public List<NhanVien> findAll() {
		return nhanVienRepository.findAll();
	}
	
	public String md5(String salt, String plainText)
	        throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("MD5");

	    if (salt != null) {
	        md.update(salt.getBytes());
	    }
	    md.update(plainText.getBytes());

	    byte byteData[] = md.digest();

	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++) {
	        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
	                .substring(1));
	    }
	    return sb.toString();
	}
}
