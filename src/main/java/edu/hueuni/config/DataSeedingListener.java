package edu.hueuni.config;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import edu.hueuni.entity.AnhMatHang;
import edu.hueuni.entity.BaiDang;
import edu.hueuni.entity.BinhLuan;
import edu.hueuni.entity.ChiTietDatHang;
import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.DonDatHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.MatHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.entity.QuaTang;
import edu.hueuni.entity.Quyen;
import edu.hueuni.entity.TraLoi;
import edu.hueuni.service.AnhMatHangService;
import edu.hueuni.service.BaiDangService;
import edu.hueuni.service.BinhLuanService;
import edu.hueuni.service.ChiTietDatHangService;
import edu.hueuni.service.ChiTietMatHangService;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.DonDatHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.loaiHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;
import edu.hueuni.service.QuaTangService;
import edu.hueuni.service.QuyenService;
import edu.hueuni.service.TraLoiService;
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private QuyenService quyenService;
	@Autowired
	private NhanVienService nhanvienService;
	@Autowired 
	private KhachHangService khachHangService;
	@Autowired
	private loaiHangService loaiHangService;
	@Autowired
	private NhomHangService nhomHangService; 
	@Autowired
	private CuaHangService cuaHangService;
	@Autowired
	private QuaTangService quaTangService;
	@Autowired
	private MatHangService matHangService;
	@Autowired
	private DonDatHangService donDatHangService;
	@Autowired
	private ChiTietDatHangService chiTietDatHangService;
	@Autowired
	private BaiDangService baiDangService;
	@Autowired 
	private BinhLuanService binhLuanService;
	@Autowired
	private TraLoiService traLoiService;	
	@Autowired
	private AnhMatHangService anhMatHangService;
	@Autowired
	private ChiTietMatHangService chiTietMatHangService;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// T??? ?????ng sinh c??c Qu???n trong database khi c??n thi???u

				if (quyenService.findByTenQuyen(MyConstances.ROLE_EMPLOYEES)==null) {
					quyenService.save(new Quyen(MyConstances.ROLE_EMPLOYEES));
				}

				if (quyenService.findByTenQuyen(MyConstances.ROLE_ADMIN)==null) {
					quyenService.save(new Quyen(MyConstances.ROLE_ADMIN));
				}
		// T??? ?????ng sinh ra c??c user trong csdl
				if(nhanvienService.findByUserName("admin")==null) {
					Quyen quyenAdmin = quyenService.findByTenQuyen(MyConstances.ROLE_ADMIN);
					if(quyenAdmin!=null) {
					
					    try {
					    	String ngayLamViecString="2020/1/1";  
					    	String ngaySinhString ="2000/1/1";
							Date ngayLamViec=new SimpleDateFormat("yyyy/MM/dd").parse(ngayLamViecString);
							Date ngaySinh=new SimpleDateFormat("yyyy/MM/dd").parse(ngaySinhString);
							
							NhanVien admin = new NhanVien("admin","???? n???ng", "03243235574", 1, 1000000,  ngayLamViec,	 ngaySinh,  "hoilamchi1", 2000000,  "L?? Tr?????ng Nam");
							admin.setQuyen(quyenAdmin);
							nhanvienService.save(admin);
					    } catch (ParseException e) {
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}  
					
					}
					
				}
				if(nhanvienService.findByUserName("employee")==null) {
					Quyen quyenEmployee = quyenService.findByTenQuyen(MyConstances.ROLE_EMPLOYEES);
					if(quyenEmployee!=null) {
					
						try {
							String ngayLamViecString="2020/11/1";  
					    	String ngaySinhString ="2000/12/1";
							Date ngayLamViec = new SimpleDateFormat("yyyy/MM/dd").parse(ngayLamViecString);
							Date ngaySinh=new SimpleDateFormat("yyyy/MM/dd").parse(ngaySinhString);
							NhanVien employee = new NhanVien("employee","Hu???", "032213374", 1, 6490000,  ngayLamViec,	 ngaySinh,  "hoilamchi1", 400000,  "L?? Thanh Tr??");
							employee.setQuyen(quyenEmployee);
							nhanvienService.save(employee);
						} catch (ParseException | NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
						
					}
					
				}
				if(nhanvienService.findByUserName("nhanvien1")==null) {
					Quyen quyenEmployee = quyenService.findByTenQuyen(MyConstances.ROLE_EMPLOYEES);
					if(quyenEmployee!=null) {
					
						try {
							String ngayLamViecString="2020/1/12";  
					    	String ngaySinhString ="2000/5/12";
							Date ngayLamViec = new SimpleDateFormat("yyyy/MM/dd").parse(ngayLamViecString);
							Date ngaySinh=new SimpleDateFormat("yyyy/MM/dd").parse(ngaySinhString);
							NhanVien employee = new NhanVien("nhanvien1","V??ng T??u", "0322152764", 1, 6532000,  ngayLamViec,ngaySinh,  "hoilamchi1", 65465400,  "L?? Thanh T??");
							employee.setQuyen(quyenEmployee);
							nhanvienService.save(employee);
						} catch (ParseException | NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
					
				}
				if(khachHangService.findByUserName("user")==null) {
					try {
						String ngaySinhString ="2000/5/11";
						Date ngaySinh=new SimpleDateFormat("yyyy/MM/dd").parse(ngaySinhString);
						khachHangService.save(new KhachHang("user", "hu???", 0, ngaySinh, "hoilamchi1", "09329174163", "L?? Tr?????ng Nam"));
					} catch (ParseException | NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(khachHangService.findByUserName("khachhang")==null) {
					String ngaySinhString ="2000/5/12";
					Date ngaySinh;
					try {
						ngaySinh = new SimpleDateFormat("yyyy/MM/dd").parse(ngaySinhString);
						khachHangService.save(new KhachHang("khachhang", "hu???", 0, ngaySinh, "hoilamchi1", "09329174163", "L?? Tr?????ng Nam"));
					} catch (ParseException | NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
					
				}
				if(khachHangService.findByUserName("khachhang1")==null) {
					String ngaySinhString ="1999/2/12";
					Date ngaySinh;
					try {
						ngaySinh = new SimpleDateFormat("yyyy/MM/dd").parse(ngaySinhString);
						khachHangService.save(new KhachHang("khachhang1", "???? n???ng", 0, ngaySinh, "hoilamchi", "09329174163", "L?? Tr?????ng Nam"));
					} catch (ParseException | NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
		//T??? ?????ng sinh ra c??c lo???i h??ng trong CSDL
				if(loaiHangService.findByTenLoaiHang(MyConstances.DI_DONG).size()==0) {
					loaiHangService.save(new LoaiHang(MyConstances.DI_DONG));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.LAPTOP).size()==0) {
					loaiHangService.save(new LoaiHang(MyConstances.LAPTOP));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.DIEN_TU).size()==0) {
					loaiHangService.save(new LoaiHang(MyConstances.DIEN_TU));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.DIEN_LANH).size()==0) {
					loaiHangService.save(new LoaiHang(MyConstances.DIEN_LANH));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.DIA_DUNG).size()==0) {
					loaiHangService.save(new LoaiHang(MyConstances.DIA_DUNG));
				}
		// T??? ?????ng sinh ra c??c nh??m h??ng DienTu
				createNhomHang(MyConstances.AMPLY, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.TIVI_SONY, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.TIVI_SAMSUNG, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.TIVI_LG, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.TIVI_TCL, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.DAN_MAY, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.LOA_BLUETOOTH, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.LOA_THANH, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.LOA_KARAOKE, MyConstances.DIEN_TU);
				createNhomHang(MyConstances.MICRO, MyConstances.DIEN_TU);
				
		//T??? ?????ng sinh ra c??c nh??m h??ng ??i???n l???nh
				createNhomHang(MyConstances.MAY_LANH, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.MAY_GIAT, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.MAY_SAY, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.TU_LANH, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.TU_DONG, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.TU_MAT, MyConstances.DIEN_LANH);
				
		//T??? d???ng sinh ra c??c h??ng GIA D???NG
				createNhomHang(MyConstances.QUAT_DIEN, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.QUAT_DIEU_HOA, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.NOI_CHIEN_KHONG_DAU, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.LO_VI_SONG, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.NOI_COM_DIEN, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.BEP_GAS, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.BEP_TU, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.NOI_AP_SUAT, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.NOI_LAU_DIEN, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.MAY_XAY_SINH_TO, MyConstances.GIA_DUNG);
				createNhomHang(MyConstances.TU_MAT, MyConstances.GIA_DUNG);
				
		//T??? ?????ng sinh ra c??c h??ng dienthoai
				createNhomHang(MyConstances.IPHONE, MyConstances.DI_DONG);
				createNhomHang(MyConstances.SAMSUNG, MyConstances.DI_DONG);
				createNhomHang(MyConstances.OPPO, MyConstances.DI_DONG);
				createNhomHang(MyConstances.VSMART, MyConstances.DI_DONG);
				createNhomHang(MyConstances.XIAOMI, MyConstances.DI_DONG);
				createNhomHang(MyConstances.NOKIA, MyConstances.DI_DONG);
				createNhomHang(MyConstances.VIVO, MyConstances.DI_DONG);
				createNhomHang(MyConstances.REALME, MyConstances.DI_DONG);
	
		//T??? ?????ng sinh ra c??c h??ng laptop
				createNhomHang(MyConstances.MACBOOK, MyConstances.LAPTOP);
				createNhomHang(MyConstances.ASUS, MyConstances.LAPTOP);
				createNhomHang(MyConstances.HP, MyConstances.LAPTOP);
				createNhomHang(MyConstances.LENOVO, MyConstances.LAPTOP);
				createNhomHang(MyConstances.ACER, MyConstances.LAPTOP);
				createNhomHang(MyConstances.DELL, MyConstances.LAPTOP);
				createNhomHang(MyConstances.LG, MyConstances.LAPTOP);
				

				
		// T??? ?????ng sinh ra c??c qu?? t???ng
				if(quaTangService.findByTenQuaTang("Chu???t kh??ng d??y").isEmpty()) {
					QuaTang quaTang = new QuaTang(10000,20,"Chu???t kh??ng d??y","/img/quatang/chuot-khong-day-logitech-m170-den-1-600x600.jpg");
					quaTangService.save(quaTang);
				}
				if(quaTangService.findByTenQuaTang("Balo du l???ch").isEmpty()) {
					QuaTang quaTang = new QuaTang(10000,20,"Balo du l???ch","/img/quatang/tai-nghe-bluetooth-samsung-itfit-a08c.jpg");
					quaTangService.save(quaTang);
				}
				if(quaTangService.findByTenQuaTang("Tai nghe bluetooth").isEmpty()) {
					QuaTang quaTang = new QuaTang(10000,20,"Tai nghe bluetooth","/img/quatang/balo-du-lich-do2-888-275k.jpg");
					quaTangService.save(quaTang);
				}
	
				
		// T??? ?????n sinh ra c??c m???t h??ng
				themMatHang(MyConstances.IPHONE_8, MyConstances.IPHONE,"Tai nghe bluetooth");
				themMatHang(MyConstances.IPHONE_X, MyConstances.IPHONE,"Balo du l???ch");
				themMatHang(MyConstances.ACER_LAPTOP, MyConstances.ACER,"Balo du l???ch");
				themMatHang(MyConstances.QUAT_LUNG_ASIA, MyConstances.QUAT_DIEN,"Balo du l???ch");
				//T??? d???ng sinh ra c??c c???a h??ng
				if(cuaHangService.findByTenCuaHang(MyConstances.DIEN_MAY_DUC_TAI).size()==0) {
					CuaHang cuaHang1 = new CuaHang("???????ng H??ng V????ng, An C???u, Th??nh ph??? Hu???, Th???a Thi??n Hu???, Vietnam",MyConstances.IFRAME_DIEN_MAY_DUC_TAI,"0827307304",MyConstances.DIEN_MAY_DUC_TAI);
					List<MatHang> listMatHang = matHangService.findAll();
					cuaHang1.setMatHangs(listMatHang);
					cuaHangService.save(cuaHang1);
				
				}
				if(cuaHangService.findByTenCuaHang(MyConstances.DIEN_MAY_PHUC_MAI).size() ==0) {
					CuaHang cuaHang2 = new CuaHang("197 Phan ????ng L??u, Ph?? Ho??, Th??nh ph??? Hu???, Th???a Thi??n Hu???, Vietnam",MyConstances.IFRAME_DIEN_MAY_PHUC_MAI,"0827307305",MyConstances.DIEN_MAY_PHUC_MAI);
					List<MatHang> listMatHang = matHangService.findAll();
					cuaHang2.setMatHangs(listMatHang);
					cuaHangService.save(cuaHang2);
				}
				if(cuaHangService.findByTenCuaHang(MyConstances.DIEN_MAY_HONG_LOI).size() ==0) {
					CuaHang cuaHang3 = new CuaHang("L?? Du???n, Ph?? Thu??n, Th??nh ph??? Hu???, Hu???, Vietnam",MyConstances.IFRAME_DIEN_MAY_HONG_LOI,"842343522248",MyConstances.DIEN_MAY_HONG_LOI);
					cuaHangService.save(cuaHang3);
				}	
//		T??? ?????ng sinh ra c??c chi ti???t m???t h??ng
				
				
		//T??? ?????ng sinh ra c??c ????n ?????t h??ng
				
				addDonDatHang(MyConstances.HUE);
				addDonDatHang(MyConstances.DA_NANG);
				addDonDatHang(MyConstances.NGUYEN_HUE);
				addDonDatHang(MyConstances.DUY_TAN);
				
		// Th??m chi ti???t ????n h??ng
			addChiTietDonHang(MyConstances.IPHONE_8, MyConstances.HUE);
			addChiTietDonHang(MyConstances.IPHONE_X, MyConstances.DA_NANG);
//			addChiTietDonHang(MyConstances.ACER, MyConstances.DUY_TAN);
		
		//Th??m ???nh m???t h??ng
			themAnhMatHang(MyConstances.IPHONE_8,"/img/mathang/iPhone-8-Plus-256GB-Gold-1-1.png");
			themAnhMatHang(MyConstances.QUAT_LUNG_ASIA,"/img/mathang/quat-lung-asia-a16019-xv0-thumb-ksp.jpg");
			themAnhMatHang(MyConstances.ACER_LAPTOP,"/img/mathang/41365_aspire_a514_56_ha7.jpg");
			themAnhMatHang(MyConstances.IPHONE_X,"/img/mathang/iPhone-X-64GB-Silver-1-1-1-1.png");
			
			
		//Th??m b??i ????ng
			/// iphone 8
			List<MatHang> listMatHang = matHangService.findByTenHang(MyConstances.IPHONE_8);
			if(listMatHang.size()>0) {
				if(baiDangService.findByTieuDe(MyConstances.TIEU_DE_SAMSUNG).size()==0) {
					BaiDang baiDang = new BaiDang(MyConstances.NOI_DUNG_SAMSUNG, MyConstances.TIEU_DE_SAMSUNG, "admin","/img/baidang/Canifa-phieu-mua-hang.jpg", listMatHang.get(0));
					baiDangService.save(baiDang);
				}
				
			}
		///them binh luan
			if(binhLuanService.findByNoiDung(MyConstances.BINH_LUAN).size() == 0) {
				List<BaiDang> listBaiDang = baiDangService.findByTieuDe(MyConstances.TIEU_DE_SAMSUNG);
				if(listBaiDang!=null) {
					if(listBaiDang.size()>0) {
						BinhLuan binhLuan = new BinhLuan(0, MyConstances.BINH_LUAN, "user", listBaiDang.get(0));
						binhLuan.setThoiGian(new Date());
						binhLuanService.save(binhLuan);
					}
				}
				
			}
		//them tra loi
			if(traLoiService.findByNoiDung(MyConstances.TRA_LOI).size() == 0) {
				List<BinhLuan> listBinhLuan = binhLuanService.findByNoiDung(MyConstances.BINH_LUAN);
				if(listBinhLuan!=null) {
					if(listBinhLuan.size() > 0) {
						TraLoi traLoi = new TraLoi(0,MyConstances.TRA_LOI,null,"admin");
						traLoi.setThoiGian(new Date());
						traLoi.setBinhLuan(listBinhLuan.get(0));
						traLoiService.save(traLoi);
					}
				}
			}
			
				
				
	}
	private void themAnhMatHang(String tenHang,String tenAnh) {
		List<MatHang> listMatHang = matHangService.findByTenHang(tenHang);
		if(listMatHang.size()>0) {
			if(anhMatHangService.findByUrl(tenAnh).size()==0) {
				AnhMatHang anhMatHang = new AnhMatHang();
				anhMatHang.setUrl(tenAnh);
				anhMatHang.setMatHang(listMatHang.get(0));
				anhMatHangService.save(anhMatHang);
			}
		}
	}
	private void addChiTietDonHang(String tenMatHang, String diaChi) {
		List<MatHang> listMatHang = matHangService.findByTenHang(tenMatHang);
		List<DonDatHang> listDonDatHang = donDatHangService.findByNoiGiaoHang(diaChi);
	
			if(listMatHang.size() > 0 && listDonDatHang.size()>0) {
				List<ChiTietDatHang> listChiTietDatHang = chiTietDatHangService.findByMatHang(listMatHang.get(0));
				if (listChiTietDatHang.size() == 0) {
				int giaBan = listMatHang.get(0).getGiaHang();
				int mucGiamGia = 0;
				int soLuong = 1;
			
				
				ChiTietDatHang chiTietDatHang = new ChiTietDatHang(giaBan, mucGiamGia, soLuong);
				chiTietDatHang.setDonDatHang(listDonDatHang.get(0));
				chiTietDatHang.setMatHang(listMatHang.get(0));
				chiTietDatHangService.save(chiTietDatHang);
			}
		}
	
		
	}
	
		
		

	private void addDonDatHang(String noiGiaoHang) {
		List<DonDatHang> listDonDatHang = donDatHangService.findByNoiGiaoHang(noiGiaoHang);
		if(listDonDatHang.size() == 0) {
			try {
				String ngayDatHangString="2020/1/1";  
			    Date ngayDatHang=new SimpleDateFormat("yyyy/MM/dd").parse(ngayDatHangString);  
			    String ngayGiaoHangString="2020/1/1";  
			    Date ngayGiaoHang=new SimpleDateFormat("yyyy/MM/dd").parse(ngayGiaoHangString);  
			    KhachHang khachHang = khachHangService.findByUserName("user");
				NhanVien	nhanVienFound = nhanvienService.findByUserName("employee");
				DonDatHang donDatHang = new DonDatHang(ngayDatHang,ngayGiaoHang,noiGiaoHang,khachHang,nhanVienFound,MyConstances.DA_DAT_HANG);
				donDatHangService.save(donDatHang);
			}catch (Exception e) {
				System.out.println(e.toString());
			}
		    
			
		}
		
	}
	private void themMatHang(String tenMatHang, String tenNhomHang,String TenQuaTang){
		if(matHangService.findByTenHang(tenMatHang).size()==0) {
			List<NhomHang> nhomHangGet = nhomHangService.findByTenNhomHang(tenNhomHang);
			List<QuaTang> listQuaTang = quaTangService.findByTenQuaTang("Chu???t kh??ng d??y");
			MatHang matHang  =new MatHang("Vnd", 100000, 20, tenMatHang, 1, 
					"china", nhomHangGet.get(0), listQuaTang);
			matHangService.save(matHang);
		}
	}
	
	
	
	private void createNhomHang(String tenNhomHang, String tenLoaiHang) {
		if(nhomHangService.findByTenNhomHang(tenNhomHang).isEmpty()) {
			 List<LoaiHang> listLoaiHang = loaiHangService.findByTenLoaiHang(tenLoaiHang);
			if(listLoaiHang.size() > 0) {
				NhomHang nhomHang = new NhomHang(tenNhomHang);
				nhomHang.setLoaiHang(listLoaiHang.get(0));
				nhomHangService.save(nhomHang);
			}
		}
	}
}
