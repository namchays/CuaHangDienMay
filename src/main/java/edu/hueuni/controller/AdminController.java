	package edu.hueuni.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.ChiTietMatHang;
import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.entity.QuaTang;
import edu.hueuni.model.AjaxResponseBody;
import edu.hueuni.model.ChiTietMatHangModel;
import edu.hueuni.model.NhomHangModel;
import edu.hueuni.model.QuaTangModel;
import edu.hueuni.service.AnhMatHangService;
import edu.hueuni.service.ChiTietMatHangService;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.DonDatHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.loaiHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;
import edu.hueuni.service.QuaTangService;

@Controller
public class AdminController {
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
	public static String uploadDirectory = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\static\\img\\mathang\\";


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
		loaiHangService.save(loaiHang);
		return "redirect:/manage-loai-hang/" + 1;
	}

	@GetMapping("/manage-loai-hang/{id}")
	public ModelAndView manageLoaiHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/admin/loaihang/manageLoaiHang");
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		mav.addObject("listLoaiHang", listLoaiHang);
		Optional<LoaiHang> loaiHangFound = loaiHangService.findById(id);
		if (loaiHangFound.isPresent()) {
			List<NhomHang> nhomHangs = loaiHangFound.get().getNhomHangs();
			mav.addObject("listNhomHang", nhomHangs);
		}
		mav.addObject("id", id);

		return mav;

	}

	@GetMapping("/delete-loai-hang/{id}")
	public ModelAndView deleteLoaiHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("redirect:/manage-loai-hang/1");
		loaiHangService.deleteById(id);
		return mav;

	}

	@PostMapping("/edit-loai-hang/{id}")
	public ModelAndView editLoaiHang(@PathVariable int id, @RequestParam(name = "tenLoaiHang") String tenLoaiHang) {
		ModelAndView mav = new ModelAndView("redirect:/manage-loai-hang/" + id);
		Optional<LoaiHang> loaiHangFound = loaiHangService.findById(id);
		if (loaiHangFound.isPresent()) {
			LoaiHang loaiHang = loaiHangFound.get();
			loaiHang.setTenLoaiHang(tenLoaiHang);
			loaiHangService.save(loaiHang);
		}
		return mav;

	}

	/// manage nhom hang
	@PostMapping("/add-nhom-hang/{id}")
	public String addNhomHangSubmit(@RequestParam(name = "tenNhomHang") String tenNhomHang, @PathVariable int id) {
		NhomHang nhomHang = new NhomHang(tenNhomHang);
		Optional<LoaiHang> LoaiHangFound = loaiHangService.findById(id);
		if (LoaiHangFound.isPresent()) {
			nhomHang.setLoaiHang(LoaiHangFound.get());
			nhomHangService.save(nhomHang);
		}

		return "redirect:/manage-loai-hang/" + id;
	}

	@PostMapping("/edit-nhom-hang/{id}")
	public ModelAndView editNhomHang(@PathVariable int id, @RequestParam(name = "tenNhomHang") String tenNhomHang) {
		ModelAndView mav = new ModelAndView("redirect:/manage-loai-hang/1");
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
		ModelAndView mav = new ModelAndView("redirect:/manage-loai-hang/" + idLoaiHang);
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
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<QuaTang> listQuaTang = quaTangService.findAll();
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		QuaTangModel quaTangModel = new QuaTangModel();
		ChiTietMatHangModel chiTietMatHangModel = new ChiTietMatHangModel();
		mav.addObject("listLoaiHang", listLoaiHang);
		mav.addObject("listQuaTang", listQuaTang);
		mav.addObject("listCuaHang", listCuaHang);
		mav.addObject("quaTangModel", quaTangModel);
		mav.addObject("chiTietMatHangModel", chiTietMatHangModel);
		return mav;
	}
	@GetMapping("/edit-mat-hang/{id}")
	public ModelAndView editMatHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/admin/mathang/editMatHang");
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<QuaTang> listQuaTang = quaTangService.findAll();
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		Optional<MatHang> matHangFound = matHangService.findById(id);
		if(matHangFound.isPresent()) {
			MatHang matHang = matHangFound.get();
			List<QuaTang> quaTangs = matHang.getQuaTangs();
			QuaTangModel quaTangModel;
			switch(quaTangs.size()) {
			  case 0:
				   quaTangModel = new QuaTangModel();
			    break;
			  case 1:
				   quaTangModel = new QuaTangModel(quaTangs.get(0).getIdQuaTang()+"","0","0");
				  break;
			  case 2:
				   quaTangModel = new QuaTangModel(quaTangs.get(0).getIdQuaTang()+"",quaTangs.get(1).getIdQuaTang()+"","0");
				  break;
			  case 3:
				   quaTangModel = new QuaTangModel(quaTangs.get(0).getIdQuaTang()+"",quaTangs.get(1).getIdQuaTang()+"",quaTangs.get(2).getIdQuaTang()+"");
			    break;
			  default:
				   quaTangModel = new QuaTangModel();
			}
			
			mav.addObject("quaTangModel", quaTangModel);
			mav.addObject("matHang", matHang);
		}
