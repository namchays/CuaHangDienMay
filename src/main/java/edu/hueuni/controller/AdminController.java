package edu.hueuni.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.model.AjaxResponseBody;
import edu.hueuni.model.NhomHangModel;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.LoaiHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.NhomHangService;

@Controller
public class AdminController {
	@Autowired
	private CuaHangService cuaHangService;
	@Autowired
	private LoaiHangService LoaiHangService;
	@Autowired
	private NhomHangService nhomHangService;
	@Autowired
	private MatHangService matHangService;

	// manage don hang

	@GetMapping("/manage-don-hang")
	public String showDonHang() {
		return "/admin/donhang/manageDonHang";
	}

	// manage cua hang
	@GetMapping("admin/manage-cua-hang")
	public ModelAndView showCuaHang() {
		List<CuaHang> cuaHangs = cuaHangService.findAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("listCuaHang", cuaHangs);
		mav.setViewName("/admin/cuahang/manageCuaHang");
		return mav;
	}

	@GetMapping("admin/delete-cua-hang/{id}")
	public String deleteCuaHang(@PathVariable int id) {
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		if (cuaHangFound.isPresent()) {
			cuaHangService.DeleteById(id);
		}
		return "redirect:/admin/manage-cua-hang";
	}

	@GetMapping("admin/edit-cua-hang/{id}")
	public ModelAndView editCuaHang(@PathVariable int id, Model model) {
		Optional<CuaHang> cuaHangFound = cuaHangService.findById(id);
		ModelAndView mav = new ModelAndView("/admin/cuahang/editCuaHang");
		if (cuaHangFound.isPresent()) {
			mav.addObject("cuaHang", cuaHangFound.get());
		}
		return mav;

	}

	@PostMapping("admin/edit-cua-hang/{id}")
	public String editCuaHangSubmit(@PathVariable int id, @ModelAttribute("cuaHang") CuaHang cuaHang) {
		cuaHang.setIdCuaHang(id);
		cuaHangService.save(cuaHang);
		return "redirect:/admin/manage-cua-hang";
	}

	@GetMapping("admin/add-cua-hang")
	public String addCuaHang() {

		return "/admin/cuahang/addCuaHang";
	}

	@PostMapping("admin/add-cua-hang")
	public String addCuaHangSubmit(@RequestParam(name = "tenCuaHang") String tenCuaHang,
			@RequestParam(name = "diaChiCuaHang") String diaChiCuaHang,
			@RequestParam(name = "soDienThoai") String soDienThoai, @RequestParam(name = "iframe") String iframe) {
		CuaHang cuaHang = new CuaHang(diaChiCuaHang, iframe, soDienThoai, tenCuaHang);
		cuaHangService.save(cuaHang);
		return "redirect:/admin/manage-cua-hang";
	}

	// manage loai hang

	@PostMapping("/add-loai-hang")
	public String addLoaiHangSubmit(@RequestParam(name = "tenLoaiHang") String tenLoaiHang) {
		LoaiHang loaiHang = new LoaiHang(tenLoaiHang);
		LoaiHangService.save(loaiHang);
		return "redirect:/admin/manage-loai-hang/" + 1;
	}

	@GetMapping("/manage-loai-hang/{id}")
	public ModelAndView manageLoaiHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/admin/loaihang/manageLoaiHang");
		List<LoaiHang> listLoaiHang = LoaiHangService.findAll();
		mav.addObject("listLoaiHang", listLoaiHang);
		Optional<LoaiHang> loaiHangFound = LoaiHangService.findById(id);
		if (loaiHangFound.isPresent()) {
			List<NhomHang> nhomHangs = loaiHangFound.get().getNhomHangs();
			mav.addObject("listNhomHang", nhomHangs);
		}
		mav.addObject("id", id);

