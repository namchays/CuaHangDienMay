package edu.hueuni.model;

import java.util.ArrayList;
import java.util.List;

public class ChiTietMatHangModel {

	private List<ChiTietMatHangItem> chiTietMatHangItem;

	// default and parameterized constructor
	public void addChiTietMatHangItem(ChiTietMatHangItem chiTietMatHangItem) {
		this.chiTietMatHangItem.add(chiTietMatHangItem);

	}

	public ChiTietMatHangModel(List<ChiTietMatHangItem> chiTietMatHangItem) {
		super();
		this.chiTietMatHangItem = chiTietMatHangItem;
	}

	public ChiTietMatHangModel() {
		super();
		chiTietMatHangItem = new ArrayList<ChiTietMatHangItem>();
	}

	public List<ChiTietMatHangItem> getChiTietMatHangItem() {
		return chiTietMatHangItem;
	}

	public void setChiTietMatHangItem(List<ChiTietMatHangItem> chiTietMatHangItem) {
		this.chiTietMatHangItem = chiTietMatHangItem;
	}

}
