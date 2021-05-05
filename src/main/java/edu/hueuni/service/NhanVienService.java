package edu.hueuni.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.config.MyConstances;
import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.Quyen;
import edu.hueuni.model.NhanVienModel;
import edu.hueuni.repository.NhanVienRepository;
@Service
public class NhanVienService {
	@Autowired 
	private NhanVienRepository nhanVienRepository;
	@Autowired 
	private DonDatHangService donDatHangService;
	@Autowired
	private QuyenService quyenService;
	
	public void save(NhanVien nhanVien) throws NoSuchAlgorithmException {
			String password = nhanVien.getPassword();
			String encryptedPassword = md5("hueunisalt", password);
			nhanVien.setPassword(encryptedPassword);
			nhanVienRepository.save(nhanVien);
	}
	public NhanVien findByUserName(String userName) {
		Optional<NhanVien> nhanVienFound = nhanVienRepository.findByUserName(userName);
		if(nhanVienFound.isPresent()) {
			return nhanVienFound.get();
		}else {
			return null;
		}
	}
	public Optional<NhanVien> findByUserNameAndPassword(String userName, String password){
		return nhanVienRepository.findByUserNameAndPassword(userName, password);
	}
	public List<NhanVien> findAll() {
		return nhanVienRepository.findAll();
	}
	public void deleteById(String username) {
		NhanVien nhanVien = this.findByUserName(username);
		List<DonDatHang> listDonDatHang = donDatHangService.findByNhanVien(nhanVien);
		if(listDonDatHang!=null) {
			if(listDonDatHang.size()>0) {
				listDonDatHang.forEach(x->{
					x.setNhanVien(null);
					donDatHangService.save(x);
				});
			}
		}
		nhanVienRepository.deleteById(username);
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
	
	public void addNhanVien(MultipartFile[] files,NhanVienModel nhanVienModel) throws ParseException, NoSuchAlgorithmException {
		NhanVien nhanVien = new NhanVien(nhanVienModel.getUserName());
		System.out.println(nhanVien.getDienThoai());
		System.out.println(nhanVien.getPassword());
	    Date ngayLamViec=new SimpleDateFormat("yyyy-MM-dd").parse(nhanVienModel.getNgayLamViec());  
	    Date ngaySinh=new SimpleDateFormat("yyyy-MM-dd").parse(nhanVienModel.getNgaySinh());  

		nhanVien.setDiaChi(nhanVienModel.getDiaChi());
		nhanVien.setDienThoai(nhanVienModel.getDienThoai());
		nhanVien.setPassword(nhanVienModel.getPassword());
		nhanVien.setLuongCoBan(nhanVienModel.getLuongCoBan());
		nhanVien.setNgayLamViec(ngayLamViec);
		nhanVien.setNgaySinh(ngaySinh);
		nhanVien.setPhuCap(nhanVienModel.getPhuCap());
		Quyen quyen = quyenService.findByTenQuyen(MyConstances.ROLE_EMPLOYEES);
		nhanVien.setQuyen(quyen);
		nhanVien.setGioiTinh(nhanVienModel.getGioiTinh());
		nhanVien.setTenNhanVien(nhanVienModel.getTenNhanVien());
		
		new File(MyConstances.DIRECTORY_IMG_USER).mkdir();
		String imageURL = null;
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			imageURL = "/img/user/" + file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(MyConstances.DIRECTORY_IMG_USER, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				break;
			}
			
			
		}
		nhanVien.setUrlAvatar(imageURL);
		
		this.save(nhanVien);
	}
	public void editNhanVien(MultipartFile[] files,NhanVienModel nhanVienModel,String username) throws ParseException, NoSuchAlgorithmException {
		NhanVien nhanVien = this.findByUserName(username);
		if(nhanVien!=null) {
			Date ngayLamViec=new SimpleDateFormat("yyyy-MM-dd").parse(nhanVienModel.getNgayLamViec());  
			Date ngaySinh=new SimpleDateFormat("yyyy-MM-dd").parse(nhanVienModel.getNgaySinh());  
		
			if(nhanVienModel.getPassword()!=null && !nhanVienModel.getPassword().equals("")) {
				nhanVien.setPassword(nhanVienModel.getPassword());
			}
			nhanVien.setDiaChi(nhanVienModel.getDiaChi());
			nhanVien.setDienThoai(nhanVienModel.getDienThoai());
			
			nhanVien.setLuongCoBan(nhanVienModel.getLuongCoBan());
			nhanVien.setNgayLamViec(ngayLamViec);
			nhanVien.setNgaySinh(ngaySinh);
			nhanVien.setPhuCap(nhanVienModel.getPhuCap());
		
			nhanVien.setGioiTinh(nhanVienModel.getGioiTinh());
			nhanVien.setTenNhanVien(nhanVienModel.getTenNhanVien());
			
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
			nhanVien.setUrlAvatar(imageURL);
			
			this.save(nhanVien);
		}
	
	}
	public void addObjectToEditNhanVien(ModelAndView mav, String username, NhanVienModel nhanVienModel) {
		NhanVien nhanVien = this.findByUserName(username);
		if(nhanVien!=null) {
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			    String ngaySinh = formatter.format(nhanVien.getNgaySinh());  
			    String ngayLamViec = formatter.format(nhanVien.getNgayLamViec());
			nhanVienModel.setUserName(username);
			nhanVienModel.setTenNhanVien(nhanVien.getUserName());
			nhanVienModel.setPhuCap(nhanVien.getPhuCap());
			nhanVienModel.setPassword(nhanVien.getPassword());
			nhanVienModel.setNgaySinh(ngaySinh);
			nhanVienModel.setNgayLamViec(ngayLamViec);
			nhanVienModel.setLuongCoBan(nhanVien.getLuongCoBan());
			nhanVienModel.setGioiTinh(nhanVien.getGioiTinh());
			nhanVienModel.setDienThoai(nhanVien.getDienThoai());
			nhanVienModel.setDiaChi(nhanVien.getDiaChi());
			
			mav.addObject("nhanVien", nhanVienModel);
		}
		
	}
}
