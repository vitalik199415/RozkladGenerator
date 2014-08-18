package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import database.Connector;
import pojo.Room;

public class LoadingRoom implements Criteria {

	private Map<Integer, Integer> loading = new HashMap <Integer,Integer>();
			
	public LoadingRoom() {
		Connector conn = Connector.getConnection();
		ArrayList<Room> rooms = conn.getRoomList();
		for (Room room : rooms){
			this.loading.put(room.getId(), 0);
		}
	}
	
	
	private void insertHash(int key){
		Integer value = (Integer) this.loading.get(key);
		if  (value != null){
			this.loading.put(key, value+1);
		}
	}
	
	@Override
	public float calc(ArrayList<TimeTable> timeTable) {
		
		float countEquals = 0;

		for(int i = 0; i < timeTable.size(); i++){
			for(int i2 = i+1; i2 < timeTable.size(); i2++){
		
				for (int j = 0; j < timeTable.get(i).getWeekA().getMon().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getMon().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekA().getMon().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekA().getMon().getSubjects().get(j2).idRoom);
						
						if (timeTable.get(i).getWeekA().getMon().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekA().getMon().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getTue().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getTue().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekA().getTue().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekA().getTue().getSubjects().get(j2).idRoom);
						if (timeTable.get(i).getWeekA().getTue().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekA().getTue().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getWed().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getWed().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekA().getWed().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekA().getWed().getSubjects().get(j2).idRoom);
						if (timeTable.get(i).getWeekA().getWed().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekA().getWed().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getThu().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getThu().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekA().getThu().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekA().getThu().getSubjects().get(j2).idRoom);
						if (timeTable.get(i).getWeekA().getThu().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekA().getThu().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekA().getFri().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekA().getFri().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekA().getFri().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekA().getFri().getSubjects().get(j2).idRoom);
						if (timeTable.get(i).getWeekA().getFri().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekA().getFri().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				
				for (int j = 0; j < timeTable.get(i).getWeekB().getMon().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getMon().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekB().getMon().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekB().getMon().getSubjects().get(j2).idRoom);

						if (timeTable.get(i).getWeekB().getMon().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekB().getMon().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getTue().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getTue().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekB().getTue().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekB().getTue().getSubjects().get(j2).idRoom);

						if (timeTable.get(i).getWeekB().getTue().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekB().getTue().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getWed().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getWed().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekB().getWed().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekB().getWed().getSubjects().get(j2).idRoom);

						if (timeTable.get(i).getWeekB().getWed().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekB().getWed().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getThu().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getThu().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekB().getThu().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekB().getThu().getSubjects().get(j2).idRoom);

						if (timeTable.get(i).getWeekB().getThu().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekB().getThu().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
				for (int j = 0; j < timeTable.get(i).getWeekB().getFri().getSubjects().size(); j++){
					for (int j2 = j; j2 < timeTable.get(i2).getWeekB().getFri().getSubjects().size(); j2++){
						insertHash(timeTable.get(i).getWeekB().getFri().getSubjects().get(j).idRoom);
						insertHash(timeTable.get(i2).getWeekB().getFri().getSubjects().get(j2).idRoom);

						if (timeTable.get(i).getWeekB().getFri().getSubjects().get(j).idRoom == timeTable.get(i2).getWeekB().getFri().getSubjects().get(j2).idRoom){
							countEquals++;
						}
					}
				}
			}
		}		
		
		Iterator<Map.Entry<Integer, Integer>> it = loading.entrySet().iterator();
		
		while (it.hasNext()){
			System.out.println(it.next());
			
		}
		return countEquals;
	}

}
