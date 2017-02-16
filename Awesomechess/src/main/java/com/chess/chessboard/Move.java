package com.chess.chessboard;

import com.chess.piece.Piece;


public abstract class Move { 

	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;
	
	private Move(final Board board,
				 final Piece movedPiece,
				 final int destinationCoordinate) {  
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}
	
	public static final class MajorMove extends Move { 
		
		/**
		 * A move that's not an attacking move.
		 * @param board current board
		 * @param movedPiece piece that was moved
		 * @param destinationCoordinate destination
		 */

		public MajorMove(Board board, Piece movedPiece, int destinationCoordinate) {
			super(board, movedPiece, destinationCoordinate);
			
		}
		
	}
	
	
	public static final class AttackMove extends Move {  

		final Piece attackedPiece;
		
		/**
		 * An attacking move.
		* @param board current board
		 * @param movedPiece piece that was moved
		 * @param destinationCoordinate destination		
		 * @param attackedPiece piece on destination
		 */
		
		public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, final Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
			
		}
		
	}
	
}