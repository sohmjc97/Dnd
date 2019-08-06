package Dnd;

import java.io.*;
import java.util.*;

public class FileReader {
	//pass in a text file, and the FileReader will print out it's contents
	//the formatting of the print needs some work
	
	private Scanner x; 
	
	
	public FileReader (String filename) {
		//for a country file 
		
		openFile("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + filename + "\\" + filename + ".txt");
		readFile();
		closeFile();
		
	}
	
	public FileReader (String filename, Country country, String type) {
		
		if (type == "City") {
			openFile("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\Cities\\" + filename + "\\" + filename + ".txt");
			readFile();
			closeFile();
		}
		else if (type == "Route") {
		}
		else if (type == "Monster") {}
		else if (type == "Encounter") {}
		else {}
		
	}
	
	public void openFile (String filename) {
		
		try {
			x = new Scanner(new File(filename));
		}
		catch (Exception e) {
			System.out.println("Could not find file.");
		}
		
	} 
	
	public void readFile () {
		
		while (x.hasNextLine()) {
			String a = x.nextLine();
			System.out.println(a);
		}
		
	}
	
	public void closeFile() {
		
		x.close();
		
	}

}
