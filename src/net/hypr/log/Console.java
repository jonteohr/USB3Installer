package net.hypr.log;

import net.hypr.strings.StringCollector;

public class Console {
	
	private int age = 0;
	
	/**Sends a message to the console.*/
	public void sendMessage(String arg) {
		System.out.println(StringCollector.prefix + arg);
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int i) {
		age = i;
	}

}
