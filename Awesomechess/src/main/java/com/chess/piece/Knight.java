package com.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.chess.chessboard.Board;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.chessboard.Tile;
import com.chess.chessengine.Alliance;
import com.chess.piece.Piece.PieceType;
import com.google.common.collect.ImmutableList;

import static com.chess.chessboard.Move.*;
public class Knight extends Piece { 
	
	private final static int[] CANDIDATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17};
	
	/**
	 * Constructor for Knight.
	 * @param pieceAlliance black or white
	 * @param piecePosition position of the piece
	 */

	public Knight(Alliance pieceAlliance, int piecePosition) { 
		super(PieceType.KNIGHT,piecePosition, pieceAlliance);
		
	}

	/**
	 * Check if tile coordinate is valid or exception tile and calculate legal moves.
	 * Check if destination tile is occupied. 
	 * If tile's occupied by enemy use attack move, if not then use major move.
	 */
	@Override
	public Collection<Move> calculateLegalMoves(Board board) { 
		
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) { 
			
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			
			if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {  
				
				if (isFirstColumnException(this.piecePosition, currentCandidateOffset) ||
						  isSecondColumnException(this.piecePosition, currentCandidateOffset) || 
						  isSeventhColumnException(this.piecePosition, currentCandidateOffset) ||  
						  isEightColumnException(this.piecePosition, currentCandidateOffset)) { 
								continue;
				}
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				
				if (!candidateDestinationTile.isTileFull()) { 
	
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
	public Knight movePiece(final Move move) {
		return new Knight(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
	@Override
	public String toString() { 
		return PieceType.KNIGHT.toString();
		
	}
	private static boolean isFirstColumnException(final int currentPosition, final int candidateOffset) { 
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
		
	}
	private static boolean isSecondColumnException(final int currentPosition, final int candidateOffset) { 
		return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
		
	}
    private static boolean isSeventhColumnException(final int currentPosition, final int candidateOffset) { 
	    return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
   	}
    private static boolean isEightColumnException(final int currentPosition, final int candidateOffset) {
	    return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == -10 || candidateOffset == -17);
   	}
}
