package tools;

import java.util.ArrayList;

public class TimeTable {

	public static final int COUNT_SUBJ_FOR_DAY = 6;
	
	private ArrayList<Byte> mon = new ArrayList<Byte>(COUNT_SUBJ_FOR_DAY);
	private ArrayList<Byte> tue = new ArrayList<Byte>(COUNT_SUBJ_FOR_DAY);
	private ArrayList<Byte> wed = new ArrayList<Byte>(COUNT_SUBJ_FOR_DAY);
	private ArrayList<Byte> thu = new ArrayList<Byte>(COUNT_SUBJ_FOR_DAY);
	private ArrayList<Byte> fri = new ArrayList<Byte>(COUNT_SUBJ_FOR_DAY);
	
	public ArrayList<Byte> getMon() {
		return mon;
	}
	public void setMon(ArrayList<Byte> mon) {
		this.mon = mon;
	}
	public ArrayList<Byte> getTue() {
		return tue;
	}
	public void setTue(ArrayList<Byte> tue) {
		this.tue = tue;
	}
	public ArrayList<Byte> getWed() {
		return wed;
	}
	public void setWed(ArrayList<Byte> wed) {
		this.wed = wed;
	}
	public ArrayList<Byte> getThu() {
		return thu;
	}
	public void setThu(ArrayList<Byte> thu) {
		this.thu = thu;
	}
	public ArrayList<Byte> getFri() {
		return fri;
	}
	public void setFri(ArrayList<Byte> fri) {
		this.fri = fri;
	}

}
