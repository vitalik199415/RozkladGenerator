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
			throw new Error("��������� �������� DayOfWeek � ������� ��� > "+countSubj);
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subjects == null) ? 0 : subjects.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayOfWeek other = (DayOfWeek) obj;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		return true;
	}
	
	

	
}
