package net.hypr.core;

import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String workspace;
	private String drivers;

	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String workspace, String drivers) {
		super(source);
		
		this.workspace = workspace;
		this.drivers = drivers;
	}
	
	public String getWorkspace() {
		return workspace;
	}
	
	public void setWorkspace(String iso) {
		this.workspace = iso;
	}
	
	public String getDrivers() {
		return drivers;
	}
	
	public void setDrivers(String drivers) {
		this.drivers = drivers;
	}
	
}
