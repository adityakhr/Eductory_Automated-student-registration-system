package Eductory;

import java.io.Serializable;
import java.util.Objects;

public class Courses implements Serializable {
	private String name;
	private int durationInWeeks;
	private int fees;
	public Courses(String a , int b, int c) {
		this.name=a;
		this.durationInWeeks=b; 
		this.fees=c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDurationInWeeks() {
		return durationInWeeks;
	}
	public void setDurationInWeeks(int durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	@Override
	public String toString(){
		return this.getName()+" "+this.getDurationInWeeks()+" "+this.getFees();
	}
	@Override 
	public int hashCode() {
		return Objects.hash(name , durationInWeeks , fees);
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		return false;
	}
}
