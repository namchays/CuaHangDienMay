package edu.hueuni.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.MatHang;
import edu.hueuni.model.GioHangItem;
import edu.hueuni.model.GioHangModel;
import edu.hueuni.service.MatHangService;

@Controller
public class GioHangController {
	
	@Autowired
	private MatHangService matHangService;
	
	@GetMapping("/add-to-cart/{id}")
	public ModelAndView addToCart(@PathVariable int id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/list-cart");
		Optional<MatHang> matHangFound = matHangService.findById(id);
		GioHangModel gioHangModel;
		if(matHangFound.isPresent()) {
			
			HttpSession session = request.getSession();
			if(session.getAttribute("gioHang") == null) {
				gioHangModel = new GioHangModel();
				session.setAttribute("gioHang", gioHangModel);
			}
		
			gioHangModel =(GioHangModel) session.getAttribute("gioHang");
			MatHang matHang = matHangFound.get();
			gioHangModel.Them(id, matHang.getTenHang(), 1, matHang.getGiaHang(),matHang.getAnhMatHangs().get(0).getUrl());
			session.setAttribute("gioHang", gioHangModel);
		}
		return mav;
	}
	@GetMapping("/list-cart")
	public ModelAndView listCartView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/cart/listCart");
		HttpSession session = request.getSession();
		GioHangModel gioHangModel =(GioHangModel) session.getAttribute("gioHang");
		List<GioHangItem> listGioHangItem = gioHangModel.ds;
		mav.addObject("tongTien", gioHangModel.tong());
		System.out.println(listGioHangItem);
		mav.addObject("listGioHangItem", listGioHangItem);
		return mav;
	}
}
