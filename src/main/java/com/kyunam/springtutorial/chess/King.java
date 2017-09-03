package com.kyunam.springtutorial.chess;

public class King extends Piece{

	private King(Color color, Position position) {
        super(color, Type.KING, position, Direction.everyDirection(), new LimitedMovingStrategy());
    }
    
    public static King createWhite(Position position) {
        return new King(Color.WHITE, position);
    }
    
    public static King createBlack(Position position) {
        return new King(Color.BLACK, position);
    }
    

}
