package engine_binary;

import database.*;
import pojo.*;
import tools.TimeTable;

import java.util.ArrayList;

/**
 * @author Andriy
 */
public class Engine {

	//Binary structure of 'ROZKLAD'
	final byte DAY 		= 1; 	//00000001
	final byte LESSON 	= 2;	//00000010
	final byte TEACHER 	= 4;	//00000100
	final byte ROOM 	= 8;	//00001000
	
	//Binary day of week
	final byte MONDAY 		= 1; 	//00000001
	final byte TUESDAY 		= 2;	//00000010
	final byte WEDNEASDAY 	= 4;	//00000100
	final byte THURSDAY 	= 8;	//00001000
	final byte FRIDAY 		= 16;	//00010000
	
	private final Connector 	conn = Connector.getConnection();
	
	private ArrayList<Room> 	roomArray;
	private ArrayList<Teacher> 	teachArray;
	private ArrayList<Subject> 	subjArray;
	private ArrayList<Group> 	groupArray;
	
	private ArrayList<TimeTable> timeTable;
	
	public void traceDatabaseIntoBinary(){
		
		this.roomArray 	= new ArrayList<Room>	(conn.getRoomList	());
		this.teachArray = new ArrayList<Teacher>(conn.getTeacherList());
		this.subjArray 	= new ArrayList<Subject>(conn.getSubjectList());
		this.groupArray = new ArrayList<Group>	(conn.getAllGroupList());
		
		
	}
	
	
	private void readDayOfWeek(){
		
		
		
		
	}
		

}