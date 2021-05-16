package edu.hueuni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {
	@GetMapping("/trang-tin-tuc")
	public ModelAndView showTrangTinTuc() {
		ModelAndView mav = new ModelAndView("/news/news");
		return mav;
	}
}
