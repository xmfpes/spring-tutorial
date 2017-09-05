package com.kyunam.springtutorial.chess;

public class Position {
	private int x;
	private int y;
	private String position;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		Character xString = (char)(x + 'a');
		Character yString = (char)(y + '0' + 1);
		this.position = xString.toString() + yString.toString();
	}
	
	public Position(String position) {
		this.position = position;
		if(position == null) {
			throw new InvalidPositionException("위치 값이 null이 될 수 없습니다.");
		}
		if(position.length() != 2) {
			System.out.println(position);
			throw new InvalidPositionException("값이 길이가 잘못되었습니다.");
		}
		this.x = position.charAt(0) - 'a';
		this.y = Character.getNumericValue(position.charAt(1)) - 1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
