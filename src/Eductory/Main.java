package Eductory;
import java.util.HashSet;
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
	public static void adminFunctionality(Scanner sc , HashSet<Courses> courses){
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
						+ "8(Show things about batch)\n"
						+ "0(Log out)\n");
				
				k=sc.nextLine();
				switch(k) {
				case "1":
					
					break;
				case "2":
					System.out.println("Showing all course");
					break;
				case "3":
					System.out.println("Upadatint details course");
					break;
				case "4":
					System.out.println("Creating a batch under course");
					break;
				case "5":
					System.out.println("Searching a batch");
					break;
				case "6":
					System.out.println("Viewing student details");
					break;
				case "7":
					System.out.println("Viewing student list of batch");
					break;
				case "8":
					System.out.println("Showing things about batch");
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
	public static void main(String[] args){
		HashSet<Courses> courses=null;
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
						adminFunctionality(sc,courses);
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
		
	}
}
