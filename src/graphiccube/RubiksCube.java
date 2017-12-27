package graphiccube;

import artificialintelligence.*;
import main.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class RubiksCube extends BorderPane{
	public static final int NUM_OF_FACES = 6;
	public static double offset = getOffset();
	private RubikFace faces[];
	private Vertex vertex;
	private HBox bottomBox;

	public RubiksCube(){
		super();
		vertex = new Vertex();
		initializeFaces();

		//Set up the top face
		offset = getOffset();
		HBox topBox = new HBox();
		topBox.getChildren().add(faces[0]);
		topBox.setPadding(new Insets(0, 0, 0, offset));
		setTop(topBox);

		//Set up the 4 faces in the center row
		HBox centerBox = new HBox();
		centerBox.getChildren().addAll(faces[1], faces[2], faces[3],faces[4]);
		setCenter(centerBox);

		//set up the bottom face
		bottomBox = new HBox();
		bottomBox.getChildren().add(faces[5]);
		bottomBox.setPadding(new Insets(0, 0, 0, offset));
		setBottom(bottomBox);
	}

	/**
	 * getOffset(): Based on the dimensions of the cube, decide an offset
	 *              for the top and bottom faces to align well over the second
	 *              face in the middle row.
	 */
	public static double getOffset(){
		double offset = (RubikFace.squareLength * SETTINGS.N);
		offset += (RubikFace.GAP * 2);//make room for the gaps on both sides
		return offset;
	}

	public Vertex getVertex(){
		return vertex;
	}

	public HBox getBottomBox(){
		return bottomBox;
	}

	/**
	 * initializeFaces(): For each face in the cube, instantiate the face
	 *                    and set its color to the default color.
	 */
	public void initializeFaces(){
		faces  = new RubikFace[NUM_OF_FACES];
		char state[][][] = vertex.getState();
		for(int i = 0; i< faces.length; i++){
			faces[i] = new RubikFace(vertex,i); 
			faces[i].changeColors(state[i]); 
		}
	}

	/**
	 * updateCubeInterface(): Ensures the colors of the cube on the user interface
	 *                        match the colors specified by the vertex.
	 */
	public void updateCubeInterface(){
		char state[][][] = vertex.getState();
		for(int i =0; i < faces.length; i++){
			faces[i].changeColors(state[i]);
		}
	}

	/**
	 * changeHoverColor(): Change the hover color of this face.
	 */
	public void changeHoverColor(){
		for(int i =0; i< faces.length; i++){
			faces[i].changeHoverColor();
		}
	}
}