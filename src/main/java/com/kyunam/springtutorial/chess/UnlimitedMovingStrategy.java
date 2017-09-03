package com.kyunam.springtutorial.chess;

import java.util.ArrayList;
import java.util.List;

public class UnlimitedMovingStrategy implements MovingStrategy {

	@Override
	public void setPossibilityPosition(ChessBoard board, Piece myPiece) {
		// TODO Auto-generated method stub
		Position position = myPiece.getPosition();
		for(int i=0; i<myPiece.getMoveDirection().size(); i++) {
			setPossibilityPositions(board, myPiece, i);
			myPiece.setPosition(position);
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
		
		int moveX = myX + x;
		int moveY = myY + y;
		
		if(!(moveX< 0 || moveY >= 8 || moveY < 0 || moveX >= 8) && !myPiece.isSameTeam(board.findPiece(moveX, moveY))) {
			System.out.println("x : " + moveX + " y : " + moveY);
			possibilityPosition.add(new Position(moveX, moveY));
			
			if(!(board.findPiece(moveX, moveY).getType() == Piece.Type.NO_PIECE))
				return;
			
			myPiece.setPosition(new Position(moveX, moveY));
			setPossibilityPositions(board, myPiece, index);
		}
	}
	
}
