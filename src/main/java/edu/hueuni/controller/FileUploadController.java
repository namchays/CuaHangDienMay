package edu.hueuni.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hueuni.entity.KhachHang;
import edu.hueuni.entity.NhanVien;
import edu.hueuni.service.KhachHangService;
import edu.hueuni.service.NhanVienService;
@Controller
public class FileUploadController {
	
	@Autowired 
	private NhanVienService nhanVienService;
	
	@Autowired 
	private KhachHangService khachHangService;
	
	public static String uploadImgDirectory = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\mathang";
	public static String uploadImgDirectoryBaiDang = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\baidang";
	public static String uploadImgDirectoryQuaTang = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\quatang";
	public static String uploadImgDirectoryComment = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\comment";
	public static String uploadImgDirectoryTraLoi = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\traloi";
	public static String uploadImgDirectoryUser = System.getProperty("user.dir") +"\\src\\main\\resources\\static";
	@RequestMapping(value = "getImg/img/mathang/{photo}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImg(@PathVariable("photo") String photo){
		System.out.println(photo);
		if(!photo.equals("")&&photo !=null) {
			try {
				Path filename = Paths.get(uploadImgDirectory,photo);
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "getImg/img/baidang/{photo}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImgBaiDang(@PathVariable("photo") String photo){
		System.out.println(photo);
		if(!photo.equals("")&&photo !=null) {
			try {
				Path filename = Paths.get(uploadImgDirectoryBaiDang,photo);
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "getImg/img/quatang/{photo}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImgQuatang(@PathVariable("photo") String photo){
		System.out.println(photo);
		if(!photo.equals("")&&photo !=null) {
			try {
				Path filename = Paths.get(uploadImgDirectoryQuaTang,photo);
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "getImg/img/comment/{photo}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImgComment(@PathVariable("photo") String photo){
		System.out.println(photo);
		if(!photo.equals("")&&photo !=null) {
			try {
				Path filename = Paths.get(uploadImgDirectoryComment,photo);
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "getImg/img/traloi/{photo}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImgTraLoi(@PathVariable("photo") String photo){
		System.out.println(photo);
		if(!photo.equals("")&&photo !=null) {
			try {
				Path filename = Paths.get(uploadImgDirectoryTraLoi,photo);
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "getAvatar/{name}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImgAvatar(@PathVariable("name") String name){
		System.out.println("name" +name);
		if(!name.equals("")&&name !=null) {
			try {
				
				String fileName = null;
				if(nhanVienService.findByUserName(name)!=null) {
					NhanVien nhanVien = nhanVienService.findByUserName(name);
					fileName = nhanVien.getUrlAvatar();
				}
				if(khachHangService.findByUserName(name)!=null) {
					KhachHang khachHang = khachHangService.findByUserName(name);
					fileName = khachHang.getUrlAvatar();
				}
				
				
				
				Path filename = Paths.get(uploadImgDirectoryUser,fileName);
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
}
