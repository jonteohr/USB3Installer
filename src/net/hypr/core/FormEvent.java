package net.hypr.core;

import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String iso;
	private String drivers;

	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String iso, String drivers) {
		super(source);
		
		this.iso = iso;
		this.drivers = drivers;
	}
	
	public String getIso() {
		return iso;
	}
	
	public void setIso(String iso) {
		this.iso = iso;
	}
	
	public String getDrivers() {
		return drivers;
	}
	
	public void setDrivers(String drivers) {
		this.drivers = drivers;
	}
	
}
