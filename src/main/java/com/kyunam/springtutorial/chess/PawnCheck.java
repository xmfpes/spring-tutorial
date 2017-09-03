package com.kyunam.springtutorial.chess;

public class PawnCheck {
	private	int [] checkBlackPawn;
	private	int [] checkWhitePawn;
	
	public PawnCheck() {
		checkBlackPawn = new int[8];
		checkWhitePawn = new int[8];
	}
	
	public void updateCheckList(Piece.Color color, int [] check) {
		if(color == Piece.Color.WHITE)
			updateWhitePawnCheckList(check);
		
		if(color == Piece.Color.BLACK)
			updateBlackPawnCheckList(check);
	}
	
	public void updateBlackPawnCheckList(int [] check) {
		for(int i=0; i<check.length; i++) {
			checkBlackPawn[i] += check[i];
		}
	}
	
	public void updateWhitePawnCheckList(int [] check) {
		for(int i=0; i<check.length; i++) {
			checkWhitePawn[i] += check[i];
		}
	}
	
	public int getPawnCheckList(int index, Piece.Color color) {
		if(Piece.Color.BLACK == color)
			return checkBlackPawn[index];
		return checkWhitePawn[index];
	}
}
