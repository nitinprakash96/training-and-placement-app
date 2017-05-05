package Company;

public class Company {
	private String name;
	private int id;
	private String password;
	private int Cpackage;
	private String field;
	private float pointer;
	private int count=0;
	private int Sid;
	public boolean valid;
	public static boolean lflag = true;
	public static boolean rflag = true;
	
	public void setName(String Cname){
		this.name=Cname;
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
	public void setid(int Cid){
		this.id=Cid;
	}
	public int getid(){
		return id;
	}
	public void setPackage(int Cpackage){
		this.Cpackage=Cpackage;
	}
	public int getPackage(){
		return Cpackage;
	}
	public void setCount(int count){
		this.count=count;
	}
	public int getCount(){
		return count;
	}
	public void setField(String Cname){
		this.field=Cname;
	}
	public String getField(){
		return field;
	}
	public float getPointer(){
		return pointer;
	}
	public void setPointer(float f){
		pointer = f;
	}
	public void setSid(int id){
		this.Sid=id;
	}
	public int getSid(){
		return Sid;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean newValid) {
		this.valid = newValid;
	}
	
}
