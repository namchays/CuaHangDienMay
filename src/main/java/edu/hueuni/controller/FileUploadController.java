package edu.hueuni.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class FileUploadController {
	public static String uploadImgDirectory = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\mathang";
	public static String uploadImgDirectoryBaiDang = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\baidang";
	public static String uploadImgDirectoryQuaTang = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\quatang";
	public static String uploadImgDirectoryUser = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\img\\user";
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
	@RequestMapping(value = "/getAvatar/{photo}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImgAvatar(@PathVariable("name") String name){
		System.out.println(name);
		if(!name.equals("")&&name !=null) {
			try {
				Path filename = Paths.get(uploadImgDirectoryUser,name);
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
