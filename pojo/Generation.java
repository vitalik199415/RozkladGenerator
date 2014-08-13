package pojo;

import java.util.ArrayList;
import java.util.Random;

import engine_binary.Engine;
import tools.*;

public class Generation {

	public ArrayList<GenerationItem> timeTableArr = new ArrayList<>(Config.GENERATION_SIZE);

	public Generation(){
		this.generateFirstGeneration();
	}
	
	private void generateFirstGeneration(){
		Engine e = new Engine();
		while (timeTableArr.size() <= Config.GENERATION_SIZE){
			e.generateStartup();
			this.timeTableArr.add(new GenerationItem(e.timeTable));
		}
	}
	
	public void calculateQuality(){
		for (GenerationItem item: this.timeTableArr){
			item.calculateQuality();
		}
	}

	// повертає false, якщо знайдено ідеальний розклад
	public Boolean fitness(){
		for (GenerationItem item: this.timeTableArr){
			if (item.quality == 100f)
				return false;
		}
		return true;
	}
	
	public void sort(){
		GenerationItem tempItem;
		for (int i = 0; i < this.timeTableArr.size(); i++){
			for (int j = 1; j < this.timeTableArr.size(); j++){
				if (this.timeTableArr.get(j).quality > this.timeTableArr.get(j-1).quality){
					tempItem = this.timeTableArr.get(j-1);
					this.timeTableArr.set(j-1, this.timeTableArr.get(j));
					this.timeTableArr.set(j, tempItem);
				}				
			}
		}
		System.out.println("QUALITY");
		for (GenerationItem item: this.timeTableArr){
			System.out.println(item.quality);		
			}
		System.out.println("_______________________");
	}
	
	private int getRandomSubject(ArrayList<OneSubject> list){
		Random rand = new Random();
		int result;
		if (list.size() == 1){
			return 0;
		}else if (list.size() < 1){
			throw new Error("Закінчився список предметів (((");
		}else {
			result = rand.nextInt(list.size()-1);
		} 
		return result;
	}
	
	public void crossover(){
	
		ArrayList<OneSubject> subjBuffer = new ArrayList<>();  
		
		OneSubject objectToCompare = new OneSubject();
		objectToCompare.idGroup = 0;
		objectToCompare.idRoom = 0;
		objectToCompare.idSubj = 0;
		objectToCompare.idTeach = 0;
		
		Random rand = new Random();
		for (GenerationItem item: this.timeTableArr){
			for (TimeTable tmtbl: item.timeTable){
				// з 33% ймовірністю вибираємо предмети, та встановлюємо їх значення в null
				subjBuffer.clear();
				for (int i = 0; i < tmtbl.getWeekA().getMon().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekA().getMon().getSubjects().get(i));
						tmtbl.getWeekA().getMon().getSubjects().set(i, objectToCompare);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getTue().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekA().getTue().getSubjects().get(i));
						tmtbl.getWeekA().getTue().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekA().getWed().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekA().getWed().getSubjects().get(i));
						tmtbl.getWeekA().getWed().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekA().getThu().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekA().getThu().getSubjects().get(i));
						tmtbl.getWeekA().getThu().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekA().getFri().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekA().getFri().getSubjects().get(i));
						tmtbl.getWeekA().getFri().getSubjects().set(i, objectToCompare);
					}
				}
				
				//Знаходимо всі null елементи, та замінюємо випадковим зі списку subjBuffer
				for (int i = 0; i < tmtbl.getWeekA().getMon().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getMon().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekA().getMon().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getTue().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getTue().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekA().getTue().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getWed().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getWed().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekA().getWed().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getThu().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getThu().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekA().getThu().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getFri().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getFri().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekA().getFri().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				
				subjBuffer.clear();
				// те саме тільки для тижня WeekB
				for (int i = 0; i < tmtbl.getWeekB().getMon().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekB().getMon().getSubjects().get(i));
						tmtbl.getWeekB().getMon().getSubjects().set(i, objectToCompare);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getTue().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekB().getTue().getSubjects().get(i));
						tmtbl.getWeekB().getTue().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekB().getWed().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekB().getWed().getSubjects().get(i));
						tmtbl.getWeekB().getWed().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekB().getThu().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekB().getThu().getSubjects().get(i));
						tmtbl.getWeekB().getThu().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekB().getFri().getSubjects().size(); i++){
					if (rand.nextInt(10) < 4){
						subjBuffer.add(tmtbl.getWeekB().getFri().getSubjects().get(i));
						tmtbl.getWeekB().getFri().getSubjects().set(i, objectToCompare);
					}
				}
				//Знаходимо всі null елементи, та замінюємо випадковим зі списку subjBuffer
				for (int i = 0; i < tmtbl.getWeekB().getMon().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getMon().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekB().getMon().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getTue().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getTue().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekB().getTue().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getWed().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getWed().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekB().getWed().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getThu().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getThu().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekB().getThu().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getFri().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getFri().getSubjects().get(i).equals(objectToCompare)){
						int index = getRandomSubject(subjBuffer);
						tmtbl.getWeekB().getFri().getSubjects().set(i, subjBuffer.get(index));
						if (subjBuffer.size() > 0)
							subjBuffer.remove(index);
					}
				}
				
			}
		}
		
	}

	public GenerationItem crossover(ArrayList<GenerationItem> gen){
		
		
		
		return null;
		
	}
	
}
