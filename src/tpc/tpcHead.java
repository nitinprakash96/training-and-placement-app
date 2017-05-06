package tpc;

public class tpcHead {
	private String name;
	private String password;
	public boolean valid;
	public static boolean lflag = true;
	public static boolean dflag = true;
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setPass(String pass){
		this.password=pass;
	}
	public String getPass(){
		return password;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean newValid) {
		this.valid = newValid;
	}
}
