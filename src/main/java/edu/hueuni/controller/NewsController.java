package edu.hueuni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.BaiDang;
import edu.hueuni.service.BaiDangService;

@Controller
public class NewsController {
	@Autowired
	private BaiDangService baiDangService;
	
	@GetMapping("/trang-tin-tuc")
	public ModelAndView showTrangTinTuc() {
		ModelAndView mav = new ModelAndView("/news/news");
		List<BaiDang> listBaiDang = baiDangService.findByMatHang(null);
		mav.addObject("listBaiDang", listBaiDang);
		
		return mav;
	}
	@GetMapping("/news-detail/{idBaiDang}")
	public ModelAndView showDetail(@PathVariable int idBaiDang) {
		ModelAndView mav = new ModelAndView("/news/details");
		BaiDang baiDang = baiDangService.findById(idBaiDang);
		mav.addObject("baiDang", baiDang);
		return mav;
	}
}
