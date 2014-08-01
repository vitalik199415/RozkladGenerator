package tools;

import java.util.ArrayList;

public class DayOfWeek {

	public static final int MAX_SUBJ_FOR_DAY = 6;
	
	private ArrayList<OneSubject> subjects;

	public DayOfWeek() {
		this(MAX_SUBJ_FOR_DAY);
	}
	
	public DayOfWeek(int countSubj) {
		if (countSubj > MAX_SUBJ_FOR_DAY)
			throw new Error("��������� �������� DayOfWeek � ������� ��� > "+countSubj);
		
		this.subjects = new ArrayList<>(countSubj);		
	}
	
	public void addSubj(OneSubject subj){
		this.subjects.add(subj);
	}
	
	public ArrayList<OneSubject> getSubjects() {
		return subjects;
	}

}
