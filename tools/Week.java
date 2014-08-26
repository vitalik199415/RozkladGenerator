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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fri == null) ? 0 : fri.hashCode());
		result = prime * result + ((mon == null) ? 0 : mon.hashCode());
		result = prime * result + ((thu == null) ? 0 : thu.hashCode());
		result = prime * result + ((tue == null) ? 0 : tue.hashCode());
		result = prime * result + ((wed == null) ? 0 : wed.hashCode());
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
		Week other = (Week) obj;
		if (fri == null) {
			if (other.fri != null)
				return false;
		} else if (!fri.equals(other.fri))
			return false;
		if (mon == null) {
			if (other.mon != null)
				return false;
		} else if (!mon.equals(other.mon))
			return false;
		if (thu == null) {
			if (other.thu != null)
				return false;
		} else if (!thu.equals(other.thu))
			return false;
		if (tue == null) {
			if (other.tue != null)
				return false;
		} else if (!tue.equals(other.tue))
			return false;
		if (wed == null) {
			if (other.wed != null)
				return false;
		} else if (!wed.equals(other.wed))
			return false;
		return true;
	}

	
	
}
