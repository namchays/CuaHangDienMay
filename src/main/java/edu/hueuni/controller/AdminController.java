	package edu.hueuni.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.BaiDang;
import edu.hueuni.entity.ChiTietDatHang;
import edu.hueuni.entity.ChiTietMatHang;
import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.entity.QuaTang;
import edu.hueuni.model.AjaxResponseBody;
import edu.hueuni.model.BaiDangModel;
import edu.hueuni.model.ChiTietMatHangItem;
import edu.hueuni.model.ChiTietMatHangModel;
import edu.hueuni.model.NhanVienModel;
import edu.hueuni.model.NhomHangModel;
import edu.hueuni.model.QuaTangItem;
import edu.hueuni.model.QuaTangModel;
import edu.hueuni.service.AnhMatHangService;
import edu.hueuni.service.BaiDangService;
import edu.hueuni.service.ChiTietMatHangService;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.DonDatHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.loaiHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;
import edu.hueuni.service.QuaTangService;

@Controller
public class AdminController {
	@Autowired
	private CuaHangService cuaHangService;
	@Autowired
	private loaiHangService loaiHangService;
	@Autowired
	private NhomHangService nhomHangService;
	@Autowired
	private MatHangService matHangService;
	@Autowired
	private QuaTangService quaTangService;
	@Autowired
	private ChiTietMatHangService chiTietMatHangService;
	@Autowired
	private AnhMatHangService anhMatHangService;
	@Autowired
	private DonDatHangService donDatHangService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private BaiDangService baiDangService;
	public static String uploadDirectory = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\static\\img\\mathang\\";


	// manage cua hang
	@GetMapping("admin/manage-cua-hang")
	public ModelAndView showCuaHang() {
		List<CuaHang> cuaHangs = cuaHangService.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("listCuaHang", cuaHangs);
		mav.setViewName("/admin/cuahang/manageCuaHang");
		return mav;
	}

	@GetMapping("admin/delete-cua-hang/{id}")
	public String deleteCuaHang(@PathVariable int id) {
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		if (cuaHangFound.isPresent()) {
			cuaHangService.DeleteById(id);
		}
		return "redirect:/admin/manage-cua-hang";
	}

	@GetMapping("admin/edit-cua-hang/{id}")
	public ModelAndView editCuaHang(@PathVariable int id, Model model) {
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		ModelAndView mav = new ModelAndView("/admin/cuahang/editCuaHang");
		if (cuaHangFound.isPresent()) {
			mav.addObject("cuaHang", cuaHangFound.get());
		}
		return mav;

	}

	@PostMapping("admin/edit-cua-hang/{id}")
	public String editCuaHangSubmit(@PathVariable int id, @ModelAttribute("cuaHang") CuaHang cuaHang) {
		cuaHang.setIdCuaHang(id);
		cuaHangService.save(cuaHang);
		return "redirect:/admin/manage-cua-hang";
	}

	@GetMapping("admin/add-cua-hang")
	public String addCuaHang() {

		return "/admin/cuahang/addCuaHang";
	}

	@PostMapping("admin/add-cua-hang")
	public String addCuaHangSubmit(@RequestParam(name = "tenCuaHang") String tenCuaHang,
			@RequestParam(name = "diaChiCuaHang") String diaChiCuaHang,
			@RequestParam(name = "soDienThoai") String soDienThoai, @RequestParam(name = "iframe") String iframe) {
		CuaHang cuaHang = new CuaHang(diaChiCuaHang, iframe, soDienThoai, tenCuaHang);
		cuaHangService.save(cuaHang);
		return "redirect:/admin/manage-cua-hang";
	}

