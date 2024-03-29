package Dnd;

import java.util.*;

import Dnd.Country.TerrainType;
import Dnd.Encounter.EncounterType;

public class Route {
	/*
	 *  Create routes between cities, each with their own unique
	 *  attributes and challenges for players to overcome 
	 */
	
	private static int route_count = 0;
	
	private City m_origin;
	private City m_destination; 
	private TerrainType m_terrainType = TerrainType.FOREST; 
	private int m_route_id = 0; 
	private int m_length = 1; //how many days to complete 
	private String m_name = "Arbitrary"; 
	private String m_description = "No description available.";
	
	private ArrayList<Encounter> m_day_encounters = new ArrayList<Encounter>();
	private ArrayList<Encounter> m_night_encounters = new ArrayList<Encounter>();
	private ArrayList<Encounter> m_all_encounters = new ArrayList<Encounter>(); 
	
	/*
	 * Constructor
	 * 
	 * @param	origin City			:City in which this route begins
	 * @param	destination City	:City in which this route ends
	 */
	public Route (City origin, City destination) {
		
		m_origin = origin;
		m_destination = destination; 
		route_count++;
		m_route_id = route_count;
		
	}
	
	/*
	 * Returns this Route's origin City 
	 * 
	 * @return m_origin City	:the City from which this Route originates
	 */
	public City get_origin () {
		
		return m_origin;
		
	}
	
	/*
	 * Returns this Route's destination City 
	 * 
	 * @return m_destination City	:the City where this Route ends
	 */
	public City get_destination () {
		
		return m_destination;
		
	}
	
	/*
	 * Returns this Route's length or duration in days 
	 * 
	 * @return m_length Integer		:the int value of how many days it takes to travel this Route
	 */
	public int get_length () {
		
		return m_length;
		
	}
	
	/*
	 * Returns this Route's name, what it's called 
	 * 
	 * @return m_name String	:the Route's name
	 */
	public String get_name () {
		
		return m_name; 
		
	}
	
	/*
	 * Returns the description of the Route.
	 * 
	 * @return	m_description String	:the description of the Route 
	 */
	public String get_description () {
		
		return m_description; 
		
	}
	
	/*
	 * Returns this Route's route ID or route #
	 * 
	 * @return m_route_id Integer	:the primary key for the Route
	 */
	public int get_route_id () {
		
		return m_route_id;
		
	}
	
	/*
	 * Returns how many Routes are currently in the Country
	 * 
	 * @return route_count Integer	:the number of routes in the Country
	 */
	public static int get_route_count () {
		
		return route_count; 
		
	}
	
	/*
	 * Returns what kind of terrain is found on this Route
	 * 
	 * @return m_terrainType TerrainType	:what variety of terrain is this route 
	 */
	public TerrainType get_terrainType() {
		
		return m_terrainType; 
		
	}
	
	/*
	 * Returns the Country to which this Route belongs
	 * 
	 * @return		Country		:the Country to which the Route's origin City belongs 
	 */
	public Country get_country () {
		
		return m_origin.get_country(); 
		
	}
	
	/*
	 * Returns the Route's list of day encounters
	 * Day encounters are currently only encounters implemented, so this is default for encounters in general 
	 * 
	 * @return	m_day_encounters ArrayList<Encounters> 	:a list of Encounter objects associated with this Route
	 */
	public ArrayList<Encounter> get_day_encounters () {
		
		return m_day_encounters;
		
	}
	
	/*
	 * Returns the Route's list of night encounters
	 * Night Encounters are currently not implemented, so it will return an empty ArrayLisy
	 * 
	 * @return	m_night_encounters ArrayList<Encounters> 	:a list of Encounter objects associated with this Route
	 */
	public ArrayList<Encounter> get_night_encounters () {
		
		return m_night_encounters; 
		
	}
	
	public ArrayList<Encounter> get_all_encounters() {
		
		return m_all_encounters; 
		
	}
	
