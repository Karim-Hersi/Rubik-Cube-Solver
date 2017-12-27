package userinterface;

import graphiccube.*;
import pane.*;
import uicomponents.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.Label;

public class UserInterface extends BorderPane{
	protected static final String CSS_ID = "borderpane";
	public static final int PALETTE_H_OFFSET = 250;
	protected MainMenu mainMenu;
	protected BorderPane outerBorderPane;
	protected RubiksCube cube;
	protected SolvePuzzleButton solvePuzzleButton; 
	protected NextButton nextButton;
	protected OptionsPane optionsPane;
	protected Label promptLabel;
	protected ButtonPalette palette;

	public UserInterface(){
		//initialize Components
		mainMenu = new MainMenu();  
		optionsPane = new OptionsPane();      
		outerBorderPane = new BorderPane();
		cube = new RubiksCube();
		solvePuzzleButton = new SolvePuzzleButton();
		nextButton = new NextButton();
		promptLabel = new Label();
		palette = new ButtonPalette();
		addButtonPaletteToPane();

		// organize the main pane
		outerBorderPane.setPadding(new Insets(30,50,30,50));
		outerBorderPane.setId(CSS_ID);
		outerBorderPane.setCenter(cube);  
		outerBorderPane.setBottom(promptLabel);
		BorderPane.setAlignment(cube,Pos.TOP_CENTER);

		// place the components on the user interface
		setTop(mainMenu);
		setCenter(outerBorderPane);
		setAlignment(outerBorderPane,Pos.TOP_CENTER);
		setBottom(solvePuzzleButton);
	}
	
	public void addButtonPaletteToPane(){
		//add the palette to the right of the cube
		HBox bottomBox = cube.getBottomBox();
		bottomBox.getChildren().add(palette);
		palette.setAlignment(Pos.BOTTOM_CENTER);
		bottomBox.setSpacing(PALETTE_H_OFFSET);
	}
}