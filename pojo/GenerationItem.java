package pojo;

import java.util.ArrayList;

import tools.LoadingRoom;
import tools.LoadingTeacher;
import tools.TimeTable;

public class GenerationItem {
	
	public float 	quality = 0; 	// 100 - ідеальний розклад 	
	
	public int 		penalty = 0;	// status oldest
	
	public float 	adaptation = 0;	// summary adaptation all items = 500
	
	public ArrayList<TimeTable> timeTable = new ArrayList<>();

	public GenerationItem(){
		
	}
	
	public GenerationItem(GenerationItem item){
		this.timeTable.clear();
		this.timeTable.addAll(item.timeTable);
		this.calculateQuality();
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenerationItem other = (GenerationItem) obj;
		if (Float.floatToIntBits(adaptation) != Float
				.floatToIntBits(other.adaptation))
			return false;
		if (penalty != other.penalty)
			return false;
		if (Float.floatToIntBits(quality) != Float
				.floatToIntBits(other.quality))
			return false;
		if (timeTable == null) {
			if (other.timeTable != null)
				return false;
		} else if (!timeTable.equals(other.timeTable))
			return false;
		return true;
	}
	
	

}
