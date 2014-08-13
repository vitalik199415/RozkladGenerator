package tools;

import pojo.Generation;

public class GenerationProcess {

	
	public static void startProcess(){
		int iterCount = 100;
		long timeout = System.currentTimeMillis();
		
		Generation gen = new Generation();
		gen.sort();
		while (gen.fitness() && (iterCount > 1)){
			gen.crossover();
			gen.calculateQuality();
			gen.sort();
			--iterCount;
		}
		timeout = System.currentTimeMillis()-timeout;
		System.out.println("TimeGeneerating: "+timeout);
	}

}
