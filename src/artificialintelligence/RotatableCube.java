package artificialintelligence;

import main.SETTINGS;

public class RotatableCube {
	public static final int NUM_OF_FACES = 6;
	public static final int TOP_FACE = 0;
	public static final int LEFT_FACE = 1;
	public static final int FRONT_FACE = 2;
	public static final int RIGHT_FACE = 3;
	public static final int BACK_FACE = 4;
	public static final int BOTTOM_FACE = 5;
	public static final int FIRST_LAYER = 0;
	public static final int LAST_LAYER = SETTINGS.N - 1;
	protected char[][][] state;
	
	public void rotateFaceClockWise(int faceIndex) {
		char[][] face = state[faceIndex];
		for (int layer = 0; layer < SETTINGS.N / 2; layer++) {
			int firstIndex = layer;
			int lastIndex = SETTINGS.N - 1 - layer;
			for(int i = firstIndex; i < lastIndex; ++i) {
				int offset = i - firstIndex;
				char temp = face[firstIndex][i];
				face[firstIndex][i] = face[lastIndex-offset][firstIndex];  
				face[lastIndex-offset][firstIndex] = face[lastIndex][lastIndex - offset]; 
				face[lastIndex][lastIndex - offset] = face[i][lastIndex]; 
				face[i][lastIndex] = temp;
			}
		}
	} 

	public void rotateFaceCounterClockWise(int faceIndex) {
		char[][] face = state[faceIndex];
		for (int layer = 0; layer < SETTINGS.N / 2; layer++) {
			int firstIndex = layer;
			int lastIndex = SETTINGS.N - 1 - layer;
			for(int i = firstIndex; i < lastIndex; ++i) {
				int offset = i - firstIndex;
				char temp = face[firstIndex][i];
				face[firstIndex][i] = face[i][lastIndex];
				face[i][lastIndex] = face[lastIndex][lastIndex - offset];
				face[lastIndex][lastIndex - offset] = face[lastIndex-offset][firstIndex];
				face[lastIndex-offset][firstIndex] = temp;
			}
		}
	}
	
	public void rotatePositiveDirectionAboutXAxis(int layer){
		if(layer == FIRST_LAYER){
			rotateFaceCounterClockWise(FRONT_FACE);
		}else if(layer == LAST_LAYER){
			rotateFaceClockWise(BACK_FACE);
		}
		
		int j = SETTINGS.N - 1;
		int last = SETTINGS.N - 1 - layer;
		char temp;
		for(int i = 0; i < SETTINGS.N; i++){
			temp = state[TOP_FACE][last][i]; //save top last row
			state[TOP_FACE][last][i] = state[RIGHT_FACE][i][layer];//set top-last row to right-layer column
			state[RIGHT_FACE][i][layer] = state[BOTTOM_FACE][layer][j];//set right-layer column to bottom-layer row
			state[BOTTOM_FACE][layer][j] = state[LEFT_FACE][j][last]; //set bottom-layer row to left last column
			state[LEFT_FACE][j][last] = temp; //set left last column to top last row
			j--;
		}
	}

	public void rotateNegativeDirectionAboutXAxis(int layer){
		if( layer == FIRST_LAYER){
			rotateFaceClockWise(FRONT_FACE);
		}else if(layer == LAST_LAYER){
			rotateFaceCounterClockWise(BACK_FACE);
		}
		
		int j = SETTINGS.N - 1;
		int last = SETTINGS.N - 1 - layer;
		char temp;
		for(int i = 0; i < SETTINGS.N; i++){
			temp = state[TOP_FACE][last][i]; //save top last row
			state[TOP_FACE][last][i] = state[LEFT_FACE][j][last];//set top-last row to left-last column
			state[LEFT_FACE][j][last] = state[BOTTOM_FACE][layer][j];//set left last column to bottom-layer row
			state[BOTTOM_FACE][layer][j] = state[RIGHT_FACE][i][layer]; //set bottom-layer row to right layer column
			state[RIGHT_FACE][i][layer] = temp; //set right-layer column to top last row
			j--;
		}
	}

