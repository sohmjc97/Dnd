package Dnd;

import java.util.*;

public class Encounter {
	/*
	 * Class to hold information on individual encounters 
	 */
	
	private static int encounter_count = 0;
	private String m_name = "encounter";
	private String m_description = "";
	private int m_encounter_id = 0;
	private Route m_route = null; 
	private City m_city = null;
	private Country m_country = null; 
	
	private ArrayList<Monster> m_enemy_list = new ArrayList<Monster>(); 

	/*
	 * Encounter Constructor for Route Encounters 
	 * 
	 * @param	route Route		:the Route where this Encounter takes place
	 * @param	name String		:the name of this Encounter 
	 */
	public Encounter (Route route, String name) {
		
		m_name = name;
		m_route = route; 
		encounter_count++;
		m_encounter_id = encounter_count;
		
	}
	
	/*
	 * Encounter Constructor for City Encounters 
	 * 
	 * @param	city City		:the City where this Encounter takes place
	 * @param	name String		:the name of this Encounter 
	 */
	public Encounter (City city, String name) {
		
		m_name = name;
		m_city = city; 
		encounter_count++;
		m_encounter_id = encounter_count;
		
	}
	
	/*
	 * For Encounters hosted in Cities 
	 * @return		m_city City		:this Encounter's host City 
	 */
	public City get_city () {
		
		return m_city; 
		
	}
	
	/*
	 * Returns the name of the Encounter 
	 * 
	 * @return		m_name String	:the name of the Encounter 
	 */
	public String get_name() {
		
		return m_name; 
		
	}
	
	/*
	 * Returns the description of the Encounter 
	 * 
	 * @return	m_description String	:the description of teh Encounter 
	 */
	public String get_description () {
		
		return m_description;
		
	}
	
	/*
	 * Returns the name of the Encounter
	 * 
	 * @return	m_name String	:the name of the Encounter
	 */
	public String get_encounter_name() {
		
		return m_name; 
		
	}
	
	/*
	 * Returns the I.D # (primary key) of the Encounter
	 * 
	 * @return	m_id Integer	:the I.D # of the Encounter
	 */
	public int get_encounter_id () {
		
		return m_encounter_id;
		
	}
	
	/*
	 * Returns the Route that this Encounter takes place on
	 * 
	 * @return 		m_route Route	:the Route to which this Encounter belongs
	 */
	public Route get_route () {
		
		return m_route; 
		
	}

	
	/*
	 * Creates a new Monster object, adds it to the Encounter, and returns it
	 * 
	 * @param	name String		:the name of the new Monster 
	 * @return	enemy Monster	:the new Monster 
	 */
	public Monster add_enemy (String name) {
		
		Monster enemy = new Monster(name);
		m_enemy_list.add(enemy);
		return enemy;
		
	}
	
	/*
	 * Removes a Monster from the Encounter and deletes it. 
	 * 
	 * @param	monster Monster		:the Monster that is to be deleted 
	 */
	public void delete_enemy(Monster monster) {
		
		m_enemy_list.remove(monster);
		monster = null; 
		
	}
	
	/*
	 * Returns the Encounter's list of enemies 
	 * 
	 * @return		m_enemy_list ArrayList<Monster>		:the list of Monsters belonging to this Encounter 
	 */
	public ArrayList<Monster> get_enemies () {
		
		return m_enemy_list; 
		
	}
	
	/*
	 * Sets the description of the Encounter
	 * 
	 * @param	descrip String	:How the Encounter should be described.
	 */
	public void set_description (String descrip) {
		
		m_description = descrip; 
		
	}
	
	/*
	 * Returns a formatted String of all the Encounter's info separated by "\n"
	 */
	public String get_all_info() {
		
		String output = "";
		output = output + "Name: " + get_encounter_name() + "\n";
		output = output + "Encounter ID: " + get_encounter_id() + "\n";
		output = output + "Description: " + get_description() + "\n";
		
		if (get_route() == null) {
			output = output + "Location: " + m_city.get_name() + "\n";
		}
		else {
			output = output + "Location: " + m_route.get_name() + "\n";
		}
		
		output = output + "Enemies: \n";
		for (Monster i: m_enemy_list) {
			output = output + i.get_all_stats();
		}
		
		return output; 
	}
	
	/*
	 * Prints all of the Encounter's info 
	 */
	public void list_all_info() {
		
		System.out.println(this.get_all_info());
		
	}
}
