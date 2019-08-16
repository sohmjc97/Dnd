package Dnd;

import java.util.*; 

public class Monster extends Being{
	/*
	 * A class of being that will represent enemies to the PC's 
	 */
	
	Encounter m_encounter = null; 
	Route m_r_host = null;
	City m_c_host = null; 
	private Country m_country = null; 
	
	/*
	 * Monster constructors.
	 * A Monster can be initialized with a name, a level, or a name and level. 
	 */
	
	public Monster(String name) {
		
		m_name = name; 
		
	}
	
	public Monster(int lvl) {
		
		m_lvl = lvl;
		
	}
	
	public Monster (String name, int lvl) {
		
		m_name = name; 
		m_lvl = lvl;
		
	}
	
	/*
	 * This is the main constructor we will be using since it links the Monster
	 * to an Encounter, Host (Route or City). and Country upon initialization. 
	 * It also populates the Ability Mods to default values. 
	 * 
	 * @param	name String				:the Monster's name
	 * @param	encounter Encounter		:the Encounter to which this Monster belongs. 
	 */
	public Monster (String name, Encounter encounter) {
		
		m_name  = name;
		m_encounter = encounter; 
		m_r_host = m_encounter.get_route(); 
		m_c_host = m_encounter.get_city(); 
		if (m_r_host == null) {
			m_country = m_encounter.get_city().get_country();
		}
		else if (m_c_host == null) {
			m_country = m_encounter.get_route().get_country(); 
		}
		else {
			System.out.println("A problem occured when trying to load Monster's country.");
		}
		
		m_abilityMods.put("STR", 0);
		m_abilityMods.put("DEX", 0);
		m_abilityMods.put("WIS", 0);
		m_abilityMods.put("INT", 0);
		m_abilityMods.put("CON", 0);
		m_abilityMods.put("CHA", 0);
		
	}
	
	
	//////////////////////////
	// Set & Get & List		//
	//////////////////////////

	/*
	 * Returns Monster's Level (lvl = Level), or how advanced the Monster is 
	 * Default levels begin at 1. 
	 * 
	 * @return		m_lvl Integer		:the value of the Monster's current Level
	 */
	public int get_lvl() {
	    
	    return m_lvl;
	    
	}
	
	/*
	 * Prints out a message with the Monster's Level
	 */
	public void list_lvl() {
		
		System.out.println("Monster Level: " + m_lvl);
		
	}
	
	/*
	 * Sets Monster's Level (Lvl = Level), or how advanced the Monster is
	 * 
	 * @param		level Integer		:the value of the Monster's level
	 */
	public void set_lvl(int level) {
	    
	    m_lvl = level;
	    
	}
	
	/*
	 * Returns Monster's HP (HP = Health/Hit Points), or how much health the Monster has 
	 * 
	 * @return		hp Integer		:the value of the Monster's current HP
	 */
	public int get_hp() {
	    
	    return m_hp;
	    
	}
	
	/*
	 * Prints a message with the Monster's HP (HP = Health/Hit Points), or how much health the Monster has 
	 */
	public void list_hp() {
		
		System.out.println("Monster HP: " + m_hp + " / " + m_maxhp);
		
	}
	
	/*
	 * Sets Monster's HP (HP = Health/Hit Points), or how much health the Monster has 
	 * 
	 * @param		hp Integer		:the value you want to set as the Monster's Max HP
	 */
	public void set_hp(int hp) {
	    
	    m_hp = hp;
	    m_maxhp = hp; 
	    
	}
	
	/*
	 * Returns Monster's AC (AC = Armor Class), or the number an attack much reach to actually hit the Monster
	 * 
	 * @return	m_ac Integer	:the integer value of the Monster's armor class 
	 */
	public int get_ac() {
	    
	    return m_ac;
	    
	}
	
	/*
	 * Prints message with Monster's AC (AC = Armor Class), or the number an attack much reach to actually hit the Monster
	 */
	public void list_ac() {
		
	    System.out.println("Monster AC: " + m_ac);
		
	}
	
	/*
	 * Sets Monster's AC (AC = Armor Class), or the number an attack much reach to actually hit the Monster
	 * 
	 * @param	ac Integer	:the integer value to be set as the Monster's armor class 
	 */
	public void set_ac(int ac) {
	    
	    m_ac = ac;
	    
	}
	
	/*
	 * Returns the Monster's name
	 * 
	 * @return	m_name String	:the name of the Monster
	 */
	public String get_name() {
	   
	    return m_name;
	    
	}
	
	/*
	 * Prints out a message with the Monster's name 
	 */
	public void list_name() {
		
		System.out.println("Monster Name: " + m_name);
		
	}
	
	/*
	 * Sets the Monster's name
	 * 
	 * @param	name String		:the name of the Monster
	 */
	public void set_name(String name){
	    
	    m_name = name; 
	    
	}
	
	/*
	 * Returns the Monster's description
	 * 
	 * @return	m_description String	:a description of the Monster
	 */
	public String get_description() {
	    
	    return m_description;
	    
	}
	
	/*
	 * Prints out a message with the Monster's description 
	 */
	public void list_description() {
		
		System.out.println("Description: " + m_description);
		
	}
	
	/*
	 * Sets the Monster's description
	 * 
	 * @param	descrip String	:a description of the Monster
	 */
	public void set_description(String descrip) {
	    
	    m_description = descrip; 
	    
	}
	
	/*
	 * dmgDie = Damage Die
	 * On a successful attack roll, the target is hit. By rolling its damage die, a monster determines how
	 * much damage its hit deals to the target. Each monster has a given damage die (i.e. a d4, d6, d10, etc.) that would
	 * allow for (1-4), (1-6), and (1-10) points of damage respectively. 
	 * 
	 * @return 	m_dmgDie Integer	:the integer value of this monster's damage die (i.e. 4, 6, 10, etc.) 
	 */
	public int get_dmgDie() {
		
	    return m_dmgDie;
	    
	}
	
