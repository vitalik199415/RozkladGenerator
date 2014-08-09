package justTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import pojo.*;
import tools.Criteria;
import tools.LoadingRoom;
import database.Connector;
import engine_binary.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connector conn = Connector.getConnection();

		Engine e = new Engine();
		long timeout = System.currentTimeMillis();

		Generation gen = new Generation();
		gen.generateFirstGeneration();
		gen.sort();
		timeout = System.currentTimeMillis()-timeout;
		System.out.println("TimeGeneerating: "+timeout);
		
		
	}
	
		


}
