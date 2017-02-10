package com.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.chessengine.Alliance;

public class Pawn  extends Piece{
	
	private final static int[] CANDIDATE_MOVE_COORDINATE ={8, 16}; 

	Pawn( final int piecePosition, final Alliance pieceAlliance) { 
		super(piecePosition, pieceAlliance);
		
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) { 
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){ 
			
			int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
			
			if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { 
				continue;
			}
			if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileFull()){ 
			// TODO Later on going to add PawnMove class to handle this.
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
			} else if (currentCandidateOffset == 16 && this.isFirstMove() && 
					(BoardUtils.SECOND_ROW[this.piecePosition]) && this.getPieceAlliance().isBlack() || 
					(BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())){ 
				final int behindCandidateDestinationCoordinate = this.piecePosition +(this.pieceAlliance.getDirection() * 8);
				if(board.getTile(behindCandidateDestinationCoordinate).isTileFull() &&
						!board.getTile(candidateDestinationCoordinate).isTileFull()){ 
					    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				}
			}
			
		}
		
		return null;
	}
	

	}
