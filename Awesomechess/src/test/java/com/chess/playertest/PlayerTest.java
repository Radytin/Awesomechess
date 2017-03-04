package com.chess.playertest;


import static org.junit.Assert.*;

import org.junit.Test;

import com.chess.chessboard.Board;
import com.chess.chessboard.Board.Builder;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.chessboard.Move.MoveFactory;
import com.chess.chessengine.Alliance;
import com.chess.piece.Bishop;
import com.chess.piece.King;
import com.chess.piece.Rook;
import com.chess.player.MoveTransition;


public class PlayerTest {
	


    @Test
    public void testIllegalMove() {
        final Board board = Board.createStandardBoard();
        final Move m1 = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
                BoardUtils.getCoordinateAtPosition("e6"));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(m1);
        assertFalse(t1.getMoveStatus().isDone());
    }

}


