package artificialintelligence;

import main.*;

import java.util.*;

public class Move{
	public static List<Move> totalMoves = getAllPossibleMoves();
	public static final Random random = new Random();
	public int layer;
	public Axis axis;
	public Direction direction;

	public Move(int layer, Axis axis, Direction direction){
		this.layer = layer;
		this.axis = axis;
		this.direction = direction;
	}

	@Override
	public String toString(){
		String s = "Rotate the " + layer + " layer ";
		s += "in the " + direction + " direction";
	    s += " about the " + axis  +" axis";
	    
	    return s;
	}

	public static List<Move> getAllPossibleMoves(){
		List<Move> totalMoves = new ArrayList<Move>();
		for(int layer = 0; layer < SETTINGS.N; layer++){
			for(Axis axis : Axis.values()){
				for(Direction direction : Direction.values()){
					totalMoves.add(new Move (layer, axis, direction));
				}
			}
		}
		return totalMoves;
	}
	
	public static void resetAllPossibleMoves(){
		totalMoves = getAllPossibleMoves();
	}
	
    public static Move getRandomMove()  {
        int index;
        int randomLayer = random.nextInt(SETTINGS.N);
        index = random.nextInt(Axis.values().length);
        Axis randomAxis = Axis.values()[index];
        index = random.nextInt(Direction.values().length);
        Direction randomDirection = Direction.values()[index];
        
        return new Move(randomLayer, randomAxis, randomDirection);
    }
}