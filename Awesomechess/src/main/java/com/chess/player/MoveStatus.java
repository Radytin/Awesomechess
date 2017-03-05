package com.chess.player;

public enum MoveStatus {
	DONE {
		@Override
		public boolean isDone() {
			return true;
		}
	}, ILLEGAL {
		@Override
		public boolean isDone() {
			return false;
		}
	}, LEAVES_PLAYER_IN_CHECK {
		@Override
		public boolean isDone() {
			return false;
		}
	};
	
	/**
	 * Is the move status done.
	 * @return true if done, false otherwise
	 */
	public abstract boolean isDone();

}
