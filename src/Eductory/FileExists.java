package Eductory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class FileExists {
	@SuppressWarnings("unchecked")
	public static HashMap<Integer, Courses> course(){
		HashMap <Integer, Courses> ans=null;
		File courseFile = new File("course.ser");
		boolean flag=false;
		try {
			if(!courseFile.exists()) {
				courseFile.createNewFile();
				flag=true;
			}
			if(flag) {
				ans=new HashMap <>();
				ObjectOutputStream op=new ObjectOutputStream(new FileOutputStream(courseFile));
				op.writeObject(ans);
				return ans;
			}
			else {
				ObjectInputStream ip = new ObjectInputStream(new FileInputStream(courseFile));
				ans = (HashMap<Integer, Courses>) ip.readObject();

				return ans;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ans;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, Batch> batch(){
		HashMap <String, Batch> ans=null;
		File batchFile = new File("batch.ser");
		boolean flag=false;
		try {
			if(!batchFile.exists()) {
				batchFile.createNewFile();
				flag=true;
			}
			if(flag) {
				ans=new HashMap <>();
				ObjectOutputStream op= new ObjectOutputStream(new FileOutputStream(batchFile));
				op.writeObject(ans);
				return ans;
			}
			else {
				ObjectInputStream ip = new ObjectInputStream(new FileInputStream(batchFile));
				ans = (HashMap<String, Batch>) ip.readObject();

				return ans;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ans;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Students> student() {
		ArrayList<Students> ans=null;
		File studentFile = new File("student.ser");
		boolean flag=false;
		try {
			if(!studentFile.exists()) {
				studentFile.createNewFile();
				flag=true;
			}
			if(flag) {
				ans=new ArrayList <Students>();
				ObjectOutputStream op=new ObjectOutputStream(new FileOutputStream(studentFile));
				op.writeObject(ans);
				return ans;
			}
			else {
				ObjectInputStream ip = new ObjectInputStream(new FileInputStream(studentFile));
				ans = (ArrayList<Students>) ip.readObject();
				return ans;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return ans;
		
	}
}
