package com.chess.chessengine;

import com.chess.player.BlackPlayer;
import com.chess.player.Player;
import com.chess.player.WhitePlayer;

public enum Alliance {
WHITE {
		@Override
		public int getDirection() {
			return -1;
		}

		@Override
		public boolean isBlack() {
			return false;
		}

		@Override
		public boolean isWhite() {
			return true;
		}

		@Override
		public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
			return whitePlayer;
		}
},

BLACK {
		@Override
		public int getDirection() {
			return 1;
		}

		@Override
		public boolean isBlack() {
			return true;
		}

		@Override
		public boolean isWhite() {
			return false;
		}

		@Override
		public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
			return blackPlayer;
		}
	
};

	/**
	 * get the direction of a piece according to the alliance.
	 * @return 1 or -1
	 */
	public abstract int getDirection();

/**
 * Check if piece is black.
 * @return false if not black, true if black.
 */

	public abstract boolean isBlack();

/**
 * Check if piece is white.
 * @return false if not white, true if white.
 */
	public abstract boolean isWhite();

public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);


}
