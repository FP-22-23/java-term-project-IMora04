package fp.domain.test;

import fp.domain.FactoryAccident;

public class TestFactory {

	public static void readFileTest(String route) {
		System.out.println(" ----- FACTORY READFILE TEST: ----- ");
		System.out.println("\tAccidents: (First 25) ");
		TestAccidents.showAccidents(FactoryAccident.readFile(route).subList(0, 100));
	}

	public static void readFileContainerTest(String route) {
		System.out.println(" ----- FACTORY READFILE (TO CONTAINER) TEST: ----- ");
		System.out.println("\tAccidents: (First 25) ");
		TestAccidents.showAccidents(FactoryAccident.readFileContainer(route).getSublist(0, 100));
	}

	public static void main(String[] args) {
		readFileTest(
				"/Users/ignacio/Desktop/US/1st_year/2C/FP/LAB/java-term-project-IMora04/data/Accidents_Catalonia.csv");
		readFileContainerTest(
				"/Users/ignacio/Desktop/US/1st_year/2C/FP/LAB/java-term-project-IMora04/data/Accidents_Catalonia.csv");

	}

}