	/*
	 * dmgDie = Damage Die
	 * On a successful attack roll, the target is hit. By rolling its damage die, a monster determines how
	 * much damage its hit deals to the target. Each monster has a given damage die (i.e. a d4, d6, d10, etc.) that would
	 * allow for (1-4), (1-6), and (1-10) points of damage respectively. 
	 * 
	 * Prints a message with the value of the monster's damage die 
	 */
	public void list_dmgDie() {
		
		 System.out.println("Damage Die: " + m_dmgDie);
		
	}
	
	/*
	 * dmgDie = Damage Die
	 * On a successful attack roll, the target is hit. By rolling its damage die, a monster determines how
	 * much damage its hit deals to the target. Each monster has a given damage die (i.e. a d4, d6, d10, etc.) that would
	 * allow for (1-4), (1-6), and (1-10) points of damage respectively. 
	 * 
	 * @param 		die Integer		:the integer value to which this monster's damage die should be set (i.e. 4, 6, 10, etc.) 
	 */
	public void set_dmgDie(int die) {
	    
	    m_dmgDie = die;
	    
	}
	

	/*
	 * numdmgDie = Number of Damage Die
	 * On a successful attack roll, the target is hit. By rolling its damage die, a monster determines how
	 * much damage its hit deals to the target. Each monster has a given damage die (i.e. a d4, d6, d10, etc.) that would
	 * allow for (1-4), (1-6), and (1-10) points of damage respectively. Some monsters get to roll their damage die more than once.
	 * i.e. An imp hits you and rolls 2 d6's for damage, meaning it could deal between 2-12 points of damage. 
	 * 
	 * @return 	m_numdmgDie Integer	:the integer value of the number of this monster's damage die (i.e. 4, 6, 10, etc.) that are rolled. 
	 */
	public int get_numdmgDie() {
	 
	    return m_numdmgDie;
	    
	}
	
	/*
	 * numdmgDie = Number of Damage Die
	 * On a successful attack roll, the target is hit. By rolling its damage die, a monster determines how
	 * much damage its hit deals to the target. Each monster has a given damage die (i.e. a d4, d6, d10, etc.) that would
	 * allow for (1-4), (1-6), and (1-10) points of damage respectively. Some monsters get to roll their damage die more than once.
	 * i.e. An imp hits you and rolls 2 d6's for damage, meaning it could deal between 2-12 points of damage. 
	 * 
	 * Prints a message  with the integer value of the number this monster's damage die (i.e. 4, 6, 10, etc.) that are rolled. 
	 */
	public void list_numdmgDie() {
		
		System.out.println("Number of Damage Die: " + m_numdmgDie);
		
	}
	
	/*
	 * numdmgDie = Number of Damage Die
	 * On a successful attack roll, the target is hit. By rolling its damage die, a monster determines how
	 * much damage its hit deals to the target. Each monster has a given damage die (i.e. a d4, d6, d10, etc.) that would
	 * allow for (1-4), (1-6), and (1-10) points of damage respectively. Some monsters get to roll their damage die more than once.
	 * i.e. An imp hits you and rolls 2 d6's for damage, meaning it could deal between 2-12 points of damage. 
	 * 
	 * @param 	m_numdmgDie Integer	:the integer value of the number of this monster's damage die (i.e. 4, 6, 10, etc.) that are rolled. 
	 */
	public void set_numdmgDie(int numdie) {
	    
	    m_numdmgDie = numdie;
	    
	}
	
	/*
	 * When a monster rolls to attack a target, it rolls a d20 and adds its Attack Modifier to 
	 * the roll to determine if it hits its target. The Attack Modifier is determined by different
	 * Ability Scores Modifiers for different kinds of Monsters. For example, a Bug Bear's Attack 
	 * Modifier might be determined by its Strength (STR) Ability Score Modifier, while an Imp's Attack Modifier
	 * might be determined by its Dexterity (DEX) Ability Score Modifier.
	 * 
	 * @return		mod Integer		:the integer value of the Monster's Attack Modifier
	 */
	public int get_attackMod() {
	    
	    int mod = m_attackMod;
	    return mod;
	    
	}
	
	/*
	 * When a monster rolls to attack a target, it rolls a d20 and adds its Attack Modifier to 
	 * the roll to determine if it hits its target. The Attack Modifier is determined by different
	 * Ability Scores Modifiers for different kinds of Monsters. For example, a Bug Bear's Attack 
	 * Modifier might be determined by its Strength (STR) Ability Score Modifier, while an Imp's Attack Modifier
	 * might be determined by its Dexterity (DEX) Ability Score Modifier.
	 * 
	 * Prints a message listing which Ability Score is associated with the Attack Modifier followed by the integer
	 * values of the Attack Modifier
	 */
	public void list_attackMod() {
		
	    System.out.println("Attack Mod: " + get_attackMod());
	    
	}
	
	/*
	 * When a monster rolls to attack a target, it rolls a d20 and adds its Attack Modifier to 
	 * the roll to determine if it hits its target. The Attack Modifier is determined by different
	 * Ability Scores Modifiers for different kinds of Monsters. For example, a Bug Bear's Attack 
	 * Modifier might be determined by its Strength (STR) Ability Score Modifier, while an Imp's Attack Modifier
	 * might be determined by its Dexterity (DEX) Ability Score Modifier.
	 * 
	 * This function sets which Ability Score Modifier should be used to determine the Attack Modifier. 
	 * 
	 * @param		mod String		:the String abbreviation of the Ability Score (ie. "STR" for Strength or "DEX" for Dexterity)
	 * 								 that is responsible for determining this Monster's Attack Modifier 
	 */
	public void set_attackMod(int mod) {
	    
	    m_attackMod = mod; 
	    
	}
	
