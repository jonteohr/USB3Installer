package net.hypr.core;

import java.util.Scanner;

import net.hypr.log.Console;

public class JavaMain {

	public static void main(String[] args) {
		Console log = new Console();
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		
		log.setAge(50);
		
		log.sendMessage("Enter the value you want to set the age to!");
		log.sendMessage("Current age is: " + log.getAge());

		int value = input.nextInt();
		
		log.sendMessage("Are you sure you want to set the age to " + value + "? (Y/N)");
		
		String line = input2.nextLine();
		
		while(!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n")) {
			log.sendMessage(line.toString() + " is not a valid option.");
			line = input2.nextLine();
		}
		
		if(line.equalsIgnoreCase("y")) {
			log.setAge(value);
			log.sendMessage("The new age is now: " + log.getAge());
		} else if(line.equalsIgnoreCase("n")) {
			log.sendMessage("Aborted!");
		}
		
	}

}
