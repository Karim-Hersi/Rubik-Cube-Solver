package graphiccube;

import artificialintelligence.*;
import main.*;
import uicomponents.*;
import javafx.scene.control.Button;
import javafx.beans.binding.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.*;
import javafx.scene.input.*;

public class RubikSquare extends Button{
	private int row, column, face;
	private Vertex vertex;
	private char currentColor;

	public RubikSquare(Vertex vertex, int row, int column, int face){
		this.vertex = vertex;
		this.row = row;
		this.column = column;
		this.face = face;
		setActionListener();
	}

	public char getCurrentColor(){
		return currentColor;
	}

	public void changeColor(char color){
		currentColor = color;
		updateColor(); 
	}

	public void updateColor() {
		String currentColorCSS = COLORS.getCSS(currentColor);
		String hoverColorCSS = COLORS.getCSS(ButtonPalette.currentColor);

		styleProperty().bind(Bindings.when(hoverProperty())
				.then( new SimpleStringProperty(hoverColorCSS) )
				.otherwise(new SimpleStringProperty(currentColorCSS)));
	}

	public void setActionListener(){
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				changeColor(ButtonPalette.currentColor);
				vertex.setPosition(ButtonPalette.currentColor, face, row, column);
			}
		});
	}
}