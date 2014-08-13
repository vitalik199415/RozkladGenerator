package tools;

import pojoFullInfo.GroupInfo;
import pojoFullInfo.RoomInfo;
import pojoFullInfo.SubjectInfo;
import pojoFullInfo.TeacherInfo;
import database.Connector;

public class OneSubject {
	
	public int idGroup;
	public int idSubj;
	public int idTeach;
	public int idRoom;
	
	@Override
	public String toString() {
		Connector conn = Connector.getConnection();
		TeacherInfo teacher = conn.getTeacherById(this.idTeach);
		SubjectInfo subj = conn.getSubjectById(this.idSubj);
		GroupInfo group = conn.getGroupById(this.idGroup);
		RoomInfo room = conn.getRoomById(this.idRoom);
		String result = " | " + group.getName() + " | " +  subj.getName()  + " | " +  teacher.getSurname()  + " | " + room.getName() + " | ";
	return result;
	} 
	
	
	

}
