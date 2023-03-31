package Eductory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
public class Main {
	
	/*Admin part*/
	
	
	public static void adminLogIn(Scanner sc) throws InvalidCredentials{
		System.out.println("Please Enter Your Username:");
		String uName=sc.nextLine().trim();
		System.out.println("Please Enter Your Password:");
		String passWord=sc.nextLine().trim();
		if(!uName.equals("admin") || !passWord.equals("admin")) {
			throw new InvalidCredentials("Invalid Credentials\n==============\n");
		}
	}
	public static void adminFunctionality(Scanner sc , HashMap<Integer,Courses> courses,HashMap<String, Batch> batchs, ArrayList<Students> students){
		try {
			adminLogIn(sc);
			System.out.println("=========================\nWelcome back ADITYA great to have you here again\n=========================");
			String k="";
			do {
				System.out.println("\nPlease select any option below:\n"
						+ "1(Add new course)\n"
						+ "2(Show all courses)\n"
						+ "3(Upadate details of course)\n"
						+ "4(Create a batch under course)\n"
						+ "5(Search a batch)\n"
						+ "6(View student details)\n"
						+ "7(View student list of batch)\n"
						+ "8(Update details of batch)\n"
						+ "0(Log out)\n");
				
				k=sc.nextLine();
				switch(k) {
				case "1":
					adminAddCourse(sc ,courses);
					break;
				case "2":
					adminPrintCourses(courses);
					break;
				case "3":
					adminUpdateDetailsOfCourse(sc, courses);
					break;
				case "4":
					adminCreatBatch(sc,batchs,students);
					break;
				case "5":
					adminSearchBatch(sc, batchs);
					break;
				case "6":
					adminViewStudentsDetails(students);
					break;
				case "7":
					adminViewListOfStudentsOfBAtch(sc, batchs);
					break;
				case "8":
					adminUpdateDetailsOfBatch(sc,batchs);
					break;
				case "0" :
					System.out.println("See you soon ADITYA\n========================\n");
					break;
				case "" :
					System.out.println("You Haven't chosen any option\n========================\n");
					break;
				default:
					System.out.println("Sorry wrong input\n========================\n");
				}
			}while(!k.equals("0"));
			
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public static HashMap<Integer, Courses> adminAddCourse(Scanner sc , HashMap<Integer,Courses> courses){
		try {
			System.out.println("\nEnter the course name");
			String s= sc.nextLine().trim();
			System.out.println("Enter the course duration");
			int k= sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the course fees");
			int l= sc.nextInt();
			sc.nextLine();
			Courses c = new Courses(s,k,l);
			int id=RandomIdGenerator.generateId();
			courses.put(id, c);
			System.out.println("Course is added successfully");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return courses;
	}
	public static void adminPrintCourses(HashMap<Integer,Courses> courses) throws NoElementFound {
		if(courses.size()>0) {
			for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
				System.out.println("("+i.getKey()+") -> "+i.getValue());
			}
		}else {
			throw new NoElementFound("There is not any course.\n");
		}
	}
	public static void adminUpdateDetailsOfCourse(Scanner sc , HashMap<Integer,Courses> courses) throws NoElementFound {
		System.out.println("Enter the course id:");
		int k= sc.nextInt();
		sc.nextLine();
		if(courses.containsKey(k)) {
			Courses cou= courses.get(k);
			System.out.println("Select a field below:\n"
					+ "1(Name of course)\n"
					+ "2(Duration of course)\n"
					+ "3(Fees of course)\n");
			try {
				String selection=sc.nextLine().trim();
				switch(selection) {
				case "1":
					System.out.println("Enter the new name:\n");
					String name=sc.nextLine().trim();
					cou.setName(name);
					break;
				case "2":
					System.out.println("Enter the new duration:\n");
					int du=sc.nextInt();
					cou.setDurationInWeeks(du);
					break;
				case "3":
					System.out.println("Enter the new fees for it:\n");
					int fee=sc.nextInt();
					cou.setFees(fee);
					break;
				default:
					System.out.println("Wrong selection!\n");
					break;
				}	
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		else {
			throw new NoElementFound("No course available with that id, Sorry!");
		}
	}
	public static HashMap<String, Batch> adminCreatBatch(Scanner sc , HashMap<String, Batch> batchs,ArrayList<Students>students) throws mobileNumber {
		try {
			System.out.println("Enter the Batch Name: ");
			String name=sc.nextLine().trim();
			System.out.println("Student details:\nEnter the first name of  student: ");
			String fName=sc.nextLine().trim();
			System.out.println("last name of  student: ");
			String lName=sc.nextLine().trim();
			System.out.println("Enter the email of  student: ");
			String email=sc.nextLine().trim();
			System.out.println("Enter the mobile number of  student: ");
			long mobile=sc.nextLong();
			sc.nextLine();
			String str="";
			str+=mobile;
			if(str.length()!=10) {
				throw new mobileNumber("Mobile Number is not valid");
			}
			if(batchs.containsKey(name)) {
				Students student= new Students (fName, lName, email, mobile);
				students.add(student);
				Batch b = batchs.get(name);
				b.getStudents().add(student);
			}
			else { 
				Students student= new Students (fName, lName, email , mobile);
				System.out.println("Starting Date(dd/MM/yyyy): ");
				String sDate=sc.nextLine().trim();
				System.out.println("Ending Date(dd/MM/yyyy): ");
				String eDate=sc.nextLine().trim();
				DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
				LocalDate sDateFinal= LocalDate.parse(sDate,dtf);
				LocalDate eDateFinal= LocalDate.parse(eDate,dtf);
				Batch b= new Batch(sDateFinal,eDateFinal);
				b.getStudents().add(student);
				students.add(student);
				batchs.put(name, b);
				System.out.println("Successfully created.\n");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return batchs;
	}
	public static void adminSearchBatch(Scanner sc ,HashMap<String , Batch> batchs ){
		System.out.println("\nSelect any option below:\n"
				+ "1(By name)\n"
				+ "2(By Starting date)\n"
				+ "3(By Ending date)\n");
		try {
			String selection=sc.nextLine().trim();
			switch(selection) {
			case "1":
				System.out.println("Enter the name of batch: ");
				String nameOfBatch=sc.nextLine().trim();
				int count=0;
				for(HashMap.Entry<String,Batch>i: batchs.entrySet()) {
					if(i.getKey().equals(nameOfBatch)) {
						System.out.println(i.getKey()+" -> "+i.getValue());
						++count;
					}
				}
				if(count==0) {
					throw new NoElementFound("No batch found.\n");
				}
				break;
			case "2":
				System.out.println("Enter the starting date of batch(dd/MM/yyyy): ");
				String sDate=sc.nextLine().trim();
				DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
				LocalDate sDateFinal= LocalDate.parse(sDate,dtf);
				int count1=0;
				for(HashMap.Entry<String,Batch>i: batchs.entrySet()) {
					if(i.getValue().startDate.isEqual(sDateFinal)) {
						System.out.println(i.getKey()+" -> "+i.getValue());
						++count1;
					}
				}
				if(count1==0) {
					throw new NoElementFound("No batch found.\n");
				}
				break;
			case "3":
				System.out.println("Enter the starting date of batch(dd/MM/yyyy): ");
				String eDate=sc.nextLine().trim();
				DateTimeFormatter dtf1= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
				LocalDate eDateFinal= LocalDate.parse(eDate,dtf1);
				int count2=0;
				for(HashMap.Entry<String,Batch>i: batchs.entrySet()) {
					if(i.getValue().endDate.isEqual(eDateFinal)) {
						System.out.println(i.getKey()+" -> "+i.getValue());
						++count2;
					}
				}
				if(count2==0) {
					throw new NoElementFound("No batch found.\n");
				}
			default:
				System.out.println("Wrong selection!\n");
				break;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void adminViewStudentsDetails(ArrayList<Students> students) throws NoElementFound {
		if(students.size()==0) {
			throw new NoElementFound("No student listed found.\n");
		}
		for(Students i: students) {
			System.out.println(i);
		}
	}
	public static void adminViewListOfStudentsOfBAtch(Scanner sc, HashMap<String,Batch> batchs) throws NoElementFound{
		System.out.println("Enter the name of the batch: ");
		String nameOfBatch=sc.nextLine().trim();
		if(batchs.containsKey(nameOfBatch)) {
			for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
				if(i.getKey().equals(nameOfBatch)) {
					System.out.println(i.getValue().getStudents());
				}
			}
		}else {
			throw new NoElementFound("No batch found");
		}
	}
	public static void adminUpdateDetailsOfBatch(Scanner sc , HashMap<String,Batch> batchs) throws NoElementFound {
		System.out.println("Select the option below:\n"
				+ "1(Batch name)\n"
				+ "2(Student first name)\n"
				+ "3(Student last name)\n"
				+ "4(Student email)\n"
				+ "5(Student mobile)\n");
		String selection=sc.nextLine().trim();
		switch(selection) {
			case "1":
				System.out.println("Enter the name of batch: ");
				String nameofBatch=sc.nextLine().trim();
				if(!batchs.containsKey(nameofBatch)) {
					throw new NoElementFound("No batch found, Sorry!\n");
				}
				else {
					System.out.println("Enter the new name: ");
					String newName= sc.nextLine().trim();
					for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
						if(i.getKey().equals(nameofBatch)) {
							Batch batch=i.getValue();
							batchs.put(newName, batch);
							System.out.println("Successfully update\n");
							batchs.remove(nameofBatch);
						}
					}
				}
				break;
			case "2":
				System.out.println("Enter the name of batch: ");
				String nameofBatch1=sc.nextLine().trim();
				if(!batchs.containsKey(nameofBatch1)) {
					throw new NoElementFound("No batch found, Sorry!\n");
				}
				else {
					System.out.println("Enter the id of student: ");
					int idofStudent= sc.nextInt();
					System.out.println("Enter the new first name of student: ");
					String nameofStudent= sc.nextLine().trim();
					for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
						int count=0;
						Batch batch=i.getValue();
						for(Students j: batch.getStudents()) {
							if(j.getId()== idofStudent) {
								j.setfName(nameofStudent);
								System.out.println("Successfully update\n");
								++count;
							}
						}
						if(count==0) {
							throw new NoElementFound("No student found, Sorry!");
						}
					}
				}
				break;
			case "3":
				System.out.println("Enter the name of batch: ");
				String nameofBatch11=sc.nextLine().trim();
				if(!batchs.containsKey(nameofBatch11)) {
					throw new NoElementFound("No batch found, Sorry!\n");
				}
				else {
					System.out.println("Enter the id of student: ");
					int idofStudent= sc.nextInt();
					System.out.println("Enter the new last name of student: ");
					String nameofStudent= sc.nextLine().trim();
					for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
						int count=0;
						Batch batch=i.getValue();
						for(Students j: batch.getStudents()) {
							if(j.getId()== idofStudent) {
								j.setlName(nameofStudent);
								System.out.println("Successfully update\n");
								++count;
							}
						}
						if(count==0) {
							throw new NoElementFound("No student found, Sorry!\n");
						}
					}
				}
				break;
			case "4":
				System.out.println("Enter the name of batch: ");
				String nameofBatch111=sc.nextLine().trim();
				if(!batchs.containsKey(nameofBatch111)) {
					throw new NoElementFound("No batch found, Sorry!");
				}
				else {
					System.out.println("Enter the id of student: ");
					int idofStudent= sc.nextInt();
					System.out.println("Enter the new email of student: ");
					String emailofStudent= sc.nextLine().trim();
					for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
						int count=0;
						Batch batch=i.getValue();
						for(Students j: batch.getStudents()) {
							if(j.getId()== idofStudent) {
								j.setEmail(emailofStudent);
								System.out.println("Successfully update\n");
								++count;
							}
						}
						if(count==0) {
							throw new NoElementFound("No student found, Sorry!\n");
						}
					}
				}
				break;
			case "5":
				System.out.println("Enter the name of batch: ");
				String nameofBatch1111=sc.nextLine().trim();
				if(!batchs.containsKey(nameofBatch1111)) {
					throw new NoElementFound("No batch found, Sorry!");
				}
				else {
					System.out.println("Enter the id of student: ");
					int idofStudent= sc.nextInt();
					System.out.println("Enter the new mobile number of student: ");
					int newmobileNumber= sc.nextInt();
					for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
						int count=0;
						Batch batch=i.getValue();
						for(Students j: batch.getStudents()) {
							if(j.getId()== idofStudent) {
								j.setMobile(newmobileNumber);
								System.out.println("Successfully update\n");
								++count;
							}
						}
						if(count==0) {
							throw new NoElementFound("No student found, Sorry!\n");
						}
					}
				}
				break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/*Student part*/
	
	public static void studentLogIn(Scanner sc, HashMap<Integer,Courses> courses,HashMap<String, Batch> batchs,ArrayList<Students> students,ArrayList<Students> users ) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HashMap<Integer,Courses> courses= FileExists.course();
		HashMap<String, Batch> batchs=FileExists.batch();
		ArrayList<Students> students = FileExists.student();
		ArrayList<Students> users = FileExists.user();
		try {
			Scanner sc = new Scanner (System.in);
			String option ="";
			do {
				System.out.println("++++++++++++++++++++++++++++\nWelcom to Eductory Institute\n++++++++++++++++++++++++++++");
				System.out.println("\nPlease Select any option below:\n"
						+ "1(Admin LogIn)\n"
						+ "2(Student LogIn)\n"
						+ "3(Student SignUp)\n"
						+ "0(Exit)");
				option=sc.nextLine();
				switch(option){
					case "1":
						adminFunctionality(sc,courses,batchs,students);
						break;
					case "2":
						studentLogIn(sc,courses, batchs, students,users);
						break;
					case "3":
						System.out.println("Signup Syccessful as a student");
						break;
					case "0" :
						System.out.println("Thanks for visiting my application\n========================\n");
						break;
					case "" :
						System.out.println("You Haven't chosen any option\n========================\n");
						break;
					default:
						System.out.println("Sorry Wrong Input\n========================\n");
				}
			}while(!option.equals("0"));
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			ObjectOutputStream cop = new ObjectOutputStream(new FileOutputStream("course.ser"));
			cop.writeObject(courses);
			ObjectOutputStream bop = new ObjectOutputStream(new FileOutputStream("batch.ser"));
			bop.writeObject(batchs);
			ObjectOutputStream sop = new ObjectOutputStream(new FileOutputStream("student.ser"));
			sop.writeObject(students);
			ObjectOutputStream uop = new ObjectOutputStream(new FileOutputStream("user.ser"));
			uop.writeObject(users);
		}
	}
}
