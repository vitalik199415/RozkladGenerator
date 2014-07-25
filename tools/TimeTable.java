package tools;

public class TimeTable {

	private DayOfWeek mon;
	private DayOfWeek tue;
	private DayOfWeek wed;
	private DayOfWeek thu;
	private DayOfWeek fri;
	
	public TimeTable(){
		this.mon = new DayOfWeek();
		this.tue = new DayOfWeek();
		this.wed = new DayOfWeek();
		this.thu = new DayOfWeek();
		this.fri = new DayOfWeek();
	}	
	
	public TimeTable(int SubjForDay){
		this.mon = new DayOfWeek(SubjForDay);
		this.tue = new DayOfWeek(SubjForDay);
		this.wed = new DayOfWeek(SubjForDay);
		this.thu = new DayOfWeek(SubjForDay);
		this.fri = new DayOfWeek(SubjForDay);
	}
	
	public TimeTable(int _monSubj, int _tueSubj, int _wedSubj, int _thuSubj, int _friSubj){
		this.mon = new DayOfWeek(_monSubj);
		this.tue = new DayOfWeek(_tueSubj);
		this.wed = new DayOfWeek(_wedSubj);
		this.thu = new DayOfWeek(_thuSubj);
		this.fri = new DayOfWeek(_friSubj);
	}

	public DayOfWeek getMon() {
		return mon;
	}

	public DayOfWeek getTue() {
		return tue;
	}

	public DayOfWeek getWed() {
		return wed;
	}

	public DayOfWeek getThu() {
		return thu;
	}

	public DayOfWeek getFri() {
		return fri;
	}

	
}
