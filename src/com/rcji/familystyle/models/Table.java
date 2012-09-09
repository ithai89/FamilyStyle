package com.rcji.familystyle.models;

public class Table {
	int cap;
	String restaurant;
	double lat;
	double lng;
	String menuItem;
	String city;
	String [] names;
	
	public Table(int cap, String restaurant, double lat, double lng, String menuItem, String city, String [] names) {
		this.cap = cap;
		this.restaurant = restaurant;
		this.lat = lat;
		this.lng = lng;
		this.menuItem = menuItem;
		this.city = city;
		this.names = names;
	}
	
	public int getCap() {
		return cap;
	}
	
	public String getRestaurant() {
		return this.restaurant;
	}
	public double getLat() {
		return this.lat;
	}
	public double getLong() {
		return this.lng;
	}
	public String getMenuItem() {
		return this.menuItem;
	}
	public String getCity() {
		return this.city;
	}
	public String toString() {
		return this.restaurant;
	}
}
