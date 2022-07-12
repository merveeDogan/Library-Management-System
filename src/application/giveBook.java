package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import Code.Book;
import Code.PersonDAO_Imp;
import Code.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class giveBook implements Initializable {
	@FXML
	private Text message;
	@FXML
	private TextField textBookSearch;
	@FXML
	private TextField ISBNgiveBook;
	@FXML
	private TextField IDgiveBook;
	@FXML
	private TableView<Book> searchTable;
	@FXML
	private TableColumn<Book ,Integer> ISBN;
	@FXML
	private TableColumn<Book, String> name;
	
	@FXML
	private TableView<User> UserTable;
	@FXML
	private TableColumn<User, Integer> ID;
	@FXML
	private TableColumn<User, String> nameUser;
	@FXML
	private TableColumn<Book, Integer> quantity;
	
	public void giveABook() {
		show();
		searchTable.refresh();
		searchTable.getItems().clear();	
		int ISBNForGive = Integer.parseInt(ISBNgiveBook.getText()); 
		int IDForGive = Integer.parseInt(IDgiveBook.getText());  
		
		if(SceneController.admin.giveBook(ISBNForGive, IDForGive))
			message.setText("Successfully gived");
		else
			message.setText("Error");
		show();
	}
	public void show() {
		 ObservableList<User> UserSearchModelObservableList = FXCollections.observableArrayList();
		 PersonDAO_Imp pdI = new PersonDAO_Imp();
			List<User> listUser = new ArrayList<User>();
			 listUser = (List<User>)pdI.listUsers();
			 for (int i = 0; i < listUser.size(); i++) {
				
				UserSearchModelObservableList.add(listUser.get(i));
			 }
			 ID.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
			 nameUser.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
			 UserTable.setItems(UserSearchModelObservableList);
		
			 ObservableList<Book> bookSearchModelObservableList = FXCollections.observableArrayList();
			 List<Book> list = new ArrayList<Book>();
			 list = SceneController.admin.searchBook("");
			 for (int i = 0; i < list.size(); i++) {			
				bookSearchModelObservableList.add(list.get(i));
			 }
			 name.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
			 ISBN.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ISBN"));		 
			 quantity.setCellValueFactory(new PropertyValueFactory<Book,Integer>("quantity"));
			 searchTable.setItems(bookSearchModelObservableList);	
			 
			 FilteredList<Book> filteredList = new FilteredList<>(bookSearchModelObservableList, e->true);
		        textBookSearch.setOnKeyReleased(e ->{
		            textBookSearch.textProperty().addListener((observableValue, oldValue, newValue) ->{
		            	 filteredList.setPredicate((Predicate<? super Book>) Books ->{
			                    if(newValue ==null || newValue.isEmpty()){
			                        return true;
			                    }
			                    String lowerCaseFilter = newValue.toUpperCase();
			                    if(Books.getName().contains(lowerCaseFilter) || Books.getName().contains(newValue)){
			                        return true;
			                    }else if(Books.getAuthor().contains(lowerCaseFilter)|| Books.getName().contains(newValue)){
			                        return true;
			                    }else if(String.valueOf(Books.getPageNumber()).contains(lowerCaseFilter)){
			                        return true;
			                    }
			                    else if(String.valueOf(Books.getISBN()).contains(lowerCaseFilter)){
		                        return true;
		                    }
			                    return false;
			                });
			            } );
		            SortedList<Book> sortedList = new SortedList<>(filteredList);
		            sortedList.comparatorProperty().bind(searchTable.comparatorProperty());
		            searchTable.setItems(sortedList);
		        });
	 
	}

	@Override
	 public void initialize(URL url, ResourceBundle rb)  {	//giveBook
			show();
		 
	 }

	
}
