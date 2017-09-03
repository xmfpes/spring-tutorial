package com.kyunam.springtutorial.chess;

import java.util.List;

public class PawnMovingStrategy implements MovingStrategy {

	@Override
	public void setPossibilityPosition(ChessBoard board, Piece myPiece) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myPiece.getMoveDirection().size(); i++) {
			setPossibilityPositions(board, myPiece, i);
		}
	}

	@Override
	public void setPossibilityPositions(ChessBoard board, Piece myPiece, int index) {
		// TODO Auto-generated method stub
		List<Position> possibilityPosition = myPiece.getPossibilityPosition();
		Position position = myPiece.getPosition();
		Direction direction = myPiece.getMoveDirection().get(index);

		int myX = position.getX();
		int myY = position.getY();

		int x = direction.getXDegree();
		int y = direction.getYDegree();
		System.out.println("x, y" + x + "," + y);
		int moveX = myX + x;
		int moveY = myY + y;

		if (direction == Direction.NORTH || direction == Direction.SOUTH) {
			if(((Pawn) myPiece).isFirstMove()) {
				moveX += x;
				moveY += y;
			}
		} else {
			if(board.findPiece(moveX, moveY).getType() == Piece.Type.NO_PIECE) {
				return ;
			}
		}
		
		if (!(moveX < 0 || moveY >= 8 || moveY < 0 || moveX >= 8)
				&& !myPiece.isSameTeam(board.findPiece(moveX, moveY))) {
			possibilityPosition.add(new Position(moveX, moveY));
		}
	}

}
