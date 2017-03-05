package com.chess.boardtest;

import com.chess.chessboard.Board;
import com.chess.chessboard.Board.Builder;
import com.chess.chessboard.BoardUtils;
import com.chess.chessboard.Move;
import com.chess.player.MoveTransition;
import com.chess.player.ai.MiniMax;
import com.chess.player.ai.MoveStrategy;
import com.chess.chessboard.Move.MoveFactory;
import com.chess.chessboard.Tile;
import com.chess.chessengine.Alliance;
import com.chess.piece.Bishop;
import com.chess.piece.King;
import com.chess.piece.Knight;
import com.chess.piece.Pawn;
import com.chess.piece.Piece;
import com.chess.piece.Queen;
import com.chess.piece.Rook;
import com.chess.player.MoveTransition;
import com.google.common.collect.Iterables;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void initialBoard() {

        final Board board = Board.createStandardBoard();
        assertEquals(board.currentPlayer().getLegalMoves().size(), 20);
        assertEquals(board.currentPlayer().getOpponent().getLegalMoves().size(), 20);
        assertFalse(board.currentPlayer().isInCheck());
        assertFalse(board.currentPlayer().isInCheckMate());
        assertFalse(board.currentPlayer().isCastled());
        assertEquals(board.currentPlayer(), board.whitePlayer());
        assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
        assertFalse(board.currentPlayer().getOpponent().isInCheck());
        assertFalse(board.currentPlayer().getOpponent().isInCheckMate());
        assertFalse(board.currentPlayer().getOpponent().isCastled());
       
        

    }

    @Test
    public void testPlainKingMove() {

        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(Alliance.BLACK, 4, false));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new King(Alliance.WHITE, 60, false));
        builder.setMoveMaker(Alliance.WHITE);
        // Set the current player
        final Board board = builder.build();
        System.out.println(board);

        assertEquals(board.whitePlayer().getLegalMoves().size(), 6);
        assertEquals(board.blackPlayer().getLegalMoves().size(), 6);
        assertFalse(board.currentPlayer().isInCheck());
        assertFalse(board.currentPlayer().isInCheckMate());
        assertFalse(board.currentPlayer().getOpponent().isInCheck());
        assertFalse(board.currentPlayer().getOpponent().isInCheckMate());
        assertEquals(board.currentPlayer(), board.whitePlayer());
        assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
       


    }

    

    @Test(expected=RuntimeException.class)
    public void testInvalidBoard() {

        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));
        //white to move
        builder.setMoveMaker(Alliance.WHITE);
        //build the board
        builder.build();
    }

    @Test
    public void testAlgebreicNotation() {
        assertEquals(BoardUtils.getPositionAtCoordinate(0), "a8");
        assertEquals(BoardUtils.getPositionAtCoordinate(1), "b8");
        assertEquals(BoardUtils.getPositionAtCoordinate(2), "c8");
        assertEquals(BoardUtils.getPositionAtCoordinate(3), "d8");
        assertEquals(BoardUtils.getPositionAtCoordinate(4), "e8");
        assertEquals(BoardUtils.getPositionAtCoordinate(5), "f8");
        assertEquals(BoardUtils.getPositionAtCoordinate(6), "g8");
        assertEquals(BoardUtils.getPositionAtCoordinate(7), "h8");
    }
//
//    @Test 
//    public void testFoolsMate(){
//    	
//    	final Board board = Board.createStandardBoard();
//    	final MoveTransition t1 = board.currentPlayer().makeMove(Move.MoveFactory.createMove
//    			(board, BoardUtils.getCoordinateAtPosition("f2"), BoardUtils.getCoordinateAtPosition("f3")));
//    	
//    	assertTrue(t1.getMoveStatus().isDone());
//    	
//    	final MoveTransition t2 = board.currentPlayer().makeMove(Move.MoveFactory.createMove
//    			(board, BoardUtils.getCoordinateAtPosition("e7"), BoardUtils.getCoordinateAtPosition("e5")));
//    	
//    	assertTrue(t2.getMoveStatus().isDone());
//    	
//    	final MoveTransition t3 = board.currentPlayer().makeMove(Move.MoveFactory.createMove
//    			(board, BoardUtils.getCoordinateAtPosition("g2"), BoardUtils.getCoordinateAtPosition("g4")));
//    	
//    	assertTrue(t3.getMoveStatus().isDone());
//    	
//    	final MoveStrategy strategy = new MiniMax(4);
//    	final Move aiMove = strategy.execute(t3.getTransitionBoard());
//    	final Move bestMove = Move.MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d8"), BoardUtils.getCoordinateAtPosition("h4"));
//    	assertEquals(aiMove,bestMove);
//    	
//    }

}


