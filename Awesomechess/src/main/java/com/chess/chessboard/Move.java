package com.chess.chessboard;

import com.chess.chessboard.Board.Builder;
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
	
	public int getDestinationCoordinate(){
		return this.destinationCoordinate;
		
	}
	
	public Piece getMovedPiece(){
		return this.movedPiece;
		
	}

	public abstract Board execute();
	
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

		@Override
		public Board execute() {
			
			final Board.Builder builder = new Board.Builder();
			
			for(final Piece piece : this.board.currentPlayer().getActivePieces()){
				//TODO hashcode and equals needs to be done here
				if(!this.movedPiece.equals(piece)){
					builder.setPiece(piece);
				}
			}
			
			for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
				builder.setPiece(piece);
			}
			
			//Moves the moved piece
			builder.setPiece(this.movedPiece.movePiece(this));
			builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
			return builder.build();
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

		@Override
		public Board execute() {
			
			return null;
		}
		
	}


	
}