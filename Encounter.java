package Dnd;

import java.io.IOException;
import java.util.*;

public class Encounter {
	/*
	 * Class to hold information on individual encounters 
	 */
	
	/*
	 * Thoughts for additional features:
	 * Encounters know how much exp their collective monsters reward
	 * Each Route/City knows how much exp their collective encounters reward
	 */
	
	public enum EncounterType {
		DAY,
		NIGHT
	}
	
	private static int encounter_count = 0;
	private int m_encounter_id = 0;
	private int m_xp = 0; 
	private String m_name = "encounter";
	private String m_description = "No description available";
	
	Route m_route = null; 
	City m_city = null;
	private Country m_country = null; 
	
	private EncounterType m_encounter_type; 
	
	private ArrayList<Monster> m_enemy_list = new ArrayList<Monster>(); 

	/*
	 * Encounter Constructor for Route Encounters 
	 * 
	 * @param	route Route		:the Route where this Encounter takes place
	 * @param	name String		:the name of this Encounter 
	 */
	public Encounter (Route route, String name, EncounterType encounterType) {
		
		m_name = name;
		m_route = route; 
		m_country = route.get_country();
		encounter_count++;
		m_encounter_id = encounter_count;
		m_encounter_type = encounterType; 

	}
	
	/*
	 * Encounter Constructor for City Encounters 
	 * 
	 * @param	city City		:the City where this Encounter takes place
	 * @param	name String		:the name of this Encounter 
	 */
	public Encounter (City city, String name, EncounterType encounterType) {
		
		m_name = name;
		m_city = city; 
		m_country = city.get_country();
		encounter_count++;
		m_encounter_id = encounter_count;
		m_encounter_type = encounterType; 
		
	}
	
