package models;
import java.time.LocalDate;
import java.util.Date;
public class user {
	private int id;
	private String name;
	private String password;
	private Date regDate;
	private String email;
	private String field;
	private float Spointer;
	private int appliedComp;
	public boolean valid; 
	public static boolean lflag = true;
	public static boolean rflag = true;
	
	public void setid(int id){
		this.id = id;
	}
	public int getid(){
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String password) {
		this.password= password;
 	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRegDate(Date regDate){
		this.regDate=regDate;
	}
	public Date getRegDate(){
		return regDate;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean newValid) {
		this.valid = newValid;
	}
	public String getField() {
		return field;
	}
	public void setField(String name) {
		this.field = name;
	}
	public float getPointer(){
		return Spointer;
	}
	public void setPointer(float f){
		Spointer = f;
	}
	public void setCid(int Cid){
		this.appliedComp=Cid;
	}
	public int getCid(){
		return appliedComp;
	}
}

