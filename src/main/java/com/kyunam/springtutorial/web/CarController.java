package com.kyunam.springtutorial.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyunam.springtutorial.car.Car;
import com.kyunam.springtutorial.car.Racing;

@Controller
@RequestMapping("/car")
public class CarController {
	static Racing racing;
	@GetMapping("")
	public String home() {
		
		racing = new Racing();
		return "/car/car";
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody Map<String, Integer> json) {
		
		int count = json.get("carCount");
		
		System.out.println("count : " + count);
		ResponseEntity<String> entity = null;
		try {
			for(int i=0; i<count; i++) {
				racing.create(new Car());
				System.out.println(racing.getCars().get(i));
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PostMapping("/run")
	public ResponseEntity<List<Car>> run() {
		ResponseEntity<List<Car>> entity = null;
		try {
			racing.run(1);
			entity = new ResponseEntity<List<Car>>(racing.getCars(), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<Car>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
