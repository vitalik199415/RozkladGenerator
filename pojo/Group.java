package pojo;

import java.util.ArrayList;

public class Group {

	private int 	id;
	private int 	countStud;
	
	private ArrayList<Subject> 	subjectsTaught = new ArrayList<>();  // id предметів що викладаються
	
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
	public ArrayList<Subject> getSubjectsTaught() {
		return subjectsTaught;
	}
	public void setSubjectsTaught(ArrayList<Subject> subjectsTaught) {
		this.subjectsTaught = subjectsTaught;
	}

}
