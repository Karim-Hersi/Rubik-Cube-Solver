package main;

import userinterface.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Main extends Application {
    public static final String APPLICATION_NAME ="Rubiks Cube";
    private static final String APPLICATION_ICON = "resources/images/cube.png";
    private static final String CSS_FILE = "resources/CSS/cube.css";
	private static final String FA_FONT = "resources/fonts/FontAwesome.otf";
    public static final double PANE_WIDTH = 600;
    public static final double PANE_HEIGHT = 425;
    public static final double FRAME_HEIGHT = 600;
    public static final double FRAME_WIDTH = 800;
    
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
    	loadFonts();
        Scene scene = new Scene(new InterfaceControl());
        scene.getStylesheets().add(CSS_FILE);
        
        //set up the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle(APPLICATION_NAME);
        primaryStage.setWidth(FRAME_WIDTH);
        primaryStage.setHeight(FRAME_HEIGHT);
        primaryStage.getIcons().add(new Image(APPLICATION_ICON));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

	public void loadFonts(){
		Font.loadFont(getClass().getResourceAsStream(FA_FONT), 36);
	}
}