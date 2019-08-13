package Dnd;

import java.util.*;

import Dnd.Country.CityType;
import Dnd.Country.TerrainType;
import Dnd.Encounter.EncounterType;

public class City {
	/*
	 * Class representing individual cities 
	 */
	
	CityType m_ctype = CityType.POOR_VILLAGE; 
	TerrainType m_TerranType = TerrainType.FOREST;
    
	private Country m_country; 

    private String m_name = "Unknown";
    private String m_description = "No description available";
    private String m_local_ruler = "Unknown";
    
    private int m_age = 0;
    private int m_population = 0; 
    
    ArrayList<String> m_NPCs = new ArrayList<String>();
    HashMap<String, String> m_NPCsDescrip = new HashMap<String, String>();
    
    ArrayList<String> m_buildings = new ArrayList<String>();
    
    ArrayList<Encounter> m_day_encounters = new ArrayList<Encounter>(); 
    ArrayList<Encounter> m_night_encounters = new ArrayList<Encounter>(); 
    ArrayList<Encounter> m_all_encounters = new ArrayList<Encounter>(); 
    ArrayList<Route> m_routesList = new ArrayList<Route>(); 
	
    
    ///////////////////////////////////////////////////
    // Constructors                                  //
    ///////////////////////////////////////////////////
    
    public City () {

        constructCity(); 
    }
    
    public City (String name) {
        
        set_name(name);
        constructCity(); 
        
    }
    
    /*
     * This should be the main Constructor that is used. 
     */
    public City (String name, Country country) {

        set_country(country);
        
        m_local_ruler = m_country.get_ruler();
        
        set_name(name);
        constructCity(); 
        
    }
    
     public City (String name, int age) {
        
        set_name(name);
        set_age(age);
        constructCity(); 
        
    }
    
     public City (String name, int age, CityType ctype) {
        
        set_name(name);
        set_age(age);
        set_ctype(ctype);
        constructCity();
        
    }

    public City (String name, int age, CityType ctype, TerrainType terrain) {
         
        set_name(name);
        set_age(age);
        set_ctype(ctype);
        set_TerrainType(terrain);
        constructCity();
        
    }


    public City (String name, CityType ctype, TerrainType terrain) {
         
        m_ctype = ctype; 
        m_TerranType = terrain; 
        m_name = name; 
        
        constructCity(); 
        
    }

    /*
     *Populates City with buildings, from largest to smallest kind of city in such a way so that the largest 
     *contains all building types, the second-largest contains all but the ones exclusively for the largest, and so on 
     *
     *This method is for changing the CityType after it has already been constructed 
     */
    public void growCity() {
   
    	m_buildings.clear();
        switch (m_ctype) {
            case CAPITAL:
                m_buildings.add("Palace"); 
                m_buildings.add("Government House");
                m_buildings.add("Ancient Cathedral");
                m_buildings.add("Lavish Inn");

            case REGIONAL_SEAT:
                m_buildings.add("Colloseum");
                m_buildings.add("Cathedral");
             
            case MINOR_CITY:
                m_buildings.add("Courthouse");
                m_buildings.add("Hospital");
                m_buildings.add("House of Worship");
               
            case COMMERCE_CENTER:
                m_buildings.add("Tailor's Shop");
                m_buildings.add("Apothecary");
                m_buildings.add("Armory");
                m_buildings.add("Inn");
                m_buildings.add("Guild Hall");
                m_buildings.add("Pub");
                m_buildings.add("Huge Market");
                
            case FARMING_COMMMUNITY:
                m_buildings.add("Small Market");
                m_buildings.add("Farms");
                m_buildings.add("Blacksmith");
                m_buildings.add("Temple");
                m_buildings.add("Town Square");

            case MINING_TOWN:
                m_buildings.add("Saloon");
                m_buildings.add("Stables");
                m_buildings.add("Dirty Hostel");

            case POOR_VILLAGE:
                m_buildings.add("Rundown Bar");
                m_buildings.add("Jailhouse");
                m_buildings.add("Slums");
                m_buildings.add("Graveyard");
                m_buildings.add("Decrepit church");
                
        }
    }
    
