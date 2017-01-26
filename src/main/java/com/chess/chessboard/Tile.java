package com.chess.chessboard;

import java.util.HashMap;
import java.util.Map;

import com.chess.piece.Piece;

public abstract class Tile {

	
	protected final int tileCoordinate;
	
	private static final Map<Integer, emptyTile> EMPTY_TILES = createAllEmptyTiles();
	
	private static Map<Integer, emptyTile> createAllEmptyTiles() {
		
		final Map<Integer, emptyTile> emptyTileMap = new HashMap<>();
		
		for (int i= 0; i<64; i++){
			emptyTileMap.put(i, new emptyTile(i));
		}
		
		return null;
	}
	Tile(int tileCoordinate){
		this.tileCoordinate=tileCoordinate;
	}
	
	public abstract boolean isTileFull(); 
		
	public abstract Piece getPiece();
	
	public static final class emptyTile extends Tile{
	
		
		emptyTile(final int coordinate) {
			super(coordinate);
		}
		@Override
		public boolean isTileFull(){
			return false;
		}
		@Override
		public Piece getPiece() {
		
			return null;
		}
	}
	
	public static final class occupiedTile extends Tile{
		private final Piece pieceOnTile;
		occupiedTile(int tileCoordinate, Piece pieceOnTile ){
			super (tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		@Override
		public boolean isTileFull() {
			return true;
		}
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
		
	}

}
