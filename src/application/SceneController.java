package application;
import java.io.IOException;
import java.net.URI;
import Code.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.*;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.awt.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("unused")
 public class SceneController {
 public static User user = new User();
 public static Admin admin = new Admin();
 private Stage stage;
 private Scene scene;
 private Parent mainscreen;
 @FXML
	private Button deneme;
 
 	//login register process
 	@FXML
	private TextField password;
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField surname;
	@FXML
 	private TextField token;	
	@FXML 
	private Text errorRegister;
	

	
	///////////////////////////
	@FXML 
	private Text idProfile;
	@FXML 
	private Text hideShow;
	@FXML
	private TextField oldPassword;
	@FXML
	private TextField newPassword;
 	@FXML
 	private AnchorPane parent;
 	
 	@FXML
 	private Button blue;
 	@FXML
 	private BorderPane bp;
 	@FXML private
 	 BorderPane ap;
	@FXML
	private Button exitProgram;
	@FXML
	private Hyperlink gitLink;
	
	PersonDAO_Imp pıD = new PersonDAO_Imp();
	
	
	@FXML
	private void home(MouseEvent event) throws IOException {
		loadPage("home");
	}
	@FXML
	private void addBook(MouseEvent event) throws IOException {
		loadPage("addBook");
	}
	@FXML
	private void userProfile(MouseEvent event) throws IOException {
		loadPage("userProfile");
	}
	@FXML
	private void searchBook(MouseEvent event) throws IOException {
		loadPage("searchBook");
	}
	@FXML
	private void deleteBook(MouseEvent event) throws IOException {
		loadPage("deleteBook");
	}
	@FXML
	private void bookReturn(MouseEvent event) throws IOException {
		loadPage("bookReturn");
	}
	@FXML
	private void fineAdmin(MouseEvent event) throws IOException {
		loadPage("FineAdmin");
	}
	@FXML
	private void giveBook(MouseEvent event) throws IOException {
		loadPage("giveBook");	
	}
	
	public void changePassword() {
		if(LogInScreen.changePassword(admin.getId(), oldPassword.getText(), newPassword.getText()))
			errorRegister.setText("Password changed succesfully");
		else errorRegister.setText("Could not change password. Wrong input");
	}

	public void hideAndShowAdmin() {
		if(hideShow.getText().equals("Hide")){
			idProfile.setText(admin.getName() + " " + admin.getSurname());
			hideShow.setText("Show");
		}
		else {
			idProfile.setText("");
			hideShow.setText("Hide");
		}
		
	}
	@FXML
	private void adminProfile(MouseEvent event) throws IOException {
		
		loadPage("ProfileAdmin");		
		
	}
	@FXML
	private void switchUserProfile(MouseEvent event) throws IOException {
		loadPage("userProfile");
		}
	@FXML
	private void readBook(MouseEvent event) throws IOException {
		loadPage("readBook");
	}

	@FXML
	private void onEnterForRegisterUser(ActionEvent ae) throws IOException{
		switchUserLogInForSignUp(ae);
	}
	@FXML
	private void onEnterForLogInUser(ActionEvent ae) throws IOException{
	   switchUserLogIn(ae);
	}
	@FXML
	private void onEnterForLogInAdmin(ActionEvent ae) throws IOException{
	   switchAdminLogIn(ae);
	}
	@FXML
	private void onEnterAdminRegister(ActionEvent ae) throws IOException{
	   switchAdminLogInForSignUp(ae);
	}

 public void switchUserSignUp(ActionEvent event) throws IOException {

		 mainscreen = FXMLLoader.load(getClass().getResource("UserSignUp.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(mainscreen);
		  stage.setScene(scene);
		  stage.show();

	 }
 public void switchAdminScreen(ActionEvent event) throws IOException {	 
		 mainscreen = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(mainscreen);
		  stage.setScene(scene);
		  stage.show();
	 
	 
 }

 public void switchUserScreen(ActionEvent event) throws IOException {
	mainscreen = FXMLLoader.load(getClass().getResource("UserScreen.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(mainscreen);
  stage.setScene(scene);
  stage.show();
 }
 public void switchHomeScreen(ActionEvent event) throws IOException {
	 mainscreen = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(mainscreen);
  stage.setScene(scene);
  stage.show();
 }
 
 
 public void switchAdminSignUp(ActionEvent event) throws IOException {//GO SIGN UP PAGE FOR ADMIN
	 mainscreen = FXMLLoader.load(getClass().getResource("adminSignUp.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(mainscreen);
  stage.setScene(scene);
  stage.show();
 }
 
 
 


 

 private void loadPage(String page) throws IOException {
	 Parent root = null;
	 root = FXMLLoader.load(getClass().getResource(page+".fxml"));
	 bp.setCenter(root);
 }

 
 public void switchUserLogIn(ActionEvent event) throws IOException {
	 try {
			 int idInt = Integer.parseInt(id.getText());
				String passwordString = password.getText();
				 if(RegisterLogin.LogInUser(idInt, passwordString)) {
				  user = (User) pıD.get(idInt);
				  mainscreen = FXMLLoader.load(getClass().getResource("userLogIn.fxml"));
				  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				  scene = new Scene(mainscreen);
				  stage.setScene(scene);
				  stage.show();
				  
			 }
			 else
				 errorRegister.setText("Wrong Input Try Again.");
		} catch (Exception e) {
			// errorRegister.setText("Wrong Input Try Again.");
		}
 }
 public void switchUserLogInForSignUp(ActionEvent event ) throws IOException {
	
		 int idInt = Integer.parseInt(id.getText());
			String passwordString = password.getText();
			String nameString = name.getText();
			String surnameString = surname.getText();
		 if(RegisterLogin.RegisterUser(idInt, passwordString, nameString, surnameString)) {
			  user = (User) pıD.get(idInt);
			 mainscreen = FXMLLoader.load(getClass().getResource("userLogIn.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(mainscreen);
			  stage.setScene(scene);
			  stage.show();
		 }
		 else
			 errorRegister.setText("Wrong Input Try Again.");
	
		
	
 }
 public void switchAdminLogInForSignUp(ActionEvent event ) throws IOException {//AFTER REGISTER
	 String tokenString = token.getText();
		int idInt = Integer.parseInt(id.getText());
		String passwordString = password.getText();
		String nameString = name.getText();
		String surnameString = surname.getText();
	 if(RegisterLogin.RegisterAdmin(idInt, passwordString, nameString, surnameString, tokenString)) {
		  admin = (Admin) pıD.get(idInt);
		 mainscreen = FXMLLoader.load(getClass().getResource("adminLogIn.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(mainscreen);
		  stage.setScene(scene);
		  stage.show();
	 }
	 else {
		 errorRegister.setText("Wrong Input Try Again.");
	 }
	
 }
 public void switchAdminLogIn(ActionEvent event) throws IOException {
	 try {
	  int idInt = Integer.parseInt(id.getText());
		String passwordString = password.getText();
		 if(RegisterLogin.LogInAdmin(idInt, passwordString)) {
			  admin = (Admin) pıD.get(idInt);
		 mainscreen = FXMLLoader.load(getClass().getResource("adminLogIn.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(mainscreen);
		  stage.setScene(scene);
		  stage.show();
	 }
	 else
		 errorRegister.setText("Wrong Input Try Again.");
} catch (Exception e) {
	 errorRegister.setText("Wrong Input Try Again.");
}
	 
	
	 
 }
 
 
 
 public void switchAdminProfile(ActionEvent event) throws IOException {
	 mainscreen = FXMLLoader.load(getClass().getResource("adminProfile.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(mainscreen);
  stage.setScene(scene);
  stage.show();
 }
 public void exitProgram(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You're about to exit!");
		alert.setContentText("Are you sure you want to close the program? ");
		
		if(alert.showAndWait().get() == ButtonType.OK){
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
		}
		
	}
 public void openGitHub(ActionEvent event)throws URISyntaxException, IOException
 {
	 Desktop.getDesktop().browse(new URI("https://github.com/DenizK7/LibrarySystem/tree/master/LibraryManagement"));
 }
 public void openContact(ActionEvent event)throws URISyntaxException, IOException
 {
	 Desktop.getDesktop().browse(new URI("https://mail.google.com/mail/u/0/#inbox?compose=CllgCJvlqQvzwXStjBZWFXcKqpcvpxXNCGMmBfVfkSwDlvhRWBCmftRKGXshMFlrjfTrbRFxLtg"));
 }
 @FXML
 private ImageView imgMode;
 private boolean isLightMode=true;
 public void changeMode (ActionEvent event){
     isLightMode=!isLightMode;
     if(isLightMode){
         setlightMode();
     }else{
         setDarkMode();
     }
 }
 public void setlightMode(){
	 
	  parent.getStylesheets().remove(getClass().getResource("lightMode.css").toExternalForm());

    parent.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    
    Image image=new Image(getClass().getResourceAsStream("/Images/darkMode.png"));
     
     imgMode.setImage(image);
 }
 private void setDarkMode(){
	  parent.getStylesheets().remove(getClass().getResource("application.css").toExternalForm());
	  parent.getStylesheets().add(getClass().getResource("lightMode.css").toExternalForm());
	  Image image=new Image(getClass().getResourceAsStream("/Images/lightMode.png"));
	    
     imgMode.setImage (image);
 }
}