	/*
	 * dmgMod = Damage Modifier
	 * After making a successful attack roll, a Monster gets to roll its damage die and then add its Damage
	 * Modifier to the Damage Die roll(s) to determine the total damage dealt to the target. 
	 * 
	 * I.e: An Imp's dmgDie = 6, numdmgDie = 2, and dmgMod = 3.
	 * 		An Imp rolls the 2 d6's, getting a (4) and a (6). It then adds its Damage Modifier (3).
	 * 		(4 + 6 + 3) = (13). Therefore the total damage dealt is (13). 
	 * 
	 * @return 		m_dmgMod Integer		:the integer value that is added to damage rolls 
	 */
	public int get_dmgMod() {
	    
	    return m_dmgMod;
	    
	}
	
	/*
	 * dmgMod = Damage Modifier
	 * After making a successful attack roll, a Monster gets to roll its damage die and then add its Damage
	 * Modifier to the Damage Die roll(s) to determine the total damage dealt to the target. 
	 * 
	 * I.e: An Imp's dmgDie = 6, numdmgDie = 2, and dmgMod = 3.
	 * 		An Imp rolls the 2 d6's, getting a (4) and a (6). It then adds its Damage Modifier (3).
	 * 		(4 + 6 + 3) = (13). Therefore the total damage dealt is (13). 
	 * 
	 * Prints out a message with the integer value that is added to damage rolls 
	 */
	public void list_dmgMod() {
		
	    System.out.println("Damage Mod: " + m_dmgMod);
		
	}

	/*
	 * dmgMod = Damage Modifier
	 * After making a successful attack roll, a Monster gets to roll its damage die and then add its Damage
	 * Modifier to the Damage Die roll(s) to determine the total damage dealt to the target. 
	 * 
	 * I.e: An Imp's dmgDie = 6, numdmgDie = 2, and dmgMod = 3.
	 * 		An Imp rolls the 2 d6's, getting a (4) and a (6). It then adds its Damage Modifier (3).
	 * 		(4 + 6 + 3) = (13). Therefore the total damage dealt is (13). 
	 * 
	 * This method sets what that integer value should be. 
	 * 
	 * @param 		dmgMod Integer		:the integer value that will be added to damage rolls 
	 */
	public void set_dmgMod(int dmgMod) {
	    
	    m_dmgMod = dmgMod; 
	    
	}
	
	/*
	 * xp = Experience Points
	 * Each Monster is worth a certain number of Experience Points. When this Monster is defeated
	 * the victor will gain this much Experience. Experience is used for leveling up.  
	 * 
	 * @return 		m_xp Integer	:the integer value of Experience that this Monster is worth
	 */
	public int get_xp () {
	    
	    return m_xp;
	    
	}
	
	/*
	 * xp = Experience Points
	 * Each Monster is worth a certain number of Experience Points. When this Monster is defeated
	 * the victor will gain this much Experience. Experience is used for leveling up.  
	 * 
	 * Prints a message with the integer value of Experience that this Monster is worth
	 */
	public void list_XP () {
		
	    System.out.println("XP: " + m_xp);
		
	}
	
	/*
	 * xp = Experience Points
	 * Each Monster is worth a certain number of Experience Points. When this Monster is defeated
	 * the victor will gain this much Experience. Experience is used for leveling up.  
	 * 
	 * Sets the value of Experience Points that this Monster should be worth upon its defeat
	 * 
	 * @param 		xp Integer	:the integer value of Experience that this Monster will be worth
	 */
	public void set_xp (int xp) {
	    
	    m_xp = xp;
	    
	}
	
	/*
	 * Each Monster holds items. Upon this Monster's defeat, the victor has the option
	 * to claim the items that the defeated Monster held. 
	 * 
	 * This method adds new items for the Monster to hold. 
	 * 
	 * @param	itemName String		:the name of the item
	 * @param	itenDescription		:a brief description of what the item is
	 */
	public void add_items (String itemName, String itemDescription) {
		
	    m_ItemDrop.put(itemName, itemDescription);
	    
	}
	
	/*
	 * Each Monster holds items. Upon this Monster's defeat, the victor has the option
	 * to claim the items that the defeated Monster held. 
	 * 
	 * This method prints out a list of the names and descriptions of the items the Monster
	 * is currently holding. 
	 */
	public void list_items () {
	    
	    System.out.println("--------------------------------------------------");
	    System.out.println("Inventory:");
	    
	    int n = 1;
	    for (String i: m_ItemDrop.keySet()) {
	        System.out.println(n + ") " + i + " | " + m_ItemDrop.get(i) );
	        n++;
	    }
	    
	    System.out.println("--------------------------------------------------");
	    
	}
	
	/*
	 * Each Monster holds items. Upon this Monster's defeat, the victor has the option
	 * to claim the items that the defeated Monster held. 
	 * 
	 * This method returns the HashMap of the names and descriptions of the items the Monster
	 * is currently holding. 
	 * 
	 * @return 		m_ItemDrop HashMap<String, String>		:the HashMap of item names & descriptions
	 */
	public HashMap<String, String> get_items_w_descrip () {

	    return m_ItemDrop;

	}
	
	/*
	 * Returns an ArrayList containing the names of the items in the Monster's inventory
	 */
	public ArrayList<String> get_item_names () {
		
		ArrayList<String> i_names = new ArrayList<String>();
		for (String name: m_ItemDrop.keySet()) {
			i_names.add(name);
		}
		return i_names;
		
	}
	
	/* 
	 * For each Ability Score there is an associated integer Modifier that is used to modify certain
	 * kinds of rolls. Ability Scores are known by their String abbreviations ("STR"=Strength, "DEX"=Dexterity, "INT"=Intelligence, 
	 * "WIS"=Wisdom,"CON"=Constitution,"CHA"=Charisma). 
	 * 
	 * Given the String abbreviation of one of these Ability Scores, this method returns the integer value of its Modifier. 
	 * 
	 * @param 		mod String		:the String abbreviation of the Ability Score you want to receive the integer Modifier of
	 * @return		theMod Integer	:the integer value of the Modifier associated with the given Ability Score 
	 */
	public int get_abilityMod(String mod) {
	     
	    int theMod = m_abilityMods.get(mod);
	    //System.out.println(theMod);
	    return theMod;
	    
	    /*if (theMod != null) {
	       
	        return theMod;
	    }
	    else {
	        System.out.println(mod + " has not been set yet.");
	    */
	    
	}
	
