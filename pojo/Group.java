package pojo;

import java.util.ArrayList;

public class Group {

	private int 	id;
	private String 	name;
	private int 	countStud;
	
	private ArrayList<Integer> 	subjectsTaught = new ArrayList<Integer>();  // предмети що викладаються
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCountStud() {
		return countStud;
	}
	public void setCountStud(int count_stud) {
		this.countStud = count_stud;
	}
	
	@Override
	public String toString() {
		String temp = this.id + "\t" + this.name+ "\t" +this.countStud + "\t{";
		for(int row: this.subjectsTaught){
			temp += row + ", ";
		}
		temp += "}";
		return temp;
	}

	public ArrayList<Integer> getSubjectsTaught() {
		return subjectsTaught;
	}
	public void setSubjectsTaught(ArrayList<Integer> subjectsTaught) {
		this.subjectsTaught = subjectsTaught;
	}

}
