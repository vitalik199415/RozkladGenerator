package pojo;

import java.util.ArrayList;
import java.util.Random;

import engine_binary.Engine;
import tools.Config;
import tools.Criteria;
import tools.LoadingRoom;
import tools.OneSubject;
import tools.TimeTable;

public class Generation {

	private ArrayList<GenerationItem> timeTableArr = new ArrayList<>(Config.GENERATION_SIZE);

	public void generateFirstGeneration(){
		Engine e =  new Engine();
		e.traceDatabaseIntoClassesStructure();
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
	// ������� false, ���� �������� ��������� �������
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
		
		System.out.println("________ RATING ________");
		for (GenerationItem it: this.timeTableArr){
			System.out.println(it.quality);
			
		}
		
	}
	
	private int getRandomSubject(ArrayList<OneSubject> list){
		Random rand = new Random();
		int result;
		if (list.size() == 1){
			return 0;
		}else if (list.size() < 1){
			throw new Error("��������� ������ �������� (((");
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
				// � 33% ��������� �������� ��������, �� ������������ �� �������� � null
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
				
				//��������� �� null ��������, �� �������� ���������� � ������ subjBuffer
				for (int i = 0; i < tmtbl.getWeekA().getMon().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getMon().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekA().getMon().getSubjects());
						tmtbl.getWeekA().getMon().getSubjects().set(i, tmtbl.getWeekA().getMon().getSubjects().get(index));
						tmtbl.getWeekA().getMon().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getTue().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getTue().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekA().getTue().getSubjects());
						tmtbl.getWeekA().getTue().getSubjects().set(i, tmtbl.getWeekA().getTue().getSubjects().get(index));
						tmtbl.getWeekA().getTue().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getWed().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getWed().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekA().getWed().getSubjects());
						tmtbl.getWeekA().getWed().getSubjects().set(i, tmtbl.getWeekA().getWed().getSubjects().get(index));
						tmtbl.getWeekA().getWed().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getThu().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getThu().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekA().getThu().getSubjects());
						tmtbl.getWeekA().getThu().getSubjects().set(i, tmtbl.getWeekA().getThu().getSubjects().get(index));
						tmtbl.getWeekA().getThu().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekA().getFri().getSubjects().size(); i++){
					if (tmtbl.getWeekA().getFri().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekA().getFri().getSubjects());
						tmtbl.getWeekA().getFri().getSubjects().set(i, tmtbl.getWeekA().getFri().getSubjects().get(index));
						tmtbl.getWeekA().getFri().getSubjects().remove(index);
					}
				}
				
				subjBuffer.clear();
				// �� ���� ����� ��� ����� WeekB
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
				
				//��������� �� null ��������, �� �������� ���������� � ������ subjBuffer
				for (int i = 0; i < tmtbl.getWeekB().getMon().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getMon().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekB().getMon().getSubjects());
						tmtbl.getWeekB().getMon().getSubjects().set(i, tmtbl.getWeekB().getMon().getSubjects().get(index));
						tmtbl.getWeekB().getMon().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getTue().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getTue().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekB().getTue().getSubjects());
						tmtbl.getWeekB().getTue().getSubjects().set(i, tmtbl.getWeekB().getTue().getSubjects().get(index));
						tmtbl.getWeekB().getTue().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getWed().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getWed().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekB().getWed().getSubjects());
						tmtbl.getWeekB().getWed().getSubjects().set(i, tmtbl.getWeekB().getWed().getSubjects().get(index));
						tmtbl.getWeekB().getWed().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getThu().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getThu().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekB().getThu().getSubjects());
						tmtbl.getWeekB().getThu().getSubjects().set(i, tmtbl.getWeekB().getThu().getSubjects().get(index));
						tmtbl.getWeekB().getThu().getSubjects().remove(index);
					}
				}
				for (int i = 0; i < tmtbl.getWeekB().getFri().getSubjects().size(); i++){
					if (tmtbl.getWeekB().getFri().getSubjects().get(i) == objectToCompare){
						int index = getRandomSubject(tmtbl.getWeekB().getFri().getSubjects());
						tmtbl.getWeekB().getFri().getSubjects().set(i, tmtbl.getWeekB().getFri().getSubjects().get(index));
						tmtbl.getWeekB().getFri().getSubjects().remove(index);
					}
				}
				
			}
		}
		
	}
	
}
