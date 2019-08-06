package Dnd;

public class Test {

public static void main(String[] args) {
		
		Country shinah = new Country("Shin-ah");
		shinah.set_ruler("Lady Kida");
	    shinah.set_population(2000000);
		shinah.set_country_age(1000);
		shinah.set_country_description("A kingdom of the sea, ruled from Atlantis.");
		
		City atlantis = shinah.add_city("Atlantis");
		
		shinah.set_capitol(atlantis);
		
		atlantis.set_age(800);
		atlantis.set_population(1000000);
		atlantis.set_description("A vast city thriving far beneath the surface of the sea.");
		atlantis.add_to_description("Safe and out of reach of the other jealous nations.");
		
		atlantis.add_NPC("Lady Kida", "A strong and fierce warrior who rides Thetis, the Dragon of the Sea.");
		atlantis.add_NPC("Ser Triton", "Consort of Ladt Kida and Knight of the Seven Seas.");
		
		atlantis.delete_NPC("Ser Triton");
		atlantis.delete_building("Palace");
		atlantis.delete_building("Froghole");
		atlantis.delete_NPC("Froggy");
		atlantis.describe_NPC("Lady Kida");
		atlantis.describe_NPC("Ser Triton");
		
		City nautica = shinah.add_city("Nautica");
		shinah.change_capitol(nautica);
		
		/*
		//shinah.list_all_info();
		atlantis.list_all_info();
		nautica.list_all_info();
		
		*/
		
		/* 
		Monster enemy = new Monster("Greg", 5);
		enemy.set_ac(15);
		enemy.set_attackMod("STR");
		enemy.set_AbilityMods(2, 3, -1, -3, 0, 1);
		enemy.set_description("He's old Gregggggggg");
		enemy.set_dmgDie(6);
		enemy.set_numdmgDie(2);
		enemy.set_hp(12);
		enemy.set_xp(400);
		
		
		enemy.add_items("Bailey's", "Coffee liqouer");
		enemy.add_items("Painting", "A painting of Bailey's as close as you could get without getting your eyeball wet");
		
		enemy.add_weakness(DamageTypes.PSYCHIC);
		enemy.add_resistance(DamageTypes.CRYO); 
		
		enemy.set_name("Bob");
		
		enemy.list_all_stats();
		
		FileCreator charsheet = new FileCreator(enemy);
		
		/* 
		FileCreator sheet = new FileCreator();
		
		sheet.open_file(enemy.get_name() + ".txt");
		sheet.addRecords(enemy.get_all_stats());
		sheet.close_file();
		
		/* enemy.skill_check(13, 12, "WIS");
		enemy.takeDamage(3, DamageTypes.PSYCHIC);
		enemy.attack(17, 18);
		enemy.takeDamage(12, DamageTypes.CRYO);
		
		Monster kara = new Monster("Kara", 1);
		kara.loot_items(enemy);
		
		kara.list_items();
		enemy.list_items(); 
		
		kara.roll();
		kara.roll();
		
		enemy.skill_check(enemy.roll(), kara.roll(), "CHA");
		
		*/
		
		//FileReader reader = new FileReader("Greg.txt");
		FileCreator sheet = new FileCreator(shinah);
		FileCreator citysheet = new FileCreator(atlantis);

	}
	
}
