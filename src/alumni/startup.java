package alumni;

public class startup extends alumni{
	private String topic;
	private String stName;
	private int stipend;
	private int vacancy;
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String name) {
		this.stName = name;
	}
	public int getStipend() {
		return stipend;
	}
	public void setStipend(int stipend) {
		this.stipend = stipend;
	}
	public int getvacancy() {
		return vacancy;
	}
	public void setvacancy(int v) {
		this.vacancy = v;
	}
}
