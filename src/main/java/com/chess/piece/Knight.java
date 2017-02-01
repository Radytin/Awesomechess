package com.chess.piece;

import java.util.ArrayList;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;
import com.chess.chessboard.Tile;
import com.chess.chessengine.Alliance;

public class Knight extends Piece { 
	
	private final static int[] CANDIDATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17};

	Knight(final int piecePosition, Alliance pieceAlliance) { 
		super(piecePosition, pieceAlliance);
		
	}

	@Override
	public List<Move> calculateLegalMoves(Board board) { 
		
		int candidateDestinationCoordinate;
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES){ 
			
			candidateDestinationCoordinate = this.piecePosition + currentCandidate;
			
			if(true){ 
				
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

}
