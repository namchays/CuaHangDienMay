package edu.hueuni.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.MatHang;
import edu.hueuni.model.GioHangItem;
import edu.hueuni.model.GioHangModel;
import edu.hueuni.model.QuantityModel;
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
			if(matHang.getAnhMatHangs().size()>0) {
				gioHangModel.Them(id, matHang.getTenHang(), 1, matHang.getGiaHang(),matHang.getAnhMatHangs().get(0).getUrl());	
			}else {
				gioHangModel.Them(id, matHang.getTenHang(), 1, matHang.getGiaHang(),null);
			}
			
			session.setAttribute("gioHang", gioHangModel);
		}
		return mav;
	}
	@GetMapping("/list-cart")
	public ModelAndView listCartView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/cart/listCart");
		HttpSession session = request.getSession();
		GioHangModel gioHangModel =(GioHangModel) session.getAttribute("gioHang");
		if(gioHangModel!=null) {
			List<GioHangItem> listGioHangItem = gioHangModel.ds;
			mav.addObject("tongTien", gioHangModel.tong());
			System.out.println(listGioHangItem);
			mav.addObject("listGioHangItem", listGioHangItem);
		}
		
		return mav;
	}

	
	@PostMapping(value="/change-quantity-cart/{id}")
	@ResponseBody
	public ResponseEntity<?> thayDoiSoLuong(@PathVariable(name = "id") Integer id,
			@RequestBody int value, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int thanhTien = 0, tongTien = 0;
		if(session.getAttribute("gioHang")!=null) {
			GioHangModel gioHangModel =(GioHangModel)  session.getAttribute("gioHang");
			if(gioHangModel!=null) {
				List<GioHangItem> listGioHangItem = gioHangModel.getDs();
				
				for (GioHangItem gioHangItem : listGioHangItem) {
					if(gioHangItem.getMaHang() == id) {
						gioHangItem.setSoLuong(value);
						thanhTien = gioHangItem.getThanhTien();
					}
				}
				tongTien = gioHangModel.tong();
				session.setAttribute("gioHang", gioHangModel);
			}
			
			
		}
		
		QuantityModel quantityModel = new QuantityModel(id,value,thanhTien,tongTien);
		return	ResponseEntity.ok(quantityModel);
	}
	@PostMapping(value="/delete-cart")
	@ResponseBody
	public ResponseEntity<?> deleteCart(@RequestBody int id, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int thanhTien = 0, tongTien = 0;
	
		if(session.getAttribute("gioHang")!=null) {
		
			GioHangModel gioHangModel =(GioHangModel)  session.getAttribute("gioHang");
			if(gioHangModel!=null) {
				gioHangModel.Xoa(id);
				tongTien = gioHangModel.tong();
				session.setAttribute("gioHang", gioHangModel);
			}
			
			
		}
		
		QuantityModel quantityModel = new QuantityModel(id,0,thanhTien,tongTien);
		return	ResponseEntity.ok(quantityModel);
	}
	

	
}