	/*
	 * For Encounters hosted in Cities 
	 * @return		m_city City		:this Encounter's host City 
	 */
	public City get_city () {
		
		return m_city; 
		
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
	 * Returns the Country to which this Encounter belongs.
	 * 
	 * @return 	Country		:the Country this Encounter object belongs to 
	 */
	public Country get_country() {
	
		return m_country; 
		
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
	 * Returns the EncounterType of the Encounter: i.e. whether it is Day or NIght
	 * 
	 * @return	m_encounter_type EncounterType 		:either EncounterType.DAY or EncounterType.NIGHT
	 */
	public EncounterType get_encounterType () {
		
		return m_encounter_type; 
		
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
	 * Returns the total experience points earned by completing this encounter
	 * 
	 * #return	 	m_xp Integer	:the amount of cumulative XP for all monsters in this encounter
	 */
	public int get_xp () {
		
		return m_xp; 
		
	}
	
	/*
	 * Adds xp for given encounter, used when adding in new monsters
	 * 
	 * @param		xp Integer		:the amount of XP to add to the encounter's total
	 */
	public void add_xp (int xp) {
		
		m_xp = m_xp + xp; 
		
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
	 * Creates a new Monster object, adds it to the Encounter, and returns it
	 * 
	 * @param	name String		:the name of the new Monster 
	 * @return	enemy Monster	:the new Monster 
	 */
	public Monster add_enemy (String name) {
		
		Monster enemy = new Monster(name, this);
		m_enemy_list.add(enemy);
		m_xp += m_xp + enemy.get_xp();
		return enemy;
		
	}
	
	/*
	 * Adds an existing Monster to this Encounter. This is used
	 * when moving an existing Monster between different Encounters.
	 * 
	 * @param	monster Monster		:an eisting Monster to be added to this Encounter
	 */
	public void append_enemy (Monster monster) {
		
		m_enemy_list.add(monster);
		m_xp = m_xp + monster.get_xp();
		//monster.m_encounter = this; 
		if (m_route == null) {
			// if this encounter is on a city
			monster.m_city = m_city;
			monster.m_route = null; 
		}
		else {
			monster.m_route = m_route;
			monster.m_city = null; 
		}
		
		
	}
	
	/*
	 * Removes a Monster from the Encounter and deletes it. 
	 * 
	 * @param	monster Monster		:the Monster that is to be deleted 
	 */
	public void delete_enemy(Monster monster) {
		
		m_enemy_list.remove(monster);
		m_xp = m_xp - monster.get_xp();
		monster = null; 
		
	}
	
	/*
	 * Removes a Monster from this Encounter, but does not delete it. This is used
	 * when moving Monsters between Encounters.
	 */
	public void remove_enemy (Monster monster) {
		
		m_enemy_list.remove(monster);
		m_xp = m_xp - monster.get_xp();
		
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
	 * Sets the total XP of the encounter to the given number, an override to adding with each monster if necessary
	 * 
	 * @param		xp Integer		:the total amount of XP that this Encounter should have
	 */
	public void set_xp (int xp) {
		
		m_xp = xp; 
		
	}
	
	
	/*
	 * This function allows this Encounter to be moved from the Host Location it currently 
	 * belongs with to a different Route. To make this change permanent,
	 * be sure to save the old Host Location, the new Route, the Encounter itself, and it enemies.
	 * after making the switch.
	 * 
	 * @param		newRoute Route 		:the Route you want to move the Encounter to
	 */
	public void change_host (Route newRoute) throws IOException {
		
		if (m_city == null) {
			//Route oldRoute = m_route; 
			m_route.removeEncounter(this);
			m_route.autoSave(); 
		
			m_route = newRoute; 
			m_route.append_encounter(this);
			m_route.autoSave(); 
		}
		else if (m_route == null) {
			//City oldCity = m_city;
			System.out.println(this.get_name() + " will be removed from " + m_city.get_name());
			m_city.removeEncounter(this);
			m_city.autoSave();
			//System.out.println(m_city.get_name() + " now has Encounters: ");
			//for (Encounter e: m_city.get_all_encounters()) {
			//	System.out.println(e.get_name());
			//}
			
			m_route = newRoute; 
			m_route.append_encounter(this);
			System.out.println("The new route is " + m_route.get_name());
			m_city = null;
			m_route.autoSave(); 
			//System.out.println(m_route.get_name() + " has been saved.");
		}
		
		this.autoSave();
		
		//System.out.println(this.get_name() + " has been saved");
		for (Monster i: m_enemy_list) {
			i.m_r_host = m_route;
			i.m_c_host = null;
			i.autoSave();
			//System.out.println(i.get_name() + " has been saved.");
		}
		
	}
	
	/*
	 * This function allows this Encounter to be moved from the Host Location it currently 
	 * belongs with to a different City. To make this change permanent,
	 * be sure to save the old Host Location, the new City, the Encounter itself, and it enemies.
	 * after making the switch.
	 * 
	 * @param		new CIty City 		:the City you want to move the Encounter to
	 */
	public void change_host (City newCity) throws IOException {
		
		if (m_city == null) {
			//route ---> city
			//Route oldRoute = m_route; 
			System.out.println(this.get_name() + " will be removed from " + m_route.get_name());
			m_route.removeEncounter(this);
			m_route.autoSave();
			//System.out.println(m_route.get_name() + " now has Encounters: ");
			//for (Encounter e: m_route.get_all_encounters()) {
				//System.out.println(e.get_name());
			//}
			m_city = newCity; 
			m_city.append_encounter(this);
			System.out.println("The new city is " + m_city.get_name());
			m_route =  null;
			m_city.autoSave();
			//System.out.println(m_city.get_name() + " has been saved.");
		}
		else if (m_route == null) {
			//city ---> city
			//City oldCity = m_city;
			m_city.removeEncounter(this);
			m_city.autoSave(); 

			m_city = newCity; 
			
			m_city.append_encounter(this);
			m_city.autoSave();
		}
		
		this.autoSave();
		//System.out.println(this.get_name() + " has been saved");
		for (Monster i: m_enemy_list) {
			//System.out.println("Saving " + i.get_name() + "to new location: " + i.get_encounter().get_name() + " in " + i.get_encounter().get_city().get_name());
			i.m_c_host = m_city;
			i.m_r_host = null;
			i.autoSave();
			//System.out.println(i.get_name() + " has been saved.");

		}

	}
	
	
	/*
	 * This is used for situations where multiple things are saved at once or
	 * during actions where a new Object is created and it is necessary to create
	 * a file for that object or save its name to its parent file to preserve it. 
	 */
	public void autoSave () {
		
		FileCreator newfile = new FileCreator(this); 
		
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
		output = output + "Total XP: " + m_xp + "\n";
		output = output + "Type: " + m_encounter_type +"\n";
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
