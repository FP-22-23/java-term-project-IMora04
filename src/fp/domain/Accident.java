package fp.domain;
import fp.common.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Accident implements Comparable<Accident> {
	
	// BASIC PROPERTIES
	
	private LocalDate date;
	private String location;
	private Victims victims;
	private Integer speed;
	private Boolean escapist;
	private Climate climate;
	private List<String> info; // LIST PROPERTY: Extra information about the accident (type, subtype, subzone, moment, wind)
	
	// 1ST CONSTRUCTOR: Receives a value for every basic property
	
	public Accident(LocalDate date, String location, Victims victims, Integer speed, Boolean escapist, Climate climate, List<String> info) {
		if (date.compareTo(LocalDate.now()) > 0) {
			
			// RESTRICTION 1: The accident's date must be lower or equal than today's date
			
			throw new IllegalArgumentException("Invalid date");			
		} else if (speed != null && speed <= 0) {
			
			// RESTRICTION 2: The maximum speed cannot be negative

			throw new IllegalArgumentException("Invalid speed");
		} else {
		this.date = date;
		this.location = location;
		this.victims = victims;
		this.speed = speed;
		this.escapist = escapist;
		this.climate = climate;
		this.info = info;
		}
		
	}
	
	// 2ND CONSTRUCTOR: Receives a string and splits it.
	
	public Accident(String s) {
		String[] values = s.split(",");
		
		if (values.length == 15) {
			date = LocalDate.parse(values[0].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			location = values[1].trim() + ", " + values[2].trim() + ", " + values[3].trim();
			victims = new Victims(Integer.valueOf(values[4].trim()), Integer.valueOf(values[5].trim()), Integer.valueOf(values[6].trim()));
			speed = (!values[7].trim().equals("NA")) ? Integer.valueOf(values[7].trim()) : null;
			switch(values[8].trim().toLowerCase()) {
				case "yes": escapist = true; break;
				case "no": escapist = false; break;
				default: escapist = null;
			}
			switch(values[9].trim().toLowerCase()) {
				case "good weather": 	climate = Climate.GOOD_WEATHER; break;
				case "heavy rain": 		climate = Climate.HEAVY_RAIN; break;
				case "light rain": 		climate = Climate.LIGHT_RAIN; break;
				case "hail": 			climate = Climate.HAIL; break;
				case "snowing": 		climate = Climate.SNOWING; break;
				default: 				climate = Climate.UNSPECIFIED;
			}
			info = List.of(
					values[14].trim(),		//type	
					values[10].trim(),		//subtype
					values[11].trim(),		//subzone
					values[13].trim(),		//moment
					values[12].trim()		//wind
					);
			
		} else {
			
			// RESTRICTION 3: String must have 15 values
			
			throw new IllegalArgumentException("Incorrect string format");
		}
	
	}
	
	// DERIVED PROPERTY: Proportion of deaths, with respect to the total number of victims (between 0 and 1)
	
	public Double getDeathsProportion() {		
		if (getVictims().getTotalVictims() != 0) {
			return Double.valueOf(getVictims().getDeaths()/getVictims().getTotalVictims());
		} else
			return 0.0;
	}
	
	// GETTER METHODS
	
	public Victims getVictims() {
		return victims;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getLocation() {
		return location;
	}

	public Boolean getEscapist() {
		return escapist;
	}

	public Integer getSpeed() {
		return speed;
	}

	public Climate getClimate() {
		return climate;
	}

	public List<String> getInfo() {
		return info;
	}
	
	// STRING REPRESENTATION: Represent every property. Every property of the type Victims is also represented

	public String toString() {
		return "Accident [DeathsProportion=" + getDeathsProportion() + ", Victims=" + getVictims()
				+ ", Date=" + getDate() + ", Location=" + getLocation() + ", Escapist=" + getEscapist()
				+ ", Speed=" + getSpeed() + ", Climate=" + getClimate() + ", Info=" + getInfo() + "]";
	}

	// EQUALITY CRITERION: An accident is equal to another if every basic property is equal
	
	public int hashCode() {
		return Objects.hash(climate, date, escapist, info, location, speed, victims);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accident other = (Accident) obj;
		return climate == other.climate && Objects.equals(date, other.date) && Objects.equals(escapist, other.escapist)
				&& Objects.equals(info, other.info) && Objects.equals(location, other.location)
				&& Objects.equals(speed, other.speed) && Objects.equals(victims, other.victims);
	}

	// ORDER CRITERION: Ordered by date. If equal, ordered by location (alphabetically)
	
	public int compareTo(Accident o) {
		int res = getDate().compareTo(o.getDate());
		if (res == 0) {
			res = getLocation().compareTo(o.getLocation());
		}
		return res;
	}
		
}