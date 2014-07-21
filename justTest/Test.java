package justTest;

import pojo.*;
import database.Connector;
import engine_binary.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connector conn = Connector.getConnection();
		
		for (Room row: conn.getRoomList()){
			System.out.println(row);
		}
		
		for (Group row: conn.getAllGroupList()){
			System.out.println(row);
		}
		for (Teacher row: conn.getTeacherList()){
			System.out.println(row);
		}
		
		for (Subject row: conn.getSubjectList()){
			System.out.println(row);
		}
		
		
		Engine e = new Engine();
		e.traceDatabaseIntoClassesStructure();
		
		
		

	}

}
