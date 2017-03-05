package com.chess.player.ai;

import com.chess.chessboard.Board;

public interface BoardEvaluator {
	
	/**
	 * Evaluate board.
	 * @param board current board
	 * @param depht search depth
	 * @return evaluation score
	 */
	int evaluate(Board board, int depht);

}
