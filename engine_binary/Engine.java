package engine_binary;

import database.*;
import pojo.*;
import tools.OneSubject;
import tools.TimeTable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * @author Andriy
 */

public class Engine {

	private final Connector 	conn = Connector.getConnection();

	private ArrayList<Room> 	roomArray;
	private ArrayList<Teacher> 	teachArray;
	private ArrayList<Subject> 	subjArray;
	private ArrayList<Group> 	groupArray;
	
	private ArrayList<TimeTable> timeTable;
	
	
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
	
	private int getRandomSubject(ArrayList<Integer> list ){
		Random r = new Random();
		if (list.size() <= 1){
			return 0; 
		}
		return	r.nextInt(list.size()-1);
	}
	
	
	public void traceDatabaseIntoClassesStructure(){
		
		this.roomArray 	= new ArrayList<Room>	(conn.getRoomList	());
		this.teachArray = new ArrayList<Teacher>(conn.getTeacherList());
		this.subjArray 	= new ArrayList<Subject>(conn.getSubjectList());
		this.groupArray = new ArrayList<Group>	(conn.getAllGroupList());
		
		//створемо кількість розкладів, що рівна кількості груп
		timeTable = new ArrayList<TimeTable>(this.groupArray.size());
		
	}
	
	public void generateStartup(){
		
		int intPart = 0;
		int facPart = 0;
		int idInList = 0;
		
		int mon=0, tue=0, wed=0, thu=0, fri=0;
		OneSubject oneSubj;
		ArrayList<Integer> subjectTaughtList;

		TimeTable tmtbl;
		
		for (Group group: this.groupArray){
			
			subjectTaughtList = group.getSubjectsTaught();
			
			intPart = group.getSubjectsTaught().size() / 5;
			facPart = group.getSubjectsTaught().size() % 5;

			System.out.println("int "+intPart+" facPart "+facPart);
			switch (facPart){
				case 1: { mon = intPart; tue = intPart+1; wed = intPart; thu = intPart; fri = intPart; break;}  
				case 2: { mon = intPart; tue = intPart+1; wed = intPart+1; thu = intPart; fri = intPart; break;}  
				case 3: { mon = intPart; tue = intPart+1; wed = intPart+1; thu = intPart+1; fri = intPart; break;}  
				case 4: { mon = intPart+1; tue = intPart+1; wed = intPart+1; thu = intPart+1; fri = intPart; break;}  
				case 0: { mon = intPart; tue = intPart; wed = intPart; thu = intPart; fri = intPart; break;}
				default: { mon = intPart; tue = intPart; wed = intPart; thu = intPart; fri = intPart; break;}
			}
			
			tmtbl = new TimeTable(mon, tue, wed, thu, fri);
			
			while (mon > 0){
				oneSubj = new OneSubject();
				oneSubj.idGroup = group.getId();
				idInList = getRandomSubject(subjectTaughtList);
				oneSubj.idSubj = subjectTaughtList.get(idInList);
				oneSubj.idRoom = getPossibleRoom(getIsLectionById(oneSubj.idSubj));
				
				subjectTaughtList.remove(idInList);
				
				tmtbl.getMon().addSubj(oneSubj);
				--mon;
				oneSubj = null;
			}
			
			while (tue > 0){
				oneSubj = new OneSubject();
				oneSubj.idGroup = group.getId();
				idInList = getRandomSubject(subjectTaughtList);
				oneSubj.idSubj = subjectTaughtList.get(idInList);
				oneSubj.idRoom = getPossibleRoom(getIsLectionById(oneSubj.idSubj));
				
				subjectTaughtList.remove(idInList);
				
				tmtbl.getTue().addSubj(oneSubj);
				--tue;
				oneSubj = null;
			}
			

			while (wed > 0){
				oneSubj = new OneSubject();
				oneSubj.idGroup = group.getId();
				idInList = getRandomSubject(subjectTaughtList);
				oneSubj.idSubj = subjectTaughtList.get(idInList);
				oneSubj.idRoom = getPossibleRoom(getIsLectionById(oneSubj.idSubj));
				
				subjectTaughtList.remove(idInList);
				
				tmtbl.getWed().addSubj(oneSubj);
				--wed;
				oneSubj = null;
			}

			while (thu > 0){
				oneSubj = new OneSubject();
				oneSubj.idGroup = group.getId();
				idInList = getRandomSubject(subjectTaughtList);
				oneSubj.idSubj = subjectTaughtList.get(idInList);
				oneSubj.idRoom = getPossibleRoom(getIsLectionById(oneSubj.idSubj));
				
				subjectTaughtList.remove(idInList);
				
				tmtbl.getThu().addSubj(oneSubj);
				--thu;
				oneSubj = null;
			}

			while (fri > 0){
				oneSubj = new OneSubject();
				oneSubj.idGroup = group.getId();
				idInList = getRandomSubject(subjectTaughtList);
				oneSubj.idSubj = subjectTaughtList.get(idInList);
				oneSubj.idRoom = getPossibleRoom(getIsLectionById(oneSubj.idSubj));
				
				subjectTaughtList.remove(idInList);
				
				tmtbl.getFri().addSubj(oneSubj);
				--fri;
				oneSubj = null;
			}
			
			
		}
		
	}

}













