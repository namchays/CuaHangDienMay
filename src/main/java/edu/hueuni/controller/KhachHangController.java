package edu.hueuni.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.NhanVienService;

@Controller
public class KhachHangController {
	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private NhanVienService nhanVienService;

	@GetMapping("/register")
	public String showRegisterForm() {
		return "/user/register";
	}
	@PostMapping("/register")
	public String registerSubmit(	@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "tenKhachHang") String tenKhachHang,
			@RequestParam(name = "diaChi") String diaChi,
			@RequestParam(name = "soDienThoai") String soDienThoai,
			@RequestParam(name = "gioiTinh") Integer gioiTinh,
			@RequestParam(name = "ngaySinh") String ngaySinh,
			Model model
			) throws ParseException, NoSuchAlgorithmException {

		
		KhachHang khachHang = khachHangService.findByUserName(userName);
		NhanVien nhanVien = nhanVienService.findByUserName(userName);
		if(khachHang!=null || nhanVien!=null) {
			model.addAttribute("hasError", "Tài khoản đã tồn tại.");
			return "/user/register";
		}else {
			try {
				   Date NgaySinhDateType=new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);  
					KhachHang newKhachHang =new KhachHang(userName,  diaChi,  gioiTinh,  NgaySinhDateType,  password,
							 soDienThoai,  tenKhachHang);
					khachHangService.save(newKhachHang);
					return "redirect:/login";
			} catch (Exception e) {
				model.addAttribute("hasError", "Thông tin đăng ký không hợp lệ");
				return "/user/register";
			}
			 
		}
		
		
	}
}
