package com.chess.chessboard;

import java.util.List;
import java.util.Map;

import com.chess.chessengine.Alliance;
import com.chess.piece.Bishop;
import com.chess.piece.King;
import com.chess.piece.Knight;
import com.chess.piece.Pawn;
import com.chess.piece.Piece;
import com.chess.piece.Queen;
import com.chess.piece.Rook;
import com.google.common.collect.ImmutableList;

public class Board {
	
	private final List<Tile> gameBoard;

	private Board(Builder builder) {
		this.gameBoard = createGameBoard(builder);
		
	}

	public Tile getTile(final int tileCoordinate) { 
		
		return null;
	}
	
	private static List<Tile> createGameBoard( final Builder builder){
		final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
		for (int i=0; i < BoardUtils.NUM_TILES; i++){
			tiles[i] = Tile.createTile(i, builder.boardConfiguration.get(i));
			
		}
		return ImmutableList.copyOf(tiles);
		
	}
	
	public static Board createStandardBoard() {
        final Builder builder = new Builder();
        // Black pieces
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4));
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));
        // White pieces
        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new King(Alliance.WHITE, 60));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));
        builder.setMoveMaker(Alliance.WHITE);
        //Assemble the board
        return builder.build();
}
	
	public static class Builder {
		
		Map<Integer, Piece> boardConfiguration;
		Alliance nextMoveMaker;
		
		public Builder(){
			
		}
		
		public Builder setPiece(final Piece piece){
			this.boardConfiguration.put(piece.getPiecePosition(), piece);
			return this;
			
		}
		public Builder setMoveMaker(Alliance nextMoveMaker){
			this.nextMoveMaker = nextMoveMaker; 
			return this;
			
		}
		
		public Board build(){
			return new Board(this);
		}
		
		
	}

}
