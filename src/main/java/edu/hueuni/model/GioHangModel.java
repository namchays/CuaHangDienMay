package edu.hueuni.model;

import java.util.ArrayList;
import java.util.List;

public class GioHangModel {
	public List<GioHangItem> ds;
	public void Them(int maHang, String tenHang, int soLuong, int gia, String imgUrl) {
		for(GioHangItem item : ds) {
			if(item.getMaHang() == maHang) {
				item.setSoLuong(item.getSoLuong()+1);
				return;
			}
		}
		ds.add(new GioHangItem(maHang, tenHang, soLuong, gia, imgUrl));
	}
	public void Xoa(int maHang) {
		for(GioHangItem item : ds) {
			if(item.getMaHang() == maHang) {
				ds.remove(item);
				return;
			}
		}
	}
	public int tong() {
		int sum = 0;
		for(GioHangItem item : ds) {
			sum +=item.getThanhTien();
		}
		return sum;
	}
	public int size() {
		return ds.size();
	}
	public GioHangModel() {
		super();
		this.ds = new ArrayList<GioHangItem>();
	}
	@Override
	public String toString() {
		return "GioHangModel [ds=" + ds + "]";
	}
	public List<GioHangItem> getDs() {
		return ds;
	}
	public void setDs(List<GioHangItem> ds) {
		this.ds = ds;
	}
	public GioHangModel(List<GioHangItem> ds) {
		super();
		this.ds = ds;
	}
	
	
	
	
	
}
