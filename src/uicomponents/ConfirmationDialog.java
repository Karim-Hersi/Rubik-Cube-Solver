package uicomponents;

import validators.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConfirmationDialog{
	public static final String HEADER = "Invalid Cube";
	public static final String ERROR_MESSAGE_1 = "This cube is already solved!";
	public static final String ERROR_MESSAGE_2 = "This cube does not have an equal count for all colors!";
	public static final String ERROR_MESSAGE_3 = "The current state of this cube is not possible!";
	public static final String[] ERROR_MESSAGES = new String[]{ERROR_MESSAGE_1, ERROR_MESSAGE_2, 
			ERROR_MESSAGE_3};

	public static void display(ValidationType result){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(HEADER);

		String message = "";
		ValidationType[] types = ValidationType.values();
		for(int i = 0; i < types.length; i++){
			if(types[i] == result){
				message += ERROR_MESSAGES[i];
			}
		}
		alert.setContentText(message);
		alert.showAndWait();
	}
}