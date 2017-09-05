package com.kyunam.springtutorial.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.kyunam.springtutorial.chess.Rank;

@Controller
@RequestMapping("/chess")
public class ChessController {
	public static ChessBoard board;

	@GetMapping("")
	public String home(Model model) {
		board = new ChessBoard();
		board.initialize();
		model.addAttribute("chessBoard", board.getReverseChessBoard());
		return "/chess/chess";
	}
}
