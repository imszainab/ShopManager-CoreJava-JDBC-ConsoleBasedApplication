package edu.jsp.shop.model;

import java.util.List;

public class Shop {
	private String shopName,address,ownerName,gst;
	private int id;
	private long contact;
	private List<Product> products;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String name) {
		this.shopName = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String owner) {
		this.ownerName = owner;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", address=" + address + ", ownerName=" + ownerName + ", gst=" + gst
				+ ", id=" + id + ", contact=" + contact + "]";
	}
	
}
