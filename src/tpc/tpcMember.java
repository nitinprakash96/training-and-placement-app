package tpc;

public class tpcMember {
	public static int count=0;
	private int id;
	private String name;
	private String email;
	private String password;
	private String freeSlot;
	private int year;
	public boolean valid;
	public static boolean lflag = true;
	public static boolean rflag = true;
	public static boolean dflag = true;
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setId(int Cid){
		this.id=Cid;
	}
	public int getId(){
		return id;
	}
	public void setFreeSlot(String slot){
		this.freeSlot=slot;
	}
	public String getFreeSlot(){
		return freeSlot;
	}
	public void setYear(int year){
		this.year=year;
	}
	public int getYear(){
		return year;
	}
	public void setPass(String pass){
		this.password=pass;
	}
	public String getPass(){
		return password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean newValid) {
		this.valid = newValid;
	}
}


