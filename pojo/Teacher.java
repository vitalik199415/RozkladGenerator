package pojo;

public class Teacher {
	
	private int id;
	private String surname;
	private String name;
	private String fName;  // father name
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFName() {
		return fName;
	}
	public void setFName(String f_name) {
		this.fName = f_name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (this.id + "\t" + this.surname+ "\t" +this.name + "\t" + this.fName + "\n"); 
	}
		

	
}
