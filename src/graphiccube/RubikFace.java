package graphiccube;

import artificialintelligence.*;
import main.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class RubikFace extends GridPane{
	public static final int GAP = 3;
	public static double squareLength  = getSquareLength();
	private static final String CSS_ID = "rubikscube";
	private RubikSquare[][] squares;

	public RubikFace(Vertex vertex, int face) {
		super();
		squareLength  = getSquareLength();
		squares  = new RubikSquare[SETTINGS.N][SETTINGS.N];
		for(int i=0; i< SETTINGS.N; i++){
			for(int j=0; j< SETTINGS.N;j++){
				squares[i][j] = new RubikSquare(vertex,i,j,face);
				squares[i][j].setPrefSize(squareLength, squareLength);
				add(squares[i][j], j, i);  
			}
		}
		setPadding(new Insets(GAP,GAP,GAP,GAP));
		setId(CSS_ID);
	}
	
	/**
	 * getSquareLength(): Based on the dimensions of the cube, decide a length
	 *                    of the square that will neatly fit the screen
	 */
	public static double getSquareLength(){
		final int MAX_FACES_PER_LINE = 4;
		final double PANE_FACTOR = 0.90;//this pane spans 90% of main pane
		final double FACE_PANE_WIDTH = (PANE_FACTOR * Main.PANE_WIDTH);
		final double SQUARE_LENGTH = (FACE_PANE_WIDTH) / (MAX_FACES_PER_LINE * SETTINGS.N);
		
		return SQUARE_LENGTH;
	}


	/**
	 *  changeHoverColor(): Change the hover color for this Rubik face.
	 */
	public void changeHoverColor() {
		for(int i=0; i< SETTINGS.N; i++){
			for(int j=0; j< SETTINGS.N;j++){
				squares[i][j].updateColor();
			}
		}
	}

	/**
	 *  changeColors(): set the color for this rubik face.
	 */
	public void changeColors(char[][] colors){
		for(int i=0; i< SETTINGS.N; i++){
			for(int j=0; j< SETTINGS.N;j++){
				squares[i][j].changeColor(colors[i][j]);
			}
		}
	}
}