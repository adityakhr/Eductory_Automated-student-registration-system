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
import java.util.Map.Entry;
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
			throw new InvalidCredentials("\n==============\nInvalid Credentials\n==============\n");
		}
	}
	public static void adminFunctionality(Scanner sc , HashMap<Integer,Courses> courses, ArrayList<Students> students){
		try {
			adminLogIn(sc);
			System.out.println("=====================\nWelcome back Admin\n=====================");
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
					adminCreatBatch(sc,courses,students);
					break;
				case "5":
					adminSearchBatch(sc,courses);
					break;
				case "6":
					adminViewStudentsDetails(students);
					break;
				case "7":
					adminViewListOfStudentsOfBAtch(sc, courses);
					break;
				case "8":
					adminUpdateDetailsOfBatch(sc,courses);
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
	
	public static void adminAddCourse(Scanner sc , HashMap<Integer,Courses> courses){
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
			System.out.println("Course is added successfully\n");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void adminPrintCourses(HashMap<Integer,Courses> courses) {
		if(courses.size()>0) {
			for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
				System.out.println("{"+i.getKey()+"} -> [\nName-: "+i.getValue().getName()+"\nDuration-: "+i.getValue().getDurationInWeeks()+"\nFees-: "+i.getValue().getFees()+"\n]\n");
			}
		}else {
			System.out.println("No course found.");
		}
	}
	public static void adminUpdateDetailsOfCourse(Scanner sc , HashMap<Integer,Courses> courses) {
		
			try {
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
					switch(selection) {
					case "1":
						System.out.println("Enter the new name:");
						String name=sc.nextLine().trim();
						cou.setName(name);
						System.out.println("Name is updated successfully.");
						break;
					case "2":
						System.out.println("Enter the new duration:");
						int du=sc.nextInt();
						sc.nextLine();
						cou.setDurationInWeeks(du);
						System.out.println("Duration is updated successfully.");
						break;
					case "3":
						System.out.println("Enter the new fees for it:");
						int fee=sc.nextInt();
						sc.nextLine();
						cou.setFees(fee);
						System.out.println("Fees is updated successfully.");
						break;
					default:
						System.out.println("Wrong selection!\n");
						break;
					}
				}
				else {
					System.out.println("No course available with that id, Sorry!");
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
	}
		
	public static void adminCreatBatch(Scanner sc , HashMap<Integer,Courses> courses,ArrayList<Students>students) {
		try {
			System.out.println("Enter the course id: ");
			int idOfCourse=sc.nextInt();
			sc.nextLine();
			if(courses.containsKey(idOfCourse)) {
				System.out.println("Enter the id of  student to be added: ");
				int id=sc.nextInt();
				sc.nextLine();
				int count=0;
				for(Students i: students) {
					if(i.getId()==id) {
						Students s=i;
						Courses c= courses.get(idOfCourse);
						HashMap<String, Batch> b =c.getBatches();
						System.out.println("Enter the batch name: ");
						String batchName= sc.nextLine().trim();
						if(b.containsKey(batchName)) {
							int ae=0;
							Batch k= b.get(batchName);
							ArrayList<Students>studs=k.getStudents();
							for(Students m: studs) {
								if(m.getId()==id) {
									++ae;
									System.out.println("Already exists");
								}
							}
							if(ae==0) {
								k.getStudents().add(s);
								++count;
								System.out.println("successfully created.");
							}
						}
						else { 
							System.out.println("Starting Date(dd/MM/yyyy): ");
							String sDate=sc.nextLine().trim();
							System.out.println("Ending Date(dd/MM/yyyy): ");
							String eDate=sc.nextLine().trim();
							DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
							LocalDate sDateFinal= LocalDate.parse(sDate,dtf);
							LocalDate eDateFinal= LocalDate.parse(eDate,dtf);
							Batch b1= new Batch(sDateFinal,eDateFinal);
							for(Students i1: students) {
								if(i1.getId()==id) {
									Students s1=i1;
									Courses c1= courses.get(idOfCourse);
									HashMap<String, Batch> batchs =c1.getBatches();
									b1.getStudents().add(s1);
									batchs.put(batchName, b1);
									++count;
								}
							}
							System.out.println("Successfully created.");
						}
					}
				}
				if(count==0) {
					System.out.println("No student found with id.");
				}
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void adminSearchBatch(Scanner sc , HashMap<Integer,Courses> courses){
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
				for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
					HashMap<String,Batch> batch=i.getValue().getBatches();
					for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
						if(j.getKey().equals(nameOfBatch)) {
							System.out.println(j.getValue());
							++count;
						}
					}
//					
				}
				if(count==0) {
					System.out.println("No batch found.\n");
				}
				break;
			case "2":
				System.out.println("Enter the starting date of batch(dd/MM/yyyy): ");
				String sDate=sc.nextLine().trim();
				DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
				LocalDate sDateFinal= LocalDate.parse(sDate,dtf);
				int count1=0;
				for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
					HashMap<String,Batch> batch=i.getValue().getBatches();
					for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
						if(j.getValue().getStartDate().equals(sDateFinal)) {
							System.out.println(j.getValue());
							++count1;
						}
					}
//					
				}
				if(count1==0) {
					System.out.println("No batch found.\n");
				}
				break;
			case "3":
				System.out.println("Enter the Ending date of batch(dd/MM/yyyy): ");
				String eDate=sc.nextLine().trim();
				DateTimeFormatter dtf1= DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
				LocalDate eDateFinal= LocalDate.parse(eDate,dtf1);
				int count2=0;
				for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
					HashMap<String,Batch> batch=i.getValue().getBatches();
					for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
						if(j.getValue().getEndDate().equals(eDateFinal)) {
							System.out.println(j.getValue());
							++count2;
						}
					}
//					
				}
				if(count2==0) {
					System.out.println("No batch found.\n");
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
	public static void adminViewStudentsDetails(ArrayList<Students> students)  {
		if(students.size()==0) {
			System.out.println("No student listed found.\n");
		}else {
			for(Students i: students) {
				System.out.println(i);
			}
		}
		
	}
	public static void adminViewListOfStudentsOfBAtch(Scanner sc,  HashMap<Integer,Courses> courses) {
		try {
			System.out.println("Enter the name of the batch: ");
			String nameOfBatch=sc.nextLine().trim();
			int count=0;
			for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
				HashMap<String,Batch> batch=i.getValue().getBatches();
				for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
					if(j.getKey().equals(nameOfBatch)) {
						System.out.println(j.getValue());
						++count;
					}
				}
			}
			if(count==0){
				System.out.println("No batch found.");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void adminUpdateDetailsOfBatch(Scanner sc , HashMap<Integer,Courses> courses) throws NoElementFound {
		try {
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
					String nameOfBatch=sc.nextLine().trim();
					int count=0;
					for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
						HashMap<String,Batch> batch=i.getValue().getBatches();
						for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
							if(j.getKey().equals(nameOfBatch)) {
								System.out.println("Enter the new name: ");
								String newName= sc.nextLine().trim();
								Batch subBatch=j.getValue();
								batch.remove(j.getKey());
								batch.put(newName, subBatch);
								System.out.println("Name is updated successfully");
								++count;
							}
						}
					}
					if(count==0){
						System.out.println("No batch found.");
					}
					break;
				case "2":
					System.out.println("Enter the name of batch: ");
					String nameOfBatch1=sc.nextLine().trim();
					int count1=0;
					for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
						HashMap<String,Batch> batch=i.getValue().getBatches();
						for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
							if(j.getKey().equals(nameOfBatch1)) {
								Batch b=j.getValue();
								ArrayList<Students> students=b.getStudents();
								System.out.println("Enter the id of Student");
								int id=sc.nextInt();
								sc.nextLine();
								for(Students m: students) {
									if(m.getId()==id) {
										Students s=m;
										System.out.println("Enter the new first name: ");
										String newName=sc.nextLine();
										s.setfName(newName);
										System.out.println("First name is updated successfully");
										++count1;
									}
								}
							}
						}
					}
					if(count1==0) {
						System.out.println("No batch or student found, Sorry!");
					}
					break;
				case "3":
					System.out.println("Enter the name of batch: ");
					String nameOfBatch11=sc.nextLine().trim();
					int count11=0;
					for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
						HashMap<String,Batch> batch=i.getValue().getBatches();
						for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
							if(j.getKey().equals(nameOfBatch11)) {
								Batch b=j.getValue();
								ArrayList<Students> students=b.getStudents();
								System.out.println("Enter the id of Student");
								int id=sc.nextInt();
								sc.nextLine();
								for(Students m: students) {
									if(m.getId()==id) {
										Students s=m;
										System.out.println("Enter the new last name: ");
										String newName=sc.nextLine();
										s.setlName(newName);
										System.out.println("Last name is updated successfully");
										++count11;
									}
								}
							}
						}
					}
					if(count11==0) {
						System.out.println("No batch or student found, Sorry!");
					}
					break;
				case "4":
					System.out.println("Enter the name of batch: ");
					String nameOfBatch111=sc.nextLine().trim();
					int count111=0;
					for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
						HashMap<String,Batch> batch=i.getValue().getBatches();
						for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
							if(j.getKey().equals(nameOfBatch111)) {
								Batch b=j.getValue();
								ArrayList<Students> students=b.getStudents();
								System.out.println("Enter the id of Student");
								int id=sc.nextInt();
								sc.nextLine();
								for(Students m: students) {
									if(m.getId()==id) {
										Students s=m;
										System.out.println("Enter the new email: ");
										String newEmail=sc.nextLine();
										s.setEmail(newEmail);
										System.out.println("Email is updated successfully");
										++count111;
									}
								}
							}
						}
					}
					if(count111==0) {
						System.out.println("No batch or student found, Sorry!");
					}
					break;
				case "5":
					System.out.println("Enter the name of batch: ");
					String nameOfBatch1111=sc.nextLine().trim();
					int count1111=0;
					for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
						HashMap<String,Batch> batch=i.getValue().getBatches();
						for(HashMap.Entry<String,Batch> j: batch.entrySet()) {
							if(j.getKey().equals(nameOfBatch1111)) {
								Batch b=j.getValue();
								ArrayList<Students> students=b.getStudents();
								System.out.println("Enter the id of Student");
								int id=sc.nextInt();
								sc.nextLine();
								for(Students m: students) {
									if(m.getId()==id) {
										Students s=m;
										System.out.println("Enter the new mobile: ");
										long mob=sc.nextLong();
										String str="";
										str+=mob;
										if(str.length()==10) {
											for(int i1=0 ; i1<str.length(); ++i1) {
												if(!Character.isDigit(str.charAt(i1))) {
													throw new mobileNumber("Mobile Number is not valid");
												}
											}
										}else {
											throw new mobileNumber("Mobile Number is not valid");
										}
										s.setMobile(mob);
										++count1111;
									}
								}
							}
						}
					}
					if(count1111==0) {
						System.out.println("No batch or student found, Sorry!");
					}
					break;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/*Student part*/
	
	public static void studentLogIn(Scanner sc, HashMap<Integer,Courses> courses,ArrayList<Students> students ) throws NoElementFound {
		try {
			System.out.println("Enter your email:");
			String uName=sc.nextLine().trim();
			System.out.println("Enter your password:");
			String pword= sc.nextLine().trim();
			int count=0;
			for(Students i:students) {
				if(i.getEmail().equals(uName) && i.getPassword().equals(pword)) {
					++count;
				}
			}
			if(count==0) {
				throw new NoElementFound("No user found!\n");
			}
			System.out.println("Login successful\n");
			String selection="";
			do {
				System.out.println("Please select any option below: \n"
						+ "1(Your details)\n"
						+ "2(Update your details)\n"
						+ "3(Change password)\n"
						+ "4(See course list)\n"
						+ "5(See batches)\n"
						+ "6(Enroll yourself)\n"
						+ "0(Logout)\n");
				selection=sc.nextLine().trim();
				switch(selection) {
					case "1":
						studentPrintProfile(students,uName);
						break;
					case "2":
						studentUpdateProfile(sc,students,uName);
						break;
					case "3":
						studentUpdatePassword(sc,students,uName);
						break;
					case "4":
						studentPrintCourses(courses);
						break;
					case "5":
						studentPrintbatches(courses);
						break;
					case "6":
						studentEnrollbatches(sc,courses,students,uName);
						break;
					case "0":
						System.out.println("Logging out....Thankyou\n");
						break;
					default:
						System.out.println("Wrong selection\n");
				}
			}while(!selection.equals("0"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void studentPrintProfile(ArrayList<Students> students,String uName) {
		for(Students i: students) {
			if(i.getEmail().equals(uName)) {
				System.out.println(i);
			}
		}
	}
	
	
	public static void studentUpdateProfile(Scanner sc,ArrayList<Students> students,String uName) throws mobileNumber{
		Students student=null;
		for(Students i: students) {
			if(i.getEmail().equals(uName)) {
				student=i;
			}
		}
		try {
			System.out.println("Please select the option below: \n"
					+ "1(First name)\n"
					+ "2(Last name)\n"
					+ "3(Email)\n"
					+ "4(Address)\n"
					+ "5(Mobile number)\n");
			String selection=sc.nextLine().trim();
			switch(selection) {
				case "1":
					System.out.println("Enter new first name: ");
					String fName=sc.nextLine().trim();
					student.setfName(fName);
					System.out.println("First name is updated");
					break;
				case "2":
					System.out.println("Enter new last name: ");
					String lName=sc.nextLine().trim();
					student.setlName(lName);
					System.out.println("Last name is updated");
					break;
				case "3":
					System.out.println("Enter new email name: ");
					String email=sc.nextLine().trim();
					student.setEmail(email);
					System.out.println("Email is updated");
					break;
				case "4":
					System.out.println("Enter new address name: ");
					String add=sc.nextLine().trim();
					student.setAddress(add);
					System.out.println("Address is updated");
					break;
				case "5":
					System.out.println("Enter new mobile number name: ");
					long mob=sc.nextLong();
					String str="";
					str+=mob;
					if(str.length()==10) {
						for(int i=0 ; i<str.length(); ++i) {
							if(!Character.isDigit(str.charAt(i))) {
								throw new mobileNumber("Mobile Number is not valid");
							}
						}
						System.out.println("Mobile number is updated");
					}else {
						throw new mobileNumber("Mobile Number is not valid");
					}
					student.setMobile(mob);
					break;
				default:
					System.out.println("Wring selection\n");
					break;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void studentUpdatePassword(Scanner sc ,ArrayList<Students> students,String uName) throws NotValidPassword {
		Students student=null;
		for(Students i: students) {
			if(i.getEmail().equals(uName)) {
				student=i;
			}
		}
		String pass= student.getPassword();
		System.out.println("Enter your old passowrd: ");
		String oldPass=sc.nextLine().trim();
		if(oldPass.equals(pass)) {
			System.out.println("Enter new password: ");
			String newPass=sc.nextLine().trim();
			int speCount=0;
			int numCount=0;
			for(int i=0 ; i<newPass.length(); ++i) {
				if(!Character.isLetter(newPass.charAt(i)) && !Character.isDigit(newPass.charAt(i)) ) {
					++speCount;
				}
				if(Character.isDigit(newPass.charAt(i)) ) {
					++numCount;
				}
			}
			if(numCount==0 || speCount==0) {
				throw new NotValidPassword("Not valid\n");
			}else {
				student.setPassword(newPass);
				System.out.println("Password has changed.");
			}
		}
	}
	
	
	
	
	public static void studentSignUp(Scanner sc ,ArrayList<Students> students ) throws mobileNumber,NotValidPassword,ExistsAlready{
		try {
			System.out.println("Student details:\nEnter the first name of  student: ");
			String fName=sc.nextLine().trim();
			System.out.println("last name of  student: ");
			String lName=sc.nextLine().trim();
			System.out.println("Enter the email of  student: ");
			String email=sc.nextLine().trim();
			System.out.println("Enter the address of  student: ");
			String add=sc.nextLine().trim();
			System.out.println("Enter the mobile number of  student: ");
			long mobile=sc.nextLong();
			sc.nextLine();
			String str="";
			str+=mobile;
			if(str.length()==10) {
				for(int i=0 ; i<str.length(); ++i) {
					if(!Character.isDigit(str.charAt(i))) {
						throw new mobileNumber("Mobile Number is not valid.\n");
					}
				}
			}else {
				throw new mobileNumber("Mobile Number is not valid.\n");
			}
			System.out.println("Enter the password of  student\n(should contains atleast 1 number and 1 special character): ");
			String pass=sc.nextLine().trim();
			int speCount=0;
			int numCount=0;
			for(int i=0 ; i<pass.length(); ++i) {
				if(!Character.isLetter(pass.charAt(i)) && !Character.isDigit(pass.charAt(i)) ) {
					++speCount;
				}
				if(Character.isDigit(pass.charAt(i)) ) {
					++numCount;
				}
			}
			if(numCount==0 || speCount==0) {
				throw new NotValidPassword("Not valid\n");
			}
			int count=0;
			for(Students i:students) {
				if(i.getEmail().equals(email)) {
					throw new ExistsAlready("Email already exists.\n");
				}
				if((i.getPassword().equals(pass)&& i.getfName().equals(fName)&& i.getlName().equals(lName)&& i.getAddress().equals(add)&&i.getMobile()==mobile)) {
					++count;
				}
			}
			if(count==0) {
				Students newS= new Students(fName, lName,email, add, mobile,pass);
				students.add(newS);
				System.out.println("Sign in successful, Please login now.");
				 
			}else {
				throw new ExistsAlready("Student already exists.\n");
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void studentPrintCourses(HashMap<Integer,Courses> courses) {
		if(courses.size()==0) {
			System.out.println("No course found.");
		}else {
			for(HashMap.Entry<Integer,Courses> i: courses.entrySet()) {
				System.out.println("{"+i.getKey()+"} -> [\nName-: "+i.getValue().getName()+"\nDuration-: "+i.getValue().getDurationInWeeks()+"\nFees-: "+i.getValue().getFees()+"\n]\n");
			}
		}
	}
	public static void studentPrintbatches(HashMap<Integer,Courses> courses) {
		if(courses.size()==0) {
			System.out.println("No course or batch found.");
		}else {
			int count=0;
			for(HashMap.Entry<Integer, Courses> i: courses.entrySet()) {
				System.out.println(i.getValue().getName()+"->"+i.getValue().getBatches());
				++count;
			}
			if(count==0) {
				System.out.println("No course or batch found.");
			}
		}
	}
	public static void studentEnrollbatches(Scanner sc,HashMap<Integer,Courses> courses,ArrayList<Students>students ,String uName) {
		try {
			Students s= null;
			for(Students i: students) {
				if(i.getEmail().equals(uName)) {
					s=i;
				}
			}
			System.out.println("Enter the course id in which you want to enroll: ");
			int id=sc.nextInt();
			sc.nextLine();
			int count=0;
			if(courses.containsKey(id)) {
				for(HashMap.Entry<Integer,Courses> i : courses.entrySet()) {
					if(i.getKey()==id) {
						System.out.println("Enter the name of batch:");
						String name=sc.nextLine().trim();
						HashMap<String,Batch>batch = i.getValue().getBatches();
						for(HashMap.Entry<String,Batch> j : batch.entrySet()) {
							if(j.getKey().equals(name)) {
								ArrayList<Students> studs=j.getValue().getStudents();
								int ae=0;
								for(Students k: students) {
									if(k.getId()==s.getId()) {
										++ae;
										System.out.println("Already exists.");
									}
								}
								if(ae==0) {
									j.getValue().getStudents().add(s);
									++count;
									System.out.println("You have registered yourself successfully.");
								}	
							}
						}
					}
				}
			}
			if(count==0) {
				System.out.println("No course or batch found.");
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*Main Method*/
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		HashMap<Integer,Courses> courses= FileExists.course();
		ArrayList<Students> students = FileExists.student();
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
						adminFunctionality(sc,courses,students);
						break;
					case "2":
						studentLogIn(sc,courses, students);
						break;
					case "3":
						studentSignUp(sc, students);
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
			ObjectOutputStream sop = new ObjectOutputStream(new FileOutputStream("student.ser"));
			sop.writeObject(students);
		}
	}
}
