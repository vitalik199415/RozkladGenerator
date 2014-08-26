package pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import engine_binary.Engine;
import tools.*;

public class Generation {
	
//	public ArrayList<Room> 		roomArray;
//	public ArrayList<Teacher> 	teachArray;
//	public ArrayList<Subject> 	subjArray;
//	public ArrayList<Group> 	groupArray;

	public ArrayList<GenerationItem> timeTableArr = new ArrayList<>(Config.GENERATION_SIZE);

	public Generation(){
//		this.generateFirstGeneration();
	}
	
	public void generateFirstGeneration(){
		Engine e = new Engine();
//		this.groupArray = e.groupArray;
//		this.roomArray = e.roomArray;
//		this.subjArray = e.subjArray;
//		this.teachArray = e.teachArray;
			
		while (timeTableArr.size() <= Config.GENERATION_SIZE){
			e.generateStartup();
			this.timeTableArr.add(new GenerationItem(e.timeTable));
		}
	}
	
	private void calculateQuality(){
		for (GenerationItem item: this.timeTableArr){
			item.calculateQuality();
		}
		
	}
	
	private void calculateAdaptaion(){
		final float MAX_ADAPTATION_RATE = 500;
		float summa = 0;
		float adaptInRate;
		for (GenerationItem item: this.timeTableArr){
			summa += item.quality;
		}
//		System.out.println("suma: "+summa);
		for (GenerationItem item: this.timeTableArr){
			adaptInRate = MAX_ADAPTATION_RATE / summa * item.quality; // на проміжку 1..500  
			item.adaptation = 1 / MAX_ADAPTATION_RATE * adaptInRate; //на проміжку 0..1 
//			System.out.println("quality: "+item.quality);
//			System.out.println("adaptInRange: "+adaptInRate);
//			System.out.println("adaptation: "+item.adaptation);
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
		this.calculateQuality();
		this.calculateAdaptaion();
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
	
	public void mutation(){
	
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
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekA().getMon().getSubjects().get(i));
						tmtbl.getWeekA().getMon().getSubjects().set(i, objectToCompare);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getTue().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekA().getTue().getSubjects().get(i));
						tmtbl.getWeekA().getTue().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekA().getWed().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekA().getWed().getSubjects().get(i));
						tmtbl.getWeekA().getWed().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekA().getThu().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekA().getThu().getSubjects().get(i));
						tmtbl.getWeekA().getThu().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekA().getFri().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
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
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekB().getMon().getSubjects().get(i));
						tmtbl.getWeekB().getMon().getSubjects().set(i, objectToCompare);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getTue().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekB().getTue().getSubjects().get(i));
						tmtbl.getWeekB().getTue().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekB().getWed().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekB().getWed().getSubjects().get(i));
						tmtbl.getWeekB().getWed().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekB().getThu().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
						subjBuffer.add(tmtbl.getWeekB().getThu().getSubjects().get(i));
						tmtbl.getWeekB().getThu().getSubjects().set(i, objectToCompare);
					}
				}	
				for (int i = 0; i < tmtbl.getWeekB().getFri().getSubjects().size(); i++){
					if (rand.nextInt(100) < 4){
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
	
	

//	public void crossover(){
//		
//		Map<Integer, Integer> prepareToOld = new HashMap<Integer, Integer>();
//		int size =  this.timeTableArr.size();
//		for(int i=0; i < this.timeTableArr.size(); i++){
//			if (this.timeTableArr.get(i).penalty > 2) {
//				System.out.println("removed: "+ i + " penalty: " + this.timeTableArr.get(i).penalty+ " size is: "+this.timeTableArr.size());
//				this.timeTableArr.remove(i);
//				this.timeTableArr.trimToSize();
//			}
//		}
//		if (this.timeTableArr.size() == size){
//			//залишаємо половину найкращих розкладів
//			for (int i = this.timeTableArr.size()-1; i >= Config.GENERATION_SIZE / 2 ; i--){
//				this.timeTableArr.remove(i);
//				System.out.println("remove from for");
//			}
//			this.timeTableArr.trimToSize();
//		}
//		
//		GenerationItem child,child2, owner1, owner2;
//		Random rand = new Random();
//		int idOwner1, idOwner2;
//		TimeTable tmtbl, tmtbl2; 
//		// породжуємо з 6 обраних нові особи
//		while( this.timeTableArr.size() < Config.GENERATION_SIZE ){
//			// вибираємо 2 індивідуальних  осіб для розмноження
//			idOwner1 = rand.nextInt(this.timeTableArr.size() - 1);
//			idOwner2 = rand.nextInt(this.timeTableArr.size() - 1);
//			
//			while (idOwner1 == idOwner2){
//				idOwner2 = rand.nextInt(this.timeTableArr.size() - 1);	
//			}
//			
//			prepareToOld.put(idOwner1,  ++this.timeTableArr.get(idOwner1).penalty);
//			prepareToOld.put(idOwner2, ++this.timeTableArr.get(idOwner2).penalty);
//		
//			owner1 = new GenerationItem(this.timeTableArr.get(idOwner1));
//			owner2 = new GenerationItem(this.timeTableArr.get(idOwner2));
//			child = new GenerationItem();
//			child2 = new GenerationItem();
//			tmtbl = new TimeTable();
//			tmtbl2 = new TimeTable();
//		
//			for (int i = 0; i < owner1.timeTable.size(); i++ ){
//				if (rand.nextBoolean()){
//					tmtbl.setWeekA(owner1.timeTable.get(i).getWeekA());
//					tmtbl.setWeekB(owner1.timeTable.get(i).getWeekB());
//					
//					tmtbl2.setWeekA(owner2.timeTable.get(i).getWeekA());
//					tmtbl2.setWeekB(owner2.timeTable.get(i).getWeekB());
//					
//				} else {
//					tmtbl.setWeekA(owner2.timeTable.get(i).getWeekA());
//					tmtbl.setWeekB(owner2.timeTable.get(i).getWeekB());
//					
//					tmtbl2.setWeekA(owner1.timeTable.get(i).getWeekA());
//					tmtbl2.setWeekB(owner1.timeTable.get(i).getWeekB());
//				} 
//				child.timeTable.add(tmtbl);
//				child2.timeTable.add(tmtbl2);
//				tmtbl = new TimeTable();
//				tmtbl2 = new TimeTable();
//			}
//			this.timeTableArr.add(child);
//			this.timeTableArr.add(child2);
//			child = null;	
//		}
//		
//		for (Map.Entry<Integer, Integer> entry: prepareToOld.entrySet()){
//			this.timeTableArr.get(entry.getKey()).penalty++;
//		}
//	}
	
	private int getIdItem(Double range){
		//System.out.println(range);
		float suma = 0; 
		for(int i=0; i < this.timeTableArr.size(); i++){
			suma += this.timeTableArr.get(i).adaptation;
			if (suma > range){
				//System.out.println("adaptation: "+ item.adaptation);
				return i;
			}
		}
		throw new Error("Not in range");
	}
	
	public void crossover(){
		Map<Integer, Integer> prepareToOld = new HashMap<>();
		
//		for(int i=0; i < this.timeTableArr.size(); i++){
//			for(int j=i+1; j < this.timeTableArr.size(); j++){
//				if (this.timeTableArr.get(i).equals(this.timeTableArr.get(j))){
//					this.timeTableArr.remove(j); 
//					System.out.println("DELETED EQUALS");
//					this.timeTableArr.trimToSize();
//				}
//			}
//		}
//		
//		for(int i=0; i < this.timeTableArr.size(); i++){
//			if (this.timeTableArr.get(i).penalty > 2) {
//				System.out.println("penalty: " + this.timeTableArr.get(i).penalty);
//				this.timeTableArr.remove(i);
//				this.timeTableArr.trimToSize();
//			}
//		}
		
//		Generation newGeneration = new Generation();
		GenerationItem father, mather, child1, child2;
		TimeTable tmtbl1, tmtbl2;
		Random rand = new Random();
		int idFather, idMather;
		
		while (this.timeTableArr.size() < Config.GENERATION_SIZE){
			idFather = this.getIdItem(rand.nextDouble());
			idMather = this.getIdItem(rand.nextDouble());
			
			while (idFather == idMather){
				idMather = this.getIdItem(rand.nextDouble());
			}

			father = new GenerationItem(this.timeTableArr.get(idFather));
			mather = new GenerationItem(this.timeTableArr.get(idMather));
			
						
			prepareToOld.put(idFather, father.penalty);
			prepareToOld.put(idMather, mather.penalty);
			
			tmtbl1 = new TimeTable();
			tmtbl2 = new TimeTable();
			child1 = new GenerationItem();
			child2 = new GenerationItem();
			
			for (int i = 0; i < father.timeTable.size(); i++ ){
				if (rand.nextBoolean()){
					tmtbl1.setWeekA(father.timeTable.get(i).getWeekA());
					tmtbl1.setWeekB(father.timeTable.get(i).getWeekB());
					
					tmtbl2.setWeekA(mather.timeTable.get(i).getWeekA());
					tmtbl2.setWeekB(mather.timeTable.get(i).getWeekB());
					
				} else {
					tmtbl1.setWeekA(mather.timeTable.get(i).getWeekA());
					tmtbl1.setWeekB(mather.timeTable.get(i).getWeekB());
					
					tmtbl2.setWeekA(father.timeTable.get(i).getWeekA());
					tmtbl2.setWeekB(father.timeTable.get(i).getWeekB());
				}
				child1.timeTable.add(tmtbl1);
				child2.timeTable.add(tmtbl2);
				tmtbl1 = new TimeTable();
				tmtbl2 = new TimeTable();
			}	
//			newGeneration.timeTableArr.add(child1);
//			newGeneration.timeTableArr.add(child2);
//			newGeneration.timeTableArr.trimToSize();
			this.timeTableArr.add(child1);
			this.timeTableArr.add(child2);
			child1 = null;	
			child2 = null;
		}
//		this.timeTableArr.clear();
//		for(int i = this.timeTableArr.size()-1; i > 2 ; i--){
//			this.timeTableArr.remove(i);
//		}
//		this.timeTableArr.addAll(newGeneration.timeTableArr);

		for (Map.Entry<Integer, Integer> entry: prepareToOld.entrySet()){
			for(int i=0; i < this.timeTableArr.size(); i++){
				if (i == entry.getKey()){
					this.timeTableArr.get(i).penalty++;
				}
			}
		}
	}
	
	public void selection(){
		Random rand = new Random();
		// якшо є однакові розклади, то видаляємо всі, крім одного
//		if (rand.nextInt(100) < 15){
			for(int i=0; i < this.timeTableArr.size() && (this.timeTableArr.size() > Config.GENERATION_SIZE); i++){
				for(int j=0; j < this.timeTableArr.size(); j++){
					if (i == j) 
						continue;
					if (this.timeTableArr.get(i).equals(this.timeTableArr.get(j))){
						this.timeTableArr.remove(j); 
						System.out.println("DELETED EQUALS i: " + i + " j: " + j );
						this.timeTableArr.trimToSize();
					}
				}
			}
//		}
//		int size = this.timeTableArr.size();
		// якщо особа  приймала участь у формуванні 3-х  і більше генерацій потомств, то видаляємо її
		for(int i=0; i < this.timeTableArr.size(); i++){
			if (this.timeTableArr.get(i).penalty >= 3) {
				System.out.println("penalty: " + this.timeTableArr.get(i).penalty);
				this.timeTableArr.remove(i);
				this.timeTableArr.trimToSize();
			}
		}
//		
//		// якщо попередні правила не пспрацювали, то нормалізуємо кількість особ, до 2-ї к-сті генерації
//		if (size == this.timeTableArr.size()){
//		int countDel = 0;
//		
//			while (this.timeTableArr.size() > Config.GENERATION_SIZE *2){
//				if(countDel >= 3) 
//					break;
//				this.timeTableArr.remove(rand.nextInt(this.timeTableArr.size()-6)+3);
//				this.timeTableArr.trimToSize();
//				countDel++;
//			}
//		}
		
		this.calculateAdaptaion();
	}
	
	
	
}
