package edu.hueuni.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.hueuni.config.MyConstances;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.Quyen;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.NhanVienService;

@Controller
public class MainController {
	@Autowired
	private KhachHangService khachHangService;

	@Autowired
	private NhanVienService nhanVienService;

	@GetMapping("/login")
	public String ShowLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String loginSystem(@ModelAttribute("nhanVien") NhanVien nhanVien, HttpServletRequest request,
			HttpServletResponse res, Model model) {
		String userName = nhanVien.getUserName();
		String password = nhanVien.getPassword();
		Optional<KhachHang> khachHang = khachHangService.findByUserNameAndPassword(userName, password);
		if (khachHang.isPresent()) {
			HttpSession session = request.getSession();
			session.setAttribute("account", khachHang.get());
			return "index";
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
}
