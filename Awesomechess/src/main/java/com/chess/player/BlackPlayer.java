package com.chess.player;

import java.util.Collection;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;
import com.chess.chessengine.Alliance;
import com.chess.piece.Piece;

public class BlackPlayer extends Player {

	public BlackPlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
	
		super(board, blackLegalMoves, whiteLegalMoves);
	}

	@Override
	public Collection<Piece> getActivePieces() {
		
		return this.board.getBlackPieces();
	}

	@Override
	public Alliance getAlliance() {
		return Alliance.BLACK;
	}

	@Override
	public Player getOpponent() {
		return this.board.whitePlayer();
	}

}
