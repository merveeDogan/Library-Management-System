module LibraryManagement {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	opens application to javafx.graphics, javafx.fxml;
	opens Code to javafx.fxml;
	
	
	exports Code;
}