	/* 
	 * For each Ability Score there is an associated integer Modifier that is used to modify certain
	 * kinds of rolls. Ability Scores are known by their String abbreviations ("STR"=Strength, "DEX"=Dexterity, "INT"=Intelligence, 
	 * "WIS"=Wisdom,"CON"=Constitution,"CHA"=Charisma). 
	 * 
	 * Prints a list of the Ability Score Abbreviations and their respective Modifier values
	 */
	public void list_abilityMods () {
	    
	    System.out.println("--Ability Modifiers--");
	    
	    for (String i: m_abilityMods.keySet()) {
	        System.out.println(i + ": " + m_abilityMods.get(i)); 
	        
	    }
	    
	}
	
	/*
	 * Returns the Country to which this Monster belongs.
	 * 
	 * @return	m_country Country	:the Country to which this Monster belongs
	 */
	public Country get_country() {
		
		return m_country; 
		
	}
	
	/*
	 * Returns the Route to which this Monster belongs.
	 * 
	 * @return	m_r_host Route	:the Route to which this Monster belongs
	 */
	public Route get_r_host () {
		
		return m_r_host; 
		
	}
	
	/*
	 * Returns the City to which this Monster belongs.
	 * 
	 * @return		m_c_host City	:the City to which this Monster belongs
	 */
	public City get_c_host () {
		
		return m_c_host; 
		
	}
	
	/*
	 * Returns the Encounter to which this Monster belongs.
	 * 
	 * @return		m_encounter Encounter		:the Encounter to which this Monster belongs
	 */
	public Encounter get_encounter () {
		
		return m_encounter; 
		
	}
	
    /*
     * Returns a String of the overview of all the monster's stats and info 
     * 
     * @return	output String	:a formatted String with stats and info separated by "\n"
     */
	public String get_all_stats() {
		
		String output = ""; 
		output = output + "-------------------------------------------------- \n";
		output = output + "<<<<<<<<<<<<<<  Monster Stats  >>>>>>>>>>>>>> \n"; 
		output = output + "Name: " + m_name + "\n"; 
		output = output + "Description: " + m_description + "\n"; 
		output = output + "Level: " + m_lvl + "\n";
		output = output + "HP: " + m_hp + "\n";
		output = output + "AC: " + m_ac + "\n";
		output = output + "XP: " + m_xp + "\n";
		output = output + "Damage Die: " + m_dmgDie + "\n";
		output = output + "Number of Damage Dice: " + m_numdmgDie + "\n";
		output = output + "Damage Mod: " + m_dmgMod +"\n";
		output = output + "Attack Mod: " + m_attackMod +"\n";
		output = output + "Status Conditions: " + m_condition + "\n";
		output = output + "----Ability Mods----\n"; 
		for (String i: m_abilityMods.keySet()) {
	        output = output + i + ": " + m_abilityMods.get(i) + "\n"; 
	    }
		
		output = output + "Weak to: " + get_weaknesses() + "\n";
		output = output + "Resistant to: " + get_resistances() + "\n";
		output = output + "Immune to Damage Types: " + get_dmg_immunities() + "\n"; 
		output = output + "Immune to Conditions: " + get_condition_immunities() + "\n";

		
		output = output + "------- Inventory --------\n";
		
		for (String i: m_ItemDrop.keySet()) {
	        output = output + i + ": " + m_ItemDrop.get(i) +"\n"; 
	        
	    }
		output = output + "-------------------------------------------------- \n";
		return output; 
	}
	
    /*
     * Prints out a sheet giving an overview of all the monster's stats and info 
     */
	public void list_all_stats(){
	    
	    System.out.println("--------------------------------------------------");
	    System.out.println("<<<<<<<<<<<<<<  Monster Stats  >>>>>>>>>>>>>>");
	    
	    list_name();
	    list_description(); 
	    list_lvl();
	    list_hp();
	    list_ac();
	    list_dmgDie();
	    list_numdmgDie(); 
	    list_dmgMod(); 
	    list_attackMod(); 
	    list_status_condition(); 
	    list_XP();
	    
	    list_abilityMods();
	    list_weaknesses();
	    list_resistances();
	    list_dmg_immunities(); 
	    list_condition_immunities(); 
	    list_items();
	    
	    System.out.println("--------------------------------------------------");
	    
	}
	
	/* 
	 * For each Ability Score there is an associated integer Modifier that is used to modify certain
	 * kinds of rolls. Ability Scores are known by their String abbreviations ("STR"=Strength, "DEX"=Dexterity, "INT"=Intelligence, 
	 * "WIS"=Wisdom,"CON"=Constitution,"CHA"=Charisma). 
	 * 
	 * Sets the Modifier values for each Ability Score Modifier 
	 * Values must be given in the correct, traditional order: STR, DEX, INT, WIS, CON, CHA
	 * 
	 * @param 		str Integer		:the value of the Strength Modifier
	 * @param 		dex Integer		:the value of the Dexterity Modifier
	 * @param 		intl Integer	:the value of the Intelligence Modifier
	 * @param 		wis Integer		:the value of the Wisdom Modifier
	 * @param 		con Integer		:the value of the Constitution Modifier
	 * @param 		cha Integer		:the value of the Charisma Modifier
	 */
	public void set_AbilityMods( int str, int dex, int wis, int intl, int con, int cha) {
	    
		m_abilityMods.put("WIS", wis);
		m_abilityMods.put("STR", str);
		m_abilityMods.put("INT", intl);
		m_abilityMods.put("CHA", cha);
		m_abilityMods.put("DEX", dex);
		m_abilityMods.put("CON", con);
		
	}
	
	public void set_AbilityMod (String Abb, int value) {
		
		m_abilityMods.put(Abb, value);
		
	}
	
	/* 
	 * For each Ability Score there is an associated integer Modifier that is used to modify certain
	 * kinds of rolls. Ability Scores are known by their String abbreviations ("STR"=Strength, "DEX"=Dexterity, "INT"=Intelligence, 
	 * "WIS"=Wisdom,"CON"=Constitution,"CHA"=Charisma). 
	 * 
	 * Given the String abbreviation of one of these Ability Scores, this method resets the integer value of its Modifier. 
	 * 
	 * @param 		modName String		:the String abbreviation of the Ability Score you want to change the integer Modifier of
	 * @param		modValue Integer	:the integer value of the Modifier to be associated with the given Ability Score 
	 */
	public void change_abilityMod(String modName, int modValue) {
	    
	    for (String i: m_abilityMods.keySet()) {
	        if (i == modName) {
	            m_abilityMods.put(i, modValue);
	        }
	        else {
	            System.out.println("There is no such mod.");
	        }
	    }
	}
	
	/*
	 * A Monster may have a weakness to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are weak, the damage will be doubled. 
	 * 
	 * i.e. A demon is weak to Radiant damage. An attacker rolls for damage with a Radiant
	 * attack, which comes out to 12 damage. Since the Demon is weak to Radiant damage, it 
	 * takes (12 x 2) or (24) damage instead of the original 12. 
	 * 
	 * This method adds a weakness type to the Monster 
	 * 
	 * @param	dmgType DamageTypes		:the type of damage you want the Monster to be weak to 
	 */
	public void add_weakness (DamageTypes dmgtype) {

	    m_weaknesses.add(dmgtype);
	    
	}
	
	/*
	 * A Monster may have a weakness to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are weak, the damage will be doubled. 
	 * 
	 * i.e. A demon is weak to Radiant damage. An attacker rolls for damage with a Radiant
	 * attack, which comes out to 12 damage. Since the Demon is weak to Radiant damage, it 
	 * takes (12 x 2) or (24) damage instead of the original 12. 
	 * 
	 * This method returns the list of DamageTypes the Monster is weak to 
	 * 
	 * @return	m_weaknesses ArrayList<DamageTypes>		:the types of damage the Monster is weak to 
	 */
	public ArrayList<DamageTypes> get_weaknesses () {
	    
	    return m_weaknesses; 
	    
	}
	
	/*
	 * A Monster may have a weakness to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are weak, the damage will be doubled. 
	 * 
	 * i.e. A demon is weak to Radiant damage. An attacker rolls for damage with a Radiant
	 * attack, which comes out to 12 damage. Since the Demon is weak to Radiant damage, it 
	 * takes (12 x 2) or (24) damage instead of the original 12. 
	 * 
	 * This method prints a list of the types of damage the Monster is weak to 
	 */
	public void list_weaknesses () {
		
		System.out.println("Weak to: " + m_weaknesses);
		
	}
	
	/*
	 * A Monster may have a weakness to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are weak, the damage will be doubled. 
	 * 
	 * i.e. A demon is weak to Radiant damage. An attacker rolls for damage with a Radiant
	 * attack, which comes out to 12 damage. Since the Demon is weak to Radiant damage, it 
	 * takes (12 x 2) or (24) damage instead of the original 12. 
	 * 
	 * This method removes the types of damage given from the Monster's weaknesses 
	 * 
	 * @param	dmgtype DamageTypes 	:the type of damage you would like the Monster to no
	 * 									 longer be weak to 
	 */
	public void remove_weakness (DamageTypes dmgtype) {
	    
	    for (int i = 0; i < m_weaknesses.size(); i++) {
	        if (m_weaknesses.get(i) == dmgtype) {
	            m_weaknesses.remove(i);
	        }
	    }
	}
	
	/*
	 * A Monster may have a resistance to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are resistant, the damage will be halved. 
	 * 
	 * i.e. A demon is resistant to Piercing damage. An attacker rolls for damage with a Piercing
	 * attack, which comes out to 12 damage. Since the Demon is resistant to Piercing damage, it 
	 * takes (12 / 2) or (6) damage instead of the original 12. 
	 * 
	 * This method adds the types of damage given from the Monster's resistances 
	 * 
	 * @param	resist DamageTypes 	:the type of damage you would like the Monster to be resistant to 
	 */
	public void add_resistance ( DamageTypes resist) {
	    
	    m_resistances.add(resist);
	    
	}
	
	/*
	 * A Monster may have a resistance to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are resistant, the damage will be halved. 
	 * 
	 * i.e. A demon is resistant to Piercing damage. An attacker rolls for damage with a Piercing
	 * attack, which comes out to 12 damage. Since the Demon is resistant to Piercing damage, it 
	 * takes (12 / 2) or (6) damage instead of the original 12. 
	 * 
	 * This method returns the types of damage the Monster's is resistant to 
	 * 
	 * @param	m_resistances ArrayList<DamageTypes> 	:the types of damages the Monster is resistant to 
	 */
	public ArrayList<DamageTypes> get_resistances () {
	    
	    return m_resistances; 
	    
	}
	
	/*
	 * A Monster may have a resistance to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are resistant, the damage will be halved. 
	 * 
	 * i.e. A demon is resistant to Piercing damage. An attacker rolls for damage with a Piercing
	 * attack, which comes out to 12 damage. Since the Demon is resistant to Piercing damage, it 
	 * takes (12 / 2) or (6) damage instead of the original 12. 
	 * 
	 * This method prints the types of damage the Monster's is resistant to 
	 */
	public void list_resistances () {
		
	    System.out.println("Resistant to: " + m_resistances);
		
	}
	
	/*
	 * A Monster may have a resistance to a certain kind of damage. If they are hit
	 * with an attack of the kind to which they are resistant, the damage will be halved. 
	 * 
	 * i.e. A demon is resistant to Piercing damage. An attacker rolls for damage with a Piercing
	 * attack, which comes out to 12 damage. Since the Demon is resistant to Piercing damage, it 
	 * takes (12 / 2) or (6) damage instead of the original 12. 
	 * 
	 * This method removes the type of damage given from the Monster's resistances
	 * 
	 * @param	dmgtype DamageTypes 	:the types of damages you want to remove from the Monster's resistances
	 */
	public void remove_resistance (DamageTypes dmgtype) {
	    
	    for (int i = 0; i < m_resistances.size(); i++) {
	        if (m_resistances.get(i) == dmgtype) {
	            m_resistances.remove(i);
	        }
	    }
	}
	
	/*
	 * Gives the monster immunity to the given DamageType
	 * 
	 * @param	damageType DamageTypes 		:the type of damage you want this creature to be immune to
	 */
	public void add_dmg_immunity (DamageTypes damageType) {
		
		m_dmg_immunities.add(damageType); 
		
	}
	
	/*
	 * Removes the monster's immunity to the given DamageType
	 * 
	 * @param	damageType DamageTypes 		:the type of damage you want this creature to no longer be immune to
	 */
	public void remove_dmg_immunity (DamageTypes damageType) {
		
		m_dmg_immunities.remove(damageType);
		
	}
	
	/*
	 * Returns the DamageTypes that the monster is immune to
	 */
	public ArrayList<DamageTypes> get_dmg_immunities () {
		
		return m_dmg_immunities;
		
	}
	
	/*
	 * Prints out the types of damages that this monster is immune to
	 */
	public void list_dmg_immunities () {
		
		System.out.println("Immune to Damage Types: " + m_dmg_immunities);
		
	}
	
	/*
	 * Gives this monster immunity to the given StatusCondition
	 * 
	 * @param	condition StatusCondition		:the kind of StatusCondition you want this monster to be immune to
	 */
	public void add_condition_immunity (StatusCondition condition) {
		
		m_condition_immunities.add(condition);
		
	}
	
	/*
	 * Removes this monster's immunity to the given StatusCondition
	 * 
	 * @param	condition StatusCondition		:the kind of StatusCondition you want this monster to no longer be immune to
	 */
	public void remove_condition_immunity(StatusCondition condition) {
		
		m_condition_immunities.remove(condition);
		
	}
	
	/*
	 * Returns the kinds of StatusConditions that this monster is immune to
	 */
	public ArrayList<StatusCondition> get_condition_immunities () {
		
		return m_condition_immunities;
		
	}
	
	/*
	 * Prints out what kind of StatusConditions this monster is immune to
	 */
	public void list_condition_immunities() {
		
		System.out.println("Immune to Status: " + m_condition_immunities);
		
	}
	
	/*
	 * Adds a Status Condition to the list of StatusCondition that this monster is currently under the effect of
	 * 
	 * @param	condition StatusCondition	:the status condition the monster should be under the effect of
	 */
	public void add_status_condition (StatusCondition condition) {
		
		m_condition.add(condition);
		
	}
	
	/*
	 * Removes a Status Condition from the list of StatusCondition that this monster is currently under the effect of
	 * 
	 * @param	condition StatusCondition	:the status condition the monster should no longer be under the effect of
	 */
	public void remove_status_condition (StatusCondition condition) {
		
		m_condition.remove(condition);
		
	}
	
	/*
	 * Returns the list of status conditions that this monster is currently under the effect of
	 */
	public ArrayList<StatusCondition> get_status_condition () {
		
		return m_condition; 
		
	}
	
	/*
	 * Prints a list of status conditions that the monster is currently under the effect of
	 */
	public void list_status_condition () {
		
		System.out.println("Status Condition: " + m_condition);
		
	}
	
	/*
	 * This function allows this Monster to be moved from the Encounter it currently 
	 * belongs with to a different Encounter. To make this change permanent,
	 * be sure to save the old Encounter, the new Encounter, and the Monster itself
	 * after making the switch.
	 * 
	 * @param		newEncounter Encounter 		:the Encounter you want to move the Monster to
	 */
	public void switchEncounter (Encounter newEncounter, Monster enemy) {
		
		//System.out.println("Removing " + enemy.get_name() + " from " + oldEncounter.get_name());
		m_encounter.remove_enemy(this);
		m_encounter.autoSave();
		//System.out.println(oldEncounter.get_enemies());
		System.out.println("The current encounter is " + m_encounter.get_name());
		newEncounter.append_enemy(this);
		m_encounter = newEncounter;
		System.out.println("The new encounter is now " + m_encounter.get_name());
		m_encounter.autoSave();
		enemy.autoSave();
		//System.out.println(newEncounter.get_enemies());
		
	}
	
	//////////////////////////
	// Actions				//
	//////////////////////////
	
	/*
	 * Saves this Monster as is to a file in the directory of its given Encounter
	 * and Host Location
	 */
	public void autoSave () {
		
		FileCreator newfile = new FileCreator(this); 
		
	}
	
	/*
	 * Makes a roll of the size of die given. The result will be between 1 and the number given. Prints
	 * and returns the result of the roll. 
	 * 
	 * @param 	sizeDice Integer	:the integer value of the size of the die (6 for d6, 20 for d20, etc.)
	 * @return	roll Integer 		:the integer result of the die roll 
	 */
	public int roll (int sizeDice) {
		Random rand = new Random(); 
		int n = rand.nextInt(sizeDice);
		int roll = (n+1); 
		System.out.println("Outcome: " + roll);
		return roll; 
	}
	
