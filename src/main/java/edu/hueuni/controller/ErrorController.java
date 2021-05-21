package edu.hueuni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController{

	@Override
	@RequestMapping("/error")
	public String getErrorPath() {
		return "/notfound/404";
	}

}