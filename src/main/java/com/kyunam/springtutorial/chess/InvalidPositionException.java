package com.kyunam.springtutorial.chess;

public class InvalidPositionException extends RuntimeException{
	private static final long serialVersionUID = -2262018355736231053L;

	public InvalidPositionException() {
		super();
	}
	
    public InvalidPositionException(String message) {
        super(message);
    }
    
    public InvalidPositionException(String message, Throwable cause) {
    		super(message, cause);
    }
}
