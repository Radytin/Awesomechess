package com.chess.boardtest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chess.chessboard.Board;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move.MoveFactory;
import com.chess.player.MoveTransition;

public class TestCheckMate {
	
	@Test
    public void testFoolsMate() {

        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("f2"),
                                BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t1.getMoveStatus().isDone());

        final MoveTransition t2 = t1.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t1.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                                BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(t2.getMoveStatus().isDone());

        final MoveTransition t3 = t2.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g2"),
                                BoardUtils.getCoordinateAtPosition("g4")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveTransition t4 = t3.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d8"),
                                BoardUtils.getCoordinateAtPosition("h4")));

        assertTrue(t4.getMoveStatus().isDone());

        assertTrue(t4.getTransitionBoard().currentPlayer().isInCheckMate());

    }

    @Test
    public void testScholarsMate() {

        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
                                BoardUtils.getCoordinateAtPosition("e4")));

        assertTrue(t1.getMoveStatus().isDone());

        final MoveTransition t2 = t1.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t1.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("a7"),
                        BoardUtils.getCoordinateAtPosition("a6")));

        assertTrue(t2.getMoveStatus().isDone());

        final MoveTransition t3 = t2.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d1"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveTransition t4 = t3.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("a6"),
                        BoardUtils.getCoordinateAtPosition("a5")));

        assertTrue(t4.getMoveStatus().isDone());

        final MoveTransition t5 = t4.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t4.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));

        assertTrue(t5.getMoveStatus().isDone());

        final MoveTransition t6 = t5.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t5.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("a5"),
                        BoardUtils.getCoordinateAtPosition("a4")));

        assertTrue(t6.getMoveStatus().isDone());

        final MoveTransition t7 = t6.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t6.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f3"),
                        BoardUtils.getCoordinateAtPosition("f7")));

        assertTrue(t7.getMoveStatus().isDone());
        assertTrue(t7.getTransitionBoard().currentPlayer().isInCheckMate());

    }
    @Test
    public void testLegalsMate() {

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

        final MoveTransition t3 = t2.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveTransition t4 = t3.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d7"),
                        BoardUtils.getCoordinateAtPosition("d6")));

        assertTrue(t4.getMoveStatus().isDone());

        final MoveTransition t5 = t4.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t4.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g1"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t5.getMoveStatus().isDone());

        final MoveTransition t6 = t5.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t5.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("c8"),
                        BoardUtils.getCoordinateAtPosition("g4")));

        assertTrue(t6.getMoveStatus().isDone());

        final MoveTransition t7 = t6.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t6.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("b1"),
                        BoardUtils.getCoordinateAtPosition("c3")));

        assertTrue(t7.getMoveStatus().isDone());

        final MoveTransition t8 = t7.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t7.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g7"),
                        BoardUtils.getCoordinateAtPosition("g6")));

        assertTrue(t8.getMoveStatus().isDone());

        final MoveTransition t9 = t8.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t8.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f3"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(t9.getMoveStatus().isDone());

        final MoveTransition t10 = t9.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t9.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g4"),
                        BoardUtils.getCoordinateAtPosition("d1")));

        assertTrue(t10.getMoveStatus().isDone());

        final MoveTransition t11 = t10.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t10.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("c4"),
                        BoardUtils.getCoordinateAtPosition("f7")));

        assertTrue(t11.getMoveStatus().isDone());

        final MoveTransition t12 = t11.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t11.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e8"),
                        BoardUtils.getCoordinateAtPosition("e7")));

        assertTrue(t12.getMoveStatus().isDone());

        final MoveTransition t13 = t12.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t12.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("c3"),
                        BoardUtils.getCoordinateAtPosition("d5")));

        assertTrue(t13.getMoveStatus().isDone());
        assertTrue(t13.getTransitionBoard().currentPlayer().isInCheckMate());

    }

    @Test
    public void testSevenMoveMate() {

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

        final MoveTransition t3 = t2.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d2"),
                        BoardUtils.getCoordinateAtPosition("d4")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveTransition t4 = t3.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d7"),
                        BoardUtils.getCoordinateAtPosition("d6")));

        assertTrue(t4.getMoveStatus().isDone());

        final MoveTransition t5 = t4.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t4.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d4"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(t5.getMoveStatus().isDone());

        final MoveTransition t6 = t5.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t5.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d8"),
                        BoardUtils.getCoordinateAtPosition("e7")));

        assertTrue(t6.getMoveStatus().isDone());

        final MoveTransition t7 = t6.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t6.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e5"),
                        BoardUtils.getCoordinateAtPosition("d6")));

        assertTrue(t7.getMoveStatus().isDone());

        final MoveTransition t8 = t7.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t7.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e4")));

        assertTrue(t8.getMoveStatus().isDone());

        final MoveTransition t9 = t8.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t8.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("e2")));

        assertTrue(t9.getMoveStatus().isDone());

        final MoveTransition t10 = t9.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t9.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e4"),
                        BoardUtils.getCoordinateAtPosition("g2")));

        assertTrue(t10.getMoveStatus().isDone());

        final MoveTransition t11 = t10.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t10.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d6"),
                        BoardUtils.getCoordinateAtPosition("c7")));

        assertTrue(t11.getMoveStatus().isDone());

        final MoveTransition t12 = t11.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t11.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g2"),
                        BoardUtils.getCoordinateAtPosition("h1")));

        assertTrue(t12.getMoveStatus().isDone());

        final MoveTransition t13 = t12.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t12.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d1"),
                        BoardUtils.getCoordinateAtPosition("d8")));

        assertTrue(t13.getMoveStatus().isDone());
        assertTrue(t13.getTransitionBoard().currentPlayer().isInCheckMate());

}
    @Test
    public void testRandomGame() {

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

        final MoveTransition t3 = t2.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g1"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveTransition t4 = t3.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("b8"),
                        BoardUtils.getCoordinateAtPosition("c6")));

        assertTrue(t4.getMoveStatus().isDone());

        final MoveTransition t5 = t4.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t4.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));

        assertTrue(t5.getMoveStatus().isDone());

        final MoveTransition t6 = t5.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t5.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("c6"),
                        BoardUtils.getCoordinateAtPosition("d4")));

        assertTrue(t6.getMoveStatus().isDone());

        final MoveTransition t7 = t6.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t6.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f3"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(t7.getMoveStatus().isDone());

        final MoveTransition t8 = t7.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t7.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d8"),
                        BoardUtils.getCoordinateAtPosition("g5")));

        assertTrue(t8.getMoveStatus().isDone());

        final MoveTransition t9 = t8.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t8.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e5"),
                        BoardUtils.getCoordinateAtPosition("f7")));

        assertTrue(t9.getMoveStatus().isDone());

        final MoveTransition t10 = t9.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t9.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g5"),
                        BoardUtils.getCoordinateAtPosition("g2")));

        assertTrue(t10.getMoveStatus().isDone());

        final MoveTransition t11 = t10.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t10.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("h1"),
                        BoardUtils.getCoordinateAtPosition("f1")));

        assertTrue(t11.getMoveStatus().isDone());

        final MoveTransition t12 = t11.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t11.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g2"),
                        BoardUtils.getCoordinateAtPosition("e4")));

        assertTrue(t12.getMoveStatus().isDone());

        final MoveTransition t13 = t12.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t12.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("c4"),
                        BoardUtils.getCoordinateAtPosition("e2")));

        assertTrue(t13.getMoveStatus().isDone());

        final MoveTransition t14 = t13.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t13.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d4"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t14.getMoveStatus().isDone());
        assertTrue(t14.getTransitionBoard().currentPlayer().isInCheckMate());

    }
    @Test
    public void testBlackburneShillingMate() {

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

        final MoveTransition t3 = t2.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g1"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveTransition t4 = t3.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("b8"),
                        BoardUtils.getCoordinateAtPosition("c6")));

        assertTrue(t4.getMoveStatus().isDone());

        final MoveTransition t5 = t4.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t4.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));

        assertTrue(t5.getMoveStatus().isDone());

        final MoveTransition t6 = t5.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t5.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("c6"),
                        BoardUtils.getCoordinateAtPosition("d4")));

        assertTrue(t6.getMoveStatus().isDone());

        final MoveTransition t7 = t6.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t6.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("f3"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(t7.getMoveStatus().isDone());

        final MoveTransition t8 = t7.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t7.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d8"),
                        BoardUtils.getCoordinateAtPosition("g5")));

        assertTrue(t8.getMoveStatus().isDone());

        final MoveTransition t9 = t8.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t8.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e5"),
                        BoardUtils.getCoordinateAtPosition("f7")));

        assertTrue(t9.getMoveStatus().isDone());

        final MoveTransition t10 = t9.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t9.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g5"),
                        BoardUtils.getCoordinateAtPosition("g2")));

        assertTrue(t10.getMoveStatus().isDone());

        final MoveTransition t11 = t10.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t10.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("h1"),
                        BoardUtils.getCoordinateAtPosition("f1")));

        assertTrue(t11.getMoveStatus().isDone());

        final MoveTransition t12 = t11.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t11.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g2"),
                        BoardUtils.getCoordinateAtPosition("e4")));

        assertTrue(t12.getMoveStatus().isDone());

        final MoveTransition t13 = t12.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t12.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("c4"),
                        BoardUtils.getCoordinateAtPosition("e2")));

        assertTrue(t13.getMoveStatus().isDone());

        final MoveTransition t14 = t13.getTransitionBoard()
                .currentPlayer()
                .makeMove(MoveFactory.createMove(t13.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d4"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t14.getMoveStatus().isDone());
        assertTrue(t14.getTransitionBoard().currentPlayer().isInCheckMate());

    }




}
