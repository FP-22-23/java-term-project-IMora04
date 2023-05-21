package fp.domain.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import fp.common.Climate;
import fp.domain.Accident;
import fp.domain.Accidents;
import fp.domain.FactoryAccident;

public class TestAccidents {

	public static void showAccidents(Accidents a) {
		for (Accident ac : a.getAccidentList()) {
			TestAccident.showAccident(ac);
			System.out.println();
		}
	}

	public static void showAccidents(Collection<Accident> a) {
		for (Accident ac : a) {
			TestAccident.showAccident(ac);
			System.out.println();
		}
	}

	public static <K, V> void mapToString(Map<K, V> m) {
		for (Map.Entry<K, V> e : m.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}

	// CONSTRUCTOR TESTS

	public static void constructor1Test() {
		System.out.println(" ----- FIRST CONSTRUCTOR TEST (Accidents, empty): ----- ");
		try {
			System.out.println(new Accidents());
		} catch (Exception e) {
			System.out.println("Error testing first constructor: Unexpected error:\n" + e);
		}
	}

	public static void constructor2Test(Collection<Accident> c) {
		System.out.println("\n ----- SECOND CONSTRUCTOR TEST (Accidents, from collection): ------ ");
		try {
			showAccidents(new Accidents(c));
		} catch (Exception e) {
			System.out.println("Error testing second constructor, Unexpected error:\n" + e);
		}
	}

	public static void constructor3Test(Stream<Accident> s) {
		System.out.println("\n ----- THIRD CONSTRUCTOR TEST (Accidents, from stream): ----- ");
		try {
			showAccidents(new Accidents(s));
		} catch (Exception e) {
			System.out.println("Error testing third constructor, Unexpected error:\n" + e);
		}
	}

	// EQUALITY, TOSTRING TESTS

	public static void equalityTest(Accidents a1, Accidents a2) {
		String result = (a1.equals(a2)) ? "a1 equals a2" : "a1 does not equal a2";
		System.out.println("EQUALITY CRITERION TEST (Accidents): " + result);
	}

	public static void toStringTest(Accidents a) {
		System.out.println("TO STRING TEST (Accidents): \n\t" + a);
	}

	// SEQUENTIAL METHOD TESTS

	public static void allInYearTest(Accidents a, Integer y) {
		String result = (a.allInYear(y)) ? "Yes they do" : "No they don't";
		System.out.println("\nallInYear Test --> Checking if all accidents occurr in " + y + ": \n\t" + result);
	}

	public static void anyInYearTest(Accidents a, Integer y) {
		String result = (a.anyInYear(y)) ? "Yes, it does" : "No, it doesn't";
		System.out.println("\nanyInYear Test --> Checking if any accident occurrs in " + y + ": \n\t" + result);

	}

	public static void avgVictimsTest(Accidents a) {
		System.out.println("\navgVictims Test --> Computing the average total victims of first 100 accidents:\n\t"
				+ a.avgVictims());
	}

	public static void countWithDeathsTest(Accidents a) {
		System.out.println(
				"\ncountWithDeaths Test --> Number of accidents in which there were deaths: " + a.countWithDeaths());
	}

	public static void filterByYearTest(Accidents a, Integer y) {
		System.out.println("\n ----- filterByYear Test --> Showing first 5 accidents that occurred in " + y
				+ " ----- \n(In this case, they only happen in 2010):\n");
		List<Accident> l = a.filterByYear(y);
		if (l.size() >= 5) {
			showAccidents(l.subList(0, 5));
		} else {
			showAccidents(l);
		}
	}

	public static void groupByLocationTest(Accidents a) {
		System.out.println(" ----- groupByLocation Test --> Grouping first 100 accidents by location: ----- \n");
		mapToString(a.groupByLocation());
	}

	public static void countByClimateTest(Accidents a) {
		System.out.println("\n ----- countByClimate Test --> Number of accidents per type of climate: ----- \n");
		mapToString(a.countByClimate());
	}

	// STREAM METHODS TESTS

	public static void allInYearStreamTest(Accidents a, Integer y) {
		String result = (a.allInYearStream(y)) ? "Yes they do" : "No they don't";
		System.out.println("\nallInYearStream Test --> Checking if all accidents occurr in " + y + ": \n\t" + result);
	}

	public static void anyInYearStreamTest(Accidents a, Integer y) {
		String result = (a.anyInYearStream(y)) ? "Yes, it does" : "No, it doesn't";
		System.out.println("\nanyInYearStream Test --> Checking if any accident occurrs in " + y + ": \n\t" + result);
	}

	public static void avgVictimsStreamTest(Accidents a) {
		System.out.println("\navgVictimsStream Test --> Computing the average total victims of first 100 accidents:\n\t"
				+ a.avgVictimsStream());
	}

	public static void countWithDeathsStreamTest(Accidents a) {
		System.out.println("\ncountWithDeathsStream Test --> Number of accidents in which there were deaths: "
				+ a.countWithDeathsStream());
	}

	public static void filterByYearStreamTest(Accidents a, Integer y) {
		System.out.println("\n ----- filterByYearStream Test --> Showing first 5 accidents that occurred in " + y
				+ " ----- \n(In this case, they only happen in 2010):\n");
		List<Accident> l = a.filterByYearStream(y);
		if (l.size() >= 5) {
			showAccidents(l.subList(0, 5));
		} else {
			showAccidents(l);
		}
	}

	public static void maxSpeedOnClimateTest(Accidents a, Climate c) {
		System.out.println("\n ----- maxSpeedOnClimate Test --> Showing max speed of accidents that occurred during "
				+ c + " climate ----- \n");
		System.out.println("Max speed was " + a.maxSpeedOnClimate(c));
	}

	public static void sortedVictimsWithEscapistsTest(Accidents a) {
		System.out.println(
				"\n ----- sortedVictimsWithEscapists Test --> Showing accidents with escapists sorted by total victims ----- \n");
		showAccidents(a.sortedVictimsWithEscapists());
	}

	public static void groupByLocationStreamTest(Accidents a) {
		System.out
				.println("\\n ----- groupByLocationStream Test --> Grouping first 100 accidents by location: ----- \n");
		mapToString(a.groupByLocationStream());
	}

	public static void countByClimateStreamTest(Accidents a) {
		System.out.println("\n ----- countByClimateStream Test --> Number of accidents per type of climate: ----- \n");
		mapToString(a.countByClimateStream());
	}

	public static void accidentMostVictimsByDateTest(Accidents a) {
		System.out.println(
				"\\n ----- accidentMostVictimsByDate Test --> Grouping dates with the maximum number of victims in that date ----- \n");
		mapToString(a.accidentMostVictimsByDate());
	}

	public static void groupSpeedsByTypeTest(Accidents a) {
		System.out.println("\\n ----- groupSpeedsByType Test --> Grouping speeds by type of accident ----- \n");
		mapToString(a.groupSpeedsByType());
	}

	public static void groupByClimateEarliestTest(Accidents a, int n) {
		System.out.println(
				"\n ----- groupByClimateEarliest Test --> Grouping Accidents by Climate. Just n most recent per Climate ----- \n");
		mapToString(a.groupByClimateEarliest(n));
	}

	public static void getLocationMostVictimsTest(Accidents a) {
		System.out.println(
				"\n ----- getLocationMostVictims Test --> Showing location in which the accident with the greatest number of accidents happened ----- \n");
		System.out.println(a.getLocationMostVictims());
	}

	public static void main(String[] args) {

		constructor1Test();

		System.out.println(
				"\n ----- Using FactoryAccident to read the file. Resulting Accidents as collection of the 2nd constructor ----- ");
		System.out.println("\n ----- JUST FIRST 100 ACCIDENTS WILL BE TAKEN INTO ACCOUNT, FOR ALL THE TESTS -----");
		Accidents shortAccidents = FactoryAccident.readFileContainer(
				"/Users/ignacio/Desktop/US/1st_year/2C/FP/LAB/java-term-project-IMora04/data/Accidents_Catalonia.csv")
				.getSublist(0, 100);

		constructor2Test(shortAccidents.getAccidentList());

		constructor3Test(shortAccidents.getAccidentList().stream());

		System.out.println("\n--- Using accidents read from the file ---\n");
		System.out.println("--- a1: Accidents from 0 to 99 ---\n--- a2: Accidents from 0 to 79 ---\n");

		equalityTest(shortAccidents, shortAccidents.getSublist(0, 80));

		toStringTest(shortAccidents);

		allInYearTest(shortAccidents, 2010);

		anyInYearTest(shortAccidents, 2011);

		avgVictimsTest(shortAccidents);

		countWithDeathsTest(shortAccidents);

		filterByYearTest(shortAccidents, 2010);
		filterByYearTest(shortAccidents, 2011);

		groupByLocationTest(shortAccidents);

		countByClimateTest(shortAccidents);

		allInYearStreamTest(shortAccidents, 2010);

		anyInYearStreamTest(shortAccidents, 2011);

		avgVictimsStreamTest(shortAccidents);

		countWithDeathsStreamTest(shortAccidents);

		filterByYearStreamTest(shortAccidents, 2010);
		filterByYearStreamTest(shortAccidents, 2011);

		maxSpeedOnClimateTest(shortAccidents, Climate.HEAVY_RAIN);
		maxSpeedOnClimateTest(shortAccidents, Climate.GOOD_WEATHER);

		sortedVictimsWithEscapistsTest(shortAccidents);

		groupByLocationStreamTest(shortAccidents);

		countByClimateStreamTest(shortAccidents);

		accidentMostVictimsByDateTest(shortAccidents);

		groupSpeedsByTypeTest(shortAccidents);

		groupByClimateEarliestTest(shortAccidents, 3);

		getLocationMostVictimsTest(shortAccidents);
	}

}
