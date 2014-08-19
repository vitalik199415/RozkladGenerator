package tools;

import java.util.ArrayList;

public class LoadingTeacher implements Criteria{

	@Override
	public float calc(ArrayList<TimeTable> timeTable) {
		
		float countEquals = 0;

		for(int i = 0; i < timeTable.size(); i++){
			for(int i2 = i+1; i2 < timeTable.size(); i2++){
				for (int j = 0; j < timeTable.get(i).getWeekA().getMon().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getMon().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekA().getMon().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekA().getMon().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getTue().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getTue().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekA().getTue().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekA().getTue().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getWed().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getWed().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekA().getWed().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekA().getWed().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getThu().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getThu().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekA().getThu().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekA().getThu().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getFri().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getFri().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekA().getFri().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekA().getFri().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				
				for (int j = 0; j < timeTable.get(i).getWeekB().getMon().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getMon().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekB().getMon().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekB().getMon().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getTue().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getTue().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekB().getTue().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekB().getTue().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getWed().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getWed().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekB().getWed().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekB().getWed().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getThu().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getThu().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekB().getThu().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekB().getThu().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getFri().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getFri().getSubjects().size(); j2++){
						if (timeTable.get(i).getWeekB().getFri().getSubjects().get(j).idTeach == timeTable.get(i2).getWeekB().getFri().getSubjects().get(j2).idTeach){
							countEquals++;
						}
					}
				}
			}

		}
		return countEquals;
	}


}
