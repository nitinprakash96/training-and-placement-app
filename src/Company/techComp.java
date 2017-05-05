package Company;

import java.util.ArrayList;

public class techComp extends Company{
	private ArrayList<String> skill=new ArrayList<String>();
	
	public void setSkill(String skill){
		this.skill.add(skill);
	}
	public ArrayList<String> getSkill(){
		return skill;
	}
}
