package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Code.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class fine implements Initializable {
	@FXML
	private TextField textBookSearch;
	@FXML
	private TableView<User> fineTable;
	@FXML
	private TableColumn<User ,Integer> ID;
	@FXML
	private TableColumn<User, String> name;
	@FXML
	private TableColumn<User, String> surname;
	@FXML
	private TableColumn<User, Integer> fineAmount;
	@Override
	 public void initialize(URL url, ResourceBundle rb)  {	//searchBook
		 ObservableList<User> UserSearchModelObservableList = FXCollections.observableArrayList();
			List<User> listUser = new ArrayList<User>();
			 listUser = SceneController.admin.listFine();
			 for (int i = 0; i < listUser.size(); i++) {
				 
				 
				UserSearchModelObservableList.add(listUser.get(i));
			
			 
			 }
			 ID.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
			 name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
			 surname.setCellValueFactory(new PropertyValueFactory<User,String>("surname"));
			 fineTable.setItems(UserSearchModelObservableList);
			 
			
	 
	 
 }

	
}
