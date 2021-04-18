package edu.hueuni.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import edu.hueuni.entity.CuaHang;
import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.LoaiHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.entity.NhomHang;
import edu.hueuni.entity.QuaTang;
import edu.hueuni.entity.Quyen;
import edu.hueuni.repository.QuaTangRepository;
import edu.hueuni.repository.QuyenRepository;
import edu.hueuni.service.CuaHangService;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.LoaiHangService;
import edu.hueuni.service.MatHangService;
import edu.hueuni.service.NhanVienService;
import edu.hueuni.service.NhomHangService;
import edu.hueuni.service.QuaTangService;
import edu.hueuni.service.QuyenService;
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private QuyenService quyenService;
	@Autowired
	private NhanVienService nhanvienService;
	@Autowired 
	private KhachHangService khachHangService;
	@Autowired
	private LoaiHangService loaiHangService;
	@Autowired
	private NhomHangService nhomHangService;
	@Autowired 
	private CuaHangService cuaHangService;
	@Autowired
	private QuaTangService quaTangService;
	@Autowired
	private MatHangService matHangService;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Tự động sinh các Quền trong database khi còn thiếu

				if (quyenService.findByTenQuyen(MyConstances.ROLE_EMPLOYEES).isEmpty()) {
					quyenService.save(new Quyen(MyConstances.ROLE_EMPLOYEES));
				}

				if (quyenService.findByTenQuyen(MyConstances.ROLE_ADMIN).isEmpty()) {
					quyenService.save(new Quyen(MyConstances.ROLE_ADMIN));
				}
		// Tự động sinh ra các user trong csdl
				if(nhanvienService.findByUserName("admin").isEmpty()) {
					Optional<Quyen> quyenAdmin = quyenService.findByTenQuyen(MyConstances.ROLE_ADMIN);
					if(quyenAdmin.isPresent()) {
						NhanVien admin = new NhanVien("admin","hoilamchi1","Lê Trường Nam");
						admin.setQuyen(quyenAdmin.get());
						nhanvienService.save(admin);
					}
					
					
					
				}
				if(nhanvienService.findByUserName("employee").isEmpty()) {
					Optional<Quyen> quyenEmployee = quyenService.findByTenQuyen(MyConstances.ROLE_EMPLOYEES);
					if(quyenEmployee.isPresent()) {
						NhanVien employee = new NhanVien("employee","hoilamchi1","Lê Văn A");
						employee.setQuyen(quyenEmployee.get());
						nhanvienService.save(employee);
					}
					
				}
				if(khachHangService.findByUserName("user").isEmpty()) {
					khachHangService.save(new KhachHang("user","hoilamchi1",true));
				}
		//Tự động sinh ra các loại hàng trong CSDL
				if(loaiHangService.findByTenLoaiHang(MyConstances.DI_DONG).isEmpty()) {
					loaiHangService.save(new LoaiHang(MyConstances.DI_DONG));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.LAPTOP).isEmpty()) {
					loaiHangService.save(new LoaiHang(MyConstances.LAPTOP));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.DIEN_TU).isEmpty()) {
					loaiHangService.save(new LoaiHang(MyConstances.DIEN_TU));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.DIEN_LANH).isEmpty()) {
					loaiHangService.save(new LoaiHang(MyConstances.DIEN_LANH));
				}
				if(loaiHangService.findByTenLoaiHang(MyConstances.DIA_DUNG).isEmpty()) {
					loaiHangService.save(new LoaiHang(MyConstances.DIA_DUNG));
				}
		// Tự động sinh ra các nhóm hàng DienTu
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
				
		//Tự động sinh ra các nhóm hàng điện lạnh
				createNhomHang(MyConstances.MAY_LANH, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.MAY_GIAT, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.MAY_SAY, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.TU_LANH, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.TU_DONG, MyConstances.DIEN_LANH);
				createNhomHang(MyConstances.TU_MAT, MyConstances.DIEN_LANH);
				
		//Tự dộng sinh ra các hàng GIA DỤNG
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
				
		//Tự động sinh ra các hàng dienthoai
				createNhomHang(MyConstances.IPHONE, MyConstances.DI_DONG);
				createNhomHang(MyConstances.SAMSUNG, MyConstances.DI_DONG);
				createNhomHang(MyConstances.OPPO, MyConstances.DI_DONG);
				createNhomHang(MyConstances.VSMART, MyConstances.DI_DONG);
				createNhomHang(MyConstances.XIAOMI, MyConstances.DI_DONG);
				createNhomHang(MyConstances.NOKIA, MyConstances.DI_DONG);
				createNhomHang(MyConstances.VIVO, MyConstances.DI_DONG);
				createNhomHang(MyConstances.REALME, MyConstances.DI_DONG);
	
		//Tự động sinh ra các hàng laptop
				createNhomHang(MyConstances.MACBOOK, MyConstances.LAPTOP);
				createNhomHang(MyConstances.ASUS, MyConstances.LAPTOP);
				createNhomHang(MyConstances.HP, MyConstances.LAPTOP);
				createNhomHang(MyConstances.LENOVO, MyConstances.LAPTOP);
				createNhomHang(MyConstances.ACER, MyConstances.LAPTOP);
				createNhomHang(MyConstances.DELL, MyConstances.LAPTOP);
				createNhomHang(MyConstances.LG, MyConstances.LAPTOP);
				
		//Tự dộng sinh ra các cửa hàng
				if(cuaHangService.findById(1).isEmpty()) {
					CuaHang cuaHang = new CuaHang("Đường Hùng Vương, An Cựu, Thành phố Huế, Thừa Thiên Huế, Vietnam",MyConstances.DIEN_MAY_DUC_TAI,"0827307304","Diện máy Đức Tài");
					cuaHang.setIdCuaHang(1);
					cuaHangService.save(cuaHang);
				
				}
				if(cuaHangService.findById(2).isEmpty()) {
					CuaHang cuaHang = new CuaHang("197 Phan Đăng Lưu, Phú Hoà, Thành phố Huế, Thừa Thiên Huế, Vietnam",MyConstances.DIEN_MAY_PHUC_MAI,"0827307305","Diện máy Phúc Mai");
					cuaHang.setIdCuaHang(2);
					cuaHangService.save(cuaHang);
				}
				
		// Tự động sinh ra các quà tặng
				if(quaTangService.findByTenQuaTang("Chuột không dây").isEmpty()) {
					QuaTang quaTang = new QuaTang(10000,20,"Chuột không dây",null);
					quaTangService.save(quaTang);
				}
				if(quaTangService.findByTenQuaTang("Balo du lịch").isEmpty()) {
					QuaTang quaTang = new QuaTang(10000,20,"Balo du lịch",null);
					quaTangService.save(quaTang);
				}
				if(quaTangService.findByTenQuaTang("Tai nghe bluetooth").isEmpty()) {
					QuaTang quaTang = new QuaTang(10000,20,"Tai nghe bluetooth",null);
					quaTangService.save(quaTang);
				}
				
				
	}
	
	
	
	private void createNhomHang(String tenNhomHang, String tenLoaiHang) {
		if(nhomHangService.findByTenNhomHang(tenNhomHang).isEmpty()) {
			Optional<LoaiHang> hangDienTu = loaiHangService.findByTenLoaiHang(tenLoaiHang);
			if(hangDienTu.isPresent()) {
				NhomHang nhomHang = new NhomHang(tenNhomHang);
				nhomHang.setLoaiHang(hangDienTu.get());
				nhomHangService.save(nhomHang);
			}
		}
	}
}
