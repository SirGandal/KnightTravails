package solver;

import java.util.ArrayList;

/**
 * A representation of a state of the problem.
 */
public final class State {

	private final int ROW_INF = 1;
	private final int ROW_SUP = 8;
	private int COL_INF = 65; //ASCII value of the capital A
	private int COL_SUP = 72; // ASCII value of the capital H
	private int row;
	private int col;
	//The previous state used during a search to reach the current state
	private State parent;
 
	public State(int row, int col) {
		this.row = row;
		this.col = col;
		
		parent = null;
	}
 
	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	/**
	 * This function is used to check if a given state represent a square that is outside the boundary of the board or not
	 * @param newRow The row index of the state that would be created
	 * @param newCol The col index of the state that would be created
	 * @return {@code True} if the new position inside the board, {@code False} if it's outside
	 */
	private boolean checkState(int newRow, int newCol) {
		return(newRow <= ROW_SUP && newRow >= ROW_INF && newCol <= COL_SUP && newCol >= COL_INF);
	}
	
	/**
	 * Finds all possible states reachable from the current state.
	 * @return an {@code ArrayList} with all possible successors
	 */
	public ArrayList<State> findNext() {
		ArrayList<State> next = new ArrayList<State>();
		int newRow, newCol;

		newRow = row + 2;
		newCol = col + 1;
		if(checkState(newRow, newCol)) {
			next.add(new State(newRow, newCol));
		}
		
		newCol = col - 1;
		if(checkState(newRow, newCol))
			next.add(new State(newRow, newCol));

		newRow = row - 2;
		newCol = col + 1;
		if(checkState(newRow, newCol))
			next.add(new State(newRow, newCol));
		
		newCol = col - 1;
		if(checkState(newRow, newCol))
			next.add(new State(newRow, newCol));
		
		newCol = col + 2;
		newRow = row + 1;
		if(checkState(newRow, newCol))
			next.add(new State(newRow, newCol));
		
		newRow = row - 1;
		if(checkState(newRow, newCol))
			next.add(new State(newRow, newCol));

		newCol = col - 2;
		newRow = row + 1;
		if(checkState(newRow, newCol))
			next.add(new State(newRow, newCol));
		
		newRow = row - 1;
		if(checkState(newRow, newCol))
			next.add(new State(newRow, newCol));

		return next;
	}

	/**
	 * Two states are equal if they have the same position.
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == null || obj == null)
			return false;
		if(obj.getClass().equals(this.getClass())) {
			if(((State)obj).getCol() == getCol() && ((State)obj).getRow() == getRow())
				return true;
			else
				return false;
		}
		return false;
	}
 
	@Override
	public String toString() {
		return (char) col + Integer.toString(row);
	}

	public final int getRow() {
		return row;
	}

	public final void setRow(int row) {
		this.row = row;
	}

	public final int getCol() {
		return col;
	}

	public final void setCol(int col) {
		this.col = col;
	}
}
