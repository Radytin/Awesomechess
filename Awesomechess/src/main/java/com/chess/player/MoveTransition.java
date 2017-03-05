package com.chess.player;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;

public class MoveTransition {
	
	private final Board transitionBoard;
	private final Move move;
	private final MoveStatus moveStatus;
	
	/**
	 * Constructor for move transition
	 * @param transitionBoard board we are transitioning into.
	 * @param move move that we want to make
	 * @param moveStatus status of the move
	 */
	
	public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
		this.transitionBoard = transitionBoard;
		this.move = move;
		this.moveStatus = moveStatus;
	}
	
	public MoveStatus getMoveStatus() {
		return this.moveStatus;
		
	}
	
	public Board getTransitionBoard() {
		return this.transitionBoard;
	}

}
