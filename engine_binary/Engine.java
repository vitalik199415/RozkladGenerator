package engine_binary;

import database.*;
import pojo.*;
import tools.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Andriy
 */

public class Engine {

	private final Connector 	conn = Connector.getConnection();

	public ArrayList<Room> 		roomArray;
	public ArrayList<Teacher> 	teachArray;
	public ArrayList<Subject> 	subjArray;
	public ArrayList<Group> 	groupArray;
	
	public ArrayList<TimeTable> timeTable;
	
	private Boolean getIsLectionById(int _id){
		for (Subject subj: subjArray){
			if (subj.getId() == _id)
				return subj.getIsLection();
		}
		throw new Error("Не знайдено жодного співпадіння в переліку предметів!!!");
	}
	
	private int getPossibleRoom(Boolean isLection){
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
		System.out.println("Size: "+list.size()+ "random: " +result);
		return result;
	}
	
	public void traceDatabaseIntoClassesStructure(){
		this.roomArray 	= new ArrayList<Room>	(conn.getRoomList	());
		this.teachArray = new ArrayList<Teacher>(conn.getTeacherList());
		this.subjArray 	= new ArrayList<Subject>(conn.getSubjectList());
		this.groupArray = new ArrayList<Group>	(conn.getAllGroupList());
		//створемо кількість розкладів, що рівна кількості груп
		this.timeTable =  new ArrayList<TimeTable>(this.groupArray.size());
	}
	
	public void generateStartup(){
//		this.traceDatabaseIntoClassesStructure();
		int idInList = 0, curDay = 0;
		TimeTable tmtbl;
		boolean inFirst; // умова вибору місця встаки предмету, який чергується
		OneSubject oneSubj;
		ArrayList<Subject> subjectTaughtList = new ArrayList<>(10);

		for (Group group: this.groupArray){
			System.out.println("In group");
			tmtbl = new TimeTable(4);
			inFirst = true;
			curDay = 1;
			
//			subjectTaughtList = group.getSubjectsTaught();
			subjectTaughtList.addAll(group.getSubjectsTaught());
			System.out.println("subjTaugh: "+subjectTaughtList.size());
			

			while (subjectTaughtList.size() > 0){
				System.out.println("in while");
				if (curDay > 5){
					curDay = 1;
				}
				oneSubj = new OneSubject();
				idInList = getRandomSubject(subjectTaughtList);
				oneSubj.idGroup = group.getId();
				oneSubj.idSubj = subjectTaughtList.get(idInList).getId();
				oneSubj.idTeach = subjectTaughtList.get(idInList).getIdTeach();
				oneSubj.idRoom = getPossibleRoom(getIsLectionById(oneSubj.idSubj));
				
				if (subjectTaughtList.get(idInList).getHourInWeek() <= 1){
					if (inFirst){
						tmtbl.getWeekA().getDayById(curDay).addSubj(oneSubj);
					} else {
						tmtbl.getWeekB().getDayById(curDay).addSubj(oneSubj);
					}
					inFirst = !inFirst; 
					curDay++;
					subjectTaughtList.remove(idInList);
					continue;
				}
					tmtbl.getWeekA().getDayById(curDay).addSubj(oneSubj);
					tmtbl.getWeekB().getDayById(curDay).addSubj(oneSubj);
					curDay++;
					
				if (subjectTaughtList.get(idInList).getHourInWeek() > 2){
					subjectTaughtList.get(idInList).setHourInWeek(subjectTaughtList.get(idInList).getHourInWeek()-2);
					continue;
				}
				subjectTaughtList.remove(idInList);
				oneSubj = null;
			}
			this.timeTable.add(tmtbl);
			tmtbl = null;
		}
		subjectTaughtList = null;
		System.out.println("END generatioon");

	}

}
