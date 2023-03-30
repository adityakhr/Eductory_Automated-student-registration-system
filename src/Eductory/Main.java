package Eductory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
	public static void adminLogIn(Scanner sc) throws InvalidCredentials{
		System.out.println("Please Enter Your Username:");
		String uName=sc.nextLine().trim();
		System.out.println("Please Enter Your Password:");
		String passWord=sc.nextLine().trim();
		if(!uName.equals("admin") || !passWord.equals("admin")) {
			throw new InvalidCredentials("Invalid Credentials\n==============\n");
		}
	}
	public static void adminFunctionality(Scanner sc , HashMap<Integer,Courses> courses,HashMap<String, Batch> batchs, ArrayList<Students> studnets){
		try {
			adminLogIn(sc);
			System.out.println("=========================\nWelcom back ADITYA great to have you here again\n=========================");
			String k="";
			do {
				System.out.println("\nPlease choose the option below:\n"
						+ "1(Add new course)\n"
						+ "2(Show all course)\n"
						+ "3(Upadate details course)\n"
						+ "4(Create a batch under course)\n"
						+ "5(Search a batch)\n"
						+ "6(View student details)\n"
						+ "7(View student list of batch)\n"
						+ "8(Update Details of batch)\n"
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
					adminCreatbatch(sc,batchs);
					break;
				case "5":
					adminSearchBaatch(sc, batchs);
					break;
				case "6":
					adminViewStudentsDetails(studnets);
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
					System.out.println("Sorry Wrong Input\n========================\n");
				}
			}while(!k.equals("0"));
			
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	public static void adminPrintCourses(HashMap<Integer,Courses> courses) {
		for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
			System.out.println("("+i.getKey()+") -> "+i.getValue());
		}
	}
	public static HashMap<Integer, Courses> adminAddCourse(Scanner sc , HashMap<Integer,Courses> courses){
		try {
			System.out.println("Enter the course name");
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
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return courses;
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
			String selection=sc.nextLine().trim();
			try {
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
	public static HashMap<String, Batch> adminCreatbatch(Scanner sc , HashMap<String, Batch> batchs) throws mobileNumber {
		System.out.println("Enter the Batch Name: ");
		String name=sc.nextLine().trim();
		System.out.println("Student details:\n first name of  student: ");
		String fName=sc.nextLine().trim();
		System.out.println("last name of  student: ");
		String lName=sc.nextLine().trim();
		System.out.println("email of  student: ");
		String email=sc.nextLine().trim();
		System.out.println("mobile number of  student: ");
		long mobile=sc.nextLong();
		sc.nextLine();
		String str="";
		str+=mobile;
		Students student=null;
		if(str.length()==10) {
			student= new Students (fName, lName, email , mobile);
		}else {
			throw new mobileNumber("Mobile Number is not valid");
		}
		if(batchs.containsKey(name)) {
			Batch b = batchs.get(name);
			b.getStudents().add(student);
		}
		else { 
			System.out.println("Starting Date(dd/MM/yyyy): ");
			String sDate=sc.nextLine().trim();
			System.out.println("Ending Date(dd/MM/yyyy): ");
			String eDate=sc.nextLine().trim();
			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate sDateFinal= LocalDate.parse(sDate,dtf);
			LocalDate eDateFinal= LocalDate.parse(eDate,dtf);
			Batch b= new Batch(sDateFinal,eDateFinal);
			b.getStudents().add(student);
			batchs.put(name, b);
		}
		return batchs;
	}
	public static void adminSearchBaatch(Scanner sc ,HashMap<String , Batch> batchs ){
		System.out.println("Select any option below:\n"
				+ "1(By name)\n"
				+ "2(By Starting date)\n"
				+ "3(By Ending date)\n");
		String selection=sc.nextLine().trim();
		switch(selection) {
		case "1":
			System.out.println("Enter the name of batch: ");
			String nameOfBatch=sc.nextLine().trim();
			for(HashMap.Entry<String,Batch>i: batchs.entrySet()) {
				if(i.getKey().equals(nameOfBatch)) {
					System.out.println(i.getKey()+" -> "+i.getValue());
				}
			}
			break;
		case "2":
			System.out.println("Enter the starting date of batch(dd/MM/yyyy): ");
			String sDate=sc.nextLine().trim();
			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate sDateFinal= LocalDate.parse(sDate,dtf);
			for(HashMap.Entry<String,Batch>i: batchs.entrySet()) {
				if(i.getValue().startDate.isEqual(sDateFinal)) {
					System.out.println(i.getKey()+" -> "+i.getValue());
				}
			}
			break;
		case "3":
			System.out.println("Enter the starting date of batch(dd/MM/yyyy): ");
			String eDate=sc.nextLine().trim();
			DateTimeFormatter dtf1= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate eDateFinal= LocalDate.parse(eDate,dtf1);
			for(HashMap.Entry<String,Batch>i: batchs.entrySet()) {
				if(i.getValue().endDate.isEqual(eDateFinal)) {
					System.out.println(i.getKey()+" -> "+i.getValue());
				}
			}
		default:
			System.out.println("Wrong selection!\n");
			break;
		}
	}
	public static void adminViewStudentsDetails(ArrayList<Students> students) {
		for(Students i: students) {
			System.out.println(i);
		}
	}
	public static void adminViewListOfStudentsOfBAtch(Scanner sc, HashMap<String,Batch> batchs){
		System.out.println("Enter the name of the batch: ");
		String nameOfBatch=sc.nextLine().trim();
		for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
			if(i.getKey().equals(nameOfBatch)) {
				System.out.println(i.getValue());
			}
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
				if(!batchs.containsValue(nameofBatch)) {
					throw new NoElementFound("No batch found, Sorry!");
				}
				else {
					System.out.println("Enter the new name: ");
					String newName= sc.nextLine().trim();
					for(HashMap.Entry<String,Batch> i: batchs.entrySet()) {
						if(i.getKey().equals(nameofBatch)) {
							Batch batch=i.getValue();
							batchs.put(newName, batch);
							batchs.remove(nameofBatch);
						}
					}
				}
				break;
			case "2":
				System.out.println("Enter the name of batch: ");
				String nameofBatch1=sc.nextLine().trim();
				if(!batchs.containsValue(nameofBatch1)) {
					throw new NoElementFound("No batch found, Sorry!");
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
				if(!batchs.containsValue(nameofBatch11)) {
					throw new NoElementFound("No batch found, Sorry!");
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
							throw new NoElementFound("No student found, Sorry!");
						}
					}
				}
				break;
			case "4":
				System.out.println("Enter the name of batch: ");
				String nameofBatch111=sc.nextLine().trim();
				if(!batchs.containsValue(nameofBatch111)) {
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
							throw new NoElementFound("No student found, Sorry!");
						}
					}
				}
				break;
			case "5":
				System.out.println("Enter the name of batch: ");
				String nameofBatch1111=sc.nextLine().trim();
				if(!batchs.containsValue(nameofBatch1111)) {
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
							throw new NoElementFound("No student found, Sorry!");
						}
					}
				}
				break;
		}
	}
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HashMap<Integer,Courses> courses= FileExists.course();
		HashMap<String, Batch> batchs=FileExists.batch();
		ArrayList<Students> students = FileExists.students();
		try {
			Scanner sc = new Scanner (System.in);
			String option ="";
			do {
				System.out.println("Please choose the option below:\n"
						+ "1(Admin LogIn)\n"
						+ "2(Student LogIn)\n"
						+ "3(Student SignUp\n"
						+"0(Exit)");
				option=sc.nextLine();
				switch(option){
					case "1":
						adminFunctionality(sc,courses,batchs,students);
						break;
					case "2":
						System.out.println("Login Syccessful as a student");
						break;
					case "3":
						System.out.println("Login Syccessful as a student");
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
			ObjectOutputStream cop = new ObjectOutputStream(new FileOutputStream("courseFile.txt"));
			cop.writeObject(courses);
			
			ObjectOutputStream bop = new ObjectOutputStream(new FileOutputStream("batchFile.txt"));
			cop.writeObject(batchs);
			
			ObjectOutputStream sop = new ObjectOutputStream(new FileOutputStream("studentFile.txt"));
			cop.writeObject(students);
		}
	}
}
