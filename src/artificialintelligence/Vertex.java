package artificialintelligence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.*;

public class Vertex extends RotatableCube{
	private int depth;
	private final String destinationState = getDestinationState();
	private static Set<String> visitedStates = new HashSet<String>();
	public Move move;
	private Vertex parent;

	public Vertex(){ 
		resetState();
		depth = 0;
	}

	public void resetState(){
		state = new char[NUM_OF_FACES][SETTINGS.N][SETTINGS.N];
		char currentColor;
		for(int i = 0; i < NUM_OF_FACES; i++){
			currentColor = COLORS.COLORS[i];
			for(int j = 0; j < SETTINGS.N; j++){
				for(int k = 0; k < SETTINGS.N; k++){
					state[i][j][k] = currentColor;
				}
			}
		}
	}

	public Vertex(Vertex parent, Move move){
		depth = parent.getDepth() + 1;
		state = parent.cloneState();
		this.move = move;
		this.parent = parent;
		applyMove(move);
	}

	public int getDepth(){
		return depth;
	}

	public Vertex getParent(){
		return parent;
	}

	public static String getDestinationState(){
		String destinationState = "";
		char currentColor;
		for(int i = 0; i < NUM_OF_FACES; i++){
			currentColor = COLORS.COLORS[i];
			for(int j = 0; j < SETTINGS.N; j++){
				for(int k = 0; k < SETTINGS.N; k++){
					destinationState += currentColor;
				}
			}
		}
		return destinationState;
	}

	public List<Move> getMovesFromRoot(){
		List<Move> movesFromRoot = new ArrayList<Move>();
		Vertex currentVertex = this;
		while(currentVertex.getParent() != null){
			movesFromRoot.add(currentVertex.move);
			currentVertex = currentVertex.getParent();
		}

		return reverse(movesFromRoot);
	}

	public static List<Move> reverse(List<Move> list) {
		for(int i = 0, j = list.size() - 1; i < j; i++) {
			list.add(i, list.remove(j));
		}
		return list;
	}

	public char[][][] getState(){
		return state;
	}

	public char[][][] cloneState(){
		char[][][] clone =  new char[NUM_OF_FACES][SETTINGS.N][SETTINGS.N];
		for(int i = 0; i < NUM_OF_FACES; i++){
			for(int j = 0; j < SETTINGS.N; j++){
				for(int k = 0; k < SETTINGS.N; k++){
					clone[i][j][k] = state[i][j][k];
				}
			}
		}
		return clone;
	}

	@Override
	public String toString(){
		String s = "";
		for(int i = 0; i < NUM_OF_FACES; i++){
			for(int j = 0; j < SETTINGS.N; j++){
				for(int k = 0; k < SETTINGS.N; k++){
					s += (state[i][j][k]);
				}
			}
		}
		return s;
	}

	public void setPosition(char color, int face, int row, int column){
		state[face][row][column] = color;
	}

	public int getMinNumOfMoves(char color, int sourceFace){
		int destinationFace = COLORS.getIndexOfColor(color);
		if(sourceFace == TOP_FACE && destinationFace == BOTTOM_FACE){
			return 2;
		}else if(sourceFace == LEFT_FACE && destinationFace == RIGHT_FACE){
			return 2;
		}else if(sourceFace == FRONT_FACE && destinationFace == BACK_FACE){
			return 2;
		}else if(sourceFace == BOTTOM_FACE && destinationFace == TOP_FACE){
			return 2;
		}else if(sourceFace == RIGHT_FACE && destinationFace == LEFT_FACE){
			return 2;
		}else if(sourceFace == BACK_FACE && destinationFace == FRONT_FACE){
			return 2;
		}else{
			return 1;
		}
	}

	public int getHeuristicOfCorners(){
		int last = SETTINGS.N - 1;
		int sum = 0;
		for(int i = 0; i < NUM_OF_FACES; i++){
			sum +=  getMinNumOfMoves(state[i][0][0], i); 
			sum +=  getMinNumOfMoves(state[i][0][last], i);
			sum +=  getMinNumOfMoves(state[i][last][0], i); 
			sum +=  getMinNumOfMoves(state[i][last][last], i); 
		}

		return sum;
	}

	public int getHeuristicOfEdges(){
		int sum = 0;
		int last = SETTINGS.N - 1;
		for(int i = 0; i < NUM_OF_FACES; i++){
			for(int j = 1; j < last; j++){//for each edge
				sum +=  getMinNumOfMoves(state[i][0][j], i); 
				sum +=  getMinNumOfMoves(state[i][last][j], i); 
				sum +=  getMinNumOfMoves(state[i][j][0], i); 
				sum +=  getMinNumOfMoves(state[i][j][last], i); 
			}
		}
		return sum;
	}

	public double getHeuristicValue(){
		return getHeuristicOfEdges() + getHeuristicOfCorners();
	}
	
	public double getManhattanDistance(){
		char[] state = toString().toCharArray();
		char[] destinationState = this.destinationState.toCharArray();
		double distance = 0;
		
		for(int i = 0; i < state.length; i++){
			if(state[i] != destinationState[i]){
				distance++;
			}
		}
		
		return (distance/8);
	}

	public List<Vertex> getAdjacentVertices(){
		List<Vertex> adjacentVertices = new ArrayList<Vertex>();
		for (Move move : Move.totalMoves) {
			Vertex child = new Vertex(this, move);
			adjacentVertices.add(child);
		}
		return adjacentVertices;
	}

	public Vertex chooseVertex(){
		List<Vertex> adjacentVertices = getAdjacentVertices();
		Vertex bestVertex = null;
		double minDistance = Integer.MAX_VALUE;

		for(Vertex adjacentVertex : adjacentVertices){
			double distance = adjacentVertex.getHeuristicValue();
			if(distance < minDistance){
				minDistance = distance;
				bestVertex = adjacentVertex;
			}
		}

		return bestVertex;
	}

	public void markStateVisited(){
		visitedStates.add(toString());
	}

	public boolean isDestinationState(){
		String currentState = toString();
		return currentState.equals(destinationState);
	}
}