package application;

import database.Connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import pojo.GenerationItem;
import tools.GenerationProcess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	private Connector conn = Connector.getConnection();

	@FXML private TabPane 			tabPanel;

	@FXML private TextField 		fac_name;
	@FXML private TextField 		fac_short;
	@FXML private Button 			fac_save;
	@FXML private Button 			fac_cancel;
	@FXML private ListView<String> 	facListView;

	@FXML private TextField 		group_name;
	@FXML private TextField 		group_short;
	@FXML private TextField 		group_count_stud;
	@FXML private ComboBox<String> 	group_fac_name_combo;
	@FXML private ComboBox<Integer> group_fac_id_combo;
	@FXML private Button 			group_save;
	@FXML private ListView<String>  groupListView;

	@FXML private TextField 		teach_surname;
	@FXML private TextField 		teach_name;
	@FXML private TextField 		teach_f_name;
	@FXML private TextField 		teach_age;
	@FXML private TextField 		teach_tel;
	@FXML private Button 			teach_save;
	@FXML private Button            teach_cancel;
	@FXML private ListView<String>  teachListView;
    
	@FXML private TextField 		subj_name;
	@FXML private TextField 		subj_short;
	@FXML private TextField 		subj_hour;
	@FXML private ComboBox<String> 	subj_teacher_name_combo;
	@FXML private ComboBox<Integer> subj_teacher_id_combo;
	@FXML private RadioButton		subj_is_lection;
	@FXML private Button 			subj_save;
	@FXML private Button			subj_cancel;
	@FXML private ListView<String>  subjListView;

	@FXML private TextField 		room_name;
	@FXML private TextField 		room_count;
	@FXML private RadioButton		room_is_laboratory;
	@FXML private Button 			room_save;
	@FXML private Button     		room_cancel;
	@FXML private ListView<String>  roomListView;
	
	@FXML private ListView<String> 	groupList;
	@FXML private ListView<String> 	selectSubjectsList;
	@FXML private ListView<String> 	allSubjectList;
	
	@FXML private Button 			generateBtn;
	@FXML private TextArea 			textArea;
	@FXML private ProgressIndicator	progressIndicator;
	
	@FXML private Button 			addSubjectToList; 
	@FXML private Button 			removeSubjectFromList; 
	
	private ArrayList<Integer> 		idGroupList = new ArrayList<Integer>();
	private ArrayList<Integer> 		idSelectedSubjectList = new ArrayList<Integer>();
	private ArrayList<Integer> 		idAllSubjectList = new ArrayList<Integer>();
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		addSubjectToList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (allSubjectList.getSelectionModel().getSelectedIndex() ==  -1)
					return;
				conn.addNewSubject_group(idGroupList.get(groupList.getSelectionModel().getSelectedIndex()), idAllSubjectList.get(allSubjectList.getSelectionModel().getSelectedIndex()));

				idSelectedSubjectList.add(idAllSubjectList.get(allSubjectList.getSelectionModel().getSelectedIndex()));
				selectSubjectsList.getItems().add(allSubjectList.getSelectionModel().getSelectedItem());
				
				idAllSubjectList.remove(allSubjectList.getSelectionModel().getSelectedIndex());
				allSubjectList.getItems().remove(allSubjectList.getSelectionModel().getSelectedIndex());
			}
		});
		
		removeSubjectFromList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (selectSubjectsList.getSelectionModel().getSelectedIndex() ==  -1)
					return;
				conn.removeSubjectGroup(idGroupList.get(groupList.getSelectionModel().getSelectedIndex()), idSelectedSubjectList.get(selectSubjectsList.getSelectionModel().getSelectedIndex()));
				
				allSubjectList.getItems().add(selectSubjectsList.getSelectionModel().getSelectedItem());
				idAllSubjectList.add(idSelectedSubjectList.get(selectSubjectsList.getSelectionModel().getSelectedIndex()));
				
				idSelectedSubjectList.remove(selectSubjectsList.getSelectionModel().getSelectedIndex());
				selectSubjectsList.getItems().remove(selectSubjectsList.getSelectionModel().getSelectedIndex());
			}
		});
		
		tabPanel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					updateComboBoxes();
					updateFacListView();
					updateTeachListView();
					updateGroupListView();
					updateSubjListView();
					updateRoomListView();
					
				if (newValue == tabPanel.getTabs().get(tabPanel.getTabs().size() - 2)) {
					groupList.getItems().clear();
					idGroupList.clear();
					groupList.getItems().addAll(conn.getGroupList());
					idGroupList.addAll(conn.getGroupIdList());
					
					allSubjectList.getItems().clear();
					idAllSubjectList.clear();
					allSubjectList.getItems().addAll(conn.getAllSubjectList());
					idAllSubjectList.addAll(conn.getAllSubjectIdList());
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
		this.updateFacListView();
		this.updateTeachListView();
		this.updateGroupListView();
		this.updateSubjListView();
		this.updateRoomListView();
	}

	public void GroupListClick(){
		if (groupList.getSelectionModel().getSelectedIndex() != -1){
			
			allSubjectList.getItems().clear();
			idAllSubjectList.clear();
			
			allSubjectList.getItems().addAll(conn.getAllSubjectList());
			idAllSubjectList.addAll(conn.getAllSubjectIdList());
			
			selectSubjectsList.getItems().clear();
			idSelectedSubjectList.clear();
			
			selectSubjectsList.getItems().addAll(conn.getSubjectsNameByIdGroup(idGroupList.get(groupList.getSelectionModel().getSelectedIndex())));
			idSelectedSubjectList.addAll(conn.getSubjectsIdByIdGroup(idGroupList.get(groupList.getSelectionModel().getSelectedIndex())));
			int index;
			for (Integer idSelectItem: idSelectedSubjectList){
				index = 0;
				for (Integer idAllItem: idAllSubjectList){
					if (idSelectItem == idAllItem){
						allSubjectList.getItems().remove(index);
						idAllSubjectList.remove(index);
						break;
					}
					index++;
				}
			}
		}
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
		updateFacListView();
		FacCancelClick();
	}
	
	public void FacCancelClick(){
		fac_name.setText("");
		fac_short.setText("");
	}

	public void updateFacListView() {
		facListView.getItems().clear();
		facListView.getItems().addAll(conn.getFacultetList());
	}

	public void GroupSaveClick() {
		int id_facult;
		id_facult = (Integer) group_fac_id_combo.getSelectionModel().getSelectedItem();
		conn.AddNewGroup(group_name.getText(), Integer.parseInt(group_count_stud.getText()), id_facult);
		updateGroupListView();
		GroupCancelClick();
	}

	public void GroupCancelClick() {
		group_name.setText("");
		group_count_stud.setText("");
	}
	
	public void updateGroupListView() {
		groupListView.getItems().clear();
		groupListView.getItems().addAll(conn.getGroupList());
	}
	
	public void TeachSaveClick() {
		conn.AddNewTeacher(teach_surname.getText(), teach_name.getText(), teach_f_name.getText(), teach_tel.getText());
		updateTeachListView();
		TeachCancelClick();
	}
	
	public void TeachCancelClick() {
		teach_surname.setText("");
		teach_name.setText("");
		teach_f_name.setText("");
		teach_tel.setText("");
	}

	public void updateTeachListView() {
		teachListView.getItems().clear();
		teachListView.getItems().addAll(conn.getTeachersList());
	}
	
	public void SubjSaveClick() {
		int id_teacher;
		id_teacher = (Integer) subj_teacher_id_combo.getSelectionModel().getSelectedItem();
		conn.AddNewSubject(subj_name.getText(), subj_short.getText(), subj_hour.getText(), subj_is_lection.isSelected(), id_teacher);
		updateSubjListView();
		SubjCancelClick();
	}

	public void SubjCancelClick() {
		subj_name.setText("");
		subj_short.setText("");
		subj_hour.setText("");
	}
	
	public void updateSubjListView() {
		subjListView.getItems().clear();
		subjListView.getItems().addAll(conn.getAllSubjectList());
	}
	
	public void RoomSaveClick() {
		conn.AddNewRoom(room_name.getText(), room_count.getText(), room_is_laboratory.isSelected());
		updateRoomListView();
		RoomCancelClick();
	}

	public void RoomCancelClick() {
		room_name.setText("");
		room_count.setText("");
	}

	public void updateRoomListView() {
		roomListView.getItems().clear();
		roomListView.getItems().addAll(conn.getRoomListView());
	}
	
	public void generateBtnClick(){
		
		GenerationItem bestTable = new GenerationItem ();
		bestTable = GenerationProcess.startProcess();

		String fileName = "result";
		File file = new File(fileName);
		try{
			if (!file.exists()){
				file.createNewFile();	
			}
			
			PrintWriter printWriter = new PrintWriter(file.getAbsoluteFile());
			
			try{
				printWriter.write(bestTable.toString());
			} finally{
				printWriter.close();
			}	
			
			StringBuilder sb = new StringBuilder();
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
			this.textArea.setText(sb.toString());	
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
