	package edu.hueuni.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
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
import edu.hueuni.model.ChiTietMatHangItem;
import edu.hueuni.model.ChiTietMatHangModel;
import edu.hueuni.model.NhomHangModel;
import edu.hueuni.model.QuaTangItem;
import edu.hueuni.model.QuaTangModel;
import edu.hueuni.service.AnhMatHangService;
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
		ModelAndView mav = new ModelAndView("/admin/user/manageNhanVien");
		List<NhanVien> listNhanVien = nhanVienService.findAll();
		mav.addObject("listNhanVien", listNhanVien);
		return mav;
	}
	@GetMapping("/admin/manage-khach-hang")
	public ModelAndView manageKhachHang() {
		ModelAndView mav = new ModelAndView("/admin/user/manageKhachHang");
		List<KhachHang> listKhachHang = khachHangService.findAll();
		mav.addObject("listKhachHang", listKhachHang);
		return mav;
	}
	
}
