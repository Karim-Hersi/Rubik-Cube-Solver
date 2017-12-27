package userinterface;

import java.util.List;

import artificialintelligence.*;
import graphiccube.RubiksCube;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.SETTINGS;
import uicomponents.ButtonPalette;
import uicomponents.ConfirmationDialog;
import validators.*;

public class InterfaceControl extends UserInterface{
	public static final int MOVES_PER_SHUFFLE = 1;
	private enum Scene{MAIN, SETTINGS};
	private Scene currentScene = Scene.MAIN;
	private List<Move> movesToSolution = Move.totalMoves;
	public static int counter  = 1;

	public InterfaceControl(){
		super();
		setActionListeners();
	}

	public void clearInstruction(){
		promptLabel.setText("");
	}

	public void makeNextMove(){
		Move currentMove = movesToSolution.get(0);
		if(currentMove == null){
			return;
		}
		movesToSolution.remove(0);
		boolean isFinalMove = movesToSolution.isEmpty();
		setInstruction(currentMove, isFinalMove);
		if(isFinalMove){
			nextButton.setDisable(true);
		}
		applyMove(currentMove);
	}

	public void applyMove(Move move){
		cube.getVertex().applyMove(move);
		cube.updateCubeInterface();
	}

	public void resetWithDimensionChange(){
		if(cube != null){ 
			//prevent the default combo box selection to call this for the dimension value
			cube = new RubiksCube();
			addButtonPaletteToPane();
		}
		setSolveButton();
		clearInstruction();
	}

	public void resetWithoutDimensionChange(){
		cube.getVertex().resetState();
		cube.updateCubeInterface();
		setSolveButton();
		clearInstruction();
	}

	public void setInstruction(Move move, boolean isFinalMove){
		String instructionMessage = "Rotate the ";
		instructionMessage += move.layer + " layer of the cube about the ";
		instructionMessage += move.axis + " axis in the ";
		instructionMessage += move.direction + " direction. ";
		if(isFinalMove){
			instructionMessage += "The cube is now solved.";
		}else{
			instructionMessage += "The result is shown above.";
		}
		promptLabel.setText(instructionMessage);
	}

	public void setPromptMessage(String message){
		promptLabel.setText(message);
	}

	public void setMainScene(){
		outerBorderPane.setCenter(cube);
		solvePuzzleButton.setDisable(false);
		solvePuzzleButton.setText();
		currentScene = Scene.MAIN;
	}

	public void setNextButton(){
		setBottom(nextButton);
		nextButton.setDisable(false);
	}

	public void setOptionsScene(){
		solvePuzzleButton.setDisable(true);
		solvePuzzleButton.removeText();
		outerBorderPane.setCenter(optionsPane);
		currentScene = Scene.SETTINGS;
	}

	public void setSolveButton(){
		setBottom(solvePuzzleButton);
	}

	public void shuffleCube(){
		Vertex vertex = cube.getVertex();
		for(int i = 0; i < MOVES_PER_SHUFFLE; i++){
			Move randomMove = Move.getRandomMove();
			vertex.applyMove(randomMove);
		}
		cube.updateCubeInterface();
	} 

	public void solvePuzzle(){
		Vertex startState = cube.getVertex();
		movesToSolution = Solver.solvePuzzle(startState);
	}

	public void switchScene(){
		if(currentScene == Scene.MAIN){
			setOptionsScene();
		}else{
			setMainScene();
		}
	}
	public void updateDimensions(String newValue){
		int newDimensions = Character.getNumericValue(newValue.charAt(0));
		SETTINGS.setDimensions(newDimensions);
		resetWithDimensionChange();
	}

	public ValidationType validateCube(){
		return Validator.validate(cube.getVertex());
	}

	public void setActionListeners(){
		optionsPane.getDimensionsComboBox().valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(@SuppressWarnings("rawtypes") ObservableValue oberservedObject, String oldValue, String newValue) {
				updateDimensions(newValue);
				Move.resetAllPossibleMoves();
			}    
		});
		mainMenu.getMenuItem().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				switchScene();
				mainMenu.update();
			}
		});  

		nextButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				makeNextMove();
			}
		});

		solvePuzzleButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ValidationType result = validateCube();
				if(result != ValidationType.VALID){
					ConfirmationDialog.display(result);
					return;
				}
				solvePuzzle();
				setNextButton();
				if(movesToSolution == null || movesToSolution.isEmpty()){
					setPromptMessage("Could not find the solution");
					nextButton.setDisable(true);
				}else{
					makeNextMove();
				}
			}
		});

		palette.getShuffleCubeButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				shuffleCube();
			}
		});

		palette.getResetButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				resetWithoutDimensionChange();
			}
		});

		addListenersToColorPalette();

	}
	public void addListenerToColorPaletteItem(int row, int column){
		Button colorPaletteItem = palette.getColorPaletteItem(row, column);
		final char color = ButtonPalette.getColor(row, column);
		colorPaletteItem.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ButtonPalette.setCurrentColor(color);
				cube.changeHoverColor();
			}
		});
	}

	public void addListenersToColorPalette(){
		for(int i = 0; i < ButtonPalette.NUM_ROWS; i++){
			for(int j = 0; j < ButtonPalette.NUM_COLS; j++){
				addListenerToColorPaletteItem(i, j);
			}
		}
	}
}
