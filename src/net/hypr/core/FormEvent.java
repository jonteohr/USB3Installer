package net.hypr.core;

import java.nio.file.Path;
import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private Path workspace;

	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, Path workspace) {
		super(source);
		
		this.workspace = workspace;
	}
	
	public Path getWorkspace() {
		return workspace;
	}
	
	public void setWorkspace(Path iso) {
		this.workspace = iso;
	}
	
}
