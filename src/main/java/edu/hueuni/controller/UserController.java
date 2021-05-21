package edu.hueuni.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.model.KhachHangModel;
import edu.hueuni.model.NhanVienModel;
import edu.hueuni.service.KhachHangService;

@Controller
public class UserController {
	@Autowired
	private KhachHangService khacHangService;

	@GetMapping("/manage-infor-khach-hang")
	public ModelAndView manageInforKhachHang(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/khachhang/infor/manageInfor");
		khacHangService.showKhachHang(mav, request);
		return mav;
	}

	@PostMapping("/edit-infor-khach-hang/{username}")
	public ModelAndView editInforUserSubmit(@ModelAttribute("khachHang") KhachHangModel khachHangModel,
			@RequestParam("files") MultipartFile[] files, @PathVariable String username)
			throws NoSuchAlgorithmException, ParseException {
		ModelAndView mav = new ModelAndView("redirect:/manage-infor-khach-hang");
		khacHangService.editInforKhachHang(files, khachHangModel, username);
		return mav;
	}
}
