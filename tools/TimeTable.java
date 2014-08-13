package tools;

public class TimeTable {
	
	private Week weekA;
	private Week weekB;
	
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
		this.weekA = weekA;
	}
	public Week getWeekB() {
		return weekB;
	}
	public void setWeekB(Week weekB) {
		this.weekB = weekB;
	}

	@Override
	public String toString() {
		return weekA.toString() + "___________________________\n" + weekB.toString(); 
	}
}
