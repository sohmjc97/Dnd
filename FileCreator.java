package Dnd;

import java.util.Formatter;
import java.io.*; 

public class FileCreator {
	/*
	 * Pass in a City, Country, Route, Encounter, or Monster object to generate a text file titled with the Object's name
	 * The text file will include a formatted infodump or statsheet with all of the relevant
	 * information about the object 
	 */
	
	private Formatter x; 
	
	/*
	 * Not yet implemented in an Editor, but will eventually have a path that goes like
	 * C:\Users\USER\Desktop\DM\Countries\Country\Route or City\Encounters\Encounter\Monsters\Monster\Monster.txt
	 * 
	 * @param	 monster Monster	:a Monster object for which you want a text file to be created
	 */
	public FileCreator (Monster monster) {
		
		open_file (monster.get_name() + ".txt");
		addRecords(monster.get_all_stats());
		close_file(); 
		
	}
	
	/*
	 * Constructor to create a text file with information on the given Route
	 * 
	 * @param	route Route		:a Route for which you want a text file
	 */
	public FileCreator (Route route) {
		File dir = new File("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + route.get_origin().get_country().get_country_name() + "\\Routes\\" + route.get_name());
		dir.mkdirs();
		open_file("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + route.get_origin().get_country().get_country_name() + "\\Routes\\" + route.get_name() + "\\" + route.get_name() + ".txt"); 
		addRecords(route.get_all_info());
		close_file(); 
	}
	
	/*
	 * Constructor to create a text file with information on the given Encounter
	 * 
	 * @param	encounter Encounter		:an Ecnounter for which you want a text file
	 */
	public FileCreator (Encounter encounter) {
		if (encounter.get_city() != null) {
			File dir = new File("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + encounter.get_city().get_country().get_country_name() + "\\Cities\\" + encounter.get_city().get_name() + "\\Encounters\\" + encounter.get_encounter_name());
			dir.mkdirs();
			open_file("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + encounter.get_city().get_country().get_country_name() + "\\Cities\\" + encounter.get_city().get_name() + "\\Encounters\\" + encounter.get_encounter_name() + "\\" + encounter.get_encounter_name() + ".txt");
			addRecords(encounter.get_all_info());
			close_file(); 
		}
		else if (encounter.get_route() != null) {
			File dir = new File("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + encounter.get_route().get_origin().get_country().get_country_name() + "\\Routes\\" + encounter.get_route().get_name() + "\\Encounters\\" + encounter.get_encounter_name());
			dir.mkdirs();
			open_file("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + encounter.get_route().get_origin().get_country().get_country_name() + "\\Routes\\" + encounter.get_route().get_name() + "\\Encounters\\" + encounter.get_encounter_name() + "\\" + encounter.get_encounter_name() + ".txt");
			addRecords(encounter.get_all_info());
			close_file(); 
		} 
	}
	
	/*
	 * Constructor to create a text file with information on the given Country
	 * 
	 * @param	country Country		:a Country for which you want a text file
	 */
	public FileCreator (Country country) {
		File dir = new File("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name());
		dir.mkdirs();
		File dir2 = new File ("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\Cities\\");
		dir2.mkdirs();
		File dir3 = new File ("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\Routes\\");
		dir3.mkdirs();
		open_file ("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\" + country.get_country_name() + ".txt");
		addRecords(country.get_all_info());
		close_file(); 
		
	}
	
	/*
	 * Constructor to create a text file with information on the given City
	 * 
	 * @param	city City		:a City for which you want a text file
	 */
	public FileCreator (City city) throws IOException {
		
		File dir = new File("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + city.get_country().get_country_name() + "\\Cities\\" + city.get_name());
		
		boolean created = dir.mkdirs(); 
		System.out.println(dir + "\n" + created);
		if (!dir.exists()) {
			FileWriter f = new FileWriter ("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + city.get_country().get_country_name() + "\\Cities\\" + city.get_name()+ "\\" + city.get_name () + ".txt");
			try {
				f.write(city.get_all_info());
				}
			catch(Exception e) {
				System.out.println("Problem saving city file" + e);
			}
			finally {
				f.close(); 
			}
				
		}
		else { 
		open_file ("C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + city.get_country().get_country_name() + "\\Cities\\" + city.get_name() + "\\" + city.get_name () + ".txt");
		addRecords(city.get_all_info());
		close_file();
		}

	}
	
	/*
	 * Attempts to open a file with the given filename. 
	 * 
	 * @param	filename String		:the name of the file you want to open
	 */
	public void open_file (String filename) {
		
		try {
			x = new Formatter(filename);
		}
		catch (Exception e) {
			System.out.println("There was an error creating the file."); 
		}
		
	}
	
	/*
	 * Takes the given String and writes it into the open text file 
	 * 
	 * @param	args String		:the text that you want to go in the file
	 */
	public void addRecords (String arg) {
		
		x.format("%s", arg);
		
	}
	
	/*
	 * Closes the file that was being edited 
	 */
	public void close_file () {
		
		x.close(); 
		
	}
	
}
