
package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import Code.Book;
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

public class deleteBook implements Initializable {
	@FXML
	private TextField ISBNdeleteBook;

	
	@FXML
	private TextField textBookSearch;
	@FXML
	private Text message;
	@FXML
	private TableView<Book> searchTable;
	@FXML
	private TableColumn<Book ,Integer> ISBN;
	@FXML
	private TableColumn<Book, String> name;
	@FXML
	private TableColumn<Book, String> author;
	@FXML
	private TableColumn<Book, Integer> pages;
	@FXML
	private TableColumn<Book, Integer> quantity;
	 ObservableList<Book> bookSearchModelObservableList = FXCollections.observableArrayList();
	public void deleteABook() {
		searchTable.getItems().clear();	
		int ISBNForBook = Integer.parseInt(ISBNdeleteBook.getText()); 
		if(SceneController.admin.deleteBookByISBN(ISBNForBook))
		message.setText("Successful");
		else
			message.setText("Error");
	//	searchTable.getItems().remove(ISBNForBook);
	    showBooks();
		
		
	}
	public void showBooks() {
		
		 
		 
		 List<Book> list = new ArrayList<Book>();
		 			list = SceneController.admin.searchBook("");

					 for (int i = 0; i < list.size(); i++) {
						 if(list.get(i).getQuantity()!=0)
						bookSearchModelObservableList.add(list.get(i));
					 }
					 
					 name.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
					 ISBN.setCellValueFactory(new PropertyValueFactory<Book,Integer>("ISBN"));
					 
					 author.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));		 			 			
					 pages.setCellValueFactory(new PropertyValueFactory<Book,Integer>("pageNumber"));
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
					                    }else if(Books.getAuthor().contains(lowerCaseFilter)|| Books.getAuthor().contains(newValue)){
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
	 public void initialize(URL url, ResourceBundle rb)  {	//searchBook
		showBooks();
	 
 }

	
}

