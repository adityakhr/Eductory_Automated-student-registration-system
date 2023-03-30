package Eductory;

public class Students {
	private int  id=RandomIdGenerator.generateId();
	private String fName;
	private String lName;
	private String email;
	private String address;
	private long mobile;
	private String password;
	
	public Students(String fName,String lName,String email,long mobile) {
		this.fName=  fName;
		this.lName= lName;
		this.email=email;
		this.mobile=mobile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Students(String fName,String lName,String email,String address,long mobile,String password) {
		this.fName=  fName;
		this.lName= lName;
		this.email=email;
		this.address=address;
		this.mobile=mobile;
		this.password=password;
	}
	@Override
	public String toString() {
		return "Id:- "+this.id+"\nName:- "+this.fName+"\nLast Name:- "+this.lName+"\nEmail:- "+this.email+"Address:- "+this.address+"Mobile Number:- "+this.mobile;
	}
	
	
}
