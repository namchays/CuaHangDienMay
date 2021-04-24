package edu.hueuni.model;

import java.util.ArrayList;
import java.util.List;

import edu.hueuni.entity.MatHang;

public class ListMatHangModel {

	public List<MatHangModel> getMatHangModel(List<MatHang> listMatHang) {
		List<MatHangModel> ds = new ArrayList<MatHangModel>();
		if (listMatHang != null) {
			if (listMatHang.size() > 0) {
				listMatHang.forEach(x -> {
					if(x.getAnhMatHangs().size() >0) {
						ds.add(new MatHangModel(x.getMaHang(), x.getDonViTinh(), x.getGiaHang(), x.getNgayNhap(),
								x.getSoLuong(), x.getTenHang(), x.getMucGiamGia(), x.getTrangThai(), x.getXuatXu(),
								x.getNhomHang().getIdNhomHang(),x.getNhomHang().getTenNhomHang(), x.getNhomHang().getLoaiHang().getIdLoaiHang(),x.getNhomHang().getLoaiHang().getTenLoaiHang(),x.getAnhMatHangs().get(0).getUrl()));
					}else {
						ds.add(new MatHangModel(x.getMaHang(), x.getDonViTinh(), x.getGiaHang(), x.getNgayNhap(),
								x.getSoLuong(), x.getTenHang(), x.getMucGiamGia(), x.getTrangThai(), x.getXuatXu(),
								x.getNhomHang().getIdNhomHang(),x.getNhomHang().getTenNhomHang(), x.getNhomHang().getLoaiHang().getIdLoaiHang(),x.getNhomHang().getLoaiHang().getTenLoaiHang()));
					}
					
				});
			}

		}
	return ds;
	}
	

}
