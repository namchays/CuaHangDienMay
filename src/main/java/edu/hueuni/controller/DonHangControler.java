package edu.hueuni.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.ChiTietDatHang;
import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.model.GioHangItem;
import edu.hueuni.model.GioHangModel;
import edu.hueuni.service.AnhMatHangService;
import edu.hueuni.service.ChiTietDatHangService;
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
	@Autowired
	private ChiTietDatHangService chiTietDatHangService;
	@GetMapping("/dat-hang")
	public ModelAndView danhSachDonHang(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/chi-tiet-dat-hang");
		HttpSession session = request.getSession();
		GioHangModel gioHangModel =(GioHangModel) session.getAttribute("gioHang");
		Object user = session.getAttribute("account");
		if(user instanceof KhachHang) {
			KhachHang khachHang = (KhachHang) user;
			if(gioHangModel!=null) {
				List<GioHangItem> listGioHangItem = gioHangModel.getDs();
				if(listGioHangItem!=null) {
					if(listGioHangItem.size()>0) {
						DonDatHang donDatHang = new DonDatHang(new Date(), null, khachHang.getDiaChi(), khachHang, null, 1);
						donDatHangService.save(donDatHang);
						for (GioHangItem gioHangItem : listGioHangItem) {
							Optional<MatHang> matHangFound = matHangService.findById(gioHangItem.getMaHang());
							if(matHangFound.isPresent()) {
								ChiTietDatHang chiTietDatHang = new ChiTietDatHang(gioHangItem.getGia(), matHangFound.get().getMucGiamGia(), gioHangItem.getSoLuong());	
								chiTietDatHang.setMatHang(matHangFound.get());
								chiTietDatHang.setDonDatHang(donDatHang);
								chiTietDatHangService.save(chiTietDatHang);
							}
							
						}
						session.setAttribute("gioHang", null);
					}
				}
			}
		}else {
			mav.setViewName("redirect:/login");
		}
		
		return mav;
	}
	@GetMapping("/chi-tiet-dat-hang")
	public ModelAndView showChiTietDatHang(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/donhang/danhSachDonHang");
		HttpSession session = request.getSession();
		Object account = session.getAttribute("account");
		if(account instanceof KhachHang) {
			KhachHang khachHang = (KhachHang) account;
			List<DonDatHang> listDonDatHang = donDatHangService.findByKhachHang(khachHang);
			mav.addObject("listDonDatHang", listDonDatHang);
		}else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}
}
