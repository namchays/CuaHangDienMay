package edu.hueuni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhanVienController {

	@GetMapping("/employee/manage-don-hang")
	public String manageDonHang() {
		return "employee/manageDonHang";
	}
}
