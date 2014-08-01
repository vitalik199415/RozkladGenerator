package tools;

import java.util.ArrayList;

public class DayOfWeek {

	public static final int MAX_SUBJ_FOR_DAY = 6;
	
	private ArrayList<OneSubject> subjects;

	public DayOfWeek() {
		this.subjects = new ArrayList<>(MAX_SUBJ_FOR_DAY);
	}
	
	public DayOfWeek(int countSubj){
		this.subjects = new ArrayList<>(countSubj);		
	}
	
	public void addSubj(OneSubject subj){
		this.subjects.add(subj);
	}
	
	public ArrayList<OneSubject> getSubjects() {
		return subjects;
	}

	
	
}
