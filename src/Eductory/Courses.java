package Eductory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Courses implements Serializable {
	private String name="";
	private int durationInWeeks=0;
	private int fees=0;
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
//	public HashMap<String, Batch> getBatches() {
//		return batches;
//	}
//	public void setBatches(HashMap<String, Batch> batches) {
//		this.batches = batches;
//	}
	@Override
	public String toString(){
		return"{\n"+"Course Name-: "+this.getName()+"\nCourse Duration In Weeks-: "+this.getDurationInWeeks()+"\nCourse Fees In Rupees-: "+this.getFees()+"\n}\n";
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
