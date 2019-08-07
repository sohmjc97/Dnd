package Dnd;

import java.util.*;

import Dnd.Country.CityType;
import Dnd.Country.TerrainType;

public class Worldbuilder {
	/*
	 * Runs a UI for quick and easy creation of new Worlds, complete with Countries, Cities, Routes, and all that goes with them. 
	 */
	
	static Scanner scanner = new Scanner(System.in); 
	
	/*
	 * Class variables for the Country, Route, and City that are currently being edited,
	 * only one of each can be edited at one time. 
	 */
	public static Country m_country = null;
	public static City m_city = null;
	public static Route m_route = null; 
	
	private static int countryCount = 0; 
	private static int globalCityCount = 0; 
	//private static int globalRouteCount = Route.get_route_count(); 
	
	private static ArrayList<String> countryList = new ArrayList<String>();
	private static ArrayList<String> globalCityList = new ArrayList<String>();
	
	public static final String OutOfRangeException = "Answer out of range. Try Again.";
	public static final String MustBeIntException = "Answer must be an integer. Try again.";
	public static final String OneOrZeroException = "Answer must be 1 or 0. Try again.";
	public static final String GenericException = "Invalid answer. Try again.";
	
	public static int get_country_count () {
		return countryCount;
	}
	
	public static int get_global_city_count() {
		return globalCityCount;
	}
	
	public static ArrayList<String> get_country_list () {
		return countryList;
	}
	
	public static ArrayList<String> get_global_city_list () {
		return globalCityList; 
	}
	
	/*
	 * Begins the Worldbuilder with a message 
	 * This is the outermost container for most worldbuilder methods. 
	 */	
	public static void build () {

		System.out.println("Welcome to World Builder!");
		System.out.println("Let's start by making a country."); 
		countryCreator();
	}
	
	/*
	 * Container within which you can customize a new Country and all the details that go with it
	 * This route will also call cityCreator and routeCreator which will create and customize
	 * Cities and Routes to go inside of your country. 
	 */
	public static void countryCreator() {

		System.out.println("----Country Creator--------");
	
		System.out.println("Name: ");
		
		if (countryCount > 0) {
			scanner.nextLine();
		}
		
		String n = scanner.nextLine();
		m_country = new Country(n);
		countryCount++;
		countryList.add(m_country.get_country_name());
		
		System.out.println("Description: ");
		m_country.set_country_description(scanner.nextLine());
		
		System.out.println("Ruler: ");
		m_country.set_ruler(scanner.nextLine());
		
		constructCountryAge(); 
		
		constructCountryPop();

		contructCities(); 
		
		contructRoutes();
		
		m_country.list_all_info();
		
		saveCountry(); 
		
		addCountry(); 
		
	}
	
	//////////////////////////////
	////RouteConstruct Methods////
	//////////////////////////////
	
	/*
	 * Constructs Routes between cities, contains all other Route construction methods. 
	 */
	public static void contructRoutes () {
		
		if (m_country.get_cities().size() > 1) {
		
			boolean done = false; 
			do {
				City origin = null;
				City destination = null; 
				try {
					System.out.println("Now let's add routes between the cities.");
					boolean first = false;
					do {
						
						int i = 1;
						for (City city:m_country.get_cities()) {
							System.out.println(i + ") " + city.get_name());
							i++;
						}
						
						System.out.println("Type the number of the origin city.");
						int a = scanner.nextInt();
						if (a < 0) {
							System.out.println(OutOfRangeException);
						}
						else if (a > m_country.get_cities().size()) {
							System.out.println(OutOfRangeException);
						}
						else {
							for (int n = 0; n < m_country.get_cities().size(); n++) {
								if ( (n+1) == a) {
									origin = m_country.get_cities().get(n);
								}
							}
							first = true;
						}
					}while (first == false); 
					
					boolean second = false;
					do {
						
						int j = 1;
						for (City city: m_country.get_cities()) {
							System.out.println(j + ") " + city.get_name());
							j++;
						}
						
						System.out.println("Type the number of the destination city.");
						int b = scanner.nextInt();
						if (b < 0) {
							System.out.println(OutOfRangeException);
						}
						else if (b > m_country.get_cities().size()) {
							System.out.println(OutOfRangeException);
						}
						else {
							for (int n = 0; n < m_country.get_cities().size(); n++) {
								if ( (n+1) == b) {
									destination = m_country.get_cities().get(n);
								}
							}
							second = true;
						}
					} while (second == false);
					
					m_route = new Route(origin, destination);
					m_country.add_route(m_route);
					System.out.println("Route # " + m_route.get_route_id() + " has been created.");
					
					constructTerrain(m_route);
					constructLength();
					addEncounters(m_route); 
					nameRoute();
					System.out.println(m_route.get_all_info());
					saveRoute();
	
					System.out.println("Do you want to add another route? (Yes = 1/ No = 0) ");
					int v = scanner.nextInt();
					if (v==1) {
						continue;
					}
					else if (v==0) {
						System.out.println("No furhter routes will be added.");
						done = true;
					}
					else {
						System.out.println(OneOrZeroException);
					}
				}
				catch(Exception e) {
					System.out.println(GenericException);
					System.out.println("Error resulting from: " + e);
					scanner.next();
				}
			}while(done==false);
		} 
	}
	
	/*
	 * Asks the user if they want to add encounters to the route.
	 * Creates and names Encounter objects, linking them to the Route
	 */
	protected static void addEncounters (Route route) {
		
		boolean done = false; 
		int n = 0;
		do {
			try {
				System.out.println("Do you want to add encounters to this route? (Yes = 1/ No = 0)");
				int a = scanner.nextInt();
				if (a == 1) {
					boolean time = false;
					do {
						System.out.println("Enter Day Encounter name: ");
						scanner.nextLine(); 
						String name = scanner.nextLine();
						
						System.out.println(name + " will be added as an encounter.");
						m_route.add_day_encounters(m_route, name);
						time = true;
						
						/* Refactoring Note:
						 * Add Night encounters as well
						 * eventually
						 */
							
					} while(time==false);
				}
				else if (a == 0) {
					System.out.println("No furhter encounters will be added.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			
			}
			catch (Exception e) {
				System.out.println(GenericException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		}while (done == false); 
	}
	
	/*
	 * Asks the user if they want to add encounters to the city.
	 * Creates and names Encounter objects, linking them to the City
	 */
	protected static void addEncounters (City city) {
		
		boolean done = false; 
		int n = 0;
		do {
			try {
				System.out.println("Do you want to add encounters to this city? (Yes = 1/ No = 0)");
				int a = scanner.nextInt();
				if (a == 1) {
					boolean time = false;
					do {
						System.out.println("Enter Encounter name: ");
						scanner.nextLine(); 
						String name = scanner.nextLine();
						
						System.out.println(name + " will be added as an encounter.");
						m_city.add_encounters(m_city, name);
						time = true;
						
						/* Refactoring Note:
						 * Add Night encounters as well
						 * eventually
						 */
							
					} while(time==false);
				}
				else if (a == 0) {
					System.out.println("No furhter encounters will be added.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			
			}
			catch (Exception e) {
				System.out.println(GenericException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		}while (done == false); 
	}
	
	/*
	 * Asks the user how many days it should take to travel the route
	 * from origin to destination, measuring in days
	 */
	protected static void constructLength () {
				
		boolean done = false; 
		do {
			try {
				System.out.println("How many days does it take to traverse this route? (Please enter a number between 0 and 14) ");
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 14) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_route.set_length(a);
					System.out.println("Okay, travel duration is " + a + " days.");
					done = true; 
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
	
	/*
	 * Asks the user to choose from a list of what sort of terrain 
	 * this Route should consist of 
	 * 
	 * @param	route Route		:the Route whose terrain is being set 
	 */
	protected static void constructTerrain (Route route) {

		boolean done = false; 
		do {
			try {
				System.out.println("What sort of terrain covers this route?");
				int n = 1;
				for (TerrainType i: TerrainType.values()) {
					System.out.println(n + ") " + i);
					n++;
				}
				
				System.out.println("Type the number of the terrain you want to select.");
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > TerrainType.values().length) {
					System.out.println(OutOfRangeException);
				}
				else {
					n = 1; 
					for (TerrainType i: TerrainType.values()) {
						if (a == n) {
							route.set_terrainType(i);
							System.out.println("Terrain type will be " + route.get_terrainType());
						}
						n++;
					}
					done = true;
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		}while(done==false);
	}
	
	/*
	 * Asks the user to type in a name for the Route
	 * I.e. "The King's Road" 
	 */
	private static void nameRoute () {
		boolean done = false;
		do {
			try {
				scanner.nextLine();
				System.out.println("What will the route be called?");
				String name = scanner.nextLine();
				m_route.set_name(name);
				System.out.println("Okay. Route #" + m_route.get_route_id() + " will be called " + m_route.get_name() );
				done = true; 
			}
			catch(Exception e) {
				System.out.println(GenericException);
				//System.out.println("Error resulting from: " + e);
				scanner.next(); 
			}
		} while (done == false);
	}
	
	/*
	 * Asks the user if they want to save the Route information to a text file
	 * If yes, all Route information is exported to a text file in its Country's directory for storage. 
	 * If not, no such text file is created. 
	 * The file is named using the Route's name ( i.e "The King's Road.txt" )
	 * and will be located in the directory like in the example below 
	 * 
	 * C::\Users\USER\Desktop\DM\Countries\Westeros\Routes\The King's Road\The King's Road.txt
	 * 
	 */
	protected static void saveRoute() {
		
		boolean done = false;
		do {
			try {
				System.out.println("Do you want to save Route #" + m_route.get_route_id() + " to a file? (Yes = 1/ No = 0)");
				int a = scanner.nextInt();
				if (a == 1) {
					FileCreator routeFile = new FileCreator(m_route);
					System.out.println("Route #" + m_route.get_route_id() + " has been saved.");
					done = true; 
				}
				else if (a == 0) {
					System.out.println("Route #" + m_route.get_route_id() + " will not be saved.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
	
	//////////////////////////////
	//CountryConstruct Methods////
	//////////////////////////////
	
	/*
	 * Prompts the user to set the age of the Country. 
	 */
	protected static void constructCountryAge () {
		
		boolean valid_age = false;
		do {
			System.out.println("Age: ");
			try {
				//scanner.nextInt(); 
				int a = scanner.nextInt();
				if (a >= 0) {
					m_country.set_country_age(a); 
					valid_age = true;
				}
				else {
					System.out.println(OutOfRangeException);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (valid_age == false);
	}
	
	/*
	 * Prompts the user to set the population of the Country. 
	 */
	protected static void constructCountryPop () {
		
		boolean valid_pop = false;
		do {
			System.out.println("Population: ");
			try {
				int p = scanner.nextInt();
				if (p >= 0) {
					m_country.set_population(p); 
					valid_pop = true;
				} 
				else {
					System.out.println(OutOfRangeException);						}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (valid_pop == false);
	}
	
	/*
	 *  Asks the user if they want to save the Country's information to a text file
	 *  If so, the file will be named after the Country's name (i.e. "Westeros.txt")
	 *  It will be located in the directory like in the example below
	 *  
	 *  C:\Users\USER\Desktop\DM\Countries\Westeros\Westeros.txt
	 *  
	 */
	protected static void saveCountry () {
		
		boolean done = false;
		do {
			System.out.println("Do you want to save this country to a file? (Yes = 1/No = 0)");
			try {
				int a = scanner.nextInt(); 
				if (a == 1) {
					FileCreator countryFile = new FileCreator(m_country);
					System.out.println(m_country.get_country_name() + " has been saved.");
					done = true;
					break;
				}
				else if (a == 0) {
					System.out.println(m_country.get_country_name() + " will not be saved.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException);
				//System.out.println("Error resulting from: " + e);
				scanner.next(); 
			}
		} while(done == false);
	}

	/*
	 *  Asks the user to decide whether to add an additional Country to the world
	 *  If so, the user will be sent back to the beginning to countryCreator() to
	 *  begin creating the new Country. 
	 *  If not, Worldbuilder is through and the program will end. 
	 */
	private static void addCountry() {
		
		boolean done = false;
		do {
			System.out.println("Do you want to create another country? (Yes = 1/No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					countryCreator(); 
					done = true;
				}
				else if (a == 0) {
					System.out.println("Okay. Ending Worldbulder...");
					scanner.close(); 
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
	
	/////////////////////////////
	////CityConstruct Methods////
	/////////////////////////////
	
	/*
	 * Prompts the user to decide if they want to add a City to the Country
	 */
	private static void contructCities() {
		
		boolean done = false;
		do {
			System.out.println("Do you want to add any cities to " + m_country.get_country_name() + "? (Yes = 1/No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 0) {
					if (m_country.m_capitol.equalsIgnoreCase("Unknown")) {
						System.out.println(m_country.get_country_name() + "must have a capital. You cannnot proceed without making one.");
					}
					else {
						System.out.println("No additional cities will be added.");
						done = true;
					}
				}
				else if (a == 1) {
					cityCreator(); 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
	
	/*
	 * Container for all other City methods where all of the details the City will be decided
	 */
	public static void cityCreator () {

		System.out.println("---------City Creator--------");
		
		if (countryCount > 0) {
			scanner.nextLine();
		}
		
		System.out.println("Name: ");
		String city_name = scanner.nextLine();
		m_city = m_country.add_city(city_name);
		globalCityCount ++; 
		globalCityList.add(city_name);
		
		System.out.println("Description: ");
		m_city.set_description(scanner.nextLine());
		
		constructAge();
		
		constructPopulation();
	
		boolean capital = constructAsCapital(); 
		
		constructCityType(capital);
		
		constructTerrain();
		
		constructLocalRuler();
		
		constructNPCs();
		
		customizeBuildings();
		
		addEncounters(m_city); 
		
		m_city.list_all_info();
		
		saveCity();
		
	}
	
	/*
	 *  Asks the user if they want to save the City's information to a text file
	 *  If so, the file will be named after the City's name (i.e. "King's Landing.txt")
	 *  It will be located in the directory like in the example below
	 *  
	 *  C:\Users\USER\Desktop\DM\Countries\Westeros\Cities\King's Landing\King's Landing.txt
	 *  
	 */
	protected static void saveCity() {
		
		boolean done = false;
		
		do {
			System.out.println("Do you want to save this city to a file? (Yes = 1/No = 0)");
			try {
				int save = scanner.nextInt(); 
				if (save == 1) {
					FileCreator cityFile = new FileCreator(m_city);
					System.out.println(m_city.get_name() + " has been saved.");
					done = true;
					break;
				}
				else if (save == 0) {
					System.out.println(m_city.get_name() + " will not be saved.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException + e);
				//System.out.println("Error resulting from: " + e);
				scanner.next(); 
			}
		} while(done == false);
	}
	
	/*
	 * Will prompt the user for their preferences in adding or deleting buildings.
	 * User can choose from a list of buildings to delete from the City and then
	 * type in the names of buildings they would like to add to the City
	 */
	protected static void customizeBuildings() {
		
		System.out.println("Here is a list of the buildings currently in " + m_city.get_name() + ":");
		m_city.list_buildings();
		
		String article = "a"; 
		String adj = ""; 
		int n = 0;
		
		boolean done = false;
		do {
			
			if (n > 0) {
				article = "another";
				adj = "more";
			}
			
			try {
				
				System.out.println("Do you want to delete " + article + " building? (Yes = 1/ No = 0)");
				int a = scanner.nextInt(); 
				n++;
				
				if (a == 1) {
					System.out.println("Which building do you want to delete?");
					int x = 1;
					for (Object i: m_city.get_buildings()) {
						System.out.print(x + ") " + i + "\n");
						x++;
					}
					System.out.println("Enter the number of the building you want to delete.");
					int b = scanner.nextInt(); 
					
					if (b > m_city.get_buildings().size()) {
						System.out.println(OutOfRangeException);
					}
					else if ( b < 0) {
						System.out.println(OutOfRangeException);
					}
					else {
						String building = "";
						for (int y = 0; y < m_city.get_buildings().size(); y++) {
							if ( (y+1) == b) {
								building = m_city.get_buildings().get(y).toString();
								m_city.delete_building(building);
							}
						}
						System.out.println(building + " has been deleted from " + m_city.get_name());
					}
				}
				else if (a == 0) {
					System.out.println("Okay, no " + adj + " buildings will be deleted.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(GenericException);
				System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
		
		addBuildings();
		
	}
	
	/*
	 * The second part of customizeBuildings() that will get the user's 
	 * input on what buildings to add to the City 
	 */
	protected static void addBuildings () {
		
		String article = "a"; 
		String adj = ""; 
		int n = 0;
		boolean done = false; 
		do {
			if (n > 0) {
				article = "another";
				adj = "more";
			}
			try {
				System.out.println("Would you like to add " + article + " building? (Yes = 1/ No = 0)");
				int a = scanner.nextInt(); 
				if (a == 1) {
					
					scanner.nextLine(); // do not remove/ change line or you'll break it. 
					System.out.println("Type the building you want to build in " + m_city.get_name()); 
					String building = scanner.nextLine(); 
					m_city.add_building(building);
					n++;
					System.out.println(building + " was added. " + m_city.get_name() + " now has...");
					m_city.list_buildings();
					
				}
				else if (a == 0) {
					System.out.println("Okay, no " + adj + " buildings will be added.");
					done = true; 
				}
				else {
					System.out.println(OutOfRangeException);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next(); 
			}
		} while (done == false);	
	}
	
	/*
	 * Prompts the user to choose from a list of terrain types to decide
	 * what sort of terrain the city should be surrounded with 
	 */
	protected static void constructTerrain () {
		
		boolean done = false; 
		do {
			try {
				System.out.println("What sort of terrain is this city located in?");
				int n = 1;
				for (TerrainType i: TerrainType.values()) {
					System.out.println(n + ") " + i);
					n++;
				}
				
				System.out.println("Type the number of the terrain you want to select.");
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > TerrainType.values().length) {
					System.out.println(OutOfRangeException);
				}
				else {
					n = 1; 
					for (TerrainType i: TerrainType.values()) {
						if (a == n) {
							m_city.set_TerrainType(i);
							System.out.println("Terrain type will be " + m_city.get_TerrainType());
						}
						n++;
					}
					done = true;
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		}while(done==false);
	}
	
	/*
	 * Prompts the user to enter the age they want the City to be
	 */
	protected static void constructAge () {
		
		boolean done = false;
		do {
			System.out.println("Age: ");
			try {
				int a = scanner.nextInt();
				if (a >= 0) {
					m_city.set_age(a); 
					done = true;
				}
				else {
					System.out.println(OutOfRangeException);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
	
	/*
	 * Prompts user to enter what the population of the city should be 
	 */
	protected static void constructPopulation () {
		
		boolean done = false;
		do {
			System.out.println("Population: ");
			try {
				int a = scanner.nextInt();
				if (a >= 0) {
					m_city.set_population(a); 
					done = true;
				} 
				else {
					System.out.println(OutOfRangeException);						}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
	
	/*
	 * Prompts the user to decide if they want this City to be the capital of the Country
	 * However, if there is already a capital, a new one cannot be set. 
	 * Additionally, there must be a capital to exit Worldbuilder, so one must be set eventually. 
	 * 
	 * @return	capital Boolean		:true if the City was set as capital, false if not 
	 */
	private static boolean constructAsCapital() {
		
		boolean done = false;
		boolean capital_exists = false;
		do {
			System.out.println("Do you want to make this city the capital? (Yes = 1/ No = 0");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					int success = m_city.get_country().set_capitol(m_city);
					if (success == 1) {
						done = true;
						capital_exists = true; 
					}
				}
				else if (a==0) {
					System.out.println("Okay. " + m_city.get_name() + " is not the capital of " + m_city.get_country().get_country_name());
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
				catch (Exception e) {
					System.out.println(OneOrZeroException);
					//System.out.println("Error resulting from: " + e);
					scanner.next();
				}
		} while (done == false);
		return capital_exists; 
	}
	
	/*
	 * If the city is a capital, it already has a city type, and so this function will do nothing.
	 * If the city is not the capital, the user will be prompted to decide from a list of city types
	 * which they would like this city to be. 
	 * 
	 * @param	capital boolean		:true if the City is a capital, false if it is not 
	 */
	protected static void constructCityType (boolean capital) {
		
		if (!capital) {
			
			boolean done = false;
			do {
				System.out.println("What sort of city is " + m_city.get_name() + " ?");
				System.out.println("Please enter the number that corresponds to the type you want.");
				
				int n = 1;
				for (CityType i: CityType.values()) {
					if (i == CityType.CAPITAL) {
						continue;
					}
					System.out.println(n + ") " + i);
					n++;
				}
				
				try {
					int a = scanner.nextInt();
					if (a < 1) {
						System.out.println(OutOfRangeException);
					}
					else if (a > CityType.values().length) {
						System.out.println(OutOfRangeException);
					}
					else {
						switch (a) {
							case 1: 
								m_city.set_ctype(CityType.REGIONAL_SEAT);
								break;
							case 2:
								m_city.set_ctype(CityType.MINOR_CITY);
								break;
							case 3:
								m_city.set_ctype(CityType.COMMERCE_CENTER);
								break;
							case 4:
								m_city.set_ctype(CityType.FARMING_COMMMUNITY);
								break;
							case 5:
								m_city.set_ctype(CityType.MINING_TOWN);
								break;
							case 6:
								m_city.set_ctype(CityType.POOR_VILLAGE);
								break;
						}
						System.out.println(m_city.get_name() + " is a " + m_city.get_ctype());
						done = true; 
					}
				}
				catch (Exception e) {
					System.out.println(MustBeIntException);
					//System.out.println("Error resulting from: " + e);
					scanner.next();
				}
			} while (done == false);
		}
	}
	
	/*
	 * Prompts the user to decide if there is local leader for the City
	 * and enter their name if there is
	 * If none is entered, the national leader is considered the local leader by default
	 */
	protected static void constructLocalRuler () {
		
		boolean done = false;
		do {
			System.out.println("Is there a local leader that runs the city? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
	
					System.out.println("What is their name?");
					scanner.nextLine(); //If you remove or change this line, it will break. Not sure why. 
					String name = scanner.nextLine();
					m_city.set_local_ruler(name);
					done = true; 
					
				}
				else if (a==0) {
					System.out.println("Okay. " + m_city.get_name() + " doesn't have a local ruler.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(GenericException);
				System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
	
	/*
	 * Prompts the user to decide if they want to add any NPC's to the City
	 * If they do, they will be prompted to give them a name and description. 
	 */
	protected static void constructNPCs () {
		
		boolean done = false;
		String article = "an"; 
		String adj = ""; 
		int n = 0;
		do {
			if (n > 0) {
				article = "another";
				adj = "more";
			}
			
			System.out.println("Do you want to add " + article + " NPC? (Yes = 1/ No = 0)"); 
			n++; 
			
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					
					scanner.nextLine();
					System.out.println("What is their name?");
					String name = scanner.nextLine();
					System.out.println("How would you descrie them>");
					String descrip = scanner.nextLine(); 
					m_city.add_NPC(name, descrip);
					
				}
				else if (a==0) {
					System.out.println("Okay, no " + adj + " NPC's will be added.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(GenericException);
				System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
	}
}
