package com.chess.chessboard;

import java.util.HashMap;
import java.util.Map;

import com.chess.piece.Piece;

public abstract class Tile {

	
	protected final int tileCoordinate;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllEmptyTiles();
	
	private static Map<Integer, EmptyTile> createAllEmptyTiles() {
		
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		return emptyTileMap;
		
		
	}
	/**
	 * Creates a new tile.
	 * @param tileCoordinate coordinate of the tile
	 * @param piece piece
	 * @return a new occupied or empty tile
	 */
	public static Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
	}
	
	
	private Tile(final int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	/**
	 * Check is tile's full.
	 * @return true or false
	 */
	public abstract boolean isTileFull(); 
	
	/**
	 * Get piece on tile.
	 * @return piece
	 */
		
	public abstract Piece getPiece();
	
	public int getTileCoordinate() {
		return this.tileCoordinate;
		
	}
	
	public static final class EmptyTile extends Tile { 
	
		
		private EmptyTile(final int coordinate) {
			super(coordinate);
		}
		
		@Override
		public String toString() {
			return "-";
			
		}
		
		
		@Override
		public boolean isTileFull() { 
			return false;
		}
		@Override
		public Piece getPiece() { 
		
			return null;
		}
	}
	
	public static final class OccupiedTile extends Tile { 
		private final Piece pieceOnTile;
		private OccupiedTile(int tileCoordinate, final Piece pieceOnTile) { 
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		@Override
		public String toString() { 
			return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
			getPiece().toString();
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
