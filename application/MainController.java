package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import database.Connector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	private Connector conn = Connector.getConnection();

	@FXML private TabPane 			tabPanel;

	@FXML private TextField 		fac_name;
	@FXML private TextField 		fac_short;
	@FXML private Button 			fac_save;
	@FXML private Button 			fac_clear;
	@FXML private ListView<String> 	FacultyList;

	@FXML private TextField 		group_name;
	@FXML private TextField 		group_short;
	@FXML private TextField 		group_count_stud;
	@FXML private ComboBox<String> 	group_fac_name_combo;
	@FXML private ComboBox<Integer> group_fac_id_combo;
	@FXML private Button 			group_save;

	@FXML private TextField 		teach_surname;
	@FXML private TextField 		teach_name;
	@FXML private TextField 		teach_f_name;
	@FXML private TextField 		teach_age;
	@FXML private TextField 		teach_tel;
	@FXML private Button 			teach_save;
    
	@FXML private TextField 		subj_name;
	@FXML private TextField 		subj_short;
	@FXML private TextField 		subj_hour;
	@FXML private ComboBox<String> 	subj_teacher_name_combo;
	@FXML private ComboBox<Integer> subj_teacher_id_combo;
	@FXML private CheckBox 			subj_is_lection;
	@FXML private Button 			subj_save;

	@FXML private TextField 		room_name;
	@FXML private CheckBox 			room_is_laboratory;
	@FXML private Button 			room_save;

	@FXML private ListView<String> 	groupList;
	@FXML private ListView<String> 	selectSubjectsList;
	@FXML private ListView<String> 	allSubjectList;


	private ArrayList<Integer> 		idGroupList = new ArrayList();
	private ArrayList<Integer> 		idSelectedSubjectList = new ArrayList();
	private ArrayList<Integer> 		idAllSubjectList = new ArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tabPanel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if (oldValue != newValue) {
					updateComboBoxes();
					updateFacultetList();
				}
				if (newValue == tabPanel.getTabs().get(tabPanel.getTabs().size() - 1)) {
					
					groupList.getItems().clear();
					idGroupList.clear();
					groupList.getItems().addAll(conn.getGroupList());
					idGroupList.addAll(conn.getGroupIdList());
					
					allSubjectList.getItems().clear();
					idAllSubjectList.clear();
					allSubjectList.getItems().addAll(conn.getAllSubjectList());
					idAllSubjectList.addAll(conn.getSubjectIdList());
					
				}
			}
		});

		group_fac_name_combo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				group_fac_id_combo.getSelectionModel().select( group_fac_name_combo.getSelectionModel().getSelectedIndex());
			}
		});

		subj_teacher_name_combo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				subj_teacher_id_combo.getSelectionModel().select(subj_teacher_name_combo.getSelectionModel().getSelectedIndex());
			}
		});

		this.updateComboBoxes();
		this.updateFacultetList();

	}

	private void updateComboBoxes() {

		group_fac_name_combo.getItems().clear();
		group_fac_id_combo.getItems().clear();
		subj_teacher_name_combo.getItems().clear();
		subj_teacher_id_combo.getItems().clear();

		group_fac_name_combo.getItems().addAll(conn.getFacultetList());
		group_fac_id_combo.getItems().addAll(conn.getFacultetIdList());
		subj_teacher_name_combo.getItems().addAll(conn.getTeachersList());
		subj_teacher_id_combo.getItems().addAll(conn.getTeachersIdList());
	}

	public void FacSaveClick() {
		conn.AddNewFacultet(fac_name.getText(), fac_short.getText());
		updateFacultetList();
		FacClearClick();
	}
	
	public void FacClearClick(){
		fac_name.setText("");
		fac_short.setText("");
	}

	public void updateFacultetList() {
		FacultyList.getItems().clear();
		FacultyList.getItems().addAll(conn.getFacultetList());
	}

	public void GroupSaveClick() {
		int id_facult;
		id_facult = (Integer) group_fac_id_combo.getSelectionModel().getSelectedItem();
		conn.AddNewGroup(group_name.getText(), Integer.parseInt(group_count_stud.getText()), id_facult);
	}

	public void TeachSaveClick() {
		conn.AddNewTeacher(teach_surname.getText(), teach_name.getText(), teach_f_name.getText(), teach_tel.getText());
	}

	public void SubjSaveClick() {
		int id_teacher;
		id_teacher = (Integer) subj_teacher_id_combo.getSelectionModel().getSelectedItem();
		conn.AddNewSubject(subj_name.getText(), subj_short.getText(), subj_hour.getText(), subj_is_lection.isSelected(), id_teacher);
	}

	public void RoomSaveClick() {
		conn.AddNewRoom(room_name.getText(), room_is_laboratory.isSelected());
	}

}
