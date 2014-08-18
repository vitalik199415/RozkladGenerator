package pojo;

import java.util.ArrayList;

import tools.LoadingRoom;
import tools.LoadingTeacher;
import tools.TimeTable;

public class GenerationItem {
	
	public float quality = 0; // 100 - ідеальний розклад 	
	
	public byte penalty = 0;
	
	public ArrayList<TimeTable> timeTable = new ArrayList<>();

	public GenerationItem(){
		
	}
	
	public GenerationItem(ArrayList<TimeTable> tmtbl){
		this.timeTable.clear();
		this.timeTable.addAll(tmtbl);
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

	@Override
	public String toString() {
		String result = "";
		for (TimeTable tmtbl: this.timeTable){
			result +=  tmtbl.toString();
		}
		return result;
	}
	
	

}
