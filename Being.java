package Dnd;

import java.util.*;

public abstract class Being {
	/*
	 * Abstract Class that will be extended for Monsters and possibly NPC's and Player Char's as well. 
	 */
	
	/*
	 * These will affect the kind of damage a being will take from
	 * certain kinds of attacks.
	 */
	public static enum DamageTypes {
        FIRE,
        LIGHTNING,
        THUNDER,
        RADIANT,
        POISON,
        NECROTIC,
        CRYO,
        BLUDGEONING,
        PIERCING,
        PSYCHIC,
        FALL
    }
    
	/*
	 * These will affect how the being performs in combat, but are not yet implemented.
	 */
    public enum StatusCondition {
        HEALTHY,
        POISONED,
        PARALYZED,
        ASLEEP,
        STUNNED,
        PRONE,
        WEAKENED,
        CONFUSED,
        TERRIFID,
        PETRIFIED,
        FROZEN
    }
    
    /*
     * Each being should know to which Encounter, Route or City, and Country they belong. 
     * These variables will track that info. 
     */
    protected Route m_route = null;
    protected City m_city = null;
    protected Country m_Country = null;
    protected Encounter m_encounter = null; 
    
    protected int m_lvl;
	protected int m_hp;
	protected int m_maxhp; 
	protected int m_ac;
	protected int m_xp; 
	protected int m_speed; //todo
	protected int m_dmgDie;
	protected int m_numdmgDie; 
	protected int m_dmgMod; 
	
	protected String m_attackMod; 
	protected String m_description; 
	protected String m_name; 
	
	HashMap<String, String> m_ItemDrop = new HashMap<String, String>();
	HashMap<String, Integer> m_abilityMods = new HashMap<String, Integer>();
	ArrayList<DamageTypes> m_weaknesses = new ArrayList<DamageTypes>(); 
	ArrayList<DamageTypes> m_resistances = new ArrayList<DamageTypes>(); 
	ArrayList<StatusCondition> m_condition = new ArrayList<StatusCondition>(); //todo
	//ArrayList<Languages> m_languages = new ArrayList<Languages>(); //todo
	
	public abstract int attack(int roll, int targetAC); 
	public abstract boolean skill_check(int roll, int dc, String skill);
	public abstract void takeDamage(int dmg, DamageTypes type);
	public abstract void die();
	
}
