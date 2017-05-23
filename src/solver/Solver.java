package solver;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * The class contains methods to solve the problem of the shortest sequence of moves 
 * for a Knight between the starting and ending positions. 
 */
public class Solver {
	//A global variable to store all visited states
	private HashSet<State> visitedStates;

	public Solver() {
		visitedStates = new HashSet<State>();
	}
	
	/**
	 * Finds a solution of the problem using a BFS (Breadth-First Search).
	 * Using the BFS guarantees that the path found is the shortest.
	 * @param initialState is the representation of the starting square
	 * @param endingState is the representation of the ending square
	 */
	public State solve(State initialState, State endingState) {
		visitedStates.clear();
		LinkedList<State> queue = new LinkedList<State>();
		queue.add(initialState);
		
		while(!queue.isEmpty()) {
			State current = queue.pollFirst();
			for(State s : current.findNext()) {
				if(s.equals(endingState)) {
					s.setParent(current);
					return s;
				}
				/*
				 * If the state has not been visited previously it's added
				 * to the queue. In this way loops are avoided.
				 */
				if(!visitedStates.contains(s)) {
					s.setParent(current);
					visitedStates.add(s);
					queue.add(s);
				}
			}
		}
		return null;
	}
	
	/**
	 * @param endingState the state that the knight have to reach.
	 * @return string corresponding to the shortest sequence of moves.
	 */
	public String findPath(State endingState) {
		if(endingState == null){
			return "Path not found";
		}else{
			StringBuffer movesSequence = new StringBuffer();
		
			while(endingState != null) {
				movesSequence.insert(0, endingState + " ");
				endingState = endingState.getParent();
			}
		
			//deleting the starting square from the displayed sequence of moves
			movesSequence.delete(0, 3);
				
			return movesSequence.toString();
		}
	}

}
