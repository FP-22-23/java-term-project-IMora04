package fp.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import fp.common.Climate;

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
	
	public Accidents getSublist(int a, int b) {
		return new Accidents(accidents.subList(a, b));
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