	public void rotatePositiveDirectionAboutYAxis(int layer){
		if(layer == FIRST_LAYER){
			rotateFaceClockWise(RIGHT_FACE);
		}else if(layer == LAST_LAYER){
			rotateFaceCounterClockWise(LEFT_FACE);
		}
		
		int j = SETTINGS.N - 1;
		int last = SETTINGS.N - 1 - layer;
		char temp;
		for(int i = 0; i < SETTINGS.N; i++){
			temp = state[FRONT_FACE][i][last]; //save front
			state[FRONT_FACE][i][last] = state[BOTTOM_FACE][i][last];//set front to bottom
			state[BOTTOM_FACE][i][last] = state[BACK_FACE][j][layer];//set bottom to back
			state[BACK_FACE][j][layer] = state[TOP_FACE][i][last]; //set back to top
			state[TOP_FACE][i][last] = temp; // set top to front
			j--;
		}
	}

	public void  rotateNegativeDirectionAboutYAxis(int layer){
		if(layer == FIRST_LAYER){
			rotateFaceCounterClockWise(RIGHT_FACE);
		}else if(layer == LAST_LAYER){
			rotateFaceClockWise(LEFT_FACE);
		}

		int j = SETTINGS.N - 1;
		int last = SETTINGS.N - 1 - layer;
		char temp;
		for(int i = 0; i < SETTINGS.N; i++){
			temp = state[FRONT_FACE][i][last]; //save front
			state[FRONT_FACE][i][last] = state[TOP_FACE][i][last];//set front to top
			state[TOP_FACE][i][last] = state[BACK_FACE][j][layer];//set top to back
			state[BACK_FACE][j][layer] = state[BOTTOM_FACE][i][last]; //set back to bottom
			state[BOTTOM_FACE][i][last] = temp; // set top to front
			j--;
		}
	}

	public void rotatePositiveDirectionAboutZAxis(int layer){
		if(layer == FIRST_LAYER){
			rotateFaceCounterClockWise(TOP_FACE);
		}else if(layer == LAST_LAYER){
			rotateFaceClockWise(BOTTOM_FACE);
		}

		char temp;
		for(int i = 0; i < SETTINGS.N; i++){
			temp = state[FRONT_FACE][layer][i]; //save front
			state[FRONT_FACE][layer][i] = state[LEFT_FACE][layer][i];//set front to left
			state[LEFT_FACE][layer][i] = state[BACK_FACE][layer][i];//set left to back
			state[BACK_FACE][layer][i] = state[RIGHT_FACE][layer][i]; //set back to right
			state[RIGHT_FACE][layer][i] = temp; // set right to front
		}
	}

	public void rotateNegativeDirectionAboutZAxis(int layer){
		if(layer == FIRST_LAYER){
			rotateFaceClockWise(TOP_FACE);
		}else if(layer == LAST_LAYER){
			rotateFaceCounterClockWise(BOTTOM_FACE);
		}

		char temp;
		for(int i = 0; i < SETTINGS.N; i++){
			temp = state[FRONT_FACE][layer][i]; //save front
			state[FRONT_FACE][layer][i] = state[RIGHT_FACE][layer][i];//set front to right
			state[RIGHT_FACE][layer][i] = state[BACK_FACE][layer][i];//set right to back
			state[BACK_FACE][layer][i] = state[LEFT_FACE][layer][i]; //set back to left
			state[LEFT_FACE][layer][i] = temp; // set left to front
		}
	}

	public void rotateAboutXAxis(Direction direction, int layer){
		switch(direction){
		case POSITIVE:  rotatePositiveDirectionAboutXAxis(layer);
		break;

		case NEGATIVE:  rotateNegativeDirectionAboutXAxis(layer);
		break;
		}
	}

	public void rotateAboutYAxis(Direction direction, int layer){
		switch(direction){
		case POSITIVE:  rotatePositiveDirectionAboutYAxis(layer);
		break;

		case NEGATIVE:  rotateNegativeDirectionAboutYAxis(layer);
		break;
		}
	}

	public void rotateAboutZAxis(Direction direction, int layer){
		switch(direction){
		case POSITIVE:  rotatePositiveDirectionAboutZAxis(layer);
		break;

		case NEGATIVE:  rotateNegativeDirectionAboutZAxis(layer);
		break;
		}
	}

	public void applyMove(Move move){
		switch(move.axis){
		case X:	rotateAboutXAxis(move.direction, move.layer);
		break;

		case Y:	rotateAboutYAxis(move.direction, move.layer);
		break; 

		case Z:	rotateAboutZAxis(move.direction, move.layer);
		break; 
		}
	}
}
