package justTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import pojo.*;
import database.Connector;
import engine_binary.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connector conn = Connector.getConnection();
		
//		for (Room row: conn.getRoomList()){
//			System.out.println(row);
//		}
//		
//		for (Group row: conn.getAllGroupList()){
//			System.out.println(row);
//		}
//		for (Teacher row: conn.getTeacherList()){
//			System.out.println(row);
//		}
//		
//		for (Subject row: conn.getSubjectList()){
//			System.out.println(row);
//		}
		
	
		Random r = new Random();
		int one=0, two=0, three=0, four=0, five=0, zero=0, six=0 ;
		for (int i = 10000; --i>0;){
			
			switch (r.nextInt(6)){
				case 0: {zero++; break;}
				case 1: {one++; break;}
				case 2: {two++; break;}
				case 3: {three++; break;}
				case 4: {four++; break;}
				case 5: {five++; break;}
				case 6: {six++; break;}
			}
			
			
		}
		System.out.println("\n0: "+zero+"\n1: "+one+"\n2: "+two+"\n3: "+three+"\n4: "+four+"\n5: "+five+ "\n6: "+six);
		
		
		
		Engine e = new Engine();
		e.generateStartup();
		
	}
	
		


}
