package Eductory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class FileExists {
	public static HashMap<Integer, Courses> course(){
		HashMap <Integer, Courses> ans=null;
		File courseFile = new File("course.txt");
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
	
	
	
	public static HashMap<String, Batch> batch(){
		HashMap <String, Batch> ans=null;
		File batchFile = new File("batch.txt");
		boolean flag=false;
		try {
			if(!batchFile.exists()) {
				batchFile.createNewFile();
				flag=true;
			}
			if(flag) {
				ans=new HashMap <>();
				ObjectOutputStream op=new ObjectOutputStream(new FileOutputStream(batchFile));
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
	public static ArrayList<Students> students() {
		// TODO Auto-generated method stub
		ArrayList<Students> ans=null;
		File studentFile = new File("student.txt");
		boolean flag=false;
		try {
			if(!studentFile.exists()) {
				studentFile.createNewFile();
				flag=true;
			}
			if(flag) {
				ans=new ArrayList <>();
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
