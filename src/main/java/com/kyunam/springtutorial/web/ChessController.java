package com.kyunam.springtutorial.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyunam.springtutorial.chess.ChessBoard;
import com.kyunam.springtutorial.chess.Piece;
import com.kyunam.springtutorial.chess.Position;

@Controller
@RequestMapping("/chess")
public class ChessController {
	static ChessBoard board;

	@GetMapping("")
	public String home() {
		board = new ChessBoard();
		System.out.println("hi");
		board.initialize();
		return "/chess/chess";
	}

	// U
	@PutMapping("/move")
	public ResponseEntity<String> move(@RequestBody Map<String, Object> json){
		ResponseEntity<String> entity = null;
		try {
			Piece piece = board.findPiece(new Position(json.get("position").toString()));
			piece.setPossibilityPosition(board, piece);
			board.move(json.get("position").toString(), json.get("target").toString());
			entity = new ResponseEntity<String>("SUCCEUSS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@PostMapping("/possibilityPosition")
	public ResponseEntity<List<Position>> getPossibilityPosition(@RequestBody Map<String, Object> json) {
		ResponseEntity<List<Position>> entity = null;
		System.out.println("이동한다.1.");
		try {
			Piece piece = board.findPiece(new Position(json.get("position").toString()));
			piece.setPossibilityPosition(board, piece);
			entity = new ResponseEntity<List<Position>>(piece.getPossibilityPosition(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<Position>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
