package edu.hueuni.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.hueuni.config.MyConstances;
import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.BaiDang;
import edu.hueuni.entity.BinhLuan;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.repository.BaiDangRepository;

@Service
public class BaiDangService {
	@Autowired
	private BaiDangRepository baiDangRepository;
	@Autowired
	private BinhLuanService binhLuanService;
	public List<BaiDang> findAll() {
		return baiDangRepository.findAll();
	}
	public void  save(BaiDang baiDang) {
		 baiDangRepository.save(baiDang);
	}
	public List<BaiDang> findByTieuDe(String tieuDe) {
		return baiDangRepository.findByTieuDe(tieuDe);
	}
	public List<BaiDang> findByMatHang(MatHang matHang) {
		return baiDangRepository.findByMatHang(matHang);
	}
	public void deleteById(int id) {

		Optional<BaiDang> baiDangFound = baiDangRepository.findById(id);
		if(baiDangFound.isPresent()) {
			List<BinhLuan> listBinhLuan = binhLuanService.findByBaiDang(baiDangFound.get());	
			if(listBinhLuan!=null) {
				if(listBinhLuan.size()>0) {
					listBinhLuan.forEach(x->{
						binhLuanService.deleteById(x.getIdBinhLuan());
					});
				}
			}
		}
		
		
		
		baiDangRepository.deleteById(id);
	}
	
	public BaiDang findById(int id) {
		Optional<BaiDang> baiDangFound = baiDangRepository.findById(id);
		if(baiDangFound.isPresent()) {
			return baiDangFound.get();
		}
		return null;
	}
	
	public void addBaiDang(String tieuDe, MultipartFile[] files, String noiDung, HttpSession session, MatHang matHang) {
		BaiDang baiDang = new BaiDang();
		baiDang.setTieuDe(tieuDe);
		baiDang.setNoiDung(noiDung);
		if (session.getAttribute("account")!=null) {
			Object account = session.getAttribute("account");
			if(account instanceof NhanVien) {
				baiDang.setUserName(((NhanVien) account).getUserName());
			}
			
		}
		
		baiDang.setMatHang(matHang);
		new File(MyConstances.DIRECTORY_BAI_DANG).mkdir();
		String imageURL = null;
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			imageURL = "/img/baidang/" + file.getOriginalFilename();
			System.out.println(imageURL);
			Path fileNameAndPath = Paths.get(MyConstances.DIRECTORY_BAI_DANG, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				break;
			}
			

		}
		baiDang.setUrlImg(imageURL);
		baiDangRepository.save(baiDang);
	}
	public void editBaiDang(String tieuDe, MultipartFile[] files, String noiDung, HttpSession session, MatHang matHang,int id) {
		Optional<BaiDang> baiDangFound = baiDangRepository.findById(id);
		BaiDang baiDang = baiDangFound.get();
		if (session.getAttribute("account")!=null) {
			Object account = session.getAttribute("account");
			if(account instanceof NhanVien) {
				baiDang.setUserName(((NhanVien) account).getUserName());
			}
			
		}
		
		baiDang.setMatHang(matHang);
		baiDang.setTieuDe(tieuDe);
		baiDang.setNoiDung(noiDung);
		new File(MyConstances.DIRECTORY_BAI_DANG).mkdir();
		String imageURL = null;
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			imageURL = "/img/baidang/" + file.getOriginalFilename();
			System.out.println(imageURL);
			Path fileNameAndPath = Paths.get(MyConstances.DIRECTORY_BAI_DANG, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				break;
			}
			
			
		}
		baiDang.setUrlImg(imageURL);
		baiDangRepository.save(baiDang);
	}
	
}
