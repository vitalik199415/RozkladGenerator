package pojo;

import java.util.ArrayList;

public class Group {

	private int 			id;
	private String 			name;
	private int 			countStud;
	private ArrayList<Integer> 	possibleRoom = new ArrayList<Integer>();
	
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
	public int getCountStud() {
		return countStud;
	}
	public void setCountStud(int count_stud) {
		this.countStud = count_stud;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (this.id + "\t" + this.name+ "\t" +this.countStud + "\n");
	}

	public ArrayList<Integer> getPossibleRoom() {
		return possibleRoom;
	}
	public void setPossibleRoom(ArrayList<Integer> possibleRoom) {
		this.possibleRoom = possibleRoom;
	}

}
