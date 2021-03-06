package com.kyunam.springtutorial.chess;

import java.util.List;

public class Pawn extends Piece {

	private boolean firstMove;

	private Pawn(Color color, Position position, List<Direction> direction) {
		super(color, Type.PAWN, position, direction, new PawnMovingStrategy());
		this.firstMove = true;
	}

	public static Pawn createWhite(Position position) {
		return new Pawn(Color.WHITE, position, Direction.whitePawnDirection());
	}

	public static Pawn createBlack(Position position) {
		return new Pawn(Color.BLACK, position, Direction.blackPawnDirection());
	}
	
	@Override
	public void move(Piece target) {
		if(verifyMovePosition(target)) {
			this.position = target.getPosition();
			this.firstMove = false;
			return;
		}
		
		throw new InvalidPositionException(target + "위치로는 이동이 불가능합니다.");
	}
	
	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	@Override
    protected String getWhiteSymbol() {
        return "&#9817;";
    }

    @Override
    protected String getBlackSymbol() {
        return "&#9823;";
    }
}
