package tools;

public class TimeTable {
	
	private Week weekA;
	private Week weekB;
	
	public TimeTable(){
		weekA = new Week();
		weekB = new Week();
	}
	
	public TimeTable(int subjForDay) {
		weekA = new Week(subjForDay);
		weekB = new Week(subjForDay);
	}
	
	public TimeTable(int _monSubj, int _tueSubj, int _wedSubj, int _thuSubj, int _friSubj){
		weekA = new Week(_monSubj, _tueSubj, _wedSubj, _thuSubj, _friSubj);
		weekB = new Week(_monSubj, _tueSubj, _wedSubj, _thuSubj, _friSubj);
	}
	
	public Week getWeekA() {
		return weekA;
	}
	public void setWeekA(Week weekA) {
		this.weekA = new  Week(weekA);
	}
	public Week getWeekB() {
		return weekB;
	}
	public void setWeekB(Week weekB) {
		this.weekB = new Week(weekB);
	}
	

	@Override
	public String toString() {
		String result = "Тиждень А\n----------------------------------------------------------\n";
		result += weekA.toString();
		result += "Тиждень B\n----------------------------------------------------------\n";
		result += weekB.toString()+ "\n";
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
		TimeTable other = (TimeTable) obj;
		if (weekA == null) {
			if (other.weekA != null)
				return false;
		} else if (!weekA.equals(other.weekA))
			return false;
		if (weekB == null) {
			if (other.weekB != null)
				return false;
		} else if (!weekB.equals(other.weekB))
			return false;
		return true;
	}
}
