package application;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Code.Book;
import Code.LogInScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class CurrentBooks implements Initializable{
	@FXML
	private TextField newPassword;
	@FXML
	private TextField oldPassword;
	@FXML
	private Text errorRegister;
	@FXML
	private Text hasFine;
	@FXML
	private Text dates;
	@FXML
	private Text idProfile;
	@FXML
	private TableView<Book> searchTable;
	@FXML
	private TableColumn<Book ,String> name;

	 ObservableList<Book> bookSearchModelObservableList = FXCollections.observableArrayList();

	
	 public void showBooks() {
		List<Book> list = new ArrayList<Book>();
		 list = SceneController.user.getBookReceived();

		 for (int i = 0; i < list.size(); i++) {			
			bookSearchModelObservableList.add(list.get(i));
		 }
		 name.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));	 

		 searchTable.setItems(bookSearchModelObservableList);		
					 
					
	}
		public void changePasswordUser() {
			if(LogInScreen.changePassword(SceneController.user.getId(), oldPassword.getText(), newPassword.getText()))
				errorRegister.setText("Password changed succesfully");
			else errorRegister.setText("Could not change password. Wrong input");
			
		}
	
	@Override
	 public void initialize(URL url, ResourceBundle rb)  {	//searchBook
		showBooks();
		
		 List<Date> list = new ArrayList<Date>();
		 String date = "";
		 list = SceneController.user.getDeadlines();
		 if(list.size()<=3)
		 for (int i = 0; i < list.size(); i++) {
			date+= list.get(i) + "\n";
		}
		 else 
			 date+= list.get(0);
		 dates.setText(date);
		 if(!SceneController.user.isHasFine()) {
				hasFine.setText("You do not have fine.");
			}
			else {
				List<Book> BookReceived = SceneController.user.getBookReceived();
				String bookName = BookReceived.get(0).getName();
				hasFine.setText("Yo have fine\n You must give back " + bookName);
			}
		idProfile.setText(SceneController.user.getName() + " " + SceneController.user.getSurname());
		
	 
 }
}