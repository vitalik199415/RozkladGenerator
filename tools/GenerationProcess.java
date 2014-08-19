package tools;

import pojo.Generation;
import pojo.GenerationItem;

public class GenerationProcess {

	public static void startProcess(){
		int iterCount = 1000;
		long timeout = System.currentTimeMillis();
		GenerationItem bestTable = new GenerationItem();
		
		Generation gen = new Generation();
		bestTable.timeTable.addAll(gen.timeTableArr.get(0).timeTable);
		bestTable.quality = gen.timeTableArr.get(0).quality;
		gen.sort();
//		System.out.println(gen.timeTableArr.get(gen.timeTableArr.size()-1));
		
		while (gen.fitness() && (iterCount > 1)){
			gen.crossover();
			gen.sort(); // ������������ �����, �� ��������� �������
			if (gen.timeTableArr.get(0).quality > bestTable.quality){
				bestTable.timeTable.clear();
				bestTable.timeTable.addAll(gen.timeTableArr.get(0).timeTable);
				bestTable.quality = gen.timeTableArr.get(0).quality;
			}
			--iterCount;
//			System.out.println(gen.timeTableArr.toString());
		}
		
		System.out.println("��������� �������:\nQUALITY: "+bestTable.quality);
		System.out.println("best Table size: " + bestTable.timeTable.size());
		System.out.println(bestTable.toString());
		
		timeout = System.currentTimeMillis()-timeout;
		System.out.println("TimeGeneerating: "+timeout);
	}
	
}
