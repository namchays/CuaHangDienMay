package edu.hueuni.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.hueuni.config.MyConstances;
import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.Quyen;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.loaiHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;

@Controller
public class MainController {
	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private CuaHangService cuaHangService;
	@Autowired
	private NhomHangService nhomHangService;
	@Autowired
	private loaiHangService loaiHangService;
	
	@GetMapping("/login")
	public String ShowLogin() {
		return "login";
	}
	@GetMapping("/")
	public String homePage(Model model) {
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		model.addAttribute("listLoaiHang", listLoaiHang);
		model.addAttribute("soCuaHang", listCuaHang.size());
		return "index";
	}

	@PostMapping("/login")
	public String loginSystem(@ModelAttribute("nhanVien") NhanVien nhanVien, HttpServletRequest request,
			HttpServletResponse res, Model model) {
		String userName = nhanVien.getUserName();
		String password = nhanVien.getPassword();
		Optional<KhachHang> khachHang = khachHangService.findByUserNameAndPassword(userName, password);
		if (khachHang.isPresent()) {
			HttpSession session = request.getSession();
			if(khachHang.get().getEnable()==false) {
				session.setAttribute("account", khachHang.get());
				return "index";
			}else {
				return "login";
			}
			
		} else {
			Optional<NhanVien> nhanVienFind = nhanVienService.findByUserNameAndPassword(userName, password);
			if (nhanVienFind.isPresent()) {
				NhanVien curNhanVien = nhanVienFind.get();
				HttpSession session = request.getSession();
				session.setAttribute("account", curNhanVien);
				Quyen quyenNhanVien = curNhanVien.getQuyen();
				if (quyenNhanVien.getTenQuyen().equals(MyConstances.ROLE_EMPLOYEES)) {
					return "redirect:/employee/manage-don-hang";
				} else if (quyenNhanVien.getTenQuyen().equals(MyConstances.ROLE_ADMIN)) {
					return "redirect:/manage-don-hang";
				}
			}
		}

		return "login";
	}
	@GetMapping("/he-thong-sieu-thi")
	public String getListCuaHang(Model model) {
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		model.addAttribute("listCuaHang", listCuaHang);
		return "/cuahang/heThongSieuThi";
	}
	@GetMapping("/chi-tiet-cua-hang/{id}")
	public String chiTietCuaHang(Model model,@PathVariable int id) {
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		if(cuaHangFound.isPresent()) {
			CuaHang cuaHang = cuaHangFound.get();
			model.addAttribute("cuaHang",cuaHang);
		}
		return "/cuahang/chiTietCuaHang";
	}

	
}
