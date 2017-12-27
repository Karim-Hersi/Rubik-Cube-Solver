package validators;

import artificialintelligence.*;
import main.*;

public class Validator{
	public static final int NUM_OF_FACES = 6;

	public static ValidationType validate(Vertex vertex){
		if(vertex.isDestinationState()){
			return ValidationType.INVALID_SOLUTION_STATE;
		}

		char[][][] state = vertex.getState();
		int[] colorCounters = new int[NUM_OF_FACES] ;
		for(int i = 0; i < NUM_OF_FACES; i++){
			for(int j = 0; j < SETTINGS.N; j++){
				for(int k = 0; k < SETTINGS.N; k++){
					int index = getColorIndex(state[i][j][k]);
					if(index != -1){
						colorCounters[index]++;
					}
				}
			}
		}

		for(int counter :colorCounters){
			if(counter != (SETTINGS.N * SETTINGS.N)){
				return ValidationType.INVALID_WRONG_COLOR_COUNT;
			}
		}

		return ValidationType.VALID;
	}

	public static int getColorIndex(char color){
		for(int i = 0; i < COLORS.COLORS.length; i++){
			if(color == COLORS.COLORS[i]){
				return i;
			}
		}

		return -1;
	}
}
