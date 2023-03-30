package Eductory;

import java.time.LocalDate;
import java.util.ArrayList;

public class Batch {
	LocalDate startDate;
	LocalDate endDate;
	private ArrayList<Students> students;
	public Batch(LocalDate a , LocalDate b) {
		this.startDate=a;
		this.endDate=b;
		this.students=new ArrayList<Students>();
	}
	public ArrayList<Students> getStudents() {
		return students;
	}
	@Override
	public String toString() {
		return "Starting date:- "+startDate+"Ending date: "+endDate+"\nStudents-> "+this.students; 
	}
	
}
