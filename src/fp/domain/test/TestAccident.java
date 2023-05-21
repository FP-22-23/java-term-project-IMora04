package fp.domain.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fp.common.Climate;
import fp.common.Victims;
import fp.domain.*;

public class TestAccident {

	public static void showAccident(Accident a) {
		System.out.println("Deaths proportion: " + a.getDeathsProportion());
		System.out.println("Victims: " + a.getVictims());
		System.out.println("Date: " + a.getDate());
		System.out.println("Location: " + a.getLocation());
		System.out.println("Escapist: " + a.getEscapist());
		System.out.println("Speed: " + a.getSpeed());
		System.out.println("Climate: " + a.getClimate());
		System.out.println("Extra Info:" + a.getInfo());
	}

	public static void constructor1Test(LocalDate date, String location, Victims victims, Integer speed,
			Boolean escapist, Climate climate, List<String> info) {
		System.out.println("FIRST CONSTRUCTOR TEST (Accident): ");
		try {
			showAccident(new Accident(date, location, victims, speed, escapist, climate, info));
		} catch (IllegalArgumentException e) {
			System.out.println("Error testing first constructor, Restrictions not followed:\n" + e);
		} catch (Exception e) {
			System.out.println("Error testing first constructor, Unexpected error:\n" + e);
		}
	}

	public static void constructor2Test(String s) {
		System.out.println("SECOND CONSTRUCTOR TEST (Accident): ");
		try {
			showAccident(new Accident(s));
		} catch (IllegalArgumentException e) {
			System.out.println("Error testing second constructor, Restrictions not followed:\n" + e);
		} catch (Exception e) {
			System.out.println("Error testing second constructor, Unexpected error:\n" + e);
		}
	}

	public static void equalityTest(Accident a1, Accident a2) {
		String result = (a1.equals(a2)) ? "a1 equals a2" : "a1 does not equal a2";
		System.out.println("EQUALITY CRITERION TEST (Accident): " + result);
	}

	public static void orderTest(Accident a1, Accident a2) {
		String result;
		if (a1.compareTo(a2) == 0) {
			result = "a1 is equal (in order) to a2";
		} else if (a1.compareTo(a2) < 0) {
			result = "a1 is smaller than a2";
		} else {
			result = "a1 is greater than a2";
		}
		System.out.println("ORDER TEST: " + result);
	}

	public static void toStringTest(Accident a) {
		System.out.println("TO STRING TEST (Accident): \n" + a);
	}

	public static void main(String[] args) {
		System.out.println("--- Using same values for both constructors ---\n");

		constructor1Test(LocalDate.of(2010, 01, 25), "CANOVES I SAMALUS,Valles Oriental,Barcelona",
				new Victims(0, 1, 0), 100, false, Climate.GOOD_WEATHER, new ArrayList<String>(
						List.of("On vacation", "Urban zone", "Light wind", "Night", "Moving vehicles collision")));
		System.out.println();

		constructor2Test(
				"25/01/2010,CANOVES I SAMALUS,Valles Oriental,Barcelona,0,1,0,100,No,Good weather,On vacation,Urban zone,Light wind,Night,Moving vehicles collision");

		System.out.println("\n--- Using two random accidents from the CSV file (a1: index 29, a2: index 39) ---");

		Accidents shortAccidents = FactoryAccident.readFileContainer(
				"/Users/ignacio/Desktop/US/1st_year/2C/FP/LAB/java-term-project-IMora04/data/Accidents_Catalonia.csv")
				.getSublist(0, 100);
		System.out.println();
		Accident a1 = shortAccidents.getAccidentList().get(29);
		Accident a2 = shortAccidents.getAccidentList().get(39);

		equalityTest(a1, a2);
		System.out.println("\n--- a1 date: " + a1.getDate() + " ---\n--- a2 date: " + a2.getDate() + " ---\n");

		orderTest(shortAccidents.getAccidentList().get(29), shortAccidents.getAccidentList().get(39));
		System.out.println();

		toStringTest(a1);
	}

}