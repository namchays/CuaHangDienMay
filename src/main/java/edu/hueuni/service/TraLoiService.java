package edu.hueuni.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.hueuni.entity.BinhLuan;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.TraLoi;
import edu.hueuni.model.BinhLuanModel;
import edu.hueuni.model.TraLoiModel;
import edu.hueuni.repository.TraLoiRepository;

@Service
public class TraLoiService {
	@Autowired
	private BinhLuanService binhLuanService;
	@Autowired
	private TraLoiRepository traLoiRepository;
	public static String uploadDirectory = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\static\\img\\traloi\\";
	public void save(TraLoi traLoi) {
		traLoiRepository.save(traLoi);
	}

	public List<TraLoi> findAll() {
		return traLoiRepository.findAll();
	}

	public List<TraLoi> findByNoiDung(String noiDung) {
		return traLoiRepository.findByNoiDung(noiDung);
	}

	public void deleteById(int id) {
		traLoiRepository.deleteById(id);
	}

	public List<TraLoi> findByBinhLuan(BinhLuan binhLuan) {
		return traLoiRepository.findByBinhLuan(binhLuan);
	}

	public TraLoiModel addTraLoi(String extraField, MultipartFile[] uploadfiles, int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		TraLoiModel traLoiModel = new TraLoiModel();
		if(session.getAttribute("account")!=null) {
			String username =null;
			Object account = session.getAttribute("account");
			if(account instanceof KhachHang) {
				username = ((KhachHang) account).getUserName();
			}else if(account instanceof NhanVien) {
				username = ((NhanVien) account).getUserName();
			}
			BinhLuan binhLuan = binhLuanService.findById(id);
			System.out.println("file"+uploadfiles);
			System.out.println("extrafield"+extraField);
			TraLoi traLoi = new TraLoi();
			
			traLoi.setNoiDung(extraField);
			traLoi.setBinhLuan(binhLuan);
			traLoi.setUserName(username);
			
			traLoiModel.setNoiDung(extraField);
			traLoiModel.setUserName(username);
			traLoiModel.setLuotThich(0);

			
			//Thêm ảnh
			new File(uploadDirectory).mkdir();
			String imageURL = null;
			StringBuilder fileNames = new StringBuilder();
			for (MultipartFile file : uploadfiles) {
				imageURL = "/img/traloi/" + file.getOriginalFilename();
				System.err.println(imageURL);
				Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
				fileNames.append(file.getOriginalFilename());
				try {
					Files.write(fileNameAndPath, file.getBytes());
				} catch (IOException e) {
					break;
				}
				traLoiModel.setUrlImage(imageURL);
				traLoi.setUrlImage(imageURL);
				System.out.println("set url image");

			}
			Date date = new Date();
		
			traLoi.setThoiGian(date);
			traLoiModel.setThoiGian(date);
			this.save(traLoi);
			traLoiModel.setIdCauTraLoi(traLoi.getIdCauTraLoi());
			
		}
		return traLoiModel;
	}
	public TraLoiModel likeTraLoi(int id) {

		Optional<TraLoi>  traLoiFound = traLoiRepository.findById(id);
		System.err.println("like tra loi");
		if(traLoiFound.isPresent()) {
			TraLoi traLoi = traLoiFound.get();
			traLoi.setLuotThich(traLoi.getLuotThich()+1);
			System.err.println("luot thich"+traLoi.getLuotThich());
			traLoiRepository.save(traLoi);

			TraLoiModel traLoiModel = new TraLoiModel();
			traLoiModel.setLuotThich(traLoi.getLuotThich());
			traLoiModel.setIdCauTraLoi(id);
			return traLoiModel;
		}else {
			System.err.println("err");
		}
		return null;
		
	}
}
