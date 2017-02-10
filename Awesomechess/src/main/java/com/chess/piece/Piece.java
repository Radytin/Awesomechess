package com.chess.piece;

import java.util.Collection;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;
import com.chess.chessengine.Alliance;

public abstract class Piece { 

	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	
	Piece(final int piecePosition, final Alliance pieceAlliance) { 
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		//TODO isFirstMove needs more work
		this.isFirstMove = false;
	}
	public Alliance getPieceAlliance(){  
		return this.pieceAlliance;
		
	}
	public boolean isFirstMove(){
		return this.isFirstMove;
		
	}
	public abstract Collection<Move> calculateLegalMoves(final Board board);
		
	
}