    /*
     *Populates City with buildings, from largest to smallest kind of city in such a way so that the largest 
     *contains all building types, the second-largest contains all but the ones exclusively for the largest, and so on 
     *
     *This method is for use in the Constructors 
     */
    private void constructCity() {

        switch (m_ctype) {
            case CAPITAL:
                
                m_buildings.add("Palace"); 
                m_buildings.add("Government House");
                m_buildings.add("Ancient Cathedral");
                m_buildings.add("Lavish Inn");

            case REGIONAL_SEAT:
                m_buildings.add("Colloseum");
                m_buildings.add("Cathedral");
                
            case MINOR_CITY:
                m_buildings.add("Courthouse");
                m_buildings.add("Hospital");
                m_buildings.add("House of Worship");

            case COMMERCE_CENTER:
                m_buildings.add("Tailor's Shop");
                m_buildings.add("Apothecary");
                m_buildings.add("Armory");
                m_buildings.add("Inn");
                m_buildings.add("Guild Hall");
                m_buildings.add("Pub");
                m_buildings.add("Huge Market");
                
            case FARMING_COMMMUNITY:
                m_buildings.add("Small Market");
                m_buildings.add("Farms");
                m_buildings.add("Blacksmith");
                m_buildings.add("Temple");
                m_buildings.add("Town Square");
                
            case MINING_TOWN:
                m_buildings.add("Saloon");
                m_buildings.add("Stables");
                m_buildings.add("Dirty Hostel");
                
            case POOR_VILLAGE:
                m_buildings.add("Rundown Bar");
                m_buildings.add("Jailhouse");
                m_buildings.add("Slums");
                m_buildings.add("Graveyard");
                m_buildings.add("Decrepit church");
                
        }
    }
    
	public void autoSave () {
		try {
			FileCreator newfile = new FileCreator(this); 
		}
		catch(Exception e) {
			System.out.println("There was problem with the Autosave.");
		}
		
	}
    
    ///////////////////////////////////////////////
    // Setters and Getters                       //
    ///////////////////////////////////////////////
    
    /*
     * Returns the City's age
     * 
     * @return		m_age Integer 		:the age of the City 
     */
    public int get_age () {
        return m_age;
    }
    
    /*
     * Prints out a message with the City's age 
     */
    public void list_age() {
    	
        System.out.println(m_name + " is " + m_age + " Years old.");
        
    }
    
    /*
     * Sets the City's age
     * 
     * @param	age Integer		:how many years old the CIty is 
     */
    public void set_age (int age) {
    	
        if (age > 0) {
        m_age = age; 
        }
        else {
            System.out.println("Age cannot be negative.");
        }
        
    }
    
    /*
     * Adds a building to the CIty's list of buildings
     * 
     * @param	building String		:the name of the building that should be added 
     */
    public void add_building (String building) {
        
        m_buildings.add(building);

    }
    
    /*
     * Deletes a building from the CIty's list of buildings
     * 
     * @param	building String		:the name of the building that should be deleted 
     */
    public void delete_building(String building) {
        
        for (int i = 0; i < m_buildings.size(); i++) {
            if (m_buildings.get(i) == building) {
                m_buildings.remove(i);
            }
        }
    }
    
    /*
     * Returns the List of buildings in the CIty
     * 
     * @return	m_buildings ArrayList<String>	:a list of Strings that are the building names 
     */
    public ArrayList<String> get_buildings () {
    	
        return m_buildings;
    
    }
    
    /*
     * Prints out a message listing the City's buildings 
     */
    public void list_buildings() {

    	System.out.println("Buildings: " + m_buildings);
    
    }
    
    /*
     * Returns the Country object associated with this city 
     *
     *@return 	m_country Country	:the Country this City belongs to
     */
    public Country get_country () {
        
        return m_country; 
    }
    
    /*
     * Prints the Country object associated with this city 
     */
    public void list_country () {
        
        System.out.println(m_name + " belongs to the country of " + m_country.get_country_name());
        
    }
    
    /*
     * Set the Country object to be associated with this city 
     */
    public void set_country(Country country) {
    	
        m_country = country; 
        
    }
    
    /*
     * Returns the CityType of the City
     * 
     * @return 	ctype CityType	:the type of City the CIty is
     */
    public CityType get_ctype () {

        return m_ctype;
        
    }
    
    /*
     * Prints a message with the CIty's CityType 
     */
    public void list_ctype () {

    	System.out.println(m_name + " is a " + m_ctype);
    	
    }
    
    /*
     * Sets the CityType of the City
     * 
     * @param 	ctype CityType	:the type of City you want it to be 
     */
    public void set_ctype (CityType ctype) {

        m_ctype = ctype; 
        growCity();
        
    }
    
    /*
     * Adds an additional String description onto the end of whatever description is already given 
     * 
     * @param 	addition String		:The String you want to add to the end of the City's existing description
     */
    public void add_to_description(String addition) { 
        
        m_description = m_description + " " + addition;
        System.out.println("The new description is: " + m_description);
        
    }
    
    /*
     * Returns the City's description
     * 
     * @return	description String	:the description of the City 
     */
    public String get_description () {
    	
        return m_description;
        
    }
    
    /*
     * Prints a message with the description of the City 
     */
    public void list_description () {
    	
        System.out.println("Description: " + m_description);
        
    }
    
    /*
     * Sets the City's description
     * 
     * @param	description String	:the description of the City 
     */
    public void set_description (String description) {
    	
        m_description = description; 
        
    }
    
    /*
     * Returns the name of the local ruler
     * 
     * @return	m_local_ruler String	:the name of the local ruler 
     */
    public String get_local_ruler () {
    	
        return m_local_ruler;
        
    }
    
    /*
     * Prints out a message with the name of the local ruler 
     */
    public void list_local_ruler () {
    	
        System.out.println("The local ruler is " + m_local_ruler);
        
    }
    
    /*
     * Sets the City's local ruler, the person who runs the City 
     * 
     * @param 	localruler String	:the name of the local ruler 
     */
    public void set_local_ruler (String localruler) {
    	
        m_local_ruler = localruler;
        
    }
    
    /*
     * Returns the population of the CIty
     * 
     * @return	m_population Integer		:the number of people living in the City
     */
    public int get_population () {
    	
    	return m_population; 
    	
    }
    
    /*
     * Prints out a message with the CIty's population 
     */
    public void list_population() {
    	
    	System.out.println(m_population);
    	
    }
    
    /*
     * Sets the population of the CIty
     * 
     * @param	pop Integer		:the number of people living in the City
     */
    public void set_population (int pop) {
    	
    	m_population = pop; 
    	
    }
    
    /*
     * Returns the name of the City
     * 
     * @return	m_name String		:the name of the City 
     */
    public String get_name () {
    	
        return m_name;
        
    }
    
    /*
     * Prints a message with the City's name
     */
    public void list_name() {
    	
        System.out.println("The city is named " + m_name + ".");
        
    }
    
    /*
     * Sets the name of the City
     * 
     * @param	name String		:the name the City should be named 
     */
    public void set_name (String name){
    	
        m_name = name; 
        
    }
    
    /*
	 * Creates an encounter object and adds it to this City's list 
	 * of encounters. 
	 * 
	 * @param	city City		:The city to which this encounter belongs
	 * @param	name String		:What this encounter is to be called 
	 */
	public Encounter add_encounters (City city, String name, EncounterType eType) {
		
		Encounter encounter = new Encounter(city, name, eType);
		if (eType == EncounterType.DAY) {
			m_day_encounters.add(encounter);
		}
		else {
			m_night_encounters.add(encounter);
		}
		m_all_encounters.add(encounter);
		return encounter; 
		
	}
	
	/*
	 * Returns the City's list of day encounters 
	 * 
	 * @return 		m_encounters ArrayList<Encounter> 		:the encounters belonging to this City
	 */
	public ArrayList<Encounter> get_day_encounters() {
		
		return m_day_encounters; 
		
	}
	
	public ArrayList<Encounter> get_night_encounters() {
		
		return m_night_encounters; 
		
	}
	
	/*
	 * Prints a list of the City's Encounters
	 */
	public void list_day_encounters () {
		
		int n = 1;
		for (Encounter i: m_day_encounters) {
			System.out.println(n + ") " + i.get_name() + ": " + i.get_description());
			n++; 
		}
		
	}
	
	/*
	 * Prints a list of the City's Night Encounters
	 */
	public void list_night_encounters () {
		
		int n = 1;
		for (Encounter i: m_night_encounters) {
			System.out.println(n + ") " + i.get_name() + ": " + i.get_description());
			n++; 
		}
		
	}
	
	/*
	 * Returns a list of all encounters, both Day and Night Encounters 
	 */
	public ArrayList<Encounter> get_all_encounters () {
		
		return m_all_encounters; 
		
	}
	
	/*
	 * Lists all Encounters, both Day and Night Encounters 
	 */
	public void list_all_encounters () {
		
		System.out.println("---Day Encouunters ---");
		list_day_encounters();
		System.out.println("---Night Encounters ---");
		list_night_encounters(); 
		
	}
	
    /*
     * Adds an NPC (without a description) to the City 
     * 
     * @param NPC String	:the name of the NPC
     */
    public void add_NPC (String NPC) {
 
        m_NPCs.add(NPC);
        
    }
    
    //REFACTOR SO NPC'S WILL BE MONSTERS OR A SUBCLASS THEREOF
    /*
     * Adds the NPC and its description to the City's catalog of NPC's
     */
    public void add_NPC(String name, String descrip){
        
        m_NPCs.add(name);
        m_NPCsDescrip.put(name, descrip);
        
    }
    
    /*
     * Checks to see if an NPC with the given name exists in this CIty
     * 
     * @param	name String		:the name of the NPC you are checking for
     * @return	boolean			:true if the NPC is in the City, false if it is not found
     */
    public boolean check_for_NPC (String name) {
        
        boolean found = false;
        for (int i = 0; i< m_NPCs.size(); i++) {
            if (m_NPCs.get(i) == name) {
                found = true;
            }
            else {
                found = false;
            }
        }
        if (found) {
            System.out.println("Yes, " + name + " is here.");
        }
        else {
            System.out.println("No, " + name + " is not here.");
        }
        return found; 
        
    }
    
    /*
     * Deletes the NPC matching name given from the City's catalog of NPC's 
     * 
     * @param	name String	:the name of the NPC you want to delete
     */
    public boolean delete_NPC (String name) {

        boolean removed = false;
        for (int i = 0; i < m_NPCs.size(); i++) {
            if (m_NPCs.get(i) == name) {
                m_NPCs.remove(i);
                removed = true;
            }
        }
        m_NPCsDescrip.remove(name);
        return removed; 
        
    }
    
    /*
     * Prints the description for the NPC name given 
     */
    public void describe_NPC (String name) {
        
        if (check_for_NPC(name) == false) {
            System.out.println("There is no person by that name here."); 
        }
        else if (m_NPCsDescrip.get(name) !=  null) {
            System.out.println(m_NPCsDescrip.get(name));
        }
        else { System.out.println("There is no description available for " + name + ".");}
        
    }
    
    /*
     * Returns a list of names of the NPC's belonging to this City 
     * 
     * @return	ArrayList<String>	:List of NPC names 
     */
    public ArrayList<String> get_NPCs () {
    	
       return m_NPCs;
       
    }
    
    /*
     * Prints a list of NPC names that belong to this City 
     */
    public void list_NPCs () {

        System.out.println(m_NPCs);
        
    }
    
    /*
     * Returns a list of names of the NPC names and descriptions belonging to this City 
     * 
     * @return	HashMap<String , String>	:map of NPC names and descriptions 
     */
    public HashMap<String, String> get_NPCs_w_Descrip () {

    	return m_NPCsDescrip;
    	
    }
    
    /*
     * Prints a list of names of the NPC's belonging to this City  
     */
    public void list_NPCs_w_Descrip () {

    	for (String i: m_NPCsDescrip.keySet()) {
            System.out.println(i + ": " + m_NPCsDescrip.get(i));
        }
    	
    }
    
    /*
     * Returns the name of the National Leader who controls the territory the CIty belongs to  
     * 
     * @return	String	:the name of the Ruler 
     */
    public String get_ruler() {
    	
    	return m_country.get_ruler();
    	
    }
    
    /*
     * Prints out a message with the National Leader who controls the territory the CIty belongs to  
     */
    public void list_ruler () {
    	
    	System.out.println(m_country.get_ruler());
    	
    }
    
    /*
     * Returns a list of the Routes that connect to this CIty 
     * 
     * @return 		ArrayList<Route>	:the list of Routes that are connected to this City 
     */
    public ArrayList<Route> get_routes() {
    	
    	return m_routesList; 
    	
    }
    
    /*
     * Given a list of Routes, adds whichever connect to it to the City's list of Routes
     * 
     * @param	routes ArrayList<Routes<	:a list of Routes to see if any connect to the City 
     */
    public void set_routes (ArrayList<Route> routes) {
    	
    	for (Route r: routes) {
    		if (r.get_origin() == this | r.get_destination() == this) {
    			//System.out.println("Route added.");
    			m_routesList.add(r);
    		}
    		else {
    			//System.out.println(r.get_origin().get_name() + " and " + r.get_destination().get_name() + " does not equal " + m_name); 
    			//System.out.println("Route endpoints could not be found.");
    		}
    	}
    	
    }
    
    /*
     * Prints out the routes that connect this city to other cities
     * Can eventually extend this to get neighboring cities as well
     */ 
    public void list_routes () {
    	
    	if (m_routesList.isEmpty()) {
    		System.out.println(m_name + " is not connected to any routes.");
    	}
    	else {
	    	System.out.println("Routes connected to " + m_name + " :");
	    	for (Route n: m_routesList) {
	    		System.out.println("Route #" + n.get_route_id() + " ( " +n.get_origin().get_name() + " --->  " + n.get_destination().get_name() + " )");
	    	}
    	}
    	
    }
    
    /*
     * Returns the TerrainType associated with this City
     * 
     * @return 		m_TerrainType	:the Terrain Type associated with this City 
     */
    public TerrainType get_TerrainType () {
    	
        return m_TerranType;
        
    }
    
    /*
     * Prints out a message with the City's TerrainType 
     */
    public void list_TerrainType () {

    	System.out.println("Terrain: " + m_TerranType);
    	
    }
    
    /*
     * Sets the TerrainType to be associated with the city 
     */
    public void set_TerrainType (TerrainType terrain) {
 
        m_TerranType = terrain;
        
    }
    
    /*
     * Removes an Encounter from the City without deleting it 
     * 
     * @param	encounter Encounter 	:the Encounter that should be removed 
     */
    public void removeEncounter (Encounter encounter) {
    	
    	m_all_encounters.remove(encounter);
    	m_day_encounters.remove(encounter);
    	m_night_encounters.remove(encounter);
    	
    }
    
    /*
     * Removes an Encounter from the City, then deletes it. 
     *   
     * @param	encounter Encounter 	:the Encounter that should be deleted 
     */
    public void deleteEncounter (Encounter encounter) {
    	
    	this.removeEncounter(encounter);
    	encounter = null; 
    	
    }
    
