package com.kyunam.springtutorial.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyunam.springtutorial.lotto.LottoMachine;
import com.kyunam.springtutorial.lotto.CustomLotto;
import com.kyunam.springtutorial.lotto.Lotto;
import com.kyunam.springtutorial.lotto.Result;
import com.kyunam.springtutorial.lotto.WinningLotto;


@Controller
@RequestMapping("/lotto")
public class LottoController {
	static LottoMachine lottoMachine;
	static WinningLotto winningLottoCreator;
	@GetMapping("")
	public String Lotto() {
		
		return "/lotto/lotto";
	}
	@GetMapping("/result")
	public String LottoResult(int money, Model model, String custom){
		int countOfLotto = 0;
		lottoMachine = new LottoMachine();
		winningLottoCreator = new WinningLotto();
		Lotto winningLotto = null;
		try {
			winningLotto = winningLottoCreator.getWinningLottor();
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			countOfLotto = lottoMachine.countOfLotto(money);
			
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		List<Lotto> buyngLottos = lottoMachine.buyAutoLottos(countOfLotto);
		Result result = lottoMachine.matchLotto(winningLotto, buyngLottos, winningLottoCreator.getBonusNum());
		result.calculateProfits(money);
		ResultDto resultDto = ResultDto.fromResult(result);
		List<Lotto> myCustomLottos = new CustomLotto(custom).getCustomLottoList();
		Result customResult = lottoMachine.matchLotto(winningLotto, myCustomLottos, winningLottoCreator.getBonusNum());
		ResultDto customLottoResultDto = ResultDto.fromResult(customResult);
		countOfLotto -=  myCustomLottos.size();
		
		model.addAttribute("money", money);
		model.addAttribute("resultDto", resultDto);
		model.addAttribute("customLottoResultDto", customLottoResultDto);
		model.addAttribute("mylotto", buyngLottos);
		model.addAttribute("winningLotto", winningLotto);
		System.out.println(custom);
		System.out.println(customLottoResultDto.getCountOfMatch4());
		
		
		
		return "/lotto/result";
	}
}
