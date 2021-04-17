package edu.hueuni.model;

import java.util.List;

import edu.hueuni.entity.NhomHang;

public class AjaxResponseBody {
	String msg;

	List<NhomHangModel> nhomHangModel;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<NhomHangModel> getNhomHangModel() {
		return nhomHangModel;
	}

	public void setNhomHangModel(List<NhomHangModel> nhomHangModel) {
		this.nhomHangModel = nhomHangModel;
	}

	public AjaxResponseBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AjaxResponseBody(String msg, List<NhomHangModel> nhomHangModel) {
		super();
		this.msg = msg;
		this.nhomHangModel = nhomHangModel;
	}
	

    
}
