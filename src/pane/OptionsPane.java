package pane;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class OptionsPane extends ScrollPane{
	public static final int TITLE_OFFSET = 1;
	public static final int DEFAULT_COMBOBOX_INDEX = 1;
	public static final String SCENE_TITLE = "Options";
	public static final String CSS_GRIDPANE = "gridPane";
	public static final String CSS_SCROLLPANE = "scrollPane";
	public static final String CSS_TITLE = "sceneTitle";
	public static final String LABEL_CSS_ID = "label";
	public static final String DIMENSIONS_LABEL = "Dimensions";
	private ComboBox<String> dimensionsComboBox;

	public OptionsPane(){
		//Set up the grid
		GridPane grid = new GridPane();
		grid.setHgap(80);
		grid.setVgap(20);
		grid.setPadding(new Insets(35, 0, 0, 25));
		grid.setId(CSS_GRIDPANE);

		//Set up the title 
		Label sceneTitle = new Label(SCENE_TITLE);
		sceneTitle.setUnderline(true);
		sceneTitle.setId(CSS_TITLE);
		grid.add(sceneTitle, 0, 0);

		//set up the dimensions option combo box
		Label dimensionLabel = new Label(DIMENSIONS_LABEL);
		dimensionLabel.setId(LABEL_CSS_ID);
		dimensionsComboBox = new ComboBox<String>();
		dimensionsComboBox.getItems().addAll("2 x 2","3 x 3","4 x 4","5 x 5");
		dimensionsComboBox.getSelectionModel().select(DEFAULT_COMBOBOX_INDEX);
		grid.add(dimensionLabel,0,TITLE_OFFSET + 2);
		grid.add(dimensionsComboBox,1,TITLE_OFFSET + 2);

		setContent(grid);
		setFitToWidth(true);
		setId(CSS_SCROLLPANE);
	}
	
	public ComboBox<String> getDimensionsComboBox(){
		return dimensionsComboBox; 
	}
}