	/*
	 * Creates an encounter object and adds it to this Route's list 
	 * of encounters. 
	 * 
	 * @param	route Route		:The route to which this encounter belongs
	 * @param	name String		:What this encounter is to be called 
	 */
	public Encounter add_encounters (Route route, String name, EncounterType eType) {
		
		Encounter encounter = new Encounter(route, name.strip(), eType);
		if (encounter.get_encounterType() == EncounterType.DAY) {
			m_day_encounters.add(encounter);
		}
		else {
			m_night_encounters.add(encounter);
		}		
		m_all_encounters.add(encounter);
		return encounter;
		
	}
	
	/*
	 * Adds an existing Encounter to this Route
	 * 
	 * @param	encounter Encounter		:the Encounter to be appended to this Route
	 */
	public void append_encounter(Encounter encounter) {
		
		if (encounter.get_encounterType() == EncounterType.DAY) {
			m_day_encounters.add(encounter);
		}
		else {
			m_night_encounters.add(encounter);
		}
		m_all_encounters.add(encounter);
		encounter.m_route = this; 
		
	}
	
	/*
	 * Removes an Encounter from this Route without deleting it. This is used for moving
	 * Encounters between Routes and Cities. 
	 * 
	 * @param 	encounter Encounter 	:the Encounter that is to be removed 
	 */
    public void removeEncounter (Encounter encounter) {
    	
    	m_day_encounters.remove(encounter);
    	m_night_encounters.remove(encounter);
    	m_all_encounters.remove(encounter);
    	
    }
    
    /*
     * Removes an Encounter from this Route and deletes it entirely. 
     * 
     * @param	encounter Encounter 	:the Encounter that is to be deleted
     */
    public void deleteEncounter (Encounter encounter) {
    	
    	this.removeEncounter(encounter);
    	encounter = null; 
    	
    }
	
	public void autoSave () {
		
		FileCreator newfile = new FileCreator(this); 
		
	}
	
	/*
	 * Sets the duration of the trip 
	 * 
	 * @param	dureation Integer	:How long (how many days) does it take to travel this Route. 
	 */
	public void set_length (int duration) {
		
		m_length = duration; 
		
	}
	
	/*
	 * Sets the name of the Route
	 * 
	 * @param	name String		:What the route is called 
	 */
	public void set_name (String name) {
		
		m_name = name; 
		
	}
	
	/*
	 * Sets the description of the Route
	 * 
	 * @param	descrip String		:how the Route should be described 
	 */
	public void set_description (String descrip) {
		
		m_description = descrip; 
		
	}
	
	/*
	 * Sets the type of terrain found on the Route
	 * 
	 * @param	tType TerrainType		:What kind of Terrain is it 
	 */
	public void set_terrainType (TerrainType tType) {
		
		m_terrainType = tType; 
		
	}
	
	/*
	 * Returns a String of formatted details asociated with this Route
	 * 
	 * @return 	output String	:a huge String containing all Route details 
	 */
	public String get_all_info () {
		
		String output = "";
		
		output = output + "Route #" + m_route_id + " " + m_name + "\n"; 
		output = output + "<< " + m_origin.get_name() + " ----> " + m_destination.get_name() + " >> \n";
		output = output + "Description: " + m_description + "\n";
		output = output + "Terrain Type: " + m_terrainType + "\n"; 
		output = output + "Length: " + m_length + " Days' Travel \n";
		output = output + "(Day Encounters) \n";
		int x = 1;
		for (Encounter i: m_day_encounters) {
			output = output + x + ") " + i.get_encounter_name() + "\n";
			x++;
		}
		output = output + "(Night Encounters) \n";
		int y = 1;
		for (Encounter i: m_night_encounters) {
			output = output + y + ") " + i.get_encounter_name() + "\n";
			y++;
		}
		return output; 
		
	}

	public void list_all_info() {

		System.out.println(this.get_all_info());
		
	}

}
