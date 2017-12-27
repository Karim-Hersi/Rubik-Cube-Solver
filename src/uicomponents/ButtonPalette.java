package uicomponents;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.*;

public class ButtonPalette extends VBox{
	public static final int NUM_ROWS = 2;
	public static final int NUM_COLS = 3;
	public static final int PALETTE_V_OFFSET = 5;
	public static final int ICON_BUTTON_SPACE = 80;
	public static final int GAP = 5;
	public static int SQUARE_LENGTH = 45;
	public static char currentColor = COLORS.WHITE;
	private Button[][] squares = new Button[NUM_ROWS][NUM_COLS];
	private GridPane colorPalette;
	private ShuffleCubeButton shuffleCubeButton;
	private ResetButton resetButton; 

	public ButtonPalette() {
		super();
		initializeColorPalette();
		addIconButtonsToColorPalette();
	}

	public ResetButton getResetButton(){
		return resetButton;
	}

	public Button getColorPaletteItem(int row, int column){
		return squares[row][column];
	}

	public static char getColor(int row, int column){
		return COLORS.COLORS[(NUM_COLS * row) + column];
	}

	public static void setCurrentColor(char color){
		currentColor = color;
	}

	public ShuffleCubeButton getShuffleCubeButton(){
		return shuffleCubeButton;
	}

	public void initializeColorPalette(){
		colorPalette = new GridPane();
		colorPalette.setHgap(GAP);
		colorPalette.setVgap(GAP);
		for(int i=0; i<NUM_ROWS; i++){
			for(int j=0; j<NUM_COLS;j++){
				initializeColorPaletteItem(i, j);
			}
		}
	}

	public void initializeColorPaletteItem(int row, int column){
		final char color = getColor(row, column);
		squares[row][column] = new Button();
		squares[row][column].setPrefSize(SQUARE_LENGTH, SQUARE_LENGTH);
		colorPalette.add(squares[row][column], column, row);  
		squares[row][column].setStyle(COLORS.getCSS(color));
	}

	public void addIconButtonsToColorPalette(){
		//initialize icon buttons
		shuffleCubeButton = new ShuffleCubeButton();
		resetButton = new ResetButton();
		HBox iconButtonBox = new HBox();
		iconButtonBox.getChildren().addAll(shuffleCubeButton, resetButton);
		iconButtonBox.setSpacing(ICON_BUTTON_SPACE);

		//add the icons below the color gridpane
		getChildren().addAll( colorPalette, iconButtonBox);
		setSpacing(PALETTE_V_OFFSET);
	}
}