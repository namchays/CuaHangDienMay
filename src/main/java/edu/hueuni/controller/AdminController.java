package edu.hueuni.controller;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.CuaHang;
import edu.hueuni.service.CuaHangService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CuaHangService cuaHangService;
	
	@GetMapping("/manageDonHang")
	public String showDonHang() {
		return "/admin/manageDonHang";
	}
	@GetMapping("/manageCuaHang")
	public ModelAndView showCuaHang() {
		List<CuaHang> cuaHangs =cuaHangService.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("listCuaHang", cuaHangs);
		mav.setViewName("/admin/manageCuaHang");
		return mav;
	}
	@GetMapping("/manageProduct")
	public String showProduct() {
		return "/admin/manageProduct";
	}
}
