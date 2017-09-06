package com.kyunam.springtutorial.car;

import java.util.ArrayList;
import java.util.List;

public class Racing {
	private List<Car> cars;

	public Racing() {
		cars = new ArrayList<Car>();
	}

	public void create(Car car) {
		cars.add(car);
	}

	public void run(int count) {
		for (int i = 0; i < count; i++) {
			oneRun();
			print();
		}
	}

	public void oneRun() {
		for(int i=0; i<cars.size(); i++) {
			cars.get(i).run();
		}
	}
	public void print() {
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).print();
		}
		System.out.println();
	}
	
	
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Racing [cars=" + cars + "]";
	}

}
