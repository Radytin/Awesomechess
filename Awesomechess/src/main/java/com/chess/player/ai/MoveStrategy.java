package com.chess.player.ai;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;

public interface MoveStrategy {
	/**
	 * Executes the search for best move.
	 * @param board current board
	 * @return best move
	 */
	Move execute(Board board);

}
