package com.chess.player.AI;

import com.chess.chessboard.Board;

public interface BoardEvaluator {
	
	int evaluate(Board board, int depht);

}
