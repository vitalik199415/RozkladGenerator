package pojo;

public class Subject{
	
	private int 	id;
	private int 	countHour; 
	private int 	hourInWeek;
	private int 	idTeach;
	private Boolean isLection;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCountHour() {
		return countHour;
	}
	public void setCountHour(int count_hour) {
		this.countHour = count_hour;
	}
	public Boolean getIsLection() {
		return isLection;
	}
	public void setIsLection(Boolean isLection) {
		this.isLection = isLection;
	}
	public int getIdTeach() {
		return idTeach;
	}
	public void setIdTeach(int idTeach) {
		this.idTeach = idTeach;
	}
	public int getHourInWeek() {
		return hourInWeek;
	}
	public void setHourInWeek(int hourInWeek) {
		this.hourInWeek = hourInWeek;
	}
	
	
}