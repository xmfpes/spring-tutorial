package com.kyunam.springtutorial.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyunam.springtutorial.chess.ChessBoard;
import com.kyunam.springtutorial.chess.Piece;
import com.kyunam.springtutorial.chess.Position;

@RestController
@RequestMapping("/chessrest")
public class ChessRestController {
	ChessBoard board;
	// U
	@PutMapping("/move")
	public ResponseEntity<Map<String, Object>> move(@RequestBody Map<String, Object> json) {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		board = ChessController.board;
		try {
			Piece piece = board.findPiece(new Position(json.get("position").toString()));
			piece.setPossibilityPosition(board, piece);
			try {
				board.move(json.get("position").toString(), json.get("target").toString());
				map.put("status", true);
			} catch(Exception e) {
				map.put("status", false);
			}
			Double whitepoint = board.caculcatePoint(Piece.Color.WHITE);
			Double blackpoint = board.caculcatePoint(Piece.Color.BLACK);
			map.put("whitepoint", whitepoint);
			map.put("blackpoint", blackpoint);
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
		System.out.println(board.showBoard());
		
		return entity;
	}

	@PostMapping("/possibilityPosition")
	public ResponseEntity<List<Position>> getPossibilityPosition(@RequestBody Map<String, Object> json) {
		ResponseEntity<List<Position>> entity = null;
		board = ChessController.board;
		try {
			System.out.println(board);
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
