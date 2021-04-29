package edu.hueuni.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.DonDatHang;
import edu.hueuni.model.GioHangItem;
import edu.hueuni.model.GioHangModel;
import edu.hueuni.service.AnhMatHangService;
import edu.hueuni.service.ChiTietMatHangService;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.DonDatHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;
import edu.hueuni.service.QuaTangService;
import edu.hueuni.service.loaiHangService;

@Controller
public class DonHangControler {
	@Autowired
	private CuaHangService cuaHangService;
	@Autowired
	private loaiHangService loaiHangService;
	@Autowired
	private NhomHangService nhomHangService;
	@Autowired
	private MatHangService matHangService;
	@Autowired
	private QuaTangService quaTangService;
	@Autowired
	private ChiTietMatHangService chiTietMatHangService;
	@Autowired
	private AnhMatHangService anhMatHangService;
	@Autowired
	private DonDatHangService donDatHangService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private KhachHangService khachHangService;
	
	@GetMapping("/dat-hang")
	public ModelAndView danhSachDonHang(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/donhang/danhSachDonHang");
		HttpSession session = request.getSession();
		GioHangModel gioHangModel =(GioHangModel) session.getAttribute("gioHang");
		if(gioHangModel!=null) {
//			DonDatHang donDatHang = new DonDatHang(null, null, null, null, null, 0)
			List<GioHangItem> listGioHangItem = gioHangModel.getDs();
			if(listGioHangItem!=null) {
				if(listGioHangItem.size()>0) {
					for (GioHangItem gioHangItem : listGioHangItem) {
						
					}
				}
			}
		}
		
		return mav;
	}
}