//		Optional<QuaTang> QuaTangFound = quaTangService.findById(id);
		
		ChiTietMatHangModel chiTietMatHangModel = new ChiTietMatHangModel();
		
		mav.addObject("listLoaiHang", listLoaiHang);
		mav.addObject("listQuaTang", listQuaTang);
		mav.addObject("listCuaHang", listCuaHang);
		
		mav.addObject("chiTietMatHangModel", chiTietMatHangModel);
		
		return mav;
	}

	@PostMapping("/add-mat-hang")
	public ModelAndView addMatHangSubmit(@RequestParam(name = "tenMatHang", required = false) String tenMatHang,
			@RequestParam(name = "giaHang") String giaHang,
			@RequestParam(name = "donViTinh") String donViTinh,
			@RequestParam(name = "xuatXu") String xuatXu,
			@RequestParam(name = "soLuong") String soLuong,
			@RequestParam(name = "tenCuaHang") String tenCuaHang,
			@RequestParam(name = "tenNhomHang") String tenNhomHang,
			@RequestParam(name = "trangThai") String trangThai,
			@ModelAttribute("quaTangModel") @Valid QuaTangModel quaTangModel,
			@ModelAttribute("ChiTietMatHangModel") ChiTietMatHangModel chiTietMatHangModel,
			@RequestParam("files") MultipartFile[] files) {
		System.out.println("TRANG THAI "+ trangThai);
		System.out.println("index"+giaHang.indexOf('$'));
		System.out.println("gia"+giaHang);
		String giaHangChange = giaHang.replaceAll(",","").substring(1);
		String firstPart = giaHangChange.substring(0, giaHangChange.indexOf("."));
		System.out.println(firstPart);
		int giaHangInt = Integer.parseInt(firstPart);
		
		ModelAndView mav = new ModelAndView("redirect:/manage-mat-hang");
		
		//Nhóm hàng ok
		int idNhomHang = Integer.parseInt(tenNhomHang);
		Optional<NhomHang> nhomHang = nhomHangService.findById(idNhomHang);
		NhomHang nhomHangGet = nhomHang.get();
		
		//Thêm quà tặng ok
		List<QuaTang> listQuaTang = addListQuaTang(Integer.parseInt(quaTangModel.getQuaTang1()),Integer.parseInt(quaTangModel.getQuaTang2()),Integer.parseInt(quaTangModel.getQuaTang3()));
			
		
		//thêm mặt hàng 
		MatHang matHang =new MatHang(donViTinh,giaHangInt, Integer.parseInt(soLuong), tenMatHang, Integer.parseInt(trangThai), 
				xuatXu, nhomHangGet, listQuaTang);
		matHangService.save(matHang);
	
		
		System.out.println(matHang.getMaHang());
		
		//thêm chi tiết mặt hàng ok
		addChiTietMatHang(chiTietMatHangModel.getCt1(),chiTietMatHangModel.getNd1(),
						  chiTietMatHangModel.getCt2(),chiTietMatHangModel.getNd2(),
						  chiTietMatHangModel.getCt3(),chiTietMatHangModel.getNd3(),
						  chiTietMatHangModel.getCt4(),chiTietMatHangModel.getNd4(),
						  chiTietMatHangModel.getCt5(),chiTietMatHangModel.getNd5(),
						  chiTietMatHangModel.getCt6(),chiTietMatHangModel.getNd6(),
						  chiTietMatHangModel.getCt7(),chiTietMatHangModel.getNd7(),
						  chiTietMatHangModel.getCt8(),chiTietMatHangModel.getNd8(),
						  chiTietMatHangModel.getCt9(),chiTietMatHangModel.getNd9(),
						  chiTietMatHangModel.getCt10(),chiTietMatHangModel.getNd10(),matHang);
		//Thêm cửa hàng ok 
		int idCuaHang = Integer.parseInt(tenCuaHang);
		
		List<CuaHang> listCuaHang = new ArrayList<CuaHang>();
		if(idCuaHang == 0) {
			listCuaHang = cuaHangService.findAll();
			for (CuaHang cuaHang : listCuaHang) {
				List<MatHang> listMatHang = cuaHang.getMatHangs();
				listMatHang.add(matHang);
				cuaHang.setMatHangs(listMatHang);
				cuaHangService.save(cuaHang);
			}
		}else {
			Optional<CuaHang> cuaHangFound = cuaHangService.findById(idCuaHang);
			CuaHang cuaHang = cuaHangFound.get();
			List<MatHang> listMatHang = cuaHang.getMatHangs();
			listMatHang.add(matHang);
			cuaHang.setMatHangs(listMatHang);
			cuaHangService.save(cuaHang);
		}
	
		//Thêm ảnh ok
		ThemAnh(files,matHang);

		
		return mav;
	}
	@PostMapping("/edit-mat-hang/{id}")
	public ModelAndView editMatHangSubmit(@RequestParam(name = "tenMatHang", required = false) String tenMatHang,
			@RequestParam(name = "giaHang") String giaHang,
			@RequestParam(name = "donViTinh") String donViTinh,
			@RequestParam(name = "xuatXu") String xuatXu,
			@RequestParam(name = "soLuong") String soLuong,
			@RequestParam(name = "tenCuaHang") String tenCuaHang,
			@RequestParam(name = "tenNhomHang") String tenNhomHang,
			@RequestParam(name = "trangThai") String trangThai,
			@ModelAttribute("quaTangModel") @Valid QuaTangModel quaTangModel,
			@ModelAttribute("ChiTietMatHangModel") ChiTietMatHangModel chiTietMatHangModel,
			@RequestParam("files") MultipartFile[] files,
			@PathVariable int id) {
		System.out.println("index"+giaHang.indexOf('$'));
		System.out.println("gia"+giaHang);
		String giaHangChange = giaHang.replaceAll(",","").substring(1);
		String firstPart = giaHangChange.substring(0, giaHangChange.indexOf("."));
		System.out.println(firstPart);
		int giaHangInt = Integer.parseInt(firstPart);
		
		ModelAndView mav = new ModelAndView("redirect:/manage-mat-hang");
		
		//Nhóm hàng ok
		int idNhomHang = Integer.parseInt(tenNhomHang);
		Optional<NhomHang> nhomHang = nhomHangService.findById(idNhomHang);
		NhomHang nhomHangGet = nhomHang.get();
		
		//Thêm quà tặng ok
		List<QuaTang> listQuaTang = addListQuaTang(Integer.parseInt(quaTangModel.getQuaTang1()),Integer.parseInt(quaTangModel.getQuaTang2()),Integer.parseInt(quaTangModel.getQuaTang3()));
		
		
		//edit mat hang
		Optional<MatHang> matHangFound = matHangService.findById(id);
		MatHang matHang = matHangFound.get();
		
		
		

		
		//thêm chi tiết mặt hàng ok
		addChiTietMatHang(chiTietMatHangModel.getCt1(),chiTietMatHangModel.getNd1(),
				chiTietMatHangModel.getCt2(),chiTietMatHangModel.getNd2(),
				chiTietMatHangModel.getCt3(),chiTietMatHangModel.getNd3(),
				chiTietMatHangModel.getCt4(),chiTietMatHangModel.getNd4(),
				chiTietMatHangModel.getCt5(),chiTietMatHangModel.getNd5(),
				chiTietMatHangModel.getCt6(),chiTietMatHangModel.getNd6(),
				chiTietMatHangModel.getCt7(),chiTietMatHangModel.getNd7(),
				chiTietMatHangModel.getCt8(),chiTietMatHangModel.getNd8(),
				chiTietMatHangModel.getCt9(),chiTietMatHangModel.getNd9(),
				chiTietMatHangModel.getCt10(),chiTietMatHangModel.getNd10(),matHang);
		//Thêm cửa hàng ok 
		int idCuaHang = Integer.parseInt(tenCuaHang);
		
		List<CuaHang> listCuaHang = new ArrayList<CuaHang>();
		if(idCuaHang == 0) {
			listCuaHang = cuaHangService.findAll();
			for (CuaHang cuaHang : listCuaHang) {
				List<MatHang> listMatHang = cuaHang.getMatHangs();
				listMatHang.add(matHang);
				cuaHang.setMatHangs(listMatHang);
				cuaHangService.save(cuaHang);
			}
		}else {
			Optional<CuaHang> cuaHangFound = cuaHangService.findById(idCuaHang);
			CuaHang cuaHang = cuaHangFound.get();
			List<MatHang> listMatHang = cuaHang.getMatHangs();
			listMatHang.add(matHang);
			cuaHang.setMatHangs(listMatHang);
			cuaHangService.save(cuaHang);
		}
		
		//Thêm ảnh ok
		ThemAnh(files,matHang);
		matHang.setQuaTangs(listQuaTang);
		matHang.setNhomHang(nhomHangGet);
		matHang.setGiaHang(giaHangInt);
		matHang.setSoLuong(Integer.parseInt(soLuong));
		matHangService.save(matHang);
		return mav;
	}

	private List<QuaTang> addListQuaTang(int idQuaTang1, int idQuaTang2, int idQuaTang3) {
		List<QuaTang> listQuaTangs = new ArrayList<QuaTang>();
		if (addQuaTang(idQuaTang1) != null) {
			listQuaTangs.add(addQuaTang(idQuaTang1));
		}
		if (addQuaTang(idQuaTang2) != null) {
			listQuaTangs.add(addQuaTang(idQuaTang2));
		}
		if (addQuaTang(idQuaTang3) != null) {
			listQuaTangs.add(addQuaTang(idQuaTang3));
		}
		return listQuaTangs;
	}

	private QuaTang addQuaTang(int idQuaTang) {
		if (idQuaTang != 0) {
			Optional<QuaTang> quaTang = quaTangService.findById(idQuaTang);
			if (quaTang.isPresent()) {
				return quaTang.get();
			}
		}
		return null;
	}

	private void addChiTietMatHang(String ct1, String nd1, String ct2, String nd2, String ct3,
			String nd3, String ct4, String nd4, String ct5, String nd5, String ct6, String nd6, String ct7, String nd7,
			String ct8, String nd8, String ct9, String nd9, String ct10, String nd10,MatHang matHang) {
		createChiTiet(ct1, nd1,matHang);
		createChiTiet(ct2, nd2,matHang);
		createChiTiet(ct3, nd3,matHang);
		createChiTiet(ct4, nd4,matHang); 
		createChiTiet(ct5, nd5,matHang); 
		createChiTiet(ct6, nd6,matHang); 
		createChiTiet(ct7, nd7,matHang); 
		createChiTiet(ct8, nd8,matHang); 
		createChiTiet(ct9, nd9,matHang); 
		createChiTiet(ct10, nd10,matHang);
	
	}

	private void createChiTiet(String ct, String nd,MatHang matHang) {
		if (ct != null && nd != null) {
			if (!ct.equals("") && !nd.equals("")) {
				ChiTietMatHang chiTietMatHang = new ChiTietMatHang(ct, nd);
				chiTietMatHang.setMatHang(matHang);
				chiTietMatHangService.save(chiTietMatHang);
			}
		}
	
	}

	private void ThemAnh(MultipartFile[] files,MatHang matHang) {
		List<AnhMatHang> listAnh = new ArrayList<AnhMatHang>();
		new File(uploadDirectory).mkdir();
		String imageURL = null;
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			imageURL = "/img/mathang/" + file.getOriginalFilename();
			AnhMatHang anh = new AnhMatHang();
			anh.setUrl(imageURL);
			System.out.println(imageURL);
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				break;
			}
			anh.setMatHang(matHang);
			anhMatHangService.save(anh);
			listAnh.add(anh);

		}
	
	}

	@PostMapping("/get-nhom-hang-by-loai-hang")
	@ResponseBody
	public ResponseEntity<?> getNhomHangByLoaiHang(@RequestBody int selectedValue, HttpServletRequest request,
			Errors errors) {
		System.out.println(selectedValue);
		AjaxResponseBody result = new AjaxResponseBody();
		if (errors.hasErrors()) {

			result.setMsg(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));

			return ResponseEntity.badRequest().body(result);

		}
		Optional<LoaiHang> loaiHangFound = loaiHangService.findById(selectedValue);
		ArrayList<NhomHang> NhomHangFound = new ArrayList<NhomHang>();
		if (loaiHangFound.isPresent()) {
			result.setMsg("success");
			NhomHangFound = (ArrayList<NhomHang>) nhomHangService.findByLoaiHang(loaiHangFound.get());
		} else {
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
	
	@GetMapping(value = "/delete-mat-hang/{id}")
	public ModelAndView deleteMatHangById(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("redirect:/manage-mat-hang");
		matHangService.deleteById(id);
		return mav;
	}
	
	//Quản lý đơn hàng
	@GetMapping("/manage-don-hang")
	public ModelAndView manageDonHang() {
		ModelAndView mav = new ModelAndView("/admin/donhang/manageDonHang");
		List<DonDatHang> listDonDatHang = donDatHangService.findAll();
		mav.addObject("listDonDatHang", listDonDatHang);
		return mav;
	}
	//quản lý nhân viên
	@GetMapping("/admin/manage-nhan-vien")
	public ModelAndView manageNhanVien() {
		ModelAndView mav = new ModelAndView("/admin/user/manageNhanVien");
		List<NhanVien> listNhanVien = nhanVienService.findAll();
		mav.addObject("listNhanVien", listNhanVien);
		return mav;
	}
	@GetMapping("/admin/manage-khach-hang")
	public ModelAndView manageKhachHang() {
		ModelAndView mav = new ModelAndView("/admin/user/manageKhachHang");
		List<KhachHang> listKhachHang = khachHangService.findAll();
		mav.addObject("listKhachHang", listKhachHang);
		return mav;
	}
	
}
