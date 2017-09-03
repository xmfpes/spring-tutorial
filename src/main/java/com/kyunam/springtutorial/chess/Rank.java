package com.kyunam.springtutorial.chess;

import java.util.ArrayList;
import java.util.List;

public class Rank {
	private List<Piece> rank;
	
	public Rank() {
		rank = new ArrayList<Piece>();
	}
	public Rank(List<Piece> rank) {
		this.rank = rank;
	}
	
	public Piece findPiece(int index) {
		return rank.get(index);
	}
	
	public void setPiece(int index, Piece piece) {
		rank.set(index, piece);
	}
	public int findPieceCountInRank(Piece.Color color, Piece.Type type) {
		int pieceCount = 0;
		for(int i=0; i<rank.size(); i++) {
			if(getIndexPiece(i).getColor() == color && getIndexPiece(i).getType() == type)
				pieceCount++;
		}
		return pieceCount;
	}
	
	public Piece getIndexPiece(int index) {
		return rank.get(index);
	}
	public int getRankSize() {
		return rank.size();
	}
	
	public static Rank createBlackPieceRank(){
		List<Piece> blackPieceList = new ArrayList<Piece>();
		blackPieceList.add(Rook.createBlack(new Position("a8")));
		blackPieceList.add(Knight.createBlack(new Position("b8")));
		blackPieceList.add(Bishop.createBlack(new Position("c8")));
		blackPieceList.add(Queen.createBlack(new Position("d8")));
		blackPieceList.add(King.createBlack(new Position("e8")));
		blackPieceList.add(Bishop.createBlack(new Position("f8")));
		blackPieceList.add(Knight.createBlack(new Position("g8")));
		blackPieceList.add(Rook.createBlack(new Position("h8")));
		return new Rank(blackPieceList);
	}
	
	public static Rank createBlackPawnRank(){
		List<Piece> blackPawnList = new ArrayList<Piece>();
		for(int i=0; i<8; i++) {
			char temp = (char) ('a' + i);
			blackPawnList.add(Pawn.createBlack(new Position(temp + "7")));
		}
		return new Rank(blackPawnList);
	}
	
	public static Rank createBlankRank(String row) {
		List<Piece> blankList = new ArrayList<Piece>();
		for(int i=0; i<8; i++) {
			char temp = (char) ('a' + i);
			blankList.add(Blank.create(new Position(temp + row)));
		}
		return new Rank(blankList);
	}
	
	public static Rank createWhitePieceRank() {
		List<Piece> whitePieceList = new ArrayList<Piece>();
		whitePieceList.add(Rook.createWhite(new Position("a1")));
		whitePieceList.add(Knight.createWhite(new Position("b1")));
		whitePieceList.add(Bishop.createWhite(new Position("c1")));
		whitePieceList.add(Queen.createWhite(new Position("d1")));
		whitePieceList.add(King.createWhite(new Position("e1")));
		whitePieceList.add(Bishop.createWhite(new Position("f1")));
		whitePieceList.add(Knight.createWhite(new Position("g1")));
		whitePieceList.add(Rook.createWhite(new Position("h1")));
		return new Rank(whitePieceList);
	}
	
	public static Rank createWhitePawnRank(){
		List<Piece> whitePawnList = new ArrayList<Piece>();
		for(int i=0; i<8; i++) {
			char temp = (char) ('a' + i);
			whitePawnList.add(Pawn.createWhite(new Position(temp + "2")));
		}
		return new Rank(whitePawnList);
	}
	
	public double getRankPoint(Piece.Color color) {
		double point = 0;
		for(int i=0; i<rank.size(); i++) {
			if(rank.get(i).getColor() == color) {
				point += rank.get(i).getType().getDefaultPoint();
			}
		}
		return point;
	}
	
	public int [] getPawnCheckList(Piece.Color color) {
		int [] check = new int[8];
		for(int i=0; i<rank.size(); i++) {
			if(rank.get(i).getType() == Piece.Type.PAWN && rank.get(i).getColor() == color) {
				check[i] += 1;
			}
		}
		return check;
	}
	
}
