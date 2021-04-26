package edu.hueuni.model;

import java.util.ArrayList;
import java.util.List;

public class QuaTangModel {
	private List<QuaTangItem> quaTangItem;

	public void addQuaTangItem(QuaTangItem quaTangItem) {
		this.quaTangItem.add(quaTangItem);
	}
	public List<QuaTangItem> getQuaTangItem() {
		return quaTangItem;
	}

	public void setQuaTangItem(List<QuaTangItem> quaTangItem) {
		this.quaTangItem = quaTangItem;
	}

	public QuaTangModel() {
		super();
		this.quaTangItem = new ArrayList<QuaTangItem>();
	}

	public QuaTangModel(List<QuaTangItem> quaTangItem) {
		super();
		this.quaTangItem = quaTangItem;
	}
	
	
	
}
