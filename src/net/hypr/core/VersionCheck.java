package net.hypr.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VersionCheck {
	
	protected UpdateAvailable updateClass = new UpdateAvailable();

	public VersionCheck() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/condolent/USB3Installer/master/version.md");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(url.openStream()));
	        
	        if(in.readLine().equalsIgnoreCase(MainFrame.version)) {
	        	
	        	// DO NOTHING
	        	
	        } else {
	        	
	        	// TODO: Show 'update available' popup
	        	updateClass.showMsg();
	        	
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
