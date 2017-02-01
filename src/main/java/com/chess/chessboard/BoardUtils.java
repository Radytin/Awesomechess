package com.chess.chessboard;

public class BoardUtils {
//Utilities for the chessboard
	
	public static final boolean[] FIRST_COLUMN = null;
	public static final boolean[] SECOND_COLUMN = null;
	public static final boolean[] SEVENTH_COLUMN = null;
	public static final boolean[] EIGHT_COLUMN = null;
	
	private BoardUtils(){
		throw new RuntimeException("Can't Instantiate!");
	}
	public static boolean isValidTileCoordinate(int coordinate) {
		
		return coordinate >=0 && coordinate <64;
	}

}
