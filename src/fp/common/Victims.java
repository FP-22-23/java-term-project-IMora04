package fp.common;

import java.util.Objects;

import fp.utils.Checkers;

public class Victims implements Comparable<Victims> {

	private Integer deaths;
	private Integer serious_inj;
	private Integer slight_inj;

	public Victims(Integer deaths, Integer serious_inj, Integer slight_inj) {

		// RESTRICTION: Number of victims must be greater or equal to 0

		Checkers.check("Incorrec victims value", deaths >= 0 && serious_inj >= 0 && slight_inj >= 0);

		this.deaths = deaths;
		this.serious_inj = serious_inj;
		this.slight_inj = slight_inj;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public Integer getSeriouslyInjured() {
		return serious_inj;
	}

	public Integer getSlightlyInjured() {
		return slight_inj;
	}

	public Integer getTotalVictims() {
		return getSeriouslyInjured() + getSlightlyInjured() + getDeaths();
	}

	public int hashCode() {
		return Objects.hash(deaths, serious_inj, slight_inj);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Victims other = (Victims) obj;
		return Objects.equals(deaths, other.deaths) && Objects.equals(serious_inj, other.serious_inj)
				&& Objects.equals(slight_inj, other.slight_inj);
	}

	public String toString() {
		return "Victims [Deaths=" + getDeaths() + ", SeriouslyInjured=" + getSeriouslyInjured() + ", SlightlyInjured="
				+ getSlightlyInjured() + ", TotalVictims=" + getTotalVictims() + "]";
	}

	public int compareTo(Victims o) {
		int res = getTotalVictims().compareTo(o.getTotalVictims());
		if (res == 0) {
			res = getDeaths().compareTo(o.getDeaths());
		}
		return res;
	}

}