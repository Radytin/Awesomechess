package com.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.chessboard.Tile;
import com.chess.chessboard.Move.AttackMove;
import com.chess.chessboard.Move.MajorMove;
import com.chess.chessengine.Alliance;
import com.chess.piece.Piece.PieceType;
import com.google.common.collect.ImmutableList;

public class King extends Piece {
	private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};
	
	/**
	 * Constructor for King.
	 * @param pieceAlliance black or white
	 * @param piecePosition position of the piece
	 */

	public King(final Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.KING,piecePosition, pieceAlliance, true);
		
	}
	public King(final Alliance pieceAlliance, final int piecePosition, final boolean isFirstMove) {
		super(PieceType.KING,piecePosition, pieceAlliance, isFirstMove);
		
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) { 
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) { 
			
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset; 
		
		//edge cases
			if (isFirstColumnException(this.piecePosition, currentCandidateOffset) ||
					  isEightColumnException(this.piecePosition, currentCandidateOffset)) { 
			
				continue;
			
			}
			
			if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { 
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				
				if (candidateDestinationTile.isTileFull()) { 
	
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				} else { 
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if (this.pieceAlliance != pieceAlliance) {  
						
						legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
					}
				}
			}
		}
		
		
		
		
		return ImmutableList.copyOf(legalMoves);
	}
	@Override
	public King movePiece(final Move move) {
	return new King(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
	@Override
	public String toString() {
		return PieceType.KING.toString();
		
	}
	private static boolean isFirstColumnException(final int currentPosition, final int candidateOffset) { 
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
		
	}
	private static boolean isEightColumnException(final int currentPosition, final int candidateOffset) { 
		return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
		
	}

	
	

}