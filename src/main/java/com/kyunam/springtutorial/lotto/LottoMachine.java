package com.kyunam.springtutorial.lotto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LottoMachine {
	private Integer data[];
	
	public LottoMachine() {
		data = new Integer[45];
		for (int i = 0; i < 45; i++) {
			data[i] = i + 1;
		}
	}

	public int getBuyingMoney(Scanner sc) {
		return sc.nextInt();
	}

	public int countOfLotto(int money) {
		if(money < 1000) {
			throw new NumberFormatException("최소 액수보다 적은 돈이 입력되었습니다.");
		}
		return money / 1000;
	}

	public Lotto buyAutoLotto() {
		return new Lotto(data);
	}

	public List<Lotto> buyAutoLottos(int countOfLotto) {
		List<Lotto> myLottolist = new ArrayList<Lotto>();
		for (int i = 0; i < countOfLotto; i++) {
			myLottolist.add(buyAutoLotto());
		}
		return myLottolist;
	}
	
	public Result matchLotto(Lotto winningLotto, List<Lotto> buyngLottos, int bonus) {
		Result result = new Result();
		for(int i=0; i<buyngLottos.size(); i++) {
			buyngLottos.get(i).checkBonus(bonus);
			result = result.updateResult(
					(new Result(buyngLottos.get(i).compareLotto(winningLotto)))
			);
		}
		return result;
	}
	
	
}
