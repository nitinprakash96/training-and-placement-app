package alumni;

public class alumni {
	private int id;
	private String name;
	private String batch;
	private String field;
	private String work;
	private String password;
	public static int count = 0 ;
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
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String pass) {
		this.password = pass;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean newValid) {
		this.valid = newValid;
	}
}
