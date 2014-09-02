package tools;

import pojo.Generation;
import pojo.GenerationItem;

public class GenerationProcess {

	public static GenerationItem startProcess(){
		int iterCount = 10000;
		long timeout = System.currentTimeMillis();
		GenerationItem bestTable = new GenerationItem();
		
		Generation gen = new Generation();
		gen.generateFirstGeneration();
		bestTable.timeTable.addAll(gen.timeTableArr.get(0).timeTable);
		bestTable.quality = gen.timeTableArr.get(0).quality;
		gen.sort();
//		System.out.println(gen.timeTableArr.get(gen.timeTableArr.size()-1));
		
		while (gen.fitness() && (iterCount > 1)){
			gen.selection();
			gen.crossover();
			gen.mutation();
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
//		System.out.println("best Table size: " + bestTable.timeTable.size());
		System.out.println(bestTable.toString());
//		
//		System.out.println("���ò���� �������:\nQUALITY: "+ gen.timeTableArr.get(gen.timeTableArr.size()-1).quality);
//		System.out.println(gen.timeTableArr.get(gen.timeTableArr.size()-1).toString());

		
		timeout = System.currentTimeMillis()-timeout;
		System.out.println("TimeGeneerating: "+timeout);
		return bestTable;
	}	
}