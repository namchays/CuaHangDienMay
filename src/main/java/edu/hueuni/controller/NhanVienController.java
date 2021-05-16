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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.entity.QuaTang;
import edu.hueuni.model.AjaxResponseBody;
import edu.hueuni.model.ChiTietMatHangItem;
import edu.hueuni.model.ChiTietMatHangModel;
import edu.hueuni.model.NhomHangModel;
import edu.hueuni.model.QuaTangItem;
import edu.hueuni.model.QuaTangModel;
import edu.hueuni.service.AnhMatHangService;
import edu.hueuni.service.BaiDangService;
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
public class NhanVienController {

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
	private BaiDangService baiDangService;
	
	public static String uploadDirectory = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\static\\img\\mathang\\";
	
	public static String uploadQuaTangDirectory = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\static\\img\\quatang\\";
	
	///----------------------------------------Manage quà tặng------------------------------------------------------
	@GetMapping("/manage-qua-tang")
	public ModelAndView manageQuaTang() {
		ModelAndView mav = new ModelAndView("/employee/quatang/manageQuaTang");
		List<QuaTang> listQuaTang = quaTangService.findAll();
		mav.addObject("listQuaTang", listQuaTang);
		
		return mav;
		
	}
	
	@GetMapping("/add-qua-tang")
	public ModelAndView addQuaTang() {
		ModelAndView mav = new ModelAndView("/employee/quatang/addQuaTang");
		return mav;
		
	}
	@GetMapping("/edit-qua-tang/{id}")
	public ModelAndView editQuaTang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/employee/quatang/editQuaTang"); 
		Optional<QuaTang> quaTangFound = quaTangService.findById(id);
		if(quaTangFound.isPresent()) {
			mav.addObject("quaTang", quaTangFound.get());
		}
		return mav;
	}
	
	@GetMapping("/delete-qua-tang/{id}")
	public ModelAndView deleteQuaTang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("redirect:/manage-qua-tang");
		quaTangService.deleteById(id);
		return mav;
		
	}
	
	@PostMapping("/add-qua-tang")
	public ModelAndView addQuaTangSubmit(
			@RequestParam(name = "tenQuaTang") String tenQuaTang,
			@RequestParam(name = "soLuong") String soLuong,
			@RequestParam(name = "gia") String gia,
			@RequestParam("files") MultipartFile[] files
			) {
		ModelAndView mav = new ModelAndView("redirect:/manage-qua-tang");
		int giaHangInt;
		try {
			giaHangInt = Integer.parseInt(gia);
		}catch (Exception e) {
			System.out.println(e.toString());
			String giaHangChange = gia.replaceAll(",","").substring(1);
			String firstPart = giaHangChange.substring(0, giaHangChange.indexOf("."));
			System.out.println(firstPart);
			giaHangInt = Integer.parseInt(firstPart);
		}
		String anhQuaTang = ThemAnh(files);
		QuaTang quaTang = new QuaTang(giaHangInt, Integer.parseInt(soLuong), tenQuaTang, anhQuaTang);
		quaTangService.save(quaTang);
		return mav;
		
	}
	
	
	@PostMapping("/add-loai-hang")
	public String addLoaiHangSubmit(@RequestParam(name = "tenLoaiHang") String tenLoaiHang) {
		LoaiHang loaiHang = new LoaiHang(tenLoaiHang);
		loaiHangService.save(loaiHang);
		return "redirect:/manage-loai-hang/" + 1;
	}

	@GetMapping("/manage-loai-hang/{id}")
	public ModelAndView manageLoaiHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/employee/loaihang/manageLoaiHang");
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		mav.addObject("listLoaiHang", listLoaiHang);
		LoaiHang loaiHangFound = loaiHangService.findById(id);
		if (loaiHangFound!=null) {
			List<NhomHang> nhomHangs = loaiHangFound.getNhomHangs();
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
		LoaiHang loaiHangFound = loaiHangService.findById(id);
		if (loaiHangFound!=null) {
		
			loaiHangFound.setTenLoaiHang(tenLoaiHang);
			loaiHangService.save(loaiHangFound);
		}
		return mav;

	}

	/// manage nhom hang
	@PostMapping("/add-nhom-hang/{id}")
	public String addNhomHangSubmit(@RequestParam(name = "tenNhomHang") String tenNhomHang, @PathVariable int id) {
		NhomHang nhomHang = new NhomHang(tenNhomHang);
		LoaiHang LoaiHangFound = loaiHangService.findById(id);
		if (LoaiHangFound!=null) {
			nhomHang.setLoaiHang(LoaiHangFound);
			nhomHangService.save(nhomHang);
		}

		return "redirect:/manage-loai-hang/" + id;
	}

	@PostMapping("/edit-nhom-hang/{id}")
	public ModelAndView editNhomHang(@PathVariable int id, @RequestParam(name = "tenNhomHang") String tenNhomHang) {
		ModelAndView mav = new ModelAndView("redirect:/manage-loai-hang/1");
		NhomHang nhomHang = nhomHangService.findById(id);
		if (nhomHang!=null) {
		
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
		ModelAndView mav = new ModelAndView("/employee/mathang/manageMatHang");
		List<MatHang> listMatHang = matHangService.findAll();
		mav.addObject("listMatHang", listMatHang);
		return mav;
	}

	@GetMapping("/add-mat-hang")
	public ModelAndView addMatHang() {
		ModelAndView mav = new ModelAndView("/employee/mathang/addMatHang");
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<QuaTang> listQuaTang = quaTangService.findAll();
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		QuaTangModel quaTangModel = new QuaTangModel();
		
			quaTangModel.addQuaTangItem(new QuaTangItem(0));
	
		ChiTietMatHangModel chiTietMatHangModel = new ChiTietMatHangModel();
		 for (int i = 1; i <= 3; i++) {
			 chiTietMatHangModel.addChiTietMatHangItem(new ChiTietMatHangItem());
		    }
		
		mav.addObject("listLoaiHang", listLoaiHang);
		mav.addObject("listQuaTang", listQuaTang);
		mav.addObject("listCuaHang", listCuaHang);
		mav.addObject("quaTangModel", quaTangModel);
		mav.addObject("chiTietMatHangModel", chiTietMatHangModel);
		return mav;
	}
	@GetMapping("/edit-mat-hang/{id}")
	public ModelAndView editMatHang(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/employee/mathang/editMatHang");
		List<LoaiHang> listLoaiHang = loaiHangService.findAll();
		List<QuaTang> listQuaTang = quaTangService.findAll();
		List<CuaHang> listCuaHang = cuaHangService.findAll();
		Optional<MatHang> matHangFound = matHangService.findById(id);
		if(matHangFound.isPresent()) {
			//thêm quà tặng vào form
			MatHang matHangGet = matHangFound.get();
			List<QuaTang> listQuaTangs = matHangGet.getQuaTangs();
			if(listQuaTangs!=null && listQuaTang.size()>0) {
				QuaTangModel quaTangModel = new QuaTangModel();
				for (QuaTang quaTang : listQuaTangs) {
					quaTangModel.addQuaTangItem(new QuaTangItem(quaTang.getIdQuaTang()));
				}
				mav.addObject("quaTangModel", quaTangModel);
				
			}else {
				QuaTangModel quaTangModel = new QuaTangModel();
				quaTangModel.addQuaTangItem(new QuaTangItem(0));
				mav.addObject("quaTangModel", quaTangModel);
			}
			//thêm chi tiết vào form
			ChiTietMatHangModel chiTietMatHangModel = new ChiTietMatHangModel();
			 List<ChiTietMatHang> listChiTietMatHang = matHangGet.getChiTietMatHangs();
			 if(listChiTietMatHang!=null && listChiTietMatHang.size()>0) {
				 for (ChiTietMatHang chiTietMatHang : listChiTietMatHang) {
					chiTietMatHangModel.addChiTietMatHangItem(new ChiTietMatHangItem(chiTietMatHang.getTenChiTiet(), chiTietMatHang.getThongTinChiTiet()));
				}
			 }else {
				 for (int i = 1; i <= 3; i++) {
					 chiTietMatHangModel.addChiTietMatHangItem(new ChiTietMatHangItem());
				    }
			 }
			 mav.addObject("chiTietMatHangModel", chiTietMatHangModel);
			 mav.addObject("matHang", matHangFound.get());
			 mav.addObject("idNhomHang", matHangGet.getNhomHang().getIdNhomHang());
			 mav.addObject("idLoaiHang", matHangGet.getNhomHang().getLoaiHang().getIdLoaiHang());
			 
		}
		mav.addObject("id", id);
		mav.addObject("listLoaiHang", listLoaiHang);
		mav.addObject("listQuaTang", listQuaTang);
		mav.addObject("listCuaHang", listCuaHang);
		
		
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
			@ModelAttribute("ChiTietMatHangModel") @Valid ChiTietMatHangModel chiTietMatHangModel,
			@RequestParam("files") MultipartFile[] files,
			@RequestParam("fileBaiDang") MultipartFile[] fileBaiDang,
			@RequestParam(name = "tieuDe") String tieuDe,
			@RequestParam(name = "noiDung") String noiDung,
			HttpServletRequest request
			) {
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
		NhomHang nhomHang = nhomHangService.findById(idNhomHang);

		
		//Thêm quà tặng ok
//		List<QuaTang> listQuaTang = addListQuaTang(Integer.parseInt(quaTangModel.getQuaTang1()),Integer.parseInt(quaTangModel.getQuaTang2()),Integer.parseInt(quaTangModel.getQuaTang3()));
		List<QuaTangItem> listQuaTangItem = quaTangModel.getQuaTangItem();
		List<QuaTang> listQuaTangs = new ArrayList<QuaTang>();
		for (QuaTangItem quaTangItem : listQuaTangItem) {
			Optional<QuaTang> quaTangFound = quaTangService.findById(quaTangItem.getId());
			if(quaTangFound.isPresent()) {
				listQuaTangs.add(quaTangFound.get());
			}
		}
		
		//thêm mặt hàng 
		MatHang matHang =new MatHang(donViTinh,giaHangInt, Integer.parseInt(soLuong), tenMatHang, Integer.parseInt(trangThai), 
				xuatXu, nhomHang, listQuaTangs);
		matHangService.save(matHang);
	
		
		System.out.println(matHang.getMaHang());
		
		//thêm chi tiết mặt hàng ok
		
		List<ChiTietMatHangItem> listChiTietMatHangItem = chiTietMatHangModel.getChiTietMatHangItem();
		for (ChiTietMatHangItem chiTietMatHangItem : listChiTietMatHangItem) {
			createChiTiet(chiTietMatHangItem.getTenChiTiet(), chiTietMatHangItem.getNoiDungChiTiet(), matHang);
		}

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
		//Thêm bài đăng
		HttpSession session = request.getSession();
		baiDangService.addBaiDang(tieuDe, fileBaiDang, noiDung, session, matHang);
		
		return mav;
	}
	@PostMapping("/edit-mat-hang/{id}")
	public ModelAndView editMatHangSubmit(@RequestParam(name = "tenHang", required = false) String tenMatHang,
			@RequestParam(name = "giaHang") String giaHang,
			@RequestParam(name = "donViTinh") String donViTinh,
			@RequestParam(name = "xuatXu") String xuatXu,
			@RequestParam(name = "soLuong") String soLuong,
			@RequestParam(name = "tenCuaHang") String tenCuaHang,
			@RequestParam(name = "tenNhomHang") String tenNhomHang,
			@RequestParam(name = "trangThai") String trangThai,
			@ModelAttribute("quaTangModel") @Valid QuaTangModel quaTangModel,
			@ModelAttribute("ChiTietMatHangModel") @Valid ChiTietMatHangModel chiTietMatHangModel,
			@RequestParam("files") MultipartFile[] files,
			@PathVariable int id
			) {
		ModelAndView mav = new ModelAndView("redirect:/manage-mat-hang");
		System.out.println("TRANG THAI "+ trangThai);
		System.out.println("index"+giaHang.indexOf('$'));
		System.out.println("gia"+giaHang);
		int giaHangInt;
		try {
			giaHangInt = Integer.parseInt(giaHang);
		}catch (Exception e) {
			System.out.println(e.toString());
			String giaHangChange = giaHang.replaceAll(",","").substring(1);
			String firstPart = giaHangChange.substring(0, giaHangChange.indexOf("."));
			System.out.println(firstPart);
			giaHangInt = Integer.parseInt(firstPart);
		}
		
		
		
		
		//Nhóm hàng chưa set
		int idNhomHang = Integer.parseInt(tenNhomHang);
		NhomHang nhomHang = nhomHangService.findById(idNhomHang);

		
		//Thêm quà tặng chưa set
		List<QuaTangItem> listQuaTangItem = quaTangModel.getQuaTangItem();
		List<QuaTang> listQuaTangs = new ArrayList<QuaTang>();
		for (QuaTangItem quaTangItem : listQuaTangItem) {
			Optional<QuaTang> quaTangFound = quaTangService.findById(quaTangItem.getId());
			if(quaTangFound.isPresent()) {
				listQuaTangs.add(quaTangFound.get());
			}
		}
		
		//sửa mặt hàng 
		Optional<MatHang> matHangFound = matHangService.findById(id);
		if(matHangFound.isPresent()) {
			MatHang matHang = matHangFound.get();
		
			
			
			System.out.println(matHang.getTenHang());
			System.out.println(matHang);
			//thêm chi tiết mặt hàng ok
			chiTietMatHangService.deleteByMatHang(matHang);
		
			List<ChiTietMatHangItem> listChiTietMatHangItem = chiTietMatHangModel.getChiTietMatHangItem();
			for (ChiTietMatHangItem chiTietMatHangItem : listChiTietMatHangItem) {
				createChiTiet(chiTietMatHangItem.getTenChiTiet(), chiTietMatHangItem.getNoiDungChiTiet(), matHang);
			}
			
			//Thêm cửa hàng  chưa
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
			
			//Thêm ảnh chưa check
			anhMatHangService.deleteByMatHang(matHang);
			ThemAnh(files,matHang);
			
			
			//set value
			
			matHang.setNhomHang(nhomHang);
			matHang.setQuaTangs(listQuaTangs);
			matHang.setGiaHang(giaHangInt);
			matHang.setSoLuong(Integer.parseInt(soLuong));
			matHang.setDonViTinh(donViTinh);
			matHang.setXuatXu(xuatXu);
			matHang.setTrangThai(Integer.parseInt(trangThai));
			matHang.setTenHang(tenMatHang);
			
			
			matHangService.save(matHang);
		}
		
	
		return mav;
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
	private String ThemAnh(MultipartFile[] files) {
		new File(uploadQuaTangDirectory).mkdir();
		String imageURL = null;
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			imageURL = "/img/quatang/" + file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadQuaTangDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				break;
			}
			
			
		}
		return imageURL;
		
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
		LoaiHang loaiHangFound = loaiHangService.findById(selectedValue);
		ArrayList<NhomHang> NhomHangFound = new ArrayList<NhomHang>();
		if (loaiHangFound!=null) {
			result.setMsg("success");
			NhomHangFound = (ArrayList<NhomHang>) nhomHangService.findByLoaiHang(loaiHangFound);
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
		ModelAndView mav = new ModelAndView("/employee/donhang/manageDonHang");
		List<DonDatHang> listDonDatHang = donDatHangService.findAll();
		mav.addObject("listDonDatHang", listDonDatHang);
		return mav;
	}
	@GetMapping("/duyet-don-hang/{soHoaDon}")
	public ModelAndView duyetDonHang(@PathVariable int soHoaDon,@RequestParam String trangThai) {
		ModelAndView mav = new ModelAndView("redirect:/manage-don-hang");
		DonDatHang donDatHang = donDatHangService.findById(soHoaDon);
		donDatHang.setTrangThai(Integer.parseInt(trangThai));
		donDatHangService.save(donDatHang);
		return mav;
	}
	@GetMapping("/delete-don-hang/{soHoaDon}")
	public ModelAndView xoaDonHang(@PathVariable int soHoaDon) {
		ModelAndView mav = new ModelAndView("redirect:/manage-don-hang");
		
		donDatHangService.deletById(soHoaDon);
		return mav;
	}
}
