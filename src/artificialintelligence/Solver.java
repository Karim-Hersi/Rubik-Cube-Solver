package artificialintelligence;

import java.util.*;

public class Solver{
	public static double threshold;
	public static double minimumCostOfPrunedNode = Double.MAX_VALUE;

	public static List<Move> solvePuzzle(Vertex root){
		List<Move> solution = null;
		threshold = root.getHeuristicValue();
		while(solution == null){
			solution = iterativeDeepeningAStarSearch(root);
			threshold = minimumCostOfPrunedNode;
		}
		return solution;
	}

	public static List<Move> iterativeDeepeningAStarSearch(Vertex startVertex) {
		Stack<Vertex> stack = new Stack<Vertex>();
		startVertex.markStateVisited();
		stack.add(startVertex);

		while (!stack.isEmpty()) {
			Vertex vertex = stack.pop();
			if(vertex.isDestinationState()){
				return vertex.getMovesFromRoot(); //we have the solution
			}else if(shouldContinuePath(vertex)){
				addAdjacentVertices(stack, vertex);//we will check the adjacent vertices
			}
		}
		return null;
	}

	public static boolean shouldContinuePath(Vertex currentVertex){
		double g = currentVertex.getDepth();
		double h = currentVertex.getHeuristicValue();
		double f = g + h;
		if(f <= threshold){
			return true;
		}else if(f < minimumCostOfPrunedNode){
			minimumCostOfPrunedNode = f;
		}
		return false;
	}

	public static void addAdjacentVertices(Stack<Vertex> stack, Vertex vertex){
		List<Vertex> children = vertex.getAdjacentVertices();
		Collections.sort(children, new Comparator<Vertex>() {
			@Override
			public int compare(Vertex v1, Vertex v2) {
				Integer v1Value =  (int) v1.getHeuristicValue();
				Integer v2Value =  (int) v2.getHeuristicValue();
				return v2Value.compareTo(v1Value);
			}
		});
		for (Vertex child : children) {
			stack.add(child);
		}

	}
}
