package fp.domain.test;

import fp.domain.Accident;

public class TestAccident {

	public static void main(String[] args) {
		
		Accident a1 = new Accident("25/01/2010,CANOVES I SAMALUS,Valles Oriental,Barcelona,0,1,0,100,No,Good weather,On vacation,Urban zone,Light wind,Night,Moving vehicles collision");
		
		System.out.println("Full object: " + a1);
		System.out.println("Deaths proportion: " + a1.getDeathsProportion());
		System.out.println("Victims: " + a1.getVictims());
		System.out.println("Date: " + a1.getDate());
		System.out.println("Location: " + a1.getLocation());
		System.out.println("Escapist: " + a1.getEscapist());
		System.out.println("Speed: " + a1.getSpeed());
		System.out.println("Climate: " + a1.getClimate());
		System.out.println("Info: " + a1.getInfo());
		
		Accident a2 = new Accident("25/01/2011,CANOVES I SAMALUS,Valles Oriental,Barcelona,0,1,0,100,No,Good weather,On vacation,Urban zone,Light wind,Night,Moving vehicles collision");
		Accident a3 = new Accident("25/01/2010,ANOVES I SAMALUS,Valles Oriental,Barcelona,0,1,0,100,No,Good weather,On vacation,Urban zone,Light wind,Night,Moving vehicles collision");
		
		System.out.println(a1.compareTo(a2));
		System.out.println(a1.compareTo(a3));
		
	}
	
}