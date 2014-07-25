package engine_binary;

import database.*;
import pojo.*;
import tools.OneSubject;
import tools.SubjForDay;
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
	
	private int getPossibleRoom(ArrayList<Room> copyRoomList){
		for(Room room: )
	}
	
	private int getRandomSubject(ArrayList<Subject> list ){
		Random r = new Random();
		int result;
		do {
			result = r.nextInt(list.size()-1);
			if (list.get(result) == null)
				continue;
			else 
				break;
			
		} while (true);
		
		return result;
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
		int temp = 0;
		
		int mon=0, tue=0, wed=0, thu=0, fri=0;
		OneSubject oneSubj;
		ArrayList<Subject> copySubjectList = this.subjArray;
		ArrayList<Room> copyRoomList = this.roomArray;

		TimeTable tmtbl;
		
		for (Group group: this.groupArray){
			
			intPart = group.getSubjectsTaught().size() / 5;
			facPart = group.getSubjectsTaught().size() % 5;

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
				
				oneSubj.idSubj = getRandomSubject(copySubjectList);
				
				copySubjectList.remove(oneSubj.idSubj);
				
				tmtbl.getMon().addSubj(oneSubj);
				--mon;
			}
			
		}
		
	}

}













