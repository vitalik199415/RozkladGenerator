package tools;

public class Week {

	private DayOfWeek mon;
	private DayOfWeek tue;
	private DayOfWeek wed;
	private DayOfWeek thu;
	private DayOfWeek fri;
	
	public Week(Week week){
		this.mon = new DayOfWeek(week.getMon());
		this.tue = new DayOfWeek(week.getTue());
		this.wed = new DayOfWeek(week.getWed());
		this.thu = new DayOfWeek(week.getThu());
		this.fri = new DayOfWeek(week.getFri()); 
	}
	
	public Week(){
		this.mon = new DayOfWeek();
		this.tue = new DayOfWeek();
		this.wed = new DayOfWeek();
		this.thu = new DayOfWeek();
		this.fri = new DayOfWeek();
	}	
	
	public Week(int SubjForDay){
		this.mon = new DayOfWeek(SubjForDay);
		this.tue = new DayOfWeek(SubjForDay);
		this.wed = new DayOfWeek(SubjForDay);
		this.thu = new DayOfWeek(SubjForDay);
		this.fri = new DayOfWeek(SubjForDay);
	}
	
	public Week(int _monSubj, int _tueSubj, int _wedSubj, int _thuSubj, int _friSubj){
		this.mon = new DayOfWeek(_monSubj);
		this.tue = new DayOfWeek(_tueSubj);
		this.wed = new DayOfWeek(_wedSubj);
		this.thu = new DayOfWeek(_thuSubj);
		this.fri = new DayOfWeek(_friSubj);
	}

	public DayOfWeek getDayById(int ident){
		if (ident > 5 || ident < 1)
			throw new Error("Âêàçàíî íåâ³ğíèé ³äåíòèô³êàòîğ äíÿ");
		DayOfWeek someDay = null;
		switch (ident){
		case 1:{someDay = this.getMon(); break;} 
		case 2:{someDay = this.getTue(); break;} 
		case 3:{someDay = this.getWed(); break;} 
		case 4:{someDay = this.getThu(); break;} 
		case 5:{someDay = this.getFri(); break;} 
		} 
		return someDay; 
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

	@Override
	public String toString() {
		String result = "";
		result += "Ïîíåä³ëîê\n" + mon.toString();
		result += "Â³âòîğîê\n" + tue.toString();
		result += "Ñåğåäà\n" + wed.toString();
		result += "×åòâåğ\n" + thu.toString();
		result += "Ï'ÿòíèöÿ\n" + fri.toString();
		return result;
	}

	
	
}
