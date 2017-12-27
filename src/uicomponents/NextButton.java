package uicomponents;

import javafx.scene.control.Button;
import main.Main;

public class NextButton extends Button{
	private static final String BUTTON_TEXT = "Next Step \uf178";
	private static final int HEIGHT = 45;
	private static final double WIDTH = Main.FRAME_WIDTH;
	private String CSS_ID = "nextbutton";

	public NextButton(){
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