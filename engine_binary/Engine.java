package engine_binary;

import database.*;
import pojo.*;

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
	
	private ArrayList<Room> 	roomArray;
	private ArrayList<Teacher> 	teachArray;
	private ArrayList<Subject> 	subjArray;
	private ArrayList<Group> 	groupArray;
	
	private void traceDatabaseIntoBinary(){
		
		
		
	}
	
	
	private void readDayOfWeek(){
		
		
		
		
	}
		

}