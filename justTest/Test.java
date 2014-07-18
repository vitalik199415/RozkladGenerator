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
		
		Engine e = new Engine();
		e.traceDatabaseIntoBinary();
		

	}

}
