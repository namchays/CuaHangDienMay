package edu.hueuni.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.config.MyConstances;
import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.ChiTietMatHang;
import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.Quyen;
import edu.hueuni.model.ListMatHangModel;
import edu.hueuni.model.MatHangModel;
import edu.hueuni.model.NhanVienModel;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.loaiHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;

@Controller
public class MainController {
	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private CuaHangService cuaHangService;
	@Autowired
	private NhomHangService nhomHangService;
	@Autowired
	private loaiHangService loaiHangService;
	@Autowired
	private MatHangService matHangService;
	@GetMapping("/login")
	public String showLogin() {
		return "/login/login";
	}
	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("account", null);
		return "redirect:/login";
	}
	@GetMapping("/")
	public String homePage(Model model) {
		addDataToNavbar(null, model);
		return "index";
	}
	@GetMapping("/detail-san-pham/{id}")
	public String chiTietMatHang(Model model,@PathVariable int id) {
		addDataToNavbar(null, model);
		System.out.println(id);
		Optional<MatHang> matHangFound = matHangService.findById(id);
		if(matHangFound.isPresent()) {
			MatHang matHang = matHangFound.get();
		
			List<ChiTietMatHang> listChiTietMatHang = matHang.getChiTietMatHangs();
			List<AnhMatHang> listAnhMatHang = matHang.getAnhMatHangs();
			if(listAnhMatHang.size() > 0) {
				String urlImg = listAnhMatHang.get(0).getUrl();
				model.addAttribute("urlImg", urlImg);
			}
			
			model.addAttribute("listChiTietMatHang", listChiTietMatHang);
			model.addAttribute("matHang", matHang );
			if(matHang.getBaiDangs().size()>0) {
				int idBaiDang = matHang.getBaiDangs().get(0).getIdBaiDang();
				model.addAttribute("idBaiDang", idBaiDang);
			}
		}
		
		
		
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<MatHang> listMatHang = matHangService.findAll();
		ListMatHangModel listMatHangModel = new ListMatHangModel();
		List<MatHangModel> listMatHangModels = listMatHangModel.getMatHangModel(listMatHang);
		
		model.addAttribute("listLoaiHang", listLoaiHang);
		model.addAttribute("listMatHangModels", listMatHangModels);
		model.addAttribute("soCuaHang", listCuaHang.size());
		model.addAttribute("idBaiDang",1);
		model.addAttribute("listCuaHang", listCuaHang);
		return "/mathang/chiTietMatHang";
	}

	@PostMapping("/login")
	public String loginSystem(@ModelAttribute("nhanVien") NhanVien nhanVien, HttpServletRequest request,
			HttpServletResponse res, Model model) throws NoSuchAlgorithmException {
		String userName = nhanVien.getUserName();
		String password = nhanVien.getPassword();
		String encryptedPassword =nhanVienService.md5("hueunisalt", password);
		Optional<KhachHang> khachHang = khachHangService.findByUserNameAndPassword(userName, encryptedPassword);
		if (khachHang.isPresent()) {
			HttpSession session = request.getSession();
			if(khachHang.get().getEnable()==true) {
				session.setAttribute("account", khachHang.get());
				return "redirect:/";
			}else {
				return "/login/login";
			}
			
		} else {
			Optional<NhanVien> nhanVienFind = nhanVienService.findByUserNameAndPassword(userName, encryptedPassword);
			if (nhanVienFind.isPresent()) {
				NhanVien curNhanVien = nhanVienFind.get();
				HttpSession session = request.getSession();
				session.setAttribute("account", curNhanVien);
				Quyen quyenNhanVien = curNhanVien.getQuyen();
				if (quyenNhanVien.getTenQuyen().equals(MyConstances.ROLE_EMPLOYEES)) {
					return "redirect:/employee/manage-don-hang";
				} else if (quyenNhanVien.getTenQuyen().equals(MyConstances.ROLE_ADMIN)) {
					return "redirect:/manage-don-hang";
				}
			}
		}

		return "/login/login";
	}
	@GetMapping("/he-thong-sieu-thi")
	public String getListCuaHang(Model model) {
		addDataToNavbar(null, model);
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		model.addAttribute("listCuaHang", listCuaHang);
		return "/cuahang/heThongSieuThi";
	}
	@GetMapping("/chi-tiet-cua-hang/{id}")
	public String chiTietCuaHang(Model model,@PathVariable int id) {
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		if(cuaHangFound.isPresent()) {
			CuaHang cuaHang = cuaHangFound.get();
			model.addAttribute("cuaHang",cuaHang);
		}
		return "/cuahang/chiTietCuaHang";
	}
	
	@GetMapping("/find-by-cua-hang/{id}")
	public ModelAndView findByCuaHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/cuahang/findByCuaHang");
		addDataToNavbar(mav, null);
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
//		List<MatHang> listMatHang = matHangService.findAll();
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		if(cuaHangFound.isPresent()) {
			CuaHang cuaHang = cuaHangFound.get();
			String tenCuaHang = cuaHang.getTenCuaHang();
			mav.addObject("tenCuaHang", tenCuaHang);
			List<MatHang> listMatHang = cuaHang.getMatHangs();
			ListMatHangModel listMatHangModel = new ListMatHangModel();
			List<MatHangModel> listMatHangModels = listMatHangModel.getMatHangModel(listMatHang);
			mav.addObject("listMatHangModels", listMatHangModels);
		}
		
		
		
		mav.addObject("listLoaiHang", listLoaiHang);
	
		mav.addObject("soCuaHang", listCuaHang.size());
		mav.addObject("listCuaHang", listCuaHang);
		return mav;
	}
	@GetMapping("/find-by-nhom-hang/{id}")
	public ModelAndView findByNhomHang(@PathVariable int id) {
		
		ModelAndView mav = new ModelAndView("/nhomhang/findByNhomHang");
		addDataToNavbar(mav, null);
		List<MatHangModel> listMatHangModel = matHangService.findByNhomHang(id, mav);
		mav.addObject("listMatHangModels", listMatHangModel);
		return mav;
	}
	
	@GetMapping("/find-by-loai-hang/{id}")
	public ModelAndView findByLoaiHang(@PathVariable int id) {
		
		ModelAndView mav = new ModelAndView("/loaihang/findByLoaiHang");
		addDataToNavbar(mav, null);
		List<MatHangModel> listMatHangModel = matHangService.findByLoaiHang(id, mav);
		mav.addObject("listMatHangModels", listMatHangModel);
		return mav;
	}
	@PostMapping("/find-by-ten")
	public ModelAndView findByTenMatHang(@RequestParam String tenMatHang) {
		ModelAndView mav = new ModelAndView("/mathang/findByTenMatHang");
		addDataToNavbar(mav, null);
		List<MatHangModel> listMatHangModel = matHangService.findByTenHangEquals(tenMatHang);
		mav.addObject("listMatHangModels", listMatHangModel);
		return mav;
	}
	@GetMapping("/manage-infor")
	public ModelAndView manageInforPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/user/infor/manageInfor");
		HttpSession session = request.getSession();
		Object account = session.getAttribute("account");
		if(account instanceof NhanVien) {
			mav.addObject("nhanVien", (NhanVien)account);
			mav.addObject("username", ((NhanVien) account).getUserName());
			nhanVienService.addObjectToEditNhanVien(mav, ((NhanVien) account).getUserName(), new NhanVienModel());
		}
		return mav;
	}
	@PostMapping("/edit-infor/{username}")
	public ModelAndView editInforSubmit(@ModelAttribute("nhanVien") NhanVienModel nhanVienModel,
			@RequestParam("files") MultipartFile[] files,
			@PathVariable String username) throws NoSuchAlgorithmException, ParseException{
		ModelAndView mav = new ModelAndView("redirect:/manage-infor");
		nhanVienService.editNhanVien(files, nhanVienModel, username);
		mav.addObject("username", username);
		return mav;
	}
	
	private void addDataToNavbar(ModelAndView mav, Model  model) {
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<MatHang> listMatHang = matHangService.findAll();
		ListMatHangModel listMatHangModel = new ListMatHangModel();
		List<MatHangModel> listMatHangModels = listMatHangModel.getMatHangModel(listMatHang);
		if(model!=null) {
			model.addAttribute("listLoaiHang", listLoaiHang);
			model.addAttribute("listMatHangModels", listMatHangModels);
			model.addAttribute("soCuaHang", listCuaHang.size());
			model.addAttribute("listCuaHang", listCuaHang);
		}
		if(mav!=null) {
			mav.addObject("listLoaiHang", listLoaiHang);
			mav.addObject("listMatHangModels", listMatHangModels);
			mav.addObject("soCuaHang", listCuaHang.size());
			mav.addObject("listCuaHang", listCuaHang);
		}
		
		
	}
}
