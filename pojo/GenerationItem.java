package pojo;

import java.util.ArrayList;

import tools.Config;
import tools.LoadingRoom;
import tools.LoadingTeacher;
import tools.TimeTable;

public class GenerationItem {
	
	public float quality = 0; // 0 - ідеальний розклад 	
	
	public ArrayList<TimeTable> timeTable = new ArrayList<>(Config.GENERATION_SIZE);

	public GenerationItem(ArrayList<TimeTable> tmtbl){
		this.timeTable = tmtbl;
		this.calculateQuality();
	}
	
	public void calculateQuality(){
		float pokaz;
		pokaz = new LoadingRoom().calc(this.timeTable);
		float _qual1 = (100f + pokaz) / (pokaz + 1.0f);

		pokaz = new LoadingTeacher().calc(this.timeTable);
		float _qual2 = (100f + pokaz) / (pokaz + 1.0f);
		
		this.quality = (_qual1 + _qual2) / 2f;
		
	}
}
