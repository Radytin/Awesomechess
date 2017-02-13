package com.chess.chessboard;

import java.util.List;
import java.util.Map;

import com.chess.chessengine.Alliance;
import com.chess.piece.Piece;
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
	
	public static Board createStandardBoard(){
		return null;
		
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
