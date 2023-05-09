package fp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.common.Climate;
import fp.common.Victims;

public class Accidents {
	
	private List<Accident> accidents;
		
	//CONSTRUCTOR 1: Empty
	
	public Accidents() {
		this.accidents = new ArrayList<Accident>();
	}
	
	//CONSTRUCTOR 2: From collection of accidents
	
	public Accidents(Collection<Accident> c) {
		this.accidents = new ArrayList<Accident>(c);
	}
	
	//CONSTRUCTOR 3: From stream of accidents
	
	public Accidents(Stream<Accident> s) {
		this.accidents = s.collect(Collectors.toCollection(ArrayList<Accident>::new));
	}
	
	//EQUALITY CRITERION: Accidents are equal to another if the same accidents are inside both lists (order and number does not matter)

	public int hashCode() {
		return Objects.hash(accidents);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accidents other = (Accidents) obj;
		return Objects.equals(new HashSet<Accident>(accidents), new HashSet<Accident>(other.accidents));
	}
	
	//METHODS
	
	public Accidents getSublist(int a, int b) {
		return new Accidents(accidents.subList(a, b));
	}
	
	public List<Accident> getAccidentList() {
		return accidents;
	}
		
	public int getNumberAccidents() {
		return accidents.size();
	}
	
	public void addAccident(Accident a) {
		accidents.add(a);
	}
	
	public void addAccidents(Collection<Accident> c) {
		accidents.addAll(c);
	}
	
	public void removeAccident(Accident a) {
		accidents.remove(a);
	}
	
	//SEQUENTIAL TREATMENT METHODS
	
	public boolean allInYear(int y) {
		boolean res = true;
		for(Accident a:accidents) {
			if(!(a.getDate().getYear() == y)) {
				res = false;
				break;
			}
		}
		return res;
	}
	
	public double avgVictims() {
		int count = 0;
		int victims = 0;
		for(Accident a:accidents) {
			count++;
			victims += a.getVictims().getTotalVictims();
		}
		return (double) victims/count;
	}
	
	public List<Accident> filterByYear(int y) {
		List<Accident> accidents_y = new ArrayList<>();
		for (Accident a:accidents) {
			if(a.getDate().getYear() == y) {
				accidents_y.add(a);
			}
		}
		return accidents_y;
	}
	
	public Map<String, List<Accident>> groupByLocation() {
		Map<String, List<Accident>> m = new HashMap<>();
		for(Accident a:accidents) {
			if(m.containsKey(a.getLocation())) { 
				m.get(a.getLocation()).add(a);
			} else {
				m.put(a.getLocation(), new ArrayList<>(List.of(a)));
			}
		}
		return m;
	}
	
	public Map<Climate, Integer> countByClimate() {
		Map<Climate, Integer> m = new HashMap<>();
		for(Accident a:accidents) {
			Climate c = a.getClimate();
			if(m.containsKey(c)) {
				m.put(c, m.get(c) + 1);
			} else {
				m.put(c, 1);
			}
		}
		return m;
	}
		
	//STREAM METHODS
		
	public boolean allInYearStream(int y) {
		return accidents.size() == accidents.stream().filter(t->t.getDate().getYear() == y).count();
	}
	
	public double avgVictimsStream() {		
		return accidents.stream().mapToInt(t->t.getVictims().getTotalVictims()).average().orElse(0);
	}

	public List<Accident> filterByYearStream(int y) {
		return accidents.stream().
				filter(t->t.getDate().getYear() == y).
				collect(Collectors.toCollection(ArrayList::new));
	}
	
	public Integer maxSpeedOnClimate(Climate c) {
		return accidents.stream().
				filter(t->t.getClimate().equals(c) && t.getSpeed() != null).
				mapToInt(t->t.getSpeed()).
				max().
				orElse(0);
	}
	
	public SortedSet<Accident> sortedVictimsWithEscapists() {
		return accidents.stream().
				filter(t->t.getEscapist() != null && t.getEscapist().equals(Boolean.TRUE)).
				collect(Collectors.toCollection(
						() -> new TreeSet<Accident>(
								Comparator.comparing(
										t->t.getVictims().getTotalVictims(),
										Comparator.reverseOrder()))));
	}
	
	public Map<String, List<Accident>> groupByLocationStream() {
		return accidents.stream().
				collect(Collectors.groupingBy(t->t.getLocation(),
						Collectors.toList()));
	}
	
	public Map<LocalDate, Victims> accidentMostVictimsByDate() {
		return accidents.stream()
				.collect(Collectors.groupingBy(t->t.getDate(),
						Collectors.collectingAndThen(
								Collectors.maxBy(
										Comparator.comparing(t->t.getVictims())),
								opt->opt.orElse(null).getVictims())));
	}
		
	public Map<String, List<Integer>> groupSpeedsByType() {
		return accidents.stream().
				collect(Collectors.groupingBy(t->t.getInfo().get(0),
						Collectors.mapping(t->t.getSpeed(), Collectors.toList())
						));
	}
		
	public SortedMap<Climate, List<Accident>> groupByClimateEarliest(int n) {
		return accidents.stream().
				sorted(Comparator.comparing(t->t.getDate(),
						Comparator.reverseOrder())).
				collect(Collectors.groupingBy(
						t->t.getClimate(),
						TreeMap::new,
						Collectors.collectingAndThen(
								Collectors.toList(),
								l->(l.size()<n) ? l:l.subList(0, n))));
	}
	
	//STRING REPRESENTATION

	public String toString() {
		String accidentsString = "";
		for(int i = 0; i < accidents.size() && i < 100; i++) {
			accidentsString = accidentsString + "\n" + accidents.get(i);
		}
		return "Number of accidents: " + getNumberAccidents() + ", Accidents (first 100): " + accidentsString ;
	}
	
}
