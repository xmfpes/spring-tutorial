package com.kyunam.springtutorial.car;

public class Car {
	private int location;
	
	public Car() {
		this.location = 0;
	}
	
	public void run() {
		if(Math.random() > 0.4) {
			this.location += 1;
		}
	}
	
	public void print() {
		for(int i=0; i<this.location; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
