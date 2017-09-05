package com.kyunam.springtutorial.chess;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
	private Color color;
	private Type type;
	protected Position position;
	protected List<Position> possibilityPosition;
	protected List<Direction> moveDirection;
	protected MovingStrategy movingStrategy;

	public enum Color {
		WHITE('w'), BLACK('b'), NOCOLOR('b');
		private char color;

		Color(char color) {
			this.color = color;
		}

		public char getColor() {
			return color;
		}

		public void setColor(char color) {
			this.color = color;
		}

	}

	public enum Type {
		PAWN('p', 1.0), ROOK('r', 5.0), KNIGHT('n', 2.5), BISHOP('b', 3.0), QUEEN('q', 9.0), KING('k',
				0.0), NO_PIECE('.', 0.0);

		private char representation;
		private double defaultPoint;

		private Type(char representation, double defaultPoint) {
			this.representation = representation;
			this.defaultPoint = defaultPoint;
		}

		public double getDefaultPoint() {
			return defaultPoint;
		}

		public char getWhiteRepresentation() {
			return representation;
		}

		public char getBlackRepresentation() {
			return Character.toUpperCase(representation);
		}

		public void setRepresentation(char representation) {
			this.representation = representation;
		}

		public void setDefaultPoint(double defaultPoint) {
			this.defaultPoint = defaultPoint;
		}

	}

	public char getPieceType() {
		if (this.color == Color.WHITE) {
			return this.type.getWhiteRepresentation();
		}
		return this.type.getBlackRepresentation();
	}

	public void move(Piece target) {
		if (verifyMovePosition(target)) {
			this.position = target.getPosition();
			return;
		}

		throw new InvalidPositionException(target + "위치로는 이동이 불가능합니다.");
	}

	public boolean verifyMovePosition(Piece target) {

		if (isSameTeam(target)) {
			throw new InvalidPositionException(target + " 위치는 이동할 수 없는 위치입니다.");
		}

		Position position = target.getPosition();
		System.out.println("kkkkk loc " + position.getX() + "," + position.getY());
		if (!possibilityPosition.contains(position)) {
			throw new InvalidPositionException(target + " 위치는 이동할 수 없는 위치입니다.");
		}

		return true;
	}

	public void setPossibilityPosition(ChessBoard board, Piece myPiece) {
		this.possibilityPosition = new ArrayList<Position>();
		movingStrategy.setPossibilityPosition(board, myPiece);
	}

	public boolean isSameTeam(Piece target) {
		if (isWhite() && target.isWhite()) {
			return true;
		}

		if (isBlack() && target.isBlack()) {
			return true;
		}

		return false;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Piece(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
	}

	protected Piece(Color color, Type type, Position position, List<Direction> directions,
			MovingStrategy movingStrategy) {
		this.color = color;
		this.type = type;
		this.position = position;
		this.moveDirection = directions;
		this.possibilityPosition = new ArrayList<Position>();
		this.movingStrategy = movingStrategy;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isBlack() {
		return this.color.equals(Color.BLACK);
	}

	public boolean isWhite() {
		return this.color.equals(Color.WHITE);
	}

	public List<Position> getPossibilityPosition() {
		return possibilityPosition;
	}

	public List<Direction> getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(List<Direction> moveDirection) {
		this.moveDirection = moveDirection;
	}

	public String getSymbol() {
		return isWhite() ? getWhiteSymbol() : getBlackSymbol();
	}

	protected abstract String getWhiteSymbol();

	protected abstract String getBlackSymbol();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
