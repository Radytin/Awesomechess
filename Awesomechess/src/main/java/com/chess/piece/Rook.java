package com.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.chessboard.Tile;
import static com.chess.chessboard.Move.*;
import com.chess.chessengine.Alliance;
import com.chess.piece.Piece.PieceType;
import com.google.common.collect.ImmutableList;

public class Rook extends Piece{
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8 };

	public Rook(Alliance pieceAlliance,int piecePosition) { 
		super(piecePosition, pieceAlliance);
		
	}
	/**
	 * Check if tile coordinate is valid and if it's exception. If it is, apply offset.
	 * If it's exception, break out.
	 * Check again if coordinates valid and then check if the destination tile's occupied.
	 * If tile's occupied by enemy, use attack move and if not, use major move.
	 * After encountering another piece, break out of loop. 
	 */
	@Override
	public Collection<Move> calculateLegalMoves(final Board board) { 
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES){  
			int candidateDestinationCoordinate = this.piecePosition;
			while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){ 
				
				if (isFirstColumnException(candidateDestinationCoordinate, candidateCoordinateOffset) || 
						isEightColumnException(candidateCoordinateOffset, candidateCoordinateOffset)){ 
					break;
					
					
				}
				candidateDestinationCoordinate += candidateCoordinateOffset;
				if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){ 
					
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					
					if (candidateDestinationTile.isTileFull()){ 
		
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					} else { 
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						
						if (this.pieceAlliance != pieceAlliance) {   
							
							legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
						}
						break;
					}
					
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString(){
		return PieceType.ROOK.toString();
		
	}
    private static boolean isFirstColumnException(final int currentPosition, final int candidateOffset){ 
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    	
    }
    private static boolean isEightColumnException(final int currentPosition, final int candidateOffset){ 
  		return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == 1);
}
}
