package com.chess.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.chessboard.Board;
import com.chess.chessboard.Move;
import com.chess.chessengine.Alliance;
import com.chess.piece.King;
import com.chess.piece.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public abstract class Player {
	
	protected final Board board;
	protected final King playerKing;
	protected final Collection<Move> legalMoves;
	private final boolean isInCheck;
	
	Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {
		
		this.board = board;
		this.playerKing = establishKing();
		this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, calculateKingCastles(legalMoves, opponentMoves)));
		this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
	}
	
	public King getPlayerKing() {
		return this.playerKing;
		
	}
	public Collection<Move> getLegalMoves() {
		return this.legalMoves;
		
	}

	protected static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> moves) {
		final List<Move> attackMoves = new ArrayList<>();
		for (Move move : moves) {
			if (piecePosition == move.getDestinationCoordinate()) {
				attackMoves.add(move);
			}
		}
		return ImmutableList.copyOf(attackMoves);
	}

	private King establishKing() {
		for (Piece piece : getActivePieces()) {
			if (piece.getPieceType().isKing()) {
				return (King) piece;
			}
			
		}
		throw new RuntimeException("Shouldn't get here!! Not a valid board.");
		
	}
	
	/**
	 * Checks is the move is included in legal moves.
	 * @param move move we want to make
	 * @return true if it's included, false otherwise
	 */
	
	public boolean isMoveLegal(final Move move) {
		return this.legalMoves.contains(move);
	}
	
	
	public boolean isInCheck() {
		return this.isInCheck;
		
	}
	public boolean isInCheckMate() {
		return this.isInCheck && !hasEscapeMoves();
		
	}
	public boolean isInStaleMate() {
		return !this.isInCheck && !hasEscapeMoves();
	}
	
	protected boolean hasEscapeMoves() {
		
		for (final Move move : this.legalMoves) {
			final MoveTransition transition = makeMove(move);
			if (transition.getMoveStatus().isDone()) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Is the player currently castled.
	 * @return true if player is castled
	 */
	public boolean isCastled() {
		return false;
	}
	/**
	 * Makes a move.
	 * @param move move we want to make
	 * @return move transition
	 */
	public MoveTransition makeMove(final Move move) {
		
		if (!isMoveLegal(move)) {
			return new MoveTransition(this.board, move, MoveStatus.ILLEGAL);
		}
		final Board transitionBoard = move.execute();
		final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
				  transitionBoard.currentPlayer().getLegalMoves());
		
		if (!kingAttacks.isEmpty()) {
			return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
		}
		
		return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
		
	}
	/**
	 * Get the active pieces on board.
	 * @return active pieces
	 */
	public abstract Collection<Piece> getActivePieces();
	/**
	 * Get the alliance of the player.
	 * @return alliance of the player
	 */
	public abstract Alliance getAlliance();
	/**
	 * Get the opponent of the player.
	 * @return opponent
	 */
	public abstract Player getOpponent();
	
	protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals);

}
