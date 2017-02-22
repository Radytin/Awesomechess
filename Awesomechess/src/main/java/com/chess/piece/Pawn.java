package com.chess.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.chessengine.Alliance;
import com.chess.piece.Piece.PieceType;
import com.google.common.collect.ImmutableList;

public class Pawn  extends Piece {  
	
	private final static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9, 16}; 
	
	/**
	 * Constructor for Pawn.
	 * @param pieceAlliance alliance of piece 
	 * @param piecePosition position of piece
	 */

	public Pawn(final Alliance pieceAlliance, int piecePosition) {  
		super(PieceType.PAWN, piecePosition, pieceAlliance);
		
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) { 
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) { 
			
			int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
			
			if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { 
				continue;
			}
			if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileFull()) { 
			// TODO Later on going to add PawnMove class to handle this..
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
			} else if (currentCandidateOffset == 16 && this.isFirstMove() && 
						 (BoardUtils.SEVENT_RANK[this.piecePosition]) && this.getPieceAlliance().isBlack() || 
						 (BoardUtils.SECOND_RANK[this.piecePosition] && this.getPieceAlliance().isWhite())) {  
				final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
				if (board.getTile(behindCandidateDestinationCoordinate).isTileFull() &&
						  !board.getTile(candidateDestinationCoordinate).isTileFull()) {  
					   legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				}
				
			} else if (currentCandidateOffset == 7 && 
					  !((BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
							  (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) { 
				if (board.getTile(candidateDestinationCoordinate).isTileFull()) {
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						//TODO  Moves for Pawn not defined in Move class yet so stub.
					    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));

					}
				}
				
			
			} else if (currentCandidateOffset == 9 &&
					  !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
					  (BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) { 
				if (board.getTile(candidateDestinationCoordinate).isTileFull()) {
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						//TODO  Moves for Pawn not defined in Move class yet so stub.
					    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));

					}
				}
				
			}
			
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	@Override
	public Pawn movePiece(final Move move) {
		return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
	@Override
	public String toString() { 
		return PieceType.PAWN.toString();
		
	}
	

}
