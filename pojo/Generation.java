package pojo;

import java.util.ArrayList;

import engine_binary.Engine;
import tools.Config;
import tools.TimeTable;
import tools.Week;

public class Generation {

	private ArrayList<GenerationItem> timeTableArr = new ArrayList<>(Config.GENERATION_SIZE);

	public void generateFirstGeneration(){
		Engine e =  new Engine();
		e.traceDatabaseIntoClassesStructure();
		while (timeTableArr.size() < Config.GENERATION_SIZE){
			e.generateStartup();
			this.timeTableArr.add(new GenerationItem(e.timeTable));
		}
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
	
	
	
	
	
	
	
}
