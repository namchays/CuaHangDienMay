package edu.hueuni.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.config.MyConstances;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.model.KhachHangModel;
import edu.hueuni.model.NhanVienModel;
import edu.hueuni.repository.KhachHangRepository;

@Service
public class KhachHangService {
	@Autowired
	private KhachHangRepository khachHangRepository;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private KhachHangService khachHangService;
	
	public void save(KhachHang khachHang) throws NoSuchAlgorithmException {

			String password = khachHang.getPassword();
			String encryptedPassword = nhanVienService.md5("hueunisalt", password);
			khachHang.setPassword(encryptedPassword);
			khachHangRepository.save(khachHang);
		
		
	}
	public Optional<KhachHang> findByUserNameAndPassword(String userName, String password){
		return khachHangRepository.findByUserNameAndPassword(userName, password);
	}
	public KhachHang findByUserName(String userName){
		
		 Optional<KhachHang> khachHangFound = khachHangRepository.findByUserName(userName);
		 if(khachHangFound.isPresent()) {
			 return khachHangFound.get();
		 }
		 return null;
	}
	public List<KhachHang> findAll() {
		return khachHangRepository.findAll();
	}
	public void showKhachHang(ModelAndView mav, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("account")!=null) {
			KhachHang khachHangFound = (KhachHang)session.getAttribute("account");
			KhachHang khachHang = khachHangService.findByUserName(khachHangFound.getUserName());
			mav.addObject("khachHang",khachHang);
			mav.addObject("username",khachHang.getUserName());
		}
	}
	public void editInforKhachHang(MultipartFile[] files,KhachHangModel khachHangModel,String username) throws NoSuchAlgorithmException, ParseException {
		if(khachHangModel.getUserName()!=null) {
			KhachHang khachHang = this.findByUserName(username);
			khachHang.setDiaChi(khachHangModel.getDiaChi());

			System.err.println("diachi"+ khachHangModel.getDiaChi());
			khachHang.setGioiTinh(khachHangModel.getGioiTinh());
			Date ngaySinh=new SimpleDateFormat("yyyy-MM-dd").parse(khachHangModel.getNgaySinh());  
			khachHang.setNgaySinh(ngaySinh);
			if(khachHangModel.getPassword()!=null && !khachHangModel.getPassword().equals(""))
			{
				String password = khachHangModel.getPassword();
				String encryptedPassword = nhanVienService.md5("hueunisalt", password);
				khachHang.setPassword(encryptedPassword);
			}
			khachHang.setSoDienThoai(khachHangModel.getSoDienThoai());
			khachHang.setTenKhachHang(khachHangModel.getTenKhachHang());
			new File(MyConstances.DIRECTORY_IMG_USER).mkdir();
			String imageURL = null;
			StringBuilder fileNames = new StringBuilder();
			boolean checkFile = false;
			for (MultipartFile file : files) {
				imageURL = "/img/user/" + file.getOriginalFilename();
				if(file.getOriginalFilename().equals("")) {
					checkFile = true;
				}
				Path fileNameAndPath = Paths.get(MyConstances.DIRECTORY_IMG_USER, file.getOriginalFilename());
				fileNames.append(file.getOriginalFilename());
				try {
					Files.write(fileNameAndPath, file.getBytes());
				} catch (IOException e) {
					break;
				}
				
				
			}
			System.out.println(files.length);
			if(checkFile==false)
			khachHang.setUrlAvatar(imageURL);
			
		
			khachHangRepository.save(khachHang);
		}
	}
	public void addObjectToEditKhachHang(ModelAndView mav, String username, KhachHangModel khachHangModel) {
		KhachHang khachHang = this.findByUserName(username);
		if(khachHang!=null) {
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			    String ngaySinh = formatter.format(khachHang.getNgaySinh());  
			    khachHangModel.setUserName(username);
			    khachHangModel.setTenKhachHang(khachHang.getUserName());
			    khachHangModel.setPassword(khachHang.getPassword());
			    khachHangModel.setNgaySinh(ngaySinh);
			khachHangModel.setGioiTinh(khachHang.getGioiTinh());
			khachHangModel.setSoDienThoai(khachHang.getSoDienThoai());
			khachHangModel.setDiaChi(khachHang.getDiaChi());
			
			mav.addObject("nhanVien", khachHangModel);
		}
		
	}
}