	/*
	 * Makes a roll of the size of die given. Rolls it as many times as given. The result will be between 1 and the number given. Prints
	 * and returns the result of the roll and the total sum of the rolls. 
	 * 
	 * @param 	sizeDice Integer	:the integer value of the size of the die (6 for d6, 20 for d20, etc.)
	 * @param	numDie Integer 		:the number of times the die should be rolled  
	 * @return	roll Integer 		:the integer result of the sum of the die rolls
	 */
	public int roll (int sizeDice, int numDie) {
		Random rand = new Random(); 
		int total = 0;
		for (int i = 0; i < numDie; i++ ) {
			int n = rand.nextInt(sizeDice);
			int roll = (n+1); 
			System.out.println("Outcome "+ (i+1) + ": " + roll);
			total += roll;
		}
		return total; 
	}
	
	/*
	 * Makes a d20 roll as default. The result will be between 1 and the number given. Prints
	 * and returns the result of the roll.
	 * 
	 * @return	roll Integer 		:the integer result of the die roll
	 */
	public int roll () {
		Random rand = new Random(); 
		int n = rand.nextInt(20);
		int roll = n+1; 
		System.out.println("Outcome: " + roll);
		return roll; 
	}
	
	/*
	 * Sometimes a Monster has to make a roll to perform, compete, or save against a certain action.
	 * This method takes their roll, what number they have to beat to succeed (dc = Difficulty Class), and the Ability Score 
	 * associated with the action and calculates whether or not the Monster succeeds. 
	 * 
	 * @param	roll Integer	:the value that the Monster rolled 
	 * @param	dc Integer 		:the number that the Monster has to reach to succeed
	 * @param	skill String	:the abbreviation of the Ability Score association with the task
	 * 							 i.e. "STR" for Grappling checks 
	 * @return 	boolean			:true if the Monster succeded, false if it failed. 
	 */
	public boolean skill_check(int roll, int dc, String skill) {
	    
	    int mod = m_abilityMods.get(skill);
	    int score = mod + roll;
	    
	    System.out.println("--------------------------------------------------");
	    System.out.println("The Monster rolled a " + roll + ".");
	    System.out.println("It has a mod of " + mod + ".");
	    System.out.println("The Monster has scored an overall " + score + ".");
	    System.out.println("The DC is " + dc + ".");
	    
	    if (roll == 1) {
	        
	        System.out.println("The Monster has criticaly failed at the skillcheck.");
	        return false;
	        
	    }
	    
	    else if (roll == 20) {
	        
	        System.out.println("The Monster has critically succeded at the skillcheck.");
	        return true;
	        
	    }
	    
	    else if ( score >= dc){
	        
	        System.out.println("It has succeded the skillcheck.");
	        return true;
	        
	    }
	    
	    else {
	        
	        System.out.println("It has failed the skillcheck.");
	        return false; 
	        
	    }
	    
	}
	
	/*
	 * When a Monster defeats another, it has the option to loot the defeated Monster's Inventory/ItemDrop
	 * This deletes all the objects from the defeated Monster's inventory and copies them into this Monster's ItemDrop. 
	 * 
	 * @param	 target Monster		:the Monster whose items are being taken away
	 */
	public void loot_items (Monster target) {
	    
	    m_ItemDrop.putAll(target.lose_items());
	    
	}
	
	/*
	 * If this Monster is defeated, it may get looted by the victor. In that case, all of its items
	 * will be deleted from its inventory and passed over to the victor.
	 * 
	 * @return	copy HashMap<String, String>	:a copy of the item names and descriptions that this Monster just lost
	 */
	public HashMap<String, String> lose_items() {
	
	    HashMap<String, String> copy = new HashMap<String, String>();
	    copy.putAll(m_ItemDrop);
	    m_ItemDrop.clear(); 
	    return copy ;
	}
	
	/*
	 * When attacking, the Monster must make an attack roll. If the attack roll + its Attack Modifier meets the target's
	 * AC (AC = Armor Class), then the hit lands. If it is below the target AC, then it misses. Given the roll and the 
	 * target's AC, this method determines the outcome of the attack, even calculating damage if successful.
	 * 
	 *  @param 		roll Integer		:the number the Monster rolled to attack
	 *  @param		targetAC Integer	:the number the Monster's total attack value must meet to succeed
	 *  @return 	Integer				:the number of damage dealt upon a successful hit, or 0 if it misses 
	 */
	public int attack (int roll, int targetAC) {
	    
		boolean critFail = false;
		boolean critSuccess = false;
		
		int attackMod = get_attackMod();
		int score = roll + attackMod; 
		
		System.out.println("--------------------------------------------------");
		
		System.out.println("The monster swings...");
		System.out.println("He rolled a " + roll);
		System.out.println("He has a mod of " + attackMod ); 
		
		System.out.println("Giving it an overall score of " + score + " !");
		
		
		if (roll == 1) {
		    
		    critFail = true;
		    
		}
		
		else if (roll == 20) {
		    
		    critSuccess = true; 
		    
		}
		
		if ( score >= targetAC) {
		    
		    System.out.println("The monster hits!");
			return dealDamage(critFail, critSuccess); 
			
		}
		
		else {
		    
		    System.out.println("Monster misses!");
		    if (critFail) {
		        return dealDamage(critFail, critSuccess);
		    }
		    
		}
		
		return 0; 
		
	}
	
	/*
	 * When attacking, the Monster must make an attack roll. If the attack roll + its Attack Modifier meets the target's
	 * AC (AC = Armor Class), then the hit lands. If it is below the target AC, then it misses. Given what advantage it is rolling with and the 
	 * target's AC, this method determines the rolls, the outcome of the attack, and even calculates the damage if successful.
	 * 
	 *  @param 		advantage boolean		:true if the Monster is rolling with Advantage, false if otherwise. 
	 *  @param		disadvantage boolean	:true if the Monster is rolling with Disadvantage, false if otherwise. 
	 *  @param		targetAC Integer		:the number the Monster's total attack value must meet to succeed
	 *  @return 	Integer					:the number of damage dealt upon a successful hit, or 0 if it misses 
	 */
	public int attack (boolean advantage, boolean disadvantage, int targetAC) {
	    
		boolean critFail = false;
		boolean critSuccess = false;
		
		System.out.println("--------------------------------------------------");
		
		System.out.println("The monster swings...");
		
		
		int roll1 = roll();
		int roll;
		
		if (advantage) {
			int roll2 = roll();
			roll = Math.max(roll1, roll2);
			System.out.println("The Monster rolled a " + roll1 + " and a " + roll2 + " .");
			System.out.println("Since it rolled with advantage, that makes the roll a " + roll + " !");
		}
		else if (disadvantage) {
			int roll2 = roll();
			roll = Math.min(roll1, roll2);
			System.out.println("The Monster rolled a " + roll1 + " and a " + roll2 + " .");
			System.out.println("Since it rolled with disadvantage, that makes the roll a " + roll + " !");
		}
		else {
			roll = roll1;
			System.out.println("He rolled a " + roll1 + " !");
		}
		
		int attackMod = get_attackMod();
		
		System.out.println("He has a mod of " + attackMod ); 
		
		int score = roll + attackMod; 
		
		System.out.println("Giving it an overall score of " + score + " !");
		
		
		if (roll == 1) {
		    
		    critFail = true;
		    
		}
		
		else if (roll == 20) {
		    
		    critSuccess = true; 
		    
		}
		
		if ( score >= targetAC) {
		    
		    System.out.println("The monster hits!");
			return dealDamage(critFail, critSuccess); 
			
		}
		
		else {
		    
		    System.out.println("Monster misses!");
		    if (critFail) {
		        return dealDamage(critFail, critSuccess);
		    }
		    
		}
		
		return 0; 
		
	}
	
	/*
	 * Internal mechanism of the attack which calculates how much damage a successful hit deals.
	 * 
	 * @param 	critFail boolean		:if the Monster rolled a 1 on attack, it has failed critically and will hurt itself.
	 * @param 	critSuccess boolean		:if the Monster rolled a 20 on attack, it has critically succeeded and will deal double damage. 
	 * @return	Integer					:the amount of damage that the Monster has dealt 
	 */
	private int dealDamage (boolean critFail, boolean critSuccess) {
		Random rand = new Random();
		if (!critFail) {
			int damage = 0;
			int i = 1;
			int n = 0; 
			do {
			    
				n = rand.nextInt(m_dmgDie);
				n=n+1; 
				damage = damage + n + m_dmgMod;
				i++;
				
			} while (i < m_numdmgDie);
			
			if (critSuccess) {
			    
				System.out.println(m_name + " landed a critical hit! Damage is doubled!");
				damage = damage*2;
				
			}
			
			System.out.println("The monster deals " + damage + " damage."); 
			return damage;
		}
		
		else if (critFail) {
		    
			// Critical failure: deal damage to self  
			System.out.println("The monster failed miserably.");
			
			int n = rand.nextInt(m_dmgDie);
			n += 1; 
			
			m_hp = m_hp - n; 
			System.out.println("It hurt itself, taking " + n + " damage.");
			return 0; 
			
		}
		
		System.out.println("--------------------------------------------------");
		return 0; 
		
	}
	
	/*
	 * When the Monster is hit by an enemy attack, it must take damage. This method calculates 
	 * how much damage based on its weaknesses and resistances. 
	 * 
	 * @param	dmg Integer			:how much base damage the Monster is dealt 
	 * @param	type DamageTypes	:the type of damage that is dealt to the Monster
	 */
	public void takeDamage (int dmg, DamageTypes type) {
	    
	    System.out.println("--------------------------------------------------");
	    System.out.println("Monster has been hit!");
	    
	    int weak = 1;
	    int resistant = 1;
	    boolean immune = false;
	    
	    for (int i = 0; i < m_weaknesses.size(); i++) {
	        
	        if (m_weaknesses.get(i) == type) {
	            
	            weak += 1; 
	            System.out.println("Monster is weak to " + type);
	            System.out.println("Damage of " + dmg + " has been doubled!");
	            
	        }
	    }
	    
	    for (int i = 0; i< m_resistances.size(); i++) {
	        
	        if (m_resistances.get(i) == type) {
	            
	            resistant += 1; 
	            System.out.println("Monster is resistant to " + type);
	            System.out.println("Damage of " + dmg + " has been halved!");
	            
	        }
	        
	    }
	    
	    for (DamageTypes imm: m_dmg_immunities) {
	    	
	    	if (imm == type) {
	    		
	    		immune = true;
	    		System.out.println(m_name + " is immune to " + type);
	    		
	    	}
	    	
	    }
	    int total;
	    if (immune) {
	    	total = 0;
	    }
	    else {
	    	total = (dmg * weak)/resistant; 
	    }
	    System.out.println("Total damage is: " + total);
		m_hp = m_hp - total; 
		System.out.println("HP has been reduced to " + m_hp + " / " + m_maxhp);
		
		if (m_hp <= 0) {
		    
			System.out.println("The creature has been killed.");
			die();
			
		}
		
		else {
		    
			System.out.println("The creature is still kicking.");
			System.out.println("It has " + m_hp + " HP left.");
		}
		
		System.out.println("--------------------------------------------------");
		
	}
	
	public void regainHP (int gain) {
		
		if (m_hp + gain >= m_maxhp) {
			m_hp = m_maxhp;
		}
		else {
			m_hp = m_hp + gain; 
		}
		
	}
	
	/*
	 * When a Monster has been defeated, it dies. 
	 * This method prints a message declaring how much XP was gained by the victor
	 * and what items were dropped from the Monster as loot. 
	 */
	public void die() {
	    
		System.out.println("You haved gained " + m_xp + " exp.");
		System.out.println("Items dropped:");
		list_items();
		System.out.println("--------------------------------------------------");
		isDead = true;
		m_encounter.remove_enemy(this);
		
	}
	
	public boolean isDead () {

		return isDead;
		
	}
	
}
