package edu.hueuni.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.hueuni.entity.BaiDang;
import edu.hueuni.entity.BinhLuan;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.service.AnhMatHangService;
import edu.hueuni.service.BaiDangService;
import edu.hueuni.service.BinhLuanService;
import edu.hueuni.service.ChiTietDatHangService;
import edu.hueuni.service.ChiTietMatHangService;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.DonDatHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;
import edu.hueuni.service.QuaTangService;
import edu.hueuni.service.QuyenService;
import edu.hueuni.service.TraLoiService;
import edu.hueuni.service.loaiHangService;

@RestController
public class CommentController {

	@Autowired
	private QuyenService quyenService;
	@Autowired
	private NhanVienService nhanvienService;
	@Autowired 
	private KhachHangService khachHangService;
	@Autowired
	private loaiHangService loaiHangService;
	@Autowired
	private NhomHangService nhomHangService; 
	@Autowired
	private CuaHangService cuaHangService;
	@Autowired
	private QuaTangService quaTangService;
	@Autowired
	private MatHangService matHangService;
	@Autowired
	private DonDatHangService donDatHangService;
	@Autowired
	private ChiTietDatHangService chiTietDatHangService;
	@Autowired
	private BaiDangService baiDangService;
	@Autowired 
	private BinhLuanService binhLuanService;
	@Autowired
	private TraLoiService traLoiService;	
	@Autowired
	private AnhMatHangService anhMatHangService;
	@Autowired
	private ChiTietMatHangService chiTietMatHangService;
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/add-comment/{id}")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles,
            @PathVariable int id,
            HttpServletRequest request) {

//        logger.debug("Multiple file upload!");
		HttpSession session = request.getSession();
		if(session.getAttribute("account")!=null) {
			String username =null;
			Object account = session.getAttribute("account");
			if(account instanceof KhachHang) {
				username = ((KhachHang) account).getUserName();
			}else if(account instanceof NhanVien) {
				username = ((NhanVien) account).getUserName();
			}
			System.out.println("id"+id);
			BaiDang baiDang = baiDangService.findById(id);
			System.out.println("file"+uploadfiles);
			System.out.println("extrafield"+extraField);
			BinhLuan binhLuan = new BinhLuan();
			binhLuan.setNoiDung(extraField);
			binhLuan.setUserName(username);
			binhLuan.setBaiDang(baiDang);
			binhLuanService.save(binhLuan);
		}
		
        // Get file name
			
        return new ResponseEntity("Successfully uploaded - ", HttpStatus.OK);

    }
}