	// manage loai hang

	
	//quản lý nhân viên
	@GetMapping("/admin/manage-nhan-vien")
	public ModelAndView manageNhanVien() {
		ModelAndView mav = new ModelAndView("/admin/user/nhanvien/manageNhanVien");
		List<NhanVien> listNhanVien = nhanVienService.findAll();
		mav.addObject("listNhanVien", listNhanVien);
		return mav;
	}
	@GetMapping("/admin/delete-nhan-vien/{username}")
	public ModelAndView deleteNhanVien(@PathVariable String username) {
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-nhan-vien");
		nhanVienService.deleteById(username);
		return mav;
	}
	@GetMapping("/admin/add-nhan-vien")
	public ModelAndView addNhanVien() {
	ModelAndView mav = new ModelAndView("admin/user/nhanvien/addNhanVien");
	mav.addObject("nhanVien", new NhanVienModel());
	return mav;
	}
	@PostMapping("/admin/add-nhan-vien")
	public ModelAndView addNhanVienSubmit(@ModelAttribute("nhanVien") NhanVienModel nhanVienModel,@RequestParam("files") MultipartFile[] files) throws ParseException, NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-nhan-vien");
		nhanVienService.addNhanVien(files, nhanVienModel);
		return mav;
	}
	
	@GetMapping("/admin/edit-nhan-vien/{username}")
	public ModelAndView editNhanVien(@PathVariable String username) {
		ModelAndView mav = new ModelAndView("admin/user/nhanvien/editNhanVien");
		nhanVienService.addObjectToEditNhanVien(mav, username, new NhanVienModel());
		return mav;
	}

	@PostMapping("/admin/edit-nhan-vien/{username}")
	public ModelAndView editNhanVienSubmit(@ModelAttribute("nhanVien") NhanVienModel nhanVienModel,
			@RequestParam("files") MultipartFile[] files,
			@PathVariable String username) throws ParseException, NoSuchAlgorithmException {
		
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-nhan-vien");
		nhanVienService.editNhanVien(files, nhanVienModel, username);
		mav.addObject("username", username);
		return mav;
	}
	
	
	///Quản lý khách hàng
	@GetMapping("/admin/manage-khach-hang")
	public ModelAndView manageKhachHang() {
		ModelAndView mav = new ModelAndView("/admin/user/khachhang/manageKhachHang");
		List<KhachHang> listKhachHang = khachHangService.findAll();
		mav.addObject("listKhachHang", listKhachHang);
		return mav;
	}
	@GetMapping("/chan-khach-hang/{userName}")
	public ModelAndView chanKhachHang(@PathVariable String userName) throws NoSuchAlgorithmException {
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-khach-hang");
		KhachHang khachHang = khachHangService.findByUserName(userName);
		khachHang.setEnable(!khachHang.getEnable());
		khachHangService.save(khachHang);
		return mav;
	}
	//Quản lý trang tin tức
	
	@GetMapping("/admin/manage-news")
	public ModelAndView quanLyTrangTinTuc() {
		ModelAndView mav = new ModelAndView("/admin/news/manageNews");
		List<BaiDang> listBaiDang = baiDangService.findByMatHang(null);
		mav.addObject("listBaiDang", listBaiDang);
		return mav;
	}
	@GetMapping("/admin/add-news")
	public ModelAndView addTinTuc() {
		ModelAndView mav = new ModelAndView("/admin/news/addNews");
		mav.addObject("baiDang", new BaiDangModel());
		return mav;
	}
	@PostMapping("/admin/add-news")
	public ModelAndView addTinTucSubmit(@ModelAttribute("baiDang") BaiDangModel baiDangModel,@RequestParam("files") MultipartFile[] files,HttpServletRequest request) {
		HttpSession  session = request.getSession();
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-news");
		baiDangService.addBaiDang(baiDangModel.getTieuDe(), files, baiDangModel.getNoiDung(), session, null);
		return mav;
	}
	
	@GetMapping("/admin/edit-news/{id}")
	public ModelAndView editTinTuc(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/admin/news/editNews");
		BaiDang baiDang = baiDangService.findById(id);
		BaiDangModel baiDangModel = new BaiDangModel(id, baiDang.getNoiDung(), baiDang.getTieuDe(), null, baiDang.getUrlImg());
		mav.addObject("baiDang", baiDangModel);
		return mav;
	}
	@PostMapping("/admin/edit-news/{id}")
	public ModelAndView edutTinTucSubmit(@ModelAttribute("baiDang") BaiDangModel baiDangModel,@RequestParam("files") MultipartFile[] files,HttpServletRequest request,@PathVariable int id) {
		HttpSession  session = request.getSession();
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-news");
		baiDangService.editBaiDang(baiDangModel.getTieuDe(), files, baiDangModel.getNoiDung(), session, null, id);
		return mav;
	}
	
}
