package pojo;

public class Subject{
	
	private int 	id;
	private String 	name; 		
	private String 	short_name; 
	private int 	count_hour; 
	
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
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public int getCount_hour() {
		return count_hour;
	}
	public void setCount_hour(int count_hour) {
		this.count_hour = count_hour;
	}

}