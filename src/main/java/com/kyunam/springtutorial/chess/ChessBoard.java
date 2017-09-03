package com.kyunam.springtutorial.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChessBoard {
	public static final String NEWLINE = System.getProperty("line.separator");
	public static final String BLANK = "........";
	private List<Rank> chessBoard;
	private PawnCheck pawnCheckList;
	
	
	public ChessBoard() {
		chessBoard = new ArrayList<Rank>();
		pawnCheckList = new PawnCheck();
	}
	
	public void initialize() {
		chessBoard.add(Rank.createWhitePieceRank());
		chessBoard.add(Rank.createWhitePawnRank());
		for(int i=0; i<4; i++) {
			Integer temp = 3 + i;
			chessBoard.add(Rank.createBlankRank(temp.toString()));
		}
		chessBoard.add(Rank.createBlackPawnRank());
		chessBoard.add(Rank.createBlackPieceRank());
	}
	
	public void initializeEmpty() {
		for(int i=0; i<8; i++) {
			Integer temp = 1 + i;
			chessBoard.add(Rank.createBlankRank(temp.toString()));
		}
	}
	
	public String showBoard() {
		StringBuilder st = new StringBuilder();
		for(int i=chessBoard.size() - 1; i>=0; i--) {
			for(int j=0; j<chessBoard.get(i).getRankSize(); j++) {
				st.append(chessBoard.get(i).getIndexPiece(j).getPieceType());
			}
			st.append(StringUtils.NEWLINE);
		}
		return st.toString();
	}
	public int getAllPieceCount() {
		return chessBoard.size() * 8;
	}
	
	public int getPieceCount(Piece.Color color, Piece.Type type) {
		int specificPieceCount = 0;
		for(int i=0; i<chessBoard.size(); i++) {
			specificPieceCount += chessBoard.get(i).findPieceCountInRank(color, type);
		}
		return specificPieceCount;
	}
	
	public Piece findPiece(String position) {
		int col = position.charAt(0) - 'a';
		int row = Character.getNumericValue(position.charAt(1)) - 1;
	
		return chessBoard.get(row).findPiece(col);
	}
	
	public Piece findPiece(Position p) {
        return findRank(p.getY()).getIndexPiece(p.getX());
    }
	
	public Piece findPiece(int x, int y) {
        return findRank(y).getIndexPiece(x);
    }
	
	public Rank findRank(int row) {
		return chessBoard.get(row);
	}
	
	public void updateRank(Piece piece) {
		Position position = piece.getPosition();
		findRank(position.getY()).setPiece(position.getX(), piece);
	}
	
	public void move(String beforeposition, String afterPosition) {
		Position before = new Position(beforeposition);
		Piece piece = findPiece(before);
        piece.move(findPiece(new Position(afterPosition)));
        updateRank(Blank.create(before));
        updateRank(piece);
	}
	
	public double caculcatePoint(Piece.Color color) {
		double pointSum = 0;
		pawnCheckList = new PawnCheck();
		for(int i=0; i<chessBoard.size(); i++) {
			pointSum += chessBoard.get(i).getRankPoint(color);
			pawnCheckList.updateCheckList(color, findRank(i).getPawnCheckList(color));
		}
		pointSum -= calculatePawnExceptionPoint(color);
		return pointSum;
	}
	
	public double calculatePawnExceptionPoint(Piece.Color color) {
		double minusPoint = 0.0;
		for(int i=0; i<8; i++) {
			int count = pawnCheckList.getPawnCheckList(i, color);
			if(count >= 2)
				minusPoint += (double)count/2.0;
		}
		return minusPoint;
	}
}
