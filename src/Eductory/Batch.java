package Eductory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Batch implements Serializable  {
	LocalDate startDate;
	LocalDate endDate;
	private TreeSet<Students> students;
	public Batch(LocalDate a , LocalDate b) {
		this.startDate=a;
		this.endDate=b;
		this.students=new TreeSet<Students>();
	}
	public TreeSet<Students> getStudents() {
		return students;
	}
	@Override 
	public String toString() {
		return "{\n"+"Starting date-: "+startDate+"\nEnding date-: "+endDate+"\nStudents-> "+this.students+"}\n"; 
	}
	
}
