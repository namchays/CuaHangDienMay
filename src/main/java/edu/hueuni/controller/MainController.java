package edu.hueuni.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.config.MyConstances;
import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.ChiTietMatHang;
import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.Quyen;
import edu.hueuni.model.ListMatHangModel;
import edu.hueuni.model.MatHangModel;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.MatHangService;
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
	@Autowired
	private MatHangService matHangService;
	@GetMapping("/login")
	public String showLogin() {
		return "/login/login";
	}
	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("account", null);
		return "redirect:/";
	}
	@GetMapping("/")
	public String homePage(Model model) {
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<MatHang> listMatHang = matHangService.findAll();
		ListMatHangModel listMatHangModel = new ListMatHangModel();
		List<MatHangModel> listMatHangModels = listMatHangModel.getMatHangModel(listMatHang);
		
		model.addAttribute("listLoaiHang", listLoaiHang);
		model.addAttribute("listMatHangModels", listMatHangModels);
		model.addAttribute("soCuaHang", listCuaHang.size());
		model.addAttribute("listCuaHang", listCuaHang);
		return "index";
	}
	@GetMapping("/detail-san-pham/{id}")
	public String chiTietMatHang(Model model,@PathVariable int id) {
		System.out.println(id);
		Optional<MatHang> matHangFound = matHangService.findById(id);
		if(matHangFound.isPresent()) {
			MatHang matHang = matHangFound.get();
		
			List<ChiTietMatHang> listChiTietMatHang = matHang.getChiTietMatHangs();
			List<AnhMatHang> listAnhMatHang = matHang.getAnhMatHangs();
			if(listAnhMatHang.size() > 0) {
				String urlImg = listAnhMatHang.get(0).getUrl();
				model.addAttribute("urlImg", urlImg);
			}
			model.addAttribute("listChiTietMatHang", listChiTietMatHang);
			model.addAttribute("matHang", matHang );
		}
		
		
		
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<MatHang> listMatHang = matHangService.findAll();
		ListMatHangModel listMatHangModel = new ListMatHangModel();
		List<MatHangModel> listMatHangModels = listMatHangModel.getMatHangModel(listMatHang);
		
		model.addAttribute("listLoaiHang", listLoaiHang);
		model.addAttribute("listMatHangModels", listMatHangModels);
		model.addAttribute("soCuaHang", listCuaHang.size());
		model.addAttribute("listCuaHang", listCuaHang);
		return "/mathang/chiTietMatHang";
	}

	@PostMapping("/login")
	public String loginSystem(@ModelAttribute("nhanVien") NhanVien nhanVien, HttpServletRequest request,
			HttpServletResponse res, Model model) throws NoSuchAlgorithmException {
		String userName = nhanVien.getUserName();
		String password = nhanVien.getPassword();
		String encryptedPassword =nhanVienService.md5("hueunisalt", password);
		Optional<KhachHang> khachHang = khachHangService.findByUserNameAndPassword(userName, encryptedPassword);
		if (khachHang.isPresent()) {
			HttpSession session = request.getSession();
			if(khachHang.get().getEnable()==true) {
				session.setAttribute("account", khachHang.get());
				return "redirect:/";
			}else {
				return "/login/login";
			}
			
		} else {
			Optional<NhanVien> nhanVienFind = nhanVienService.findByUserNameAndPassword(userName, encryptedPassword);
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

		return "/login/login";
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
	
	@GetMapping("/find-by-cua-hang/{id}")
	public ModelAndView findByCuaHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/cuahang/findByCuaHang");
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
//		List<MatHang> listMatHang = matHangService.findAll();
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		if(cuaHangFound.isPresent()) {
			CuaHang cuaHang = cuaHangFound.get();
			String tenCuaHang = cuaHang.getTenCuaHang();
			mav.addObject("tenCuaHang", tenCuaHang);
			List<MatHang> listMatHang = cuaHang.getMatHangs();
			ListMatHangModel listMatHangModel = new ListMatHangModel();
			List<MatHangModel> listMatHangModels = listMatHangModel.getMatHangModel(listMatHang);
			mav.addObject("listMatHangModels", listMatHangModels);
		}
		
		
		
		mav.addObject("listLoaiHang", listLoaiHang);
	
		mav.addObject("soCuaHang", listCuaHang.size());
		mav.addObject("listCuaHang", listCuaHang);
		return mav;
	}

	
}
