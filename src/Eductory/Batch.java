package Eductory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Batch implements Serializable  {
	private LocalDate startDate;
	private LocalDate endDate;
	private ArrayList<Students> students;
	public Batch(LocalDate a , LocalDate b) {
		this.startDate=a;
		this.endDate=b;
		this.students=new ArrayList<Students>();
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Students> getStudents() {
		return students;
	}
	@Override 
	public String toString() {
		return "{\n"+"Starting date-: "+startDate+"\nEnding date-: "+endDate+"\nStudents-> "+this.students+"}\n"; 
	}
	
}
