package com.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.chessboard.Tile;
import com.chess.chessengine.Alliance;

public class Knight extends Piece { 
	
	private final static int[] CANDIDATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17};

	Knight(final int piecePosition, Alliance pieceAlliance) { 
		super(piecePosition, pieceAlliance);
		
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) { 
		
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){ 
			
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){ 
				
				if(isFirstColumnException(this.piecePosition, currentCandidateOffset) ||
						isSecondColumnException(this.piecePosition, currentCandidateOffset) || 
						isSeventhColumnException(this.piecePosition, currentCandidateOffset) || 
						isEightColumnException(this.piecePosition, currentCandidateOffset)){
					continue;
				}
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				
				if(candidateDestinationTile.isTileFull()){ 
					//Stubbed out because Move is not ready yet
					legalMoves.add(new Move());
				} else { 
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if(this.pieceAlliance != pieceAlliance){
						//Stubbed out because Move is not ready yet
						legalMoves.add(new Move());
					}
				}
			}
		}
		
		//Maybe a Guava Immutable List here 
		return legalMoves;
	}
	private static boolean isFirstColumnException(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17|| (candidateOffset == -10) || candidateOffset == 6 || candidateOffset == 15);
		
	}
	private static boolean isSecondColumnException(final int currentPosition, final int candidateOffset){
		return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset== 6);
		
	}
   private static boolean isSeventhColumnException(final int currentPosition, final int candidateOffset){
	   return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10 );
   }
   private static boolean isEightColumnException(final int currentPosition, final int candidateOffset){
	   return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == -10 || candidateOffset == -17 );
   }
}
