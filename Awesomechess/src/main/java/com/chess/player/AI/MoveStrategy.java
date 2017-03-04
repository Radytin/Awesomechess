package com.chess.player.AI;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;

public interface MoveStrategy {
	
	Move execute(Board board);

}
