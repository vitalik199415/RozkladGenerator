package engine_binary;

import database.*;
import pojo.*;
import tools.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;


/**
 * @author Andriy
 */

public class Engine {

	private final Connector 	conn = Connector.getConnection();
	
	public Map<Integer, Integer> loadRoom = new HashMap<Integer, Integer>();

	public ArrayList<Room> 		roomArray;
	public ArrayList<Teacher> 	teachArray;
	public ArrayList<Subject> 	subjArray;
	public ArrayList<Group> 	groupArray;
	
	public ArrayList<TimeTable> timeTable;
	
	public Engine(){
		this.traceDatabaseIntoClassesStructure();
	}
	
	private Boolean getIsLectionById(int _id){
		for (Subject subj: subjArray){
			if (subj.getId() == _id)
				return subj.getIsLection();
		}
		throw new Error("Не знайдено жодного співпадіння в переліку предметів!!!");
	}
	
	private int getPossibleRoom(Group group, Boolean isLection){
		ArrayList<Room> possibleRoom = new ArrayList<>(); 
		for (Room room: this.roomArray){
			if ( (room.getCount_seating() >= group.getCountStud() ) && ( room.getIsLaboratory() != isLection )){
				possibleRoom.add(room);
			}
		}
		//System.out.println("pos size: "+ possibleRoom.size());
		if ( possibleRoom.size() <= 0 ){
			Random r = new Random();
			int result;
			do {
				result = r.nextInt(this.roomArray.size()-1);
				if (this.roomArray.get(result).getIsLaboratory() == isLection)
					continue;
				else 
					break;
			} while (true);
			return this.roomArray.get(result).getId();
		}
		
		int min = Integer.MAX_VALUE-1, idRoom = Integer.MAX_VALUE-1;
		
		for (Room room: possibleRoom){
			if (this.loadRoom.get(room.getId()) < min ){
				min = this.loadRoom.get(room.getId());
				idRoom = room.getId();
			}
		}
		if (this.loadRoom.get(idRoom) != null){
			int value = this.loadRoom.get(idRoom);
			this.loadRoom.put(idRoom, value+1);
			return idRoom;
		} else {
			throw new Error("НЕ вийшло визначити аудиторію для проведення пари в групі: " + group.getId() + " " + isLection) ;
		}
	}
	
	private int getRandomSubject(ArrayList<Subject> list, Boolean isOneHourInWeek){
		
		if (isOneHourInWeek){
			ArrayList<Subject> possibleList = new ArrayList<>(4);
			for(Subject subj: list){
				if (subj.getHourInWeek() == 1){
					possibleList.add(subj);
				}
			}
			if ( ! possibleList.isEmpty() ){
				return getRandomSubject(possibleList);	
			} else 
				return -1;
		} else {
			return getRandomSubject(list);
		}
		
	}
	
	private int getRandomSubject(ArrayList<Subject> list){
		Random rand = new Random();
		int result;
		if (list.size() == 1){
			return 0;
		}else if (list.size() < 1){
			throw new Error("Закінчився список предметів (((");
		}else {
			result = rand.nextInt(list.size()-1);
		} 
//		System.out.println("Size: "+list.size()+ "random: " +result);
		return result;
	}
	
	public void traceDatabaseIntoClassesStructure(){
		this.roomArray 	= new ArrayList<Room>	(conn.getRoomList	());
		this.teachArray = new ArrayList<Teacher>(conn.getTeacherList());
		this.subjArray 	= new ArrayList<Subject>(conn.getSubjectList());
		this.groupArray = new ArrayList<Group>	(conn.getAllGroupList());
		
		for (Room room: this.roomArray){
			this.loadRoom.put(room.getId(), 0);
		}
	}
	
	public void generateStartup(){
		
		this.timeTable = null;
		this.timeTable =  new ArrayList<TimeTable>(this.groupArray.size());
		for (Map.Entry<Integer, Integer> entry: this.loadRoom.entrySet()){
			entry.setValue(0);
		}
		int idInList = 0, curDay = 0;
		boolean inFirst = true;
		TimeTable tmtbl;
		OneSubject oneSubj;
		ArrayList<Subject> subjectTaughtList = new ArrayList<>(10);

	
		
		for (Group group: this.groupArray){
			tmtbl = new TimeTable(4);
			curDay = 0;
			subjectTaughtList.addAll(group.getSubjectsTaught());
//			System.out.println("idGroup: "+group.getId());
//			for (Subject s: subjectTaughtList){
//				System.out.println(s.getId()+ " HOUR: "+s.getCountHour()+ " INWEEK: "+s.getHourInWeek());
//			}
			
			while (subjectTaughtList.size() > 0){
				curDay++;
//				System.out.println(tmtbl);
				if (curDay > 5){
					curDay = 1;
				}
				oneSubj = new OneSubject();
				idInList = getRandomSubject(subjectTaughtList); 
				oneSubj.idGroup = group.getId();
				oneSubj.idSubj = subjectTaughtList.get(idInList).getId();
				oneSubj.idTeach = subjectTaughtList.get(idInList).getIdTeach();
				oneSubj.idRoom = getPossibleRoom(group, getIsLectionById(oneSubj.idSubj));
				
				if (subjectTaughtList.get(idInList).getHourInWeek() < 2){
					tmtbl.getWeekA().getDayById(curDay).addSubj(oneSubj);
					subjectTaughtList.remove(idInList);
					oneSubj = null;
					idInList = getRandomSubject(subjectTaughtList,true); 
					if (idInList != -1){
						oneSubj = new OneSubject();
						oneSubj.idGroup = group.getId();
						oneSubj.idSubj = subjectTaughtList.get(idInList).getId();
						oneSubj.idTeach = subjectTaughtList.get(idInList).getIdTeach();
						oneSubj.idRoom = getPossibleRoom(group, getIsLectionById(oneSubj.idSubj));
						tmtbl.getWeekB().getDayById(curDay).addSubj(oneSubj);
						subjectTaughtList.remove(idInList);
						oneSubj = null;
					}
					continue;
				}
					tmtbl.getWeekA().getDayById(curDay).addSubj(oneSubj);
					tmtbl.getWeekB().getDayById(curDay).addSubj(oneSubj);
					
				if (subjectTaughtList.get(idInList).getHourInWeek() > 2){
					subjectTaughtList.get(idInList).setHourInWeek(subjectTaughtList.get(idInList).getHourInWeek()-2);
					oneSubj = null;
					continue;
				}
				subjectTaughtList.remove(idInList);
				oneSubj = null;
				
			}
			this.timeTable.add(tmtbl);
			tmtbl = null;
		}
		subjectTaughtList = null;
	}

}
