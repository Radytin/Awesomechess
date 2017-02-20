package com.chess.piece;

import java.util.Collection;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;
import com.chess.chessengine.Alliance;

public abstract class Piece { 
	
	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	
	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {  
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		//TODO isFirstMove needs more work
		this.isFirstMove = false;
		this.pieceType = pieceType;
	}
	
	public int getPiecePosition() {
		return this.piecePosition;
		
	}
	public Alliance getPieceAlliance() {   
		return this.pieceAlliance;
		
	}
	/**
	 * Check if it's first move, used for pawn jump.
	 * @return true if it's first move
	 */
	public boolean isFirstMove() { 
		return this.isFirstMove;
		//TODO needs more work
		
	}
	
	public PieceType getPieceType(){
		return this.pieceType;
		
	}
	/**
	 * Collection of legal moves.
	 * @param board current game game board
	 * @return Collection of legal moves.
	 */
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	
	 
	public enum PieceType {
		
		PAWN("P") {
			@Override
			public boolean isKing() {
				
				return false;
			}
		},
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
			
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
			
				return false;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				
				return true;
			}
		};
		
		
		private String pieceName;
		
		PieceType(final String pieceName) {
			this.pieceName = pieceName;
		}
		@Override
		public String toString() {
			return this.pieceName;
			
		}
		public abstract boolean isKing();
		
	}
	
}
