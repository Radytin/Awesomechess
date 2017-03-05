package com.chess.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;
import com.chess.chessboard.Tile;
import com.chess.chessengine.Alliance;
import com.chess.piece.Piece;
import com.chess.piece.Rook;
import com.google.common.collect.ImmutableList;

public class WhitePlayer extends Player {
	/**
	 * Constructor for white player.
	 * @param board current board
	 * @param whiteLegalMoves legal moves of the white player
	 * @param blackLegalMoves legal moves of the black player
	 */

	public WhitePlayer(final Board board, final Collection<Move> whiteLegalMoves, final Collection<Move> blackLegalMoves) {
		
		super(board, whiteLegalMoves, blackLegalMoves);
	}

	@Override
	public Collection<Piece> getActivePieces() {
		
		return this.board.getWhitePieces();
	}

	@Override
	public Alliance getAlliance() {
		return Alliance.WHITE;
	}

	@Override
	public Player getOpponent() {
		return this.board.blackPlayer();
	}

	@Override
	protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
		final List<Move> kingCastles = new ArrayList<>();
		//White's king side castling
		if (this.playerKing.isFirstMove() && this.isInCheck()) {
			if (!this.board.getTile(61).isTileFull() && !this.board.getTile(62).isTileFull()) {
				final Tile rookTile = this.board.getTile(63);
				
				if (rookTile.isTileFull() && rookTile.getPiece().isFirstMove()) {
					if (Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
								 Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() &&
								 rookTile.getPiece().getPieceType().isRook()) {
						
						kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 63, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
					}
					
				}
					
			}
			
			//White's queen side castling
			
			if (!this.board.getTile(59).isTileFull() && !this.board.getTile(58).isTileFull() && !this.board.getTile(57).isTileFull()) {
				final Tile rookTile = this.board.getTile(56);
				if (rookTile.isTileFull() && rookTile.getPiece().isFirstMove() &&
							 Player.calculateAttacksOnTile(58, opponentLegals).isEmpty() &&
							 Player.calculateAttacksOnTile(59, opponentLegals).isEmpty() &&
							 rookTile.getPiece().getPieceType().isRook()) {
					kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 58, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
				}
				
			}
		}
		
		
		return ImmutableList.copyOf(kingCastles);
	}

}
