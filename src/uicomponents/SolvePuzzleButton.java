package uicomponents;

import javafx.scene.control.Button;
import main.Main;
public class SolvePuzzleButton extends Button{
	private static final String BUTTON_TEXT = "Solve Puzzle \uf12e ";
	private static final int HEIGHT = 45;
	private static final double WIDTH = Main.FRAME_WIDTH;
	private String CSS_ID = "solvepuzzlebutton";

	public SolvePuzzleButton(){
		super(BUTTON_TEXT);
		setPrefSize(WIDTH, HEIGHT);
		setId(CSS_ID);
	}

	public void setText(){
		setText(BUTTON_TEXT);
	}

	public void removeText(){
		setText("");
	}
}