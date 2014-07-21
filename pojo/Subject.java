package pojo;

public class Subject{
	
	private int 				id;
	private String 				name; 		
	private String 				shortName; 
	private int 				countHour; 
	private Boolean 			isLection;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String short_name) {
		this.shortName = short_name;
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
	
	@Override
	public String toString() {
		String isLect = this.getIsLection() ? "Лекція" : "";
		String temp = this.id + "  " + this.name+ "\t" +this.shortName + "\t" + this.countHour + "h\t" + isLect + "\n";
	 	return temp;
	}

}