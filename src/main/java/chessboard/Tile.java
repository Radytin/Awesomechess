package chessboard;

public abstract class Tile {
	
	int tileCoordinate;
	
	Tile(int tileCoordinate){
		this.tileCoordinate=tileCoordinate;
	}
	public abstract boolean isTileFull(); 
		
	public abstract Piece getPiece();
	
	public static final class emptyTile extends Tile{
		
		emptyTile(int coordinate) {
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
		Piece pieceOnTile;
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
