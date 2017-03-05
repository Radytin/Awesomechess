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
import com.chess.player.ai.StandardBoardEvaluator;


public class PlayerTest {
	
	  @Test
	  public void testSimpleEvaluation() {
	      final Board board = Board.createStandardBoard();
	      final MoveTransition t1 = board.currentPlayer()
	              .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
	                              BoardUtils.getCoordinateAtPosition("e4")));
	      assertTrue(t1.getMoveStatus().isDone());
	      final MoveTransition t2 = t1.getTransitionBoard()
	              .currentPlayer()
	              .makeMove(MoveFactory.createMove(t1.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e7"),
	                        BoardUtils.getCoordinateAtPosition("e5")));
	        assertTrue(t2.getMoveStatus().isDone());
	        assertEquals(new StandardBoardEvaluator().evaluate(t2.getTransitionBoard(), 0), 0);
	}

    @Test
    public void testIllegalMove() {
        final Board board = Board.createStandardBoard();
        final Move m1 = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
                BoardUtils.getCoordinateAtPosition("e6"));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(m1);
        assertFalse(t1.getMoveStatus().isDone());
    }
    @Test
    public void testDiscoveredCheck() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(Alliance.BLACK, 4, false));
        builder.setPiece(new Rook(Alliance.BLACK, 24));
        // White Layout
        builder.setPiece(new Bishop(Alliance.WHITE, 44));
        builder.setPiece(new Rook(Alliance.WHITE, 52));
        builder.setPiece(new King(Alliance.WHITE, 58, false));
        // Set the current player
        builder.setMoveMaker(Alliance.WHITE);
        final Board board = builder.build();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e3"),
                                BoardUtils.getCoordinateAtPosition("b6")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getTransitionBoard().currentPlayer().isInCheck());
        final MoveTransition t2 = t1.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t1.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("a5"),
                        BoardUtils.getCoordinateAtPosition("b5")));
        assertFalse(t2.getMoveStatus().isDone());
        final MoveTransition t3 = t1.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t1.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("a5"),
                        BoardUtils.getCoordinateAtPosition("e5")));
        assertTrue(t3.getMoveStatus().isDone());
}

}


