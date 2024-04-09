module Unit_8_PA {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	requires json.simple;
	
	opens application to javafx.graphics, javafx.fxml;
}
