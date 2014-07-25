package justTest;

import java.util.ArrayList;
import java.util.Iterator;

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
		
		
		
		ArrayList<Subject> arr = new ArrayList<Subject>(8);
		int n=0;
		for (Iterator<Subject> iterator = arr.iterator(); iterator.hasNext();) {
			Subject el = iterator.next();
			el = new Subject();
		}
		
		System.out.println("size "+arr.size());
		for(Subject el: arr){
			System.out.println("iteration "+n);
			if ((n & 2) == 0){
				System.out.println("\tdelete "+n);
				el = null;
			}
			n++;
		}
		
		n=0;
		System.out.println("size "+arr.size());
		for(Subject el: arr){
			System.out.println("iteration "+n);
			}
			n++;
		}


}
