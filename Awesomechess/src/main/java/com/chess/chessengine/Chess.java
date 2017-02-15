package com.chess.chessengine;

import com.chess.chessboard.Board;

public class Chess {
	
	public static void main (String[] args){
		
		Board board = Board.createStandardBoard();
		
		System.out.println(board);
	}

}
