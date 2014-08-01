package pojo;

import java.util.ArrayList;

public class Group {

	private int 	id;
	private int 	countStud;
	
	private ArrayList<Integer> 	subjectsTaught = new ArrayList<Integer>();  // id предметів що викладаються
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCountStud() {
		return countStud;
	}
	public void setCountStud(int count_stud) {
		this.countStud = count_stud;
	}
	public ArrayList<Integer> getSubjectsTaught() {
		return subjectsTaught;
	}
	public void setSubjectsTaught(ArrayList<Integer> subjectsTaught) {
		this.subjectsTaught = subjectsTaught;
	}

}
