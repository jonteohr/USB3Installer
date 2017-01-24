package net.hypr.core;

import java.util.EventListener;

public interface FormListener extends EventListener {
	
	public void formEventOccured(FormEvent e) throws Exception;

}
