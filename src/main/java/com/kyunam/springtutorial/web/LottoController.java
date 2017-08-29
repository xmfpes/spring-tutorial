package com.kyunam.springtutorial.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyunam.springtutorial.lotto.LottoMachine;
import com.kyunam.springtutorial.lotto.Lotto;
import com.kyunam.springtutorial.lotto.LottoReport;
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
	public String LottoResult(int money, Model model) throws IOException {
		
		lottoMachine = new LottoMachine();
		winningLottoCreator = new WinningLotto();
		Lotto winningLotto = winningLottoCreator.getWinningLottor();
		int countOfLotto = lottoMachine.countOfLotto(money);
		List<Lotto> buyngLottos = lottoMachine.buyAutoLottos(countOfLotto);
		Result result = lottoMachine.matchLotto(winningLotto, buyngLottos);
		LottoReport lottoReport = new LottoReport(money, result);
		
		model.addAttribute("money", money);
		model.addAttribute("lottoresult", result);
		model.addAttribute("mylotto", buyngLottos);
		model.addAttribute("winningLotto", winningLotto);
		
		return "/lotto/result";
	}
}
