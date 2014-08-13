package tools;

import pojo.Generation;

public class GenerationProcess {

	
	public static void startProcess(){
		int iterCount = 5;
		long timeout = System.currentTimeMillis();
		
		Generation gen = new Generation();

		while (gen.fitness() && (iterCount > 1)){
			gen.crossover();
			gen.sort(); // підраховується якітсь, та сортується розклад
			--iterCount;
			System.out.println(gen.timeTableArr.toString());
		}
		timeout = System.currentTimeMillis()-timeout;
		System.out.println("TimeGeneerating: "+timeout);
	}

}