    /*
     * Adds an existing Encounter to the City
     * 
     * @param	encounter Encounter		:the Encounter that should be appended to the City
     */
    public void append_encounter (Encounter encounter) {
    	
    	m_all_encounters.add(encounter);
    	if (encounter.get_encounterType() == EncounterType.DAY) {
    		m_day_encounters.add(encounter);
    	}
    	else {
    		m_night_encounters.add(encounter);
    	}
    	encounter.m_city = this; 
    	
    }
    
    /*
     * Returns a formatted String containing all of the City's information 
     * NOTE: This function got weird at one point and got obsessed with the lines
     * for encounters and routes and would double copy those lines in a way that made no
     * logical sense. Moving them up so that they were between the buildings section and
     * the end of the String seems to have fixed it, so beware of rearranging output String. 
     */
    public String get_all_info() {
		
		String output = ""; 
		output = output + "-------------------------------------------------- \n";
		output = output + "<<<<<<<<<<<<<<  City InfoDump  >>>>>>>>>>>>>> \n"; 
		output = output + "Name: " + m_name +"\n"; 
		output = output + "Description: " + get_description() +"\n"; 
		output = output + "Age: " + m_age + "\n"; 
		output = output + "Population: " + m_population+ "\n"; 
		output = output + "Ruler: " + m_local_ruler+ "\n"; 
		output = output + "Country: " + m_country.get_country_name()+ "\n"; 
		output = output + "City-Type: " + m_ctype+ "\n"; 
		output = output + "Terrain-Type: " + m_TerranType+ "\n"; 
		output = output + "NPCs: " + "\n";
		
		for (String i: m_NPCsDescrip.keySet()) {
	        output = output + i + " | " + m_NPCsDescrip.get(i) + "\n"; 
	    }
		
		output = output + "-------------------------------------------------- \n Routes: \n";
		int m = 1;
		if (m_routesList.isEmpty()) {
			output = output + "No routes are connected to this city. \n";
		}
		else {
			for (Route i: m_routesList) {
				output = output + "#" + m + " " + i.get_name() + " " + i.get_origin().get_name() + " --> " + i.get_destination().get_name() + " " + "\n";
				m++; 
			}
		}
		        
		output = output + "-------------------------------------------------- \n Encounters: \n";
		/*if (m_day_encounters.isEmpty()) {
			output = output + "No day encounters to mention. \n";
		}
		else {*/
			int n = 1;
			output = output + "(Day Encounters) \n";
			for (Encounter i: m_day_encounters) {
				output = output + n + ") " + i.get_name() + "\n";
				n++;
			}
		//} 
		
		/*if (m_night_encounters.isEmpty()) {
			output = output + "No night encounters to mention. \n";
		}
		else {*/
			int k = 1;
			output = output + "(Night Encounters) \n";
			for (Encounter i: m_night_encounters) {
				output = output + k + ") " + i.get_name() + "\n";
				k++;
			}
		//} 
		output = output + "-------------------------------------------------- \n";
		
		output = output + "Buildings: " + "\n";
		
		for (String i: m_buildings) {
			output = output + i + "\n";
		}
		return output;
		
	}
    
    /*
     * Prints out a sheet with all of the City's information 
     */
    public void list_all_info () {
        
    	System.out.println("------------------------------------");
        System.out.println("<<<<<< City InfoDump >>>>>>");
        
        list_name();
        list_country();
        System.out.println(m_name + " is under the control of " + get_ruler());
        list_ctype();
        System.out.println(get_local_ruler() + " runs the city."); 
        
        list_age();
        list_description();
        list_TerrainType();
        
        System.out.println("Population: " + get_population());
        
        list_buildings();
        
        System.out.println("------------------------------------");
        
        System.out.println("<<< NPC's >>>");
        
        list_NPCs_w_Descrip();
        
        System.out.println("------------------------------------");
        
        list_routes(); 
        
        System.out.println("------------------------------------");
        System.out.println("Encounters: ");
        
        list_all_encounters(); 
        
        System.out.println("------------------------------------");
        
    }

}
