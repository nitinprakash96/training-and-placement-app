package alumni;

public class research extends alumni{
	private String topic;
	private String coreField;
	private int stipend;
	static int total;
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getcoreField() {
		return coreField;
	}
	public void setcoreField(String core) {
		this.coreField = core;
	}
	public void setStipend(int s){
		this.stipend = s;
	}
	public int getStipend(){
		return stipend;
	}
	
}
