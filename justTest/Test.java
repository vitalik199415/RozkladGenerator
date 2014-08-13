package justTest;

import pojo.*;

public class Test {

	public static void main(String[] args) {

		int iterCount = 1000;
		long timeout = System.currentTimeMillis();
		
		Generation gen = new Generation();
		gen.generateFirstGeneration();
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
