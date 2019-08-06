package Dnd;

import java.io.FileNotFoundException;
import java.util.*;

import Dnd.Country.CityType;
import Dnd.Country.TerrainType; 

public class WorldEditor extends Worldbuilder {
	/*
	 * Runs UI for world editing, beginning with a country and everything inside of it. 
	 */
	
	/*
	 * Main function of WorldEditor
	 * Attempts to open the file and directory for the country that is to be edited
	 * If the file and directory are successfully opened, the main menu (giving the user
	 * options for editing the Country) will be displayed. 
	 */
	public static void edit () {
		m_country = open_country_file(); 
		runMainMenu(); 
	}
	
	/*
	 * Asks for a user to type in the name of the country they want to edit
	 * The path is pre-written, so as long as they enter the name of a 
	 * Country created using Worldbuilder (and they spell 
	 * the Country's name correctly), it will open that country's file 
	 * and work in its directory in order to access its file and that of its Cities and Routes
	 * 
	 * @return 		county / NotCountry Country		:either the reconstructed Country is successful or a null Country if not
	 */
	private static Country open_country_file() {
		
		boolean done = false;
		do {
			try {
				
				System.out.println("Welcome to World Editor!");
				System.out.println("Type the name of the country you want to edit. Be sure to spell it correctly.");
				String country_name = scanner.nextLine(); 
				
				try {
					Country country = reconstruct_country(country_name);
					done = true; 
					return country;
				}
				catch (Exception e) {
					System.out.println("There was a problem opening the country file.Try again. \n" + e);
					//scanner.next();
				}
			}
			catch (Exception e) {
				System.out.println (GenericException + "\n" + e);
				//scanner.next();
			}
		}while (done == false);
		
		Country notCountry = null; 
		return notCountry;
	}
	
	/*
	 * This opens up the text file that was created when the Country was saved in Worldbuilder
	 * It reads the contents of the text file and parses the information stored therein to
	 * create a copies of the original Country, City, and Route objects that were created in Worldbuilder
	 * 
	 * @param	filename String		:string inputed by the user that will be used to look up Country file
	 * @throws	FileNotFoundException	:if the input doesn't match any of the existing files in the Country directory
	 * @return	country Country		:Country object created in populateCountryVariables() subfunction
	 */
	private static Country reconstruct_country (String filename) throws FileNotFoundException{

		String[] countryAttributes = {
				"Name", 
				"Description:",
				"Age:",
				"Population:",
				"Ruler:"
				};
		
		ArrayList<String> resultSet = new ArrayList<String>();
		ArrayList<String> cityResults = new ArrayList<String>();
		ArrayList<String> routeResults = new ArrayList<String>();
				
		FileSearch filesearch = new FileSearch();
		for (String i: countryAttributes) {
			String result = filesearch.parseFile(filename, i);
			resultSet.add(result);
		}

		Country country = populateCountryVariables (countryAttributes, resultSet);
		for (int i = 0; i < 20 ; i++) {
			String result = filesearch.parseFile(filename, i+")"); 
			cityResults.add(result);
		}

		//System.out.println(cityResults);
		reconstructCities(cityResults, country);

		String route = "";
		for (int r = 1; r < 15; r++) {
			route = ""; 
			try {
				route = filesearch.parseFile(filename, "#"+r);

				if (route == "") {
					break; 
				}
				else {
					routeResults.add(route);
				}
			}
			catch (Exception e) {
				System.out.println("There was an issue when parsing the country file");
			}
			
		}

		reconstructRoutes(routeResults, country); 
		
		//System.out.println(country.get_routes());
		for (City i: country.get_cities()) {
			i.set_routes(country.get_routes());
			//System.out.println(i.get_name() + " has routes " + i.get_routes());
			i.list_all_info();
		}
		
		reconstructEncounters(country); 
		
		country.list_all_info();
		return country;
		
	}
	
	/*
	 *  Copies the values of the original Country object from its text file and populates
	 *  a new Country object's variables with them.
	 *  
	 *  @param		attributes String[]			:contains String names of Country attributes
	 *  @param 		values ArrayList<String>	:contains according values parsed from Country text file
	 *  @return		country Country				:new copy of original Country object
	 */
	private static Country populateCountryVariables (String[] attributes, ArrayList<String> values) {

		String name = values.get(0).split(": ")[1];
		String descrip = values.get(1).split(": ")[1];
		String age = values.get(2).split(": ")[1];
		String pop = values.get(3).split(": ")[1];
		String ruler = values.get(4).split(": ")[1];
		

		Country country = new Country(name);
		country.set_country_description(descrip);
		country.set_country_age(Integer.parseInt(age));
		country.set_population(Integer.parseInt(pop));
		country.set_ruler(ruler);
		return country;
	}
	
	/*
	 * Looks in the Country's directory to find the files belonging to its cities.
	 * Creates new copies of the City objects using their text files and links the cities to the 
	 * new Country copy.
	 * 
	 * @param	cities ArrayList<String>	:contains the names of cities parsed from the Country text file
	 * @param	country Country				:the Country object created by populateCountryVariables()
	 * @throws	FileNotFoundExceptionq		:if the text file associated with the city name cannot be found/opened 
	 */
	private static void reconstructCities (ArrayList<String> cities, Country country) throws FileNotFoundException {

		ArrayList<City> m_cities = new ArrayList<City>(); 
		
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i) == "") {
				continue;
			}
			else {
				String name = "";
				String[] each = cities.get(i).toString().split("\\)"); 
				name = each[1].strip(); 
				City city = country.add_city(name);
				m_cities.add(city);
			}

		}

		String[] cityAttributes = {
				"Name", 
				"Description:",
				"Age:",
				"Population:",
				"Ruler:",
				"City-Type:",
				"Terrain-Type:"};
		
		ArrayList<String> attributeResults = new ArrayList<String>();

		for (City i: country.get_cities()) {		
			attributeResults.clear();
			
			FileSearch filesearch = new FileSearch();
			for (String attribute: cityAttributes) {
				//System.out.println("Filename: " + i.get_name() + " Attribbute: " + attribute + " Country: " + i.get_country().get_country_name());
				String result = filesearch.parseFile(i.get_name(), attribute, i.get_country());
				attributeResults.add(result);
			}
			
			HashMap<String, String> npc_descrip_list = new HashMap<String, String>();
			String npc = "|";
			try {
				String result = filesearch.parseFile(i.get_name(), npc, i.get_country());
				String[] npc_list = result.split("\n"); 
				
				for (String a: npc_list) {
					String[] each = a.split(" \\| ");		
					npc_descrip_list.put(each[0], each[1]);
				}
			}
			catch(Exception e) {
				//System.out.println("There are no NPC's.");
			}

			//System.out.println(i.get_buildings());
			
			String bsearch = "Buildings:";
			String buildings = filesearch.parseFile(i.get_name(), bsearch, i.get_country());
			//System.out.println(buildings);
						
			String encounters = filesearch.parseFile(i.get_name(), ")", i.get_country());
			System.out.println("Encounters: " + encounters);
			//System.out.println(npc_descrip_list);
			System.out.println("Got to through file parsing.");

			populateCityVariable(attributeResults, npc_descrip_list, buildings, encounters, i); 

		}
	}
	
	/*
	 * Uses the information parsed from the city's text file to populate the copy City's variables. 
	 * 
	 * @param 	results ArrayList<String>		:contains attribute values parsed from City's text file
	 * @param	npc_map HashMap<String,String>	:contains names and descriptions of NPC's parsed from City's text file
	 * @param	building String					:contains names of buildings separated by "\n", parsed from City's text file
	 * @param	city City						:new copy of the City object created in reconstructCities()
	 */
	private static void populateCityVariable (ArrayList<String> results, HashMap<String, String> npc_map, String buildings, String encounters, City city) {
		
		String descrip = results.get(1).split(": ")[1];
		String age = results.get(2).split(": ")[1];
		String pop = results.get(3).split(": ")[1];
		String ruler = results.get(4).split(": ")[1];
		String ctype =  results.get(5).split(": ")[1];
		String ttype =  results.get(6).split(": ")[1];

		city.set_description(descrip);
		city.set_age(Integer.parseInt(age));
		city.set_population(Integer.parseInt(pop));
		city.set_local_ruler(ruler);
		
		for (CityType i: CityType.values()) {
			if (ctype.equalsIgnoreCase(i.toString())) {
				city.set_ctype(i);
			}
		}
		
		for (TerrainType i: TerrainType.values()) {
			if (ttype.equalsIgnoreCase(i.toString())) {
				city.set_TerrainType(i);
			}
		}
		
		for (String npc: npc_map.keySet()) {
			city.add_NPC(npc, npc_map.get(npc));
		}
		
		//System.out.println(city.get_buildings());
		
		city.get_buildings().clear();
		for (int i = 0; i < city.get_buildings().size(); i++) {
			city.delete_building(city.get_buildings().get(i));
		}
		
		String[] building_array = buildings.split("\n");
		
		for (String item: building_array) {
			//System.out.println(item);
			
			if (item.contains("Buildings")) {
				//do nothing 
			}
			else if (item.contains("--")) {
				//do nothing 
			}
			else {
				city.add_building(item);
			}
		}
		
		if (encounters.equalsIgnoreCase("")) {
			System.out.println("No encounters to add.");
		}
		else {
			System.out.println("Encounters String -- " + encounters); 
			String[] encounter_array = encounters.split("\n");
			//int i = 1;
			for (String item: encounter_array) {
				System.out.println("Lines of encounter String --" + item); 
				String info = item.split("\\)")[1];
	
				//String[] splitInfo = info.split(":");
				
				String e_name = info.strip(); 
				//String e_descrip = splitInfo[1].strip();
				
				Encounter e = city.add_encounters(city, e_name);
				//e.set_description(e_descrip);
				//i++;
			}
			System.out.println("Got to end of populateCityVariables..");
		} 

	}
	
	/*
	 * Reconstructs Route objects from the Country file and Route files 
	 * 
	 * @param	routes ArrayList<String>	:contains route names and details from old Country text file 
	 * @param	country Country				:the new Country object 
	 */
	private static void reconstructRoutes (ArrayList<String> route, Country country) {
		
		//System.out.println(route);
		for (Object r: route) {
			String[] each = r.toString().split(":");
			String name = each[1].strip();
			String origin = each[2].strip();
			String destin = each[3].strip(); 
			
			createRoute(origin, destin, country);
			
			try {
				populateRouteVariables(name, country);
			}
			catch (FileNotFoundException e) {
				System.out.println("There was an issue opening the route file.");
			}
		}
	}
	
	/*
	 * Reconstructs Encounter objects from the City files or Route files and its own Encounter file 
	 * 
	 * @param	country Country				:the new Country object to which this Encounter belongs
	 */
	private static void reconstructEncounters (Country country) throws FileNotFoundException {
		System.out.println("Entering Encounter Reconstructor... \n");
		FileSearch filesearch = new FileSearch();
		
		//get Route Encounter
		for (Route route: country.get_routes()) {
			System.out.println("Looking for encounters on " + route.get_name());
			for (Encounter encounter: route.get_day_encounters()) {
				System.out.println("Attempting to retreive " + encounter.get_name());
				String results = "";
				try {
					results = filesearch.parseFile(encounter.get_name(), " ", country, route);
				}
				catch (Exception e) {
					System.out.println("There is not yet a file saved for " + encounter.get_name());
				}
				if (results == "") {
					System.out.println("Cannot glean any information for this Encounter. \n");
				}
				else {
					populateEncounterVariables(encounter, results);
				}
				//System.out.println(results + "\n");
			}
		}
		
		//get City Encounters 
		for (City city: country.get_cities()) {
			System.out.println("Looking for encounters in " + city.get_name());
			//System.out.println(city.get_encounters());
			for (Encounter encounter: city.m_encounters) {
				System.out.println("Attempting to retreive " + encounter.get_name());
				
				String results = "";
				try {
					results = filesearch.parseFile(encounter.get_name(), " ", country, city);
				}
				catch (Exception e) {
					System.out.println("There is not yet a file saved for " + encounter.get_name());
				}
				if (results == "") {
					System.out.println("Cannot glean any information for this Encounter. \n");
				}
				else {
					
					populateEncounterVariables(encounter, results);
				}
			}
		}
		
	}

	/*
	 * Takes the newly made Encounter object and copies in the data from the old object to make it a copy
	 * 
	 * @param	encounter Encounter 	: the newly made Encounter object
	 * @param	result String			: the lines parsed from the old Encounter's textfile
	 */
	private static void populateEncounterVariables (Encounter encounter, String results) {

		String[] lines = results.split("\n");
		
		//System.out.println("Reach this point.");

		//String name = lines[0].split(": ")[1]; 
		//int id = Integer.parseInt(lines[1].split(": ")[1]);
		String descrip = ""; 
		try {
			descrip =  lines[2].split(": ")[1];
		}
		catch (Exception e) {
			//
		}
		encounter.set_description(descrip);
		
		String[] enemies = results.split("-------------------------------------------------- ");
		
		for (int i = 1; i < enemies.length; i++) {
			//System.out.println(enemies[i]);
			String[] enemy_lines = enemies[i].split("\n");
			for (String line: enemy_lines) {
				//System.out.println(x+ ") " + line);
				if (line.contains("Name")) {
					encounter.add_enemy(line.split(": ")[1]);
				}
			}
		}
		System.out.println(" ");
		System.out.println(encounter.get_all_info());
		
	}
	
	/*
	 * Obtains the name of the origin and destination city of individuals Routes from the Country text file
	 * in order to create a new Route object into which the old route values can be copied and which is then
	 * linked to the Country object. 
	 * 
	 * @param	origin String	:name of the origin City
	 * @param	destin String	:name of the destination City
	 * @param	country Country	:the new Country object 
	 */
	private static void createRoute(String origin, String destin, Country country) {
		City originCity = null;
		City destinCity = null;
		
		boolean validOrigin = false;
		boolean validDestin = false; 
		//System.out.println("Got here: "+ country);
		
		for (int i = 0; i < country.get_cities().size(); i++) {
			
			//System.out.println(country.get_cities().get(i).get_name() + " is not equal to " + origin + " or " + destin);
			
			if (country.get_cities().get(i).get_name().equalsIgnoreCase(origin)) {
				//System.out.println(country.get_cities().get(i).get_name() + " is equal to " + origin);
				
				originCity = country.get_cities().get(i); 
				validOrigin = true;
			}
			else {
				//System.out.println(country.get_cities().get(i).get_name() + " is not equal to " + origin);
			}
			if (country.get_cities().get(i).get_name().equalsIgnoreCase(destin)) {
				//System.out.println(country.get_cities().get(i).get_name() + " is equal to " + destin);
				
				destinCity = country.get_cities().get(i); 
				validDestin = true; 
			}
			else {
				//System.out.println(country.get_cities().get(i).get_name() + " is not equal to " + destin);
			}
		}
		
		//System.out.println(validOrigin + " AND " + validDestin);
		if (validOrigin & validDestin) {
			Route newRoute = new Route(originCity, destinCity); 
			m_route = newRoute; 
			country.add_route(newRoute);
		}
		else {
			System.out.println("Endpoint cities not valid.");
		}
	}
	
	/*
	 * Obtains Route details from old Route text file and copies them into the new Route object 
	 * 
	 * @param	name String  	:name of the Route/ Route text file 
	 * @param	country Country	:the new Country object 
	 */
	private static void populateRouteVariables (String name, Country country) throws FileNotFoundException {
		
		m_route.set_name(name);
		FileSearch filesearch = new FileSearch(); 
		String results = filesearch.parseFile(name, ":", country, "r");
		String[] each = results.split("\n");
		
		//values will hold terrain type and length
		ArrayList<String> values = new ArrayList<String>(); 
		m_route.set_description(each[0].split(": ")[1]);
		values.add(each[1].split(": ")[1]);
		values.add(each[2].split(": ")[1].split(" ")[0]);
		
		int length = Integer.parseInt(values.get(1));
		m_route.set_length(length);
		
		for (TerrainType i: TerrainType.values()) {
			if (values.get(0).equalsIgnoreCase(i.toString())) {
				m_route.set_terrainType(i);
			}
		}
		
		ArrayList<String> encounterList = new ArrayList<String>();
		for (int x = 1; x < 20; x++) {
			String encounter = filesearch.parseFile(name, x+")", country, "r");
			if (encounter != "") {
				String encounterName = encounter.split("\\)")[1];
				encounterList.add(encounterName);
			}
		}
		
		//System.out.println(encounterList);
		for (int y = 0; y < encounterList.size(); y++) {
			m_route.add_day_encounters(m_route, encounterList.get(y));
		}
		
		System.out.println(m_route.get_all_info()); 
		
	}
	
	/*
	 * Runs the main Country editing menu until the user chooses to end the program
	 */
	private static void runMainMenu () {
		
		boolean done = false; 
		do {
			System.out.println("------Country Editor------");
			System.out.println("What would you like to edit?");
			list_country_edits(); 
			 try {
				 int a = scanner.nextInt();
				 if (a <= 0) {
					 System.out.println(OutOfRangeException);
				 }
				 else if (a > 10) {
					 System.out.println(OutOfRangeException);
				 }
				 else {
					 done = parseChoice(a);
				 }
			 }
			 catch (Exception e) {
				 System.out.println(MustBeIntException);
				 scanner.next();
			 }
			
		}while(done == false);
		System.out.println("Ending World Editor...");
	}
	
	/*
	 * Lists all option for editing the Country, including moving into cityEditor and routeEditor
	 */
	private static void list_country_edits() {
		String output = "";
		output = output + "1) Name (Note: if you change this, a new file will be created. \n";
		output = output + "2) Description \n";
		output = output + "3) Age \n";
		output = output + "4) Population \n";
		output = output + "5) Ruler \n";
		output = output + "6) Cities \n";
		output = output + "7) Routes \n";
		output = output + "8) View Country Details \n";
		output = output + "9) Save Edits \n";
		output = output + "10) Exit Editor \n";
		System.out.println(output);
	}
	
	/*
	 * Chooses which Country attribute to edit based on user input in edit()
	 * 
	 * @param choice Integer	:value taken from user input 
	 */
	private static Boolean parseChoice(int choice) {
		
		boolean done = false;
		switch (choice) {
			case 1:
				System.out.println("Name editing currently now allowewd.");
				//m_country.set_country_name(name);
				break; 
			case 2:
				System.out.println("What should the new description be?");
				scanner.nextLine(); 
				String descrip = scanner.nextLine();
				m_country.set_country_description(descrip);
				System.out.println("Description has been changed to: \n" + m_country.get_country_description() + "\n");
				break; 
			case 3:
				Worldbuilder.constructCountryAge(); 
				System.out.println("Age has been changed to: " + m_country.get_country_age() + "\n");
				break; 
			case 4:
				Worldbuilder.constructCountryPop();
				System.out.println("Population has been changed to: " + m_country.get_population() + "\n");
				break; 
			case 5:
				System.out.println("Who should the new ruler be?");
				scanner.nextLine(); 
				String ruler = scanner.nextLine();
				m_country.set_ruler(ruler);
				System.out.println("Ruler has been changed to: " + m_country.get_ruler() + "\n");
				break; 
			case 6:
				//System.out.println("City editiing not available.");
				cityEditor.edit(); 
				break;
			case 7:
				//ystem.out.println("Route editiing not available.");
				routeEditor.edit();
				break; 
			case 8:
				m_country.list_all_info();
				break;
			case 9:
				Worldbuilder.saveCountry();
				break; 
			case 10:
				done = true;
				break; 
		}
		return done; 
	}
	
	
}
