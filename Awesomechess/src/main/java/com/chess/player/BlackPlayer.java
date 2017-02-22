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

public class BlackPlayer extends Player {

	public BlackPlayer(final Board board, final Collection<Move> whiteLegalMoves, final Collection<Move> blackLegalMoves) {
	
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

	
	@Override
	protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
		final List<Move> kingCastles = new ArrayList<>();
		//Black's king side castling
		if(this.playerKing.isFirstMove() && this.isInCheck()){
			if(!this.board.getTile(5).isTileFull() && !this.board.getTile(6).isTileFull()){
				final Tile rookTile = this.board.getTile(7);
				
				if(rookTile.isTileFull() && rookTile.getPiece().isFirstMove()){
					if(Player.calculateAttacksOnTile(5, opponentLegals).isEmpty() &&
							Player.calculateAttacksOnTile(6, opponentLegals).isEmpty() &&
							rookTile.getPiece().getPieceType().isRook()){
						
						kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 6, (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 5));
					}
					
				}
					
			}
			
			//Black's queen side castling
			
			if(!this.board.getTile(1).isTileFull() && !this.board.getTile(2).isTileFull() && !this.board.getTile(3).isTileFull()){
				final Tile rookTile = this.board.getTile(0);
				if(rookTile.isTileFull() && rookTile.getPiece().isFirstMove() &&
						Player.calculateAttacksOnTile(2, opponentLegals).isEmpty() &&
						Player.calculateAttacksOnTile(3, opponentLegals).isEmpty() &&
						rookTile.getPiece().getPieceType().isRook()){
					kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 2, (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 3));
				}
				
			}
		}
		
		
		return ImmutableList.copyOf(kingCastles);
	}

}
