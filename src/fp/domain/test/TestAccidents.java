package fp.domain.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import fp.domain.Accident;
import fp.domain.Accidents;
import fp.domain.FactoryAccident;

public class TestAccidents {
	
	public static void showAccidents(Accidents a) {
		for(Accident ac:a.getAccidentList()) {
			TestAccident.showAccident(ac);
			System.out.println();
		}
	}
	
	public static void showAccidents(Collection<Accident> a) {
		for(Accident ac:a) {
			TestAccident.showAccident(ac);
			System.out.println();
		}
	}
	
	public static void constructor1Test() {
		System.out.println("FIRST CONSTRUCTOR TEST (Accidents, empty): ");
		try {
			System.out.println(new Accidents());
		} catch(Exception e) {
			System.out.println("Error testing first constructor: Unexpected error:\n" + e);
		}
	}
	
	
	public static void constructor2Test(Collection<Accident> c) {
		System.out.println("\nSECOND CONSTRUCTOR TEST (Accidents, from collection): ");
		try {
			showAccidents(new Accidents(c));
		} catch(Exception e) {
			System.out.println("Error testing second constructor, Unexpected error:\n" + e);
		}
	}
	
	public static void equalityTest(Accidents a1, Accidents a2) {
		String result = (a1.equals(a2)) ? "a1 equals a2" : "a1 does not equal a2";
		System.out.println("EQUALITY CRITERION TEST (Accidents): " + result);
	}
	
	public static void toStringTest(Accidents a) {
		System.out.println("TO STRING TEST (Accidents): \n\t" + a);
	}
	
	public static void allInYearTest(Accidents a, Integer y) {
		String result = (a.allInYear(y)) ? "Yes they do" : "No they don't";
		System.out.println("\nallInYear Test --> Checking if all accidents occurr in " + y + ": \n\t" + result);
	}
	
	public static void avgVictimsTest(Accidents a) {
		System.out.println("\navgVictims Test --> Computing the average total victims of first 100 accidents:\n\t" + a.avgVictims());
	}
	
	public static void filterByYearTest(Accidents a, Integer y) {
		System.out.println("\nfilterByYear Test --> Showing first 5 accidents that occurred in " + y + " (In this case, they only happen in 2010):\n");
		List<Accident> l = a.filterByYear(y);
		if(l.size() >= 5) {
			showAccidents(l.subList(0, 5));
		} else {
			showAccidents(l);
		}
	}
	
	public static <K, V> void mapToString(Map<K, V> m) {
		for(Map.Entry<K, V> e:m.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}
	
	public static void groupByLocationTest(Accidents a) {
		System.out.println("groupByLocation Test --> Grouping first 100 accidents by location:\n");
		mapToString(a.groupByLocation());
	}
	
	public static void countByClimateTest(Accidents a) {
		System.out.println("\ncountByClimate Test --> Number of accidents per type of climate:\n");
		mapToString(a.countByClimate());
	}
	
	public static void main(String[] args) {
		constructor1Test();
		
		System.out.println("\n--- Using FactoryAccident to read the file. Resulting Accidents as collection of the 2nd constructor ---");
		Accidents shortAccidents = FactoryAccident.readFile(
				"/Users/ignacio/Desktop/US/1st_year/2C/FP/LAB/java-term-project-IMora04/data/Accidents_Catalonia.csv"
				).getSublist(0, 100);
		
		constructor2Test(shortAccidents.getAccidentList());
		
		System.out.println("\n--- Using accidents read from the file ---\n");
		System.out.println("--- a1: Accidents from 0 to 99 ---\n--- a2: Accidents from 0 to 79 ---\n");
				
		equalityTest(shortAccidents, shortAccidents.getSublist(0, 80));
		
		toStringTest(shortAccidents);
		
		allInYearTest(shortAccidents, 2010);

		avgVictimsTest(shortAccidents);
		
		filterByYearTest(shortAccidents, 2010);
		filterByYearTest(shortAccidents, 2011);
		
		groupByLocationTest(shortAccidents);
		
		countByClimateTest(shortAccidents);

	}
	
}
