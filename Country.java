package Dnd;

import java.util.*; 

public class Country {
	/*
	 * Class for objects that represent countries. They have Cities and Routes between Cities,
	 * as well as many attributes that make them unique. 
	 */

	public enum CityType {
        CAPITAL,
        REGIONAL_SEAT,
        MINOR_CITY,
        COMMERCE_CENTER,
        FARMING_COMMMUNITY,
        MINING_TOWN,
        POOR_VILLAGE,
    }
    
	public static enum TerrainType {
	    FOREST,
	    OCEAN,
	    SWAMP,
	    DESERT,
	    PLAINS,
	    HILLS,
	    MOUNTAINS,
	    COAST
	}
	
	/*
	 * Class variables initiated with default values
	 */
	protected String m_country_name = "Unkown";
    protected String m_capitol = "Unknown";
    protected String m_ruler = "Unknown";
    protected int m_country_age = 0; 
    protected int m_population = 0; 
    static protected int cityCount = 0; 
    protected String m_country_description = "Unknown";
    
    /*
     * Data structures for holding coordiniate ingo for implementing a map, currently not implemented
     */
    private static HashMap<String, Integer[]> m_city_coords = new HashMap<String, Integer[]>();
    private static HashMap<String,String> m_map_key = new HashMap<String,String>(); 
    
    /*
     * Holds the Country and Route objects that belong to each particular country
     */
    ArrayList<City> m_cities = new ArrayList<City>();
    ArrayList<Route> m_routes = new ArrayList<Route>();
    
    /*
     * Some extra information such as language and religion that 
     * can be implemented later. 
     */
    ArrayList<String> m_langs = new ArrayList<String>();
    ArrayList<String> m_religions = new ArrayList<String>();
    
    /*
     * Country default constructor
     * 
     * @param	 name String	:the name of the Country
     */
    public Country(String name) {
        
        set_country_name(name);
        
    }
    
	///////////////////////////////////////////
	// Set & Get                             //
	///////////////////////////////////////////
	
	/*
	 * Sets the String value of the Country's name 
	 * 
	 * @param 		m_country_name 		:the Country's name 
	 */
	public void set_country_name (String name) {
		
		m_country_name = name; 
		
	}
	
	/*
	 * Returns the String value of the Country's name 
	 * 
	 * @return 		m_country_name 		:the Country's name 
	 */
	public String get_country_name () {
		
		return m_country_name;
		
	}
	
	/*
	 * Prints a message with the Country's name 
	 */
	public void list_country_name () {
		
		System.out.println("Name: " + m_country_name);
		
	}
	
	/*
	 * Sets the Integer value of the Country's age 
	 * 
	 * @param	age Integer	:the age of the Country 
	 */
	public void set_country_age (int age) {
		
			m_country_age = age;
			
	}
	
	/*
	 * Returns the Integer value of the Country's age 
	 * 
	 * @return	m_age Integer	:the age of the Country 
	 */
	public int get_country_age () {
		
		return m_country_age;
		
	}
	
	/*
	 * Prints a message with the Country's age 
	 */
	public void list_country_age () {
		
		System.out.println("Age: " + m_country_age);
		
	}
	
	/*
	 * Sets the String value of the Country's description 
	 * 
	 * @param 	m_country_description	:the Country's description 
	 */
	public void set_country_description (String descrip) {
		
		m_country_description = descrip;
		
	}
	
	/*
	 * Returns the String value with the Country's description 
	 * 
	 * @return 	m_country_description	:the Country's description 
	 */
	public String get_country_description () {
		
		return m_country_description;
		
	}
	
	/*
	 * Prints out a message with the Country's description 
	 */
	public void list_country_description () {
		
		System.out.println("Description: " + m_country_description);
		
	}
	
	/*
	 * Sets the integer value of the Country's population 
	 * 
	 * @param	 m_population Integer 	:the population of the Country 
	 */
	public void set_population (int pop) {
		
		m_population = pop; 
		
	}
	
	/*
	 * Returns the integer value of the Country's population 
	 * 
	 * @return	 m_population Integer 	:the population of the Country 
	 */
	public int get_population () {
		
		return m_population;
		
	}
	
	/*
	 * Prints out a message with the population of the Country 
	 */
	public void list_population () {
		
		System.out.println("Population: " + m_population);
		
	}
	
	/*
	 * Sets the name of the Country's Ruler
	 * 
	 * @param	name String		:the name of the Country's RUler 
	 */
	public void set_ruler (String name) {
		
		m_ruler = name ;
		
	}
	
	/*
	 * Returns the name of the Country's Ruler
	 * 
	 * @return		String		:the name of the Country's Ruler 
	 */
	public String get_ruler () {
		
		return m_ruler;
		
	}
	
	/*
	 * Prints out a message with the name of the Country's Ruler 
	 */
	public void list_ruler () {
		
		System.out.println("Ruler: " + m_ruler);
		
	}
	
	/*
	 * Adds the given Route to the Country's list of Routes
	 * 
	 * @param		route Route		:the Route to be added to the Country 
	 */
	public void add_route (Route route) {
		
		m_routes.add(route);
		
	}
	
	/*
	 * Returns a list of the Country's Routes 
	 * 
	 * @return 		ArrayList<Route> 	:list of the Routes in this Country 
	 */
	public ArrayList<Route> get_routes () {
		
		return m_routes; 
		
	}
	
	/*
	 * Prints out a list of this Country's Routes
	 */
	public void list_routes () {
		
		for (Route i: m_routes) {
			System.out.println(i);
		}
		
	}
	
	/*
	 * Returns a list of the Country's Cities 
	 * 
	 * @return 		ArrayList<City> 	:list of the Cities in this Country 
	 */
	public ArrayList<City> get_cities () {
		
		return m_cities; 
		
	}
	
	/*
	 * Returns the name of the Capital City
	 * 
	 * @return 		String		:name of the Capital City 
	 */
	public String get_capitol() {
		
		return m_capitol;
		
	}
	
	/*
	 * Changes to capital of the Country 
	 * 
	 * @param	city City		:the City you want to be the new capital
	 * @return	result Integer	:1 if successfully changed, 0 otherwise  
	 */
	public int change_capitol (City city) {
		
		if (city.get_ctype() == CityType.CAPITAL) {
			System.out.println("Error: " + city.get_name() + " is already the capital."); 
			return 0;
		}
		else {
			for(City i: m_cities) {
				if (i.get_name() == city.get_name()) {
					m_capitol = i.get_name();
					i.set_ctype(CityType.CAPITAL);
					System.out.println(city.get_name() + " is now the capitol.");
					city.growCity();
					return 1; 
				}
			}
		}
		
		System.out.println(m_country_name + " has no such city.");
		return 0; 
	}

	/*
	 *  Sets the Country's Capital City. If there is already a Capital City
	 *  this method will print an error and do nothing. 
	 *  
	 *  @param 		city City		:the City that is going to be the Capital City
	 *  @return 	result Integer	:1 if successfully set, 0 otherwise 
	 */
	public int set_capitol (City city) {
		
		boolean repeat = false;
		
		for (City i: m_cities) {
			if (i.get_ctype() == CityType.CAPITAL) {
				System.out.println("Error: " + i.get_name() + " is already the capital."); 
				System.out.println("       If you want to change the capital, use the change_capital method.");
				repeat = true;
				return 0; 
			}
		} 
		
		for(City i: m_cities) {
			if (i.get_name() == city.get_name() & !repeat) {
				m_capitol = i.get_name();
				i.set_ctype(CityType.CAPITAL);
				System.out.println(city.get_name() + " is now the capitol.");
				city.growCity();
				
				return 1; 
			}
		}
		
		System.out.println(m_country_name + " has no such city.");
		return 0; 
	}
	
	/*
	 *Prints out all of the Cities in this Country 
	 */
	public void list_cities () {
	
		int n = 1;
		for (City i: m_cities){
			String flag = "";
			if (i.get_ctype() == CityType.CAPITAL) {
				flag = flag + "*capital*";
			}
			System.out.println(n + ") " + i.get_name() + "   " + flag);
			n++; 
		}
		
	}
	
	/*
	 * Returns a String with all of the Country's information separated by "\n"
	 * 
	 * @param	output String	:String containing Country information 
	 */
	public String get_all_info() {
		
		String output = ""; 
		output = output + "-------------------------------------------------- \n";
		output = output + "<<<<<<<<<<<<<<  Country InfoDump  >>>>>>>>>>>>>> \n"; 
		output = output + "Name: " + m_country_name + "\n"; 
		output = output + "Description: " + get_country_description() + "\n"; 
		output = output + "Age: " + m_country_age + "\n"; 
		output = output + "Population: " + m_population + "\n"; 
		output = output + "Ruler: " + m_ruler + "\n"; 
		output = output + "Capitol: " + m_capitol + "\n"; 
		output = output + "Cities: " + "\n";
		int v = 1;
		for (City i: m_cities) {
	        output = output + v + ") " + i.get_name() + "\n"; 
	        v++;
	    }
		output = output + "-------------------------------------------------- \n";
		output = output + "Routes:  \n";
		for (Route i: m_routes) {
			output = output + "#" + i.get_route_id() + " : " + i.get_name() + " : " +i.get_origin().get_name() + " : " + i.get_destination().get_name() + "\n";
		}
		output = output + "-------------------------------------------------- \n";

		return output;
		
	}
	
	/*
	 * Prints out a sheet wuth the Country's information
	 */
	public void list_all_info () {
		
		System.out.println("------------------------------------");
		System.out.println("<<<<<< Country InfoDump >>>>>>");
		
		list_country_name();
		list_country_age();
		list_country_description();
		list_ruler();
		list_population();
		
		System.out.println("Cities:");
		list_cities();
		
		System.out.println("------------------------------------");
		
		System.out.println("Routes: ");
		for (Route i: m_routes) {
			System.out.println("Route #" + i.get_route_id() + " " + i.get_name() + " ( " +i.get_origin().get_name() + " --->  " + i.get_destination().get_name() + " )");
		}
		
		System.out.println("------------------------------------");
		
	}
	
	////////////////////////////////////////////
	// Actions                                //
	////////////////////////////////////////////
	
	/*
	 * Creates a new City with the given name and places it in this Country
	 * 
	 * @param 	name String		:the name of the new City 
	 */
	public City add_city (String name) {
	
		Country m_country = this; 
		City city = new City(name, m_country);
		m_cities.add(city);
		cityCount ++; 
		return city; 
	
	}
	
	/*
	 * Deletes the given City from the Country 
	 * 
	 * @param 	city City	:the City that is being deleted 
	 */
	public void delete_city (City city) {
		
		boolean exists = false; 
		for (int i = 0; i < m_cities.size(); i++) {
			if (m_cities.get(i).get_name() == city.get_name()) {
					m_cities.remove(m_cities.get(i));
					//System.out.println(name + " has been deleted.");
					exists = true; 
					cityCount = cityCount - 1; 
			}
		}
		
		if (!exists) {
			System.out.println("There is no such city in " + get_country_name());
		}
		
	}
	
	/*
	 * If a City is deleted from the Country, so should the Routes that connected it to other Cities.
	 * This Methods takes a City that is being deleted and makes sure those Routes
	 * are also deleted. 
	 * 
	 * @param	city City	:the City that's being deleted 
	 */
	public void delete_routes (City city) {
		
		try {
			int count = m_routes.size(); 
			for (int i = 0; i < count; i ++) {
				if (m_routes.get(i).get_origin() == city | m_routes.get(i).get_destination() == city) {
					Route gone = m_routes.remove(i);
					for (City x: m_cities) {
						if (x.get_routes().contains(gone)) {
							x.get_routes().remove(gone);
						}
					}
					i = i - 1; 
				}
			}
		}
		catch (Exception e) {
			//DO NOT REMOVE THIS CATCH. IT WILl BREAK; 
			//System.out.println("Something going on");
		}
		
	}
		
}
