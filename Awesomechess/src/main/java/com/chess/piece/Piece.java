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
	private final int cachedHashCode;
	
	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {  
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		this.isFirstMove = isFirstMove;
		this.pieceType = pieceType;
		this.cachedHashCode = computeHashCode();
	}
	
	private int computeHashCode() {
		int result = pieceType.hashCode();
		result = 31 * result + pieceAlliance.hashCode();
		result = 31 * result + piecePosition;
		result = 31 * result + (isFirstMove ? 1 : 0);
		return result;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Piece)) {
			return false;
		}
		final Piece otherPiece = (Piece) other; 
		return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
				pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
		
	}
	@Override
	public int hashCode() {
		return this.cachedHashCode;
	
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
	
	public PieceType getPieceType() {
		return this.pieceType;
		
	}
	
	public int getPieceValue() {
		return this.pieceType.getPieceValue();
	}
	/**
	 * Collection of legal moves.
	 * @param board current game game board
	 * @return Collection of legal moves.
	 */
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	
	/**
	 * Move piece.
	 * @param move move
	 * @return move piece
	 */
	public abstract Piece movePiece(Move move);
	
	 
	public enum PieceType {
		
		PAWN("P", 100) {
			@Override
			public boolean isKing() {
				
				return false;
			}

			@Override
			public boolean isRook() {
				
				return false;
			}
		},
		KNIGHT("N", 300) {
			@Override
			public boolean isKing() {
				
				return false;
			}

			@Override
			public boolean isRook() {
				return false;
			}
		},
		BISHOP("B", 300) {
			@Override
			public boolean isKing() {
			
				return false;
			}

			@Override
			public boolean isRook() {
				
				return false;
			}
		},
		ROOK("R", 500) {
			@Override
			public boolean isKing() {
			
				return false;
			}

			@Override
			public boolean isRook() {
				
				return true;
			}
		},
		QUEEN("Q", 900) {
			@Override
			public boolean isKing() {
				
				return false;
			}

			@Override
			public boolean isRook() {
				
				return false;
			}
		},
		KING("K", 10000) {
			@Override
			public boolean isKing() {
				
				return true;
			}

			@Override
			public boolean isRook() {
				
				return false;
			}
		};
		
		
		private String pieceName;
		private int pieceValue;
		
		PieceType(final String pieceName, final int pieceValue) {
			this.pieceName = pieceName;
			this.pieceValue = pieceValue;
		}
		@Override
		public String toString() {
			return this.pieceName;
			
		}
		/**
		 * Get the value of piece.
		 * @return value of piece
		 */
		public int getPieceValue() {
			return this.pieceValue;
		}
		/**
		 * Is the piece king.
		 * @return true if piece is king, false otherwise.
		 */
		public abstract boolean isKing();
		/**
		 * Is the piece Rook.
		 * @return true if piece is Rook, false otherwise.
		 */
		public abstract boolean isRook();
		
		
		
	}
	
}
