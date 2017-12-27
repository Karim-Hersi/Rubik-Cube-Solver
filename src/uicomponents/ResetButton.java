package uicomponents;
import javafx.scene.control.Label;

public class ResetButton extends Label{
	private static final String ICON_CODE = "\uf021";
	private static final String CSS_ID = "icon";
	private static final String CSS_FONT_SIZE = "-fx-font-size: 19.5px;";

	public ResetButton(){
		super(ICON_CODE);
		setId(CSS_ID);
		setStyle(CSS_FONT_SIZE);
	}
}