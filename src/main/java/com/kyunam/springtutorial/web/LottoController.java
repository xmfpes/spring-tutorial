package com.kyunam.springtutorial.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyunam.springtutorial.lotto.LottoManager;
import com.kyunam.springtutorial.lotto.NonAutoLottos;
import com.kyunam.springtutorial.lotto.Lotto;
import com.kyunam.springtutorial.lotto.Result;
import com.kyunam.springtutorial.lotto.WinningLottoManager;


@Controller
@RequestMapping("/lotto")
public class LottoController {
	static LottoManager lottoManager;
	static WinningLottoManager winningLottoCreator;
	static NonAutoLottos nonAutoLottos;
	@GetMapping("")
	public String Lotto() {
		
		return "/lotto/lotto";
	}
	@PostMapping("/result")
	public String LottoResult(int money, Model model, String custom){
		int countOfLotto = 0;
		lottoManager = new LottoManager();
		winningLottoCreator = new WinningLottoManager();
		Lotto winningLotto = null;
		List<Lotto> customLottos = null;
		List<Lotto> buyngLottos = null;
		
		try {
			nonAutoLottos = new NonAutoLottos(custom);
			countOfLotto = lottoManager.countOfLotto(money - nonAutoLottos.getCustomLottoMoney());
			customLottos = nonAutoLottos.getCustomLottoList();
			buyngLottos = lottoManager.buyAutoLottos(countOfLotto);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		try {
			winningLotto = winningLottoCreator.getWinningLottor();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			Result result = lottoManager.matchLotto(winningLotto, buyngLottos, winningLottoCreator.getBonusNum());
			result.calculateProfits(money - nonAutoLottos.getCustomLottoMoney());
			ResultDto resultDto = ResultDto.fromResult(result);
			
			
			Result customResult = lottoManager.matchLotto(winningLotto, customLottos, winningLottoCreator.getBonusNum());
			customResult.calculateProfits(nonAutoLottos.getCustomLottoMoney());
			ResultDto customLottoResultDto = ResultDto.fromResult(customResult);
			
			model.addAttribute("resultDto", resultDto);
			model.addAttribute("customLottoResultDto", customLottoResultDto);
			model.addAttribute("winningLotto", winningLotto);
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		return "/lotto/result";
	}
}
