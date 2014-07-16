package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Andriy
 * @see Connector Singleton
 */

public class Connector {

	private static Connector connector;

	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	private final String DRIVER_NAME = "org.postgresql.Driver";
	private final String URL = "jdbc:postgresql://localhost/DBRozkladGenerator";
	private final String USER = "postgres";
	private final String PASS = "root";

	private Connector() {

		long timeout = System.currentTimeMillis();

		// ���������� ��������
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ϳ��������� �� ��
		try {
			this.db = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		timeout = System.currentTimeMillis() - timeout;
		System.out.println("user:" + USER + " connected to db.. " + "timeout: "
				+ timeout + "ms");

	}

	/**
	 * ����� ������� ��������� ����� Connector �� �� ��������� ������
	 * Singleton
	 * 
	 * @return Connector instance
	 */
	public static Connector getConnection() {
		if (connector == null) {
			return connector = ConnectionInstanceHolder.conn;
		} else {
			return connector;
		}
	}

	public void AddNewFacultet(String _name, String _shortName) {

		String sql = "INSERT INTO faculty VALUES(DEFAULT, '%s', '%s')";
		final String QUERY_ADD_FAC = String.format(sql, _name, _shortName);

		try {
			this.ps = this.db.prepareStatement(QUERY_ADD_FAC);
			this.ps.execute();
			System.out.println(QUERY_ADD_FAC + " Successful!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(QUERY_ADD_FAC + " ERROR!!!");
		}

	}

	public void AddNewGroup(String _name, int _countStud, int _idFacultet) {

		String sql = "INSERT INTO \"group\" VALUES(DEFAULT, '%s', %d, %d)";
		final String QUERY_ADD_GROUP = String.format(sql, _name, _countStud,
				_idFacultet);

		try {
			this.ps = this.db.prepareStatement(QUERY_ADD_GROUP);
			this.ps.execute();
			System.out.println(QUERY_ADD_GROUP + " Successful!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(QUERY_ADD_GROUP + " ERROR!!!");
		}

	}

	public void AddNewTeacher(String _surname, String _name, String _fName,
			String _tel) {

		String sql = "INSERT INTO teacher VALUES(DEFAULT, '%s', '%s', '%s', '%s')";
		final String QUERY_ADD_TEACHER = String.format(sql, _surname, _name,
				_fName, _tel);

		try {
			this.ps = this.db.prepareStatement(QUERY_ADD_TEACHER);
			this.ps.execute();
			System.out.println(QUERY_ADD_TEACHER + " Successful!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(QUERY_ADD_TEACHER + " ERROR!!!");
		}

	}

	public void AddNewSubject(String _name, String _shortName, String _hours,
			boolean _isLection, int _idTeacher) {

		String sql = "INSERT INTO subject VALUES (DEFAULT, '%s', '%s', '%s', %b, %d)";
		final String QUERY_ADD_SUBJECT = String.format(sql, _name, _shortName,
				_hours, _isLection, _idTeacher);

		try {
			this.ps = this.db.prepareStatement(QUERY_ADD_SUBJECT);
			this.ps.execute();
			System.out.println(QUERY_ADD_SUBJECT + " Successful!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(QUERY_ADD_SUBJECT + " ERROR!!!");
		}

	}

	public void AddNewRoom(String _name, boolean _isLaboratory) {

		String sql = "INSERT INTO room VALUES(DEFAULT, '%s', %b)";
		final String QUERY_ADD_ROOM = String.format(sql, _name, _isLaboratory);

		try {
			this.ps = this.db.prepareStatement(QUERY_ADD_ROOM);
			this.ps.execute();
			System.out.println(QUERY_ADD_ROOM + " Successful!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(QUERY_ADD_ROOM + " ERROR!!!");
		}

	}

	public ArrayList<String> getFacultetList() {

		ArrayList<String> arr;

		final String GET_FACULTY_NAMES_LIST = "SELECT * FROM faculty WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_FACULTY_NAMES_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<String>();
			while (this.rs.next()) {
				arr.add(rs.getString("fac_name").trim() + " ("
						+ rs.getString("fac_short").trim() + ")");
			}
			System.out.println(GET_FACULTY_NAMES_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_FACULTY_NAMES_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;

	}

	public ArrayList<Integer> getFacultetIdList() {

		ArrayList<Integer> arr;

		final String GET_FACULTY_ID_LIST = "SELECT fac_id FROM faculty WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_FACULTY_ID_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<Integer>();
			while (this.rs.next()) {
				arr.add(rs.getInt("fac_id"));
			}
			System.out.println(GET_FACULTY_ID_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_FACULTY_ID_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;

	}

	public ArrayList<String> getTeachersList() {

		ArrayList<String> arr;

		final String GET_TEACHERS_LIST = "SELECT teach_name, teach_surname, teach_father_name FROM teacher WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_TEACHERS_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<String>();
			while (this.rs.next()) {
				arr.add(rs.getString("teach_surname").trim() + " "
						+ rs.getString("teach_name").trim() + " "
						+ rs.getString("teach_father_name").trim());
			}
			System.out.println(GET_TEACHERS_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_TEACHERS_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;

	}

	public ArrayList<Integer> getTeachersIdList() {

		ArrayList<Integer> arr;

		final String GET_TEACHERS_ID_LIST = "SELECT teach_id FROM teacher WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_TEACHERS_ID_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<Integer>();
			while (this.rs.next()) {
				arr.add(rs.getInt("teach_id"));
			}
			System.out.println(GET_TEACHERS_ID_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_TEACHERS_ID_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;

	}

	
	public ArrayList<String> getGroupList() {

		ArrayList<String> arr;

		final String GET_GROUP_LIST = "SELECT group_name FROM \"group\" WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_GROUP_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<String>();
			while (this.rs.next()) {
				arr.add(rs.getString("group_name").trim());
			}
			System.out.println(GET_GROUP_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_GROUP_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;
	}

	public ArrayList<Integer> getGroupIdList() {

		ArrayList<Integer> arr;

		final String GET_GROUP_ID_LIST = "SELECT group_id FROM \"group\" WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_GROUP_ID_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<Integer>();
			while (this.rs.next()) {
				arr.add(rs.getInt("group_id"));
			}
			System.out.println(GET_GROUP_ID_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_GROUP_ID_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;
	}
	
	public ArrayList<Integer> getSubjectIdList() {

		ArrayList<Integer> arr;

		final String GET_SUBJECT_ID_LIST = "SELECT subj_id FROM subject WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_SUBJECT_ID_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<Integer>();
			while (this.rs.next()) {
				arr.add(rs.getInt("subj_id"));
			}
			System.out.println(GET_SUBJECT_ID_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_SUBJECT_ID_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;
	}

	public ArrayList<String> getAllSubjectList(){

		ArrayList<String> arr;

		final String GET_All_SUBJECTS_LIST = "SELECT subj_name FROM subject WHERE true";
		try {
			this.ps = this.db.prepareStatement(GET_All_SUBJECTS_LIST);
			this.rs = this.ps.executeQuery();

			arr = new ArrayList<String>();
			while (this.rs.next()) {
				arr.add(rs.getString("subj_name").trim());
			}
			System.out.println(GET_All_SUBJECTS_LIST + " Succesfull!");
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(GET_All_SUBJECTS_LIST + " FALLING DOWN!!!");
		} finally {
			this.rs = null;
			this.ps = null;
			arr = null;
		}

		return arr;
		
	}
	
	public ArrayList<Integer> getSubjectsIdByIdGroup(int _id){
		
		ArrayList<Integer> arr;
		
		final String GET_SUBJ_ID_BY_ID_GROUP = "SELECT subj_id FROM subject, subjet_group, \"group\" "
											+ "WHERE (group_subject_id_group = "+_id+") and "
												+ "(group_subject_id_subject = subj_id) and "
												+ "(group_subject_id_group = group_id)";
		try{
			this.ps = db.prepareStatement(GET_SUBJ_ID_BY_ID_GROUP);
			this.ps.execute();
			arr = new ArrayList<Integer>();
			while (rs.next()){
				arr.add(rs.getInt("subj_id"), null);
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			this.ps = null;
			this.rs = null;
			arr = null;
			
		}
		
		return arr;
		
	}
	
	/**
	 * class ConnectionInstanceHolder ����������� ��� ������� ���������� �����
	 * Connection �� �������������� ��������� ��� ���������������� ���������
	 */
	static class ConnectionInstanceHolder {
		private static final Connector conn = new Connector();
	}

}
