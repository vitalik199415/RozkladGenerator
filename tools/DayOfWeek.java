package tools;

import java.util.ArrayList;

public class DayOfWeek {
	
	private ArrayList<OneSubject> subjects;

	public DayOfWeek(DayOfWeek dOf){
		this(dOf.getSubjects().size());
		this.subjects.addAll(dOf.getSubjects());
	}
	
	public DayOfWeek() {
		this(Config.MAX_SUBJ_FOR_DAY);
	}
	
	public DayOfWeek(int countSubj) {
		if (countSubj > Config.MAX_SUBJ_FOR_DAY)
			throw new Error("Неможливо створити DayOfWeek з кількістю пар > "+countSubj);
		
		this.subjects = new ArrayList<>(countSubj);		
	}
	
	public void addSubj(OneSubject subj){
		this.subjects.add(subj);
	}
	
	public ArrayList<OneSubject> getSubjects() {
		return subjects;
	}

	@Override
	public String toString() {
		String result = "";
		for (OneSubject subj:this.subjects) {
			result += subj.toString() + "\n";
		}
		
		return result;
		
	}

	
}
