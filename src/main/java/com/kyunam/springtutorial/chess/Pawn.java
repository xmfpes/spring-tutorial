package com.kyunam.springtutorial.chess;

import java.util.List;

public class Pawn extends Piece {

	private boolean firstMove = false;

	private Pawn(Color color, Position position, List<Direction> direction) {
		super(color, Type.PAWN, position, Direction.blackPawnDirection(), new PawnMovingStrategy());
	}

	public static Pawn createWhite(Position position) {
		return new Pawn(Color.WHITE, position, Direction.whitePawnDirection());
	}

	public static Pawn createBlack(Position position) {
		return new Pawn(Color.BLACK, position, Direction.blackPawnDirection());
	}
}
