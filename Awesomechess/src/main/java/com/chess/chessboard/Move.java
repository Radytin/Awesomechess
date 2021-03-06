package com.chess.chessboard;

import com.chess.chessboard.Board.Builder;
import com.chess.piece.Piece;
import com.chess.piece.Rook;
import com.chess.piece.Pawn;


public abstract class Move { 

	protected final Board board;
	protected final Piece movedPiece;
	protected final int destinationCoordinate;
	protected final boolean isFirstMove;
	
	public static final Move NULL_MOVE = new NullMove();
	
	private Move(final Board board,
				 final Piece movedPiece,
				 final int destinationCoordinate) {  
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
		this.isFirstMove = movedPiece.isFirstMove();
	}
	
	private Move(final Board board,
			     final int destinationCoordinate) {  
		this.board = board;
		this.destinationCoordinate = destinationCoordinate;
		this.movedPiece = null;
		this.isFirstMove = false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 30;
		int result = 1;
		result = prime * result + this.destinationCoordinate;
		result = prime * result + this.movedPiece.hashCode();
		result = prime * result + this.movedPiece.getPiecePosition();
		
		
		return result;
	}
	@Override
	public boolean equals(final Object other) {
		
		if (this == other) {
			return true;
		}
		if (!(other instanceof Move)) {
			return false;
		}
		final Move otherMove = (Move) other;
		return getCurrentCoordinate() == otherMove.getCurrentCoordinate() &&
				getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
			    getMovedPiece().equals(otherMove.getMovedPiece());
		
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public int getCurrentCoordinate() {
		return this.getMovedPiece().getPiecePosition();
	}
	
	public int getDestinationCoordinate() {
		return this.destinationCoordinate;
		
	}
	
	public Piece getMovedPiece() {
		return this.movedPiece;
		
	}
	
	public boolean isAttack() {
		return false;
		
	}
	public boolean isCastlingMove() {
		return false;
		
	}
	
	public Piece getAttackedPiece() {
		return null;
	}


	/**
	 * Execute move.
	 * @return build new board
	 */
	public Board execute() {
		
		final Board.Builder builder = new Board.Builder();
		
		for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
			if (!this.movedPiece.equals(piece)) {
				builder.setPiece(piece);
			}
		}
		
		for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
			builder.setPiece(piece);
		}
		
		//Moves the moved piece
		builder.setPiece(this.movedPiece.movePiece(this));
		builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		return builder.build();
	}
	
	public static class MajorAttackMove extends AttackMove {
/**
 * Attack move of a major piece like knight etc.
 * @param board current board
 * @param movedPiece piece that was moved
 * @param destinationCoordinate destination of the move
 * @param attackedPiece piece that is attacked
 */
		public MajorAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinate, attackedPiece);
		}
		@Override
		public boolean equals(final Object other) {
			return this == other || other instanceof MajorAttackMove && super.equals(other);
			
		}
		@Override 
		public String toString() {
			return movedPiece.getPieceType() + BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
		}
	}

	public static final class MajorMove extends Move { 
		
		/**
		 * A move that's not an attacking move.
		 * @param board current board
		 * @param movedPiece piece that was moved
		 * @param destinationCoordinate destination
		 */

		public MajorMove(Board board, Piece movedPiece, int destinationCoordinate) {
			super(board, movedPiece, destinationCoordinate);
			
		}
		@Override
		public boolean equals(final Object other) {
			return this == other || other instanceof MajorMove && super.equals(other);
			
		}
		/**
		 * Prints major move into a string.
		 * @return major move to string
		 */
		public String toString() {
			return movedPiece.getPieceType().toString() + BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
		}

		
	}
	
	
	public static class AttackMove extends Move {  

		final Piece attackedPiece;
		
		/**
		 * An attacking move.
		* @param board current board
		 * @param movedPiece piece that was moved
		 * @param destinationCoordinate destination		
		 * @param attackedPiece piece on destination
		 */
		
		public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, final Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
			
		}
		@Override
		public int hashCode() {
			return this.attackedPiece.hashCode() + super.hashCode();
		}
		@Override
		public boolean equals(final Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof AttackMove)) {
				return false;
			}
			final AttackMove otherAttackMove = (AttackMove) other;
			return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
		}

		@Override
		public boolean isAttack() {
			return true;
		}
		@Override
		public Piece getAttackedPiece() {
			return this.attackedPiece;
		}
	}
    public static final class PawnMove extends Move { 
		
		/**
		 * A Pawn move that's not an attacking move.
		 * @param board current board
		 * @param movedPiece piece that was moved
		 * @param destinationCoordinate destination
		 */

		public PawnMove(Board board, Piece movedPiece, int destinationCoordinate) {
			super(board, movedPiece, destinationCoordinate);
			
		}
		
		@Override
		public boolean equals(final Object other) {
			return this == other || other instanceof PawnMove && super.equals(other);
			
		}
		@Override
		public String toString() {
			return BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
		}

		
	}

    public static class PawnAttackMove extends AttackMove { 
	
	/**
	 * A Pawn move that's an attacking move.
	 * @param board current board
	 * @param movedPiece piece that was moved
	 * @param destinationCoordinate destination
	 * @param attackedPiece piece attacked
	 */

	    public PawnAttackMove(Board board, Piece movedPiece, int destinationCoordinate, final Piece attackedPiece) {
		super(board, movedPiece, destinationCoordinate, attackedPiece);
		
	  	}
	
	    @Override
	    public boolean equals(final Object other) {
		
	    	return this == other || other instanceof PawnAttackMove && super.equals(other);
		
		}
	
	    @Override
	    public String toString() {
	    	return BoardUtils.getPositionAtCoordinate(this.movedPiece.getPiecePosition()).substring(0, 1) + "x" +
				BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
		}

	
    }
    public static final class PawnEnPassantAttackMove extends PawnAttackMove { 
	
	/**
	 * A Pawn En Passant- move.
	 * @param board current board
	 * @param movedPiece piece that was moved
	 * @param destinationCoordinate destination
	 * @param attackedPiece piece attacked
	 * 
	 */

        public PawnEnPassantAttackMove(Board board, Piece movedPiece, int destinationCoordinate, final Piece attackedPiece) {
		super(board, movedPiece, destinationCoordinate, attackedPiece);
		
	    }
	    @Override
	    public boolean equals(final Object other) {
		
		    return this == other || other instanceof PawnEnPassantAttackMove && super.equals(other);
		
	    }
	    @Override
	    public Board execute() {
		    final Builder builder = new Builder();
		    for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
			    if (!this.movedPiece.equals(piece)) {
				    builder.setPiece(piece);
			    }
		    }
		    for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
			    if (!piece.equals(this.getAttackedPiece())) {
				    builder.setPiece(piece);
			    }
		    }
		    builder.setPiece(this.movedPiece.movePiece(this));
		    builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		    return builder.build();
	    }

	
    }
    public static  class PawnPromotion extends Move {
	    final Move decoratedMove;
	    final Pawn promotedPawn;
	/**
	 * Constructor for pawn promotion.
	 * @param decoratedMove move
	 */
	    public PawnPromotion(final Move decoratedMove) {
		    super(decoratedMove.getBoard(), decoratedMove.getMovedPiece(), decoratedMove.getDestinationCoordinate());
		    this.decoratedMove = decoratedMove;
		    this.promotedPawn = (Pawn) this.decoratedMove.getMovedPiece();
	    }
	
	    @Override
	    public int hashCode() {
		    return decoratedMove.hashCode() + (31 * promotedPawn.hashCode());
	    }
	    @Override
	    public boolean equals(final Object other) {
		    return this == other || other instanceof PawnPromotion && (super.equals(other));
	    }
	
	
	    @Override
	    public Board execute() {
		
		    final Board pawnMovedBoard = this.decoratedMove.execute();
		    final Board.Builder builder = new Builder();
		    for (final Piece piece : pawnMovedBoard.currentPlayer().getActivePieces()) {
			    if (!this.promotedPawn.equals(piece)) {
				    builder.setPiece(piece);
			    }
		    }
		    for (final Piece piece : pawnMovedBoard.currentPlayer().getOpponent().getActivePieces()) {
			    builder.setPiece(piece);
		    }
		    builder.setPiece(this.promotedPawn.getPromotionPiece().movePiece(this));
		    builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		
		
		    return builder.build();
		
	    }
	    @Override
	    public boolean isAttack() {
		    return this.decoratedMove.isAttack();
		
	    }
	
	    @Override
	    public Piece getAttackedPiece() {
		    return this.decoratedMove.getAttackedPiece();
		
	    }
	    @Override
	    public String toString() {
		    return "";
		
	    }
    }
    public static final class PawnJump extends Move { 
	
	/**
	 * A Pawn move that's not an attacking move.
	 * @param board current board
	 * @param movedPiece piece that was moved
	 * @param destinationCoordinate destination
	 */

	    public PawnJump(Board board, Piece movedPiece, int destinationCoordinate) {
		    super(board, movedPiece, destinationCoordinate);
		
	    }
	
	    @Override
	    public Board execute() {
		    final Builder builder = new Builder();
		    for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
			    if (!this.movedPiece.equals(piece)) {
				    builder.setPiece(piece);
			    }
		    }
		    for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
			    builder.setPiece(piece);
		    }
		
		    final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
		    builder.setPiece(movedPawn);
		    builder.setEnpassantPawn(movedPawn);
		    builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		    return builder.build();
	
	
	    }
	    @Override
	    public String toString() {
		    return BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
	    }
	
	

	
    }
    static abstract class CastleMove extends Move { 
	
	
	    protected final Rook castleRook;
	    protected final int castleRookStart;
	    protected final int castleRookDestination;
	
	/**
	 * A castle move.
	 * @param board current board
	 * @param movedPiece piece that was moved
	 * @param destinationCoordinate destination
	 */

	    public CastleMove(final Board board, final Piece movedPiece, final int destinationCoordinate,
						     final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
		    super(board, movedPiece, destinationCoordinate);
		    this.castleRook = castleRook;
		    this.castleRookStart = castleRookStart;
		    this.castleRookDestination = castleRookDestination;
		
		
	    }
	    public Rook getCastleRook() {
		    return this.castleRook;
	    }
	    @Override
	    public boolean isCastlingMove() {
		    return true;
	    }
	    @Override
	    public Board execute() {
		
		    final Builder builder = new Builder();
		    for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
			    if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
				    builder.setPiece(piece);
			    }
		    }
		    for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
			    builder.setPiece(piece);
		    }
		    builder.setPiece(this.movedPiece.movePiece(this));
		    builder.setPiece(new Rook(this.castleRook.getPieceAlliance(), this.castleRookDestination));
		    builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		    return builder.build();
	    }
	
	
	    @Override
	    public int hashCode() {
		    final int prime = 31;
		    int result = super.hashCode();
		    result = prime * result + this.castleRook.hashCode();
		    result = prime * result + this.castleRookDestination;
		    return result;
	    }
	
	    @Override
	    public boolean equals(final Object other) {
		    if (this == other) {
			    return true;
		    }
		    if (!(other instanceof CastleMove)) {
			    return false;
			
		    }
		    final CastleMove otherCastleMove = (CastleMove) other;
		    return super.equals(otherCastleMove) && this.castleRook.equals(otherCastleMove.castleRook);
		
		
	    }
	
    }
    public static final class KingSideCastleMove extends CastleMove { 
	
	/**
	 * A king side castle move.
	 * @param board current board
	 * @param movedPiece piece moved
	 * @param destinationCoordinate destination of the move
	 * @param castleRook piece that is castle rook
	 * @param castleRookStart where castle rook move starts
	 * @param castleRookDestination destination of the castle rook
	 */

	    public KingSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate, final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
		    super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
		
	    }
	
	    @Override
	    public boolean equals(Object other) {
		    return this == other || other instanceof KingSideCastleMove && super.equals(other);
		
	    }
	
	    @Override
	    public String toString() {
		    return "O-O";
		
	    }

	
    }
    public static final class QueenSideCastleMove extends CastleMove { 
	
	/**
	 * A queen side castle move.
	 * @param board current board
	 * @param movedPiece piece moved
	 * @param destinationCoordinate destination of the move
	 * @param castleRook piece that is castle rook
	 * @param castleRookStart where castle rook move starts
	 * @param castleRookDestination destination of the castle rook
	 */

	    public QueenSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate, final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
		    super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
		
	    }
	    @Override
	    public boolean equals(Object other) {
		    return this == other || other instanceof QueenSideCastleMove && super.equals(other);
		
	    }
	
	    @Override
	    public String toString() {
		    return "O-O-O";
		
	    }

	
    }
    public static final class NullMove extends Move { 
	
	/**
	 * An invalid move.
	 */
	    public NullMove() {
		    super(null, -1);
		
	    }
	
	    @Override
	    public Board execute() {
		    throw new RuntimeException("Cannot execute a null move!");
	    }
	
	    @Override
	    public int getCurrentCoordinate() {
		    return -1;
	    }

	
    }

    public static class MoveFactory {
	
	    private MoveFactory() {
		    throw new RuntimeException("Not instantiable!");
	    }
	    
	    /**
	     * Creates a move.
	     * @param board current game board
	     * @param currentCoordinate starting coordinate
	     * @param destinationCoordinate destination of a move
	     * @return move
	     */
	
	    public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate) {
		
		    for (Move move : board.getAllLegalMoves()) {
			    if (move.getCurrentCoordinate() == currentCoordinate &&
					      move.getDestinationCoordinate() == destinationCoordinate) {
				    return move;
				
			    }
		    }
		
		    return NULL_MOVE;
		
		
	    }
    }

	
}