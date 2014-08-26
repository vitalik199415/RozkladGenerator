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
		String isLect = subj.getIsLection() ? "" : "Лабораторна | ";
		String result = " | " + group.getName() + " | " +  subj.getShortName()  + " | " + isLect +  teacher.getSurname()  + " | " + room.getName() + " | ";
	return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idGroup;
		result = prime * result + idRoom;
		result = prime * result + idSubj;
		result = prime * result + idTeach;
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
		OneSubject other = (OneSubject) obj;
		if (idGroup != other.idGroup)
			return false;
		if (idRoom != other.idRoom)
			return false;
		if (idSubj != other.idSubj)
			return false;
		if (idTeach != other.idTeach)
			return false;
		return true;
	} 
	
	
	
	

}