		return mav;

	}

	@GetMapping("/delete-loai-hang/{id}")
	public ModelAndView deleteLoaiHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-loai-hang/1");
		LoaiHangService.deleteById(id);
		return mav;

	}

	@PostMapping("/edit-loai-hang/{id}")
	public ModelAndView editLoaiHang(@PathVariable int id, @RequestParam(name = "tenLoaiHang") String tenLoaiHang) {
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-loai-hang/" + id);
		Optional<LoaiHang> loaiHangFound = LoaiHangService.findById(id);
		if (loaiHangFound.isPresent()) {
			LoaiHang loaiHang = loaiHangFound.get();
			loaiHang.setTenLoaiHang(tenLoaiHang);
			LoaiHangService.save(loaiHang);
		}
		return mav;

	}

	/// manage nhom hang
	@PostMapping("/add-nhom-hang/{id}")
	public String addNhomHangSubmit(@RequestParam(name = "tenNhomHang") String tenNhomHang, @PathVariable int id) {
		NhomHang nhomHang = new NhomHang(tenNhomHang);
		Optional<LoaiHang> LoaiHangFound = LoaiHangService.findById(id);
		if (LoaiHangFound.isPresent()) {
			nhomHang.setLoaiHang(LoaiHangFound.get());
			nhomHangService.save(nhomHang);
		}

		return "redirect:/manage-loai-hang/" + id;
	}

	@PostMapping("/edit-nhom-hang/{id}")
	public ModelAndView editNhomHang(@PathVariable int id, @RequestParam(name = "tenNhomHang") String tenNhomHang) {
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-loai-hang/1");
		Optional<NhomHang> nhomHangFound = nhomHangService.findById(id);
		if (nhomHangFound.isPresent()) {
			NhomHang nhomHang = nhomHangFound.get();
			nhomHang.setTenNhomHang(tenNhomHang);
			nhomHangService.save(nhomHang);
		}
		return mav;

	}

	@GetMapping("/delete-nhom-hang/{id}/{idLoaiHang}")
	public ModelAndView deleteNhomHang(@PathVariable int id, @PathVariable int idLoaiHang) {
		ModelAndView mav = new ModelAndView("redirect:/admin/manage-loai-hang/" + idLoaiHang);
		nhomHangService.deleteById(id);
		return mav;

	}

	// manage mặt hàng
	@GetMapping("/manage-mat-hang")
	public ModelAndView showMatHang() {
		ModelAndView mav = new ModelAndView("/admin/mathang/manageMatHang");
		List<MatHang> listMatHang = matHangService.findAll();
		mav.addObject("listMatHang", listMatHang);
		return mav;
	}

	@GetMapping("/add-mat-hang")
	public ModelAndView addMatHang() {
		ModelAndView mav = new ModelAndView("/admin/mathang/addMatHang");
		List<LoaiHang> listLoaiHang = LoaiHangService.findAll();
		mav.addObject("listLoaiHang", listLoaiHang);
		return mav;
	}

	@PostMapping("/get-nhom-hang-by-loai-hang")
	@ResponseBody
	public ResponseEntity<?> getNhomHangByLoaiHang(@RequestBody int selectedValue, HttpServletRequest request,Errors errors) {
		System.out.println(selectedValue);
		AjaxResponseBody result = new AjaxResponseBody();
		 if (errors.hasErrors()) {

	            result.setMsg(errors.getAllErrors()
	                        .stream().map(x -> x.getDefaultMessage())
	                        .collect(Collectors.joining(",")));

	            return ResponseEntity.badRequest().body(result);

	        }
		Optional<LoaiHang> loaiHangFound = LoaiHangService.findById(selectedValue);
		ArrayList<NhomHang> NhomHangFound  =new ArrayList<NhomHang>();
		if(loaiHangFound.isPresent()) {
			result.setMsg("success");
			NhomHangFound =(ArrayList<NhomHang>) nhomHangService.findByLoaiHang(loaiHangFound.get());
		}else {
			result.setMsg("error");
		}

		
		ArrayList<NhomHangModel> nhomHangReturn = new ArrayList<NhomHangModel>();

		System.out.println(NhomHangFound);
		for (NhomHang nhomHang : NhomHangFound) {
			NhomHangModel nhomHangModel = new NhomHangModel(nhomHang.getIdNhomHang(), nhomHang.getTenNhomHang());
			nhomHangReturn.add(nhomHangModel);
		}
		result.setNhomHangModel(nhomHangReturn);
		return ResponseEntity.ok(result);
	}

}
