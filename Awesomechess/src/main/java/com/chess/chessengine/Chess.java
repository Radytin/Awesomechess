package com.chess.chessengine;

import com.chess.chessboard.Board;

public class Chess {
	
	/**
	 * Create a new standard board.
	 * @param args
	 */
	
	public static void main (String[] args){
		
		Board board = Board.createStandardBoard();
		
		System.out.println(board);
	}

}
