package com.kyunam.springtutorial.lotto;

import java.util.ArrayList;
import java.util.List;

public class Result {
	public enum Match{
		MATCH3(3, 5000),
		MATCH4(4, 50000),
		MATCH5(5, 1500000),
		MATCH6(6, 1000000000),
		MATCHBonus(7, 100000000);
		
		private int count;
		private int money;
		
		private Match(int count, int money) {
			this.count = count;
			this.money = money;
		}
		
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getMoney() {
			return money;
		}

		public void setMoney(int money) {
			this.money = money;
		}

		public static Match getMatch(int count) {
			Match [] list = Match.values();
			for(Match match : list) {
				if(match.getCount() == count) {
					return match;
				}
			}
			throw new IllegalArgumentException(count + "는 유효하지 않은 값입니다.");
		}
	}
	private List<MatchingResult> results;
	private int profit;
	
	private int sumOfLottoMoney;
	private int sumOfMoney;

	public Result() {
		results = new ArrayList<MatchingResult>();
		results.add(new MatchingResult(Match.MATCH3));
		results.add(new MatchingResult(Match.MATCH4));
		results.add(new MatchingResult(Match.MATCH5));
		results.add(new MatchingResult(Match.MATCH6));
		results.add(new MatchingResult(Match.MATCHBonus));
	}

	public void updateResult(int count) {
		if(count < 3) {
			//3이하는 미당첨, 연산할 필요 없음
			return;
		}
		for(MatchingResult result : results) {
			if(result.checkMatch(Match.getMatch(count))) {
				result.updateMatcingResult();
				this.sumOfLottoMoney +=  Match.getMatch(count).getMoney();
			}
		}
	}

	public int getSumOfLottoMoney() {
		return sumOfLottoMoney;
	}
	
	public void calculateProfits(int money) {
		this.sumOfMoney = money;
		this.profit = (int)(((double)(sumOfLottoMoney) / money)*100);
	}
	
	public int getSumOfMoney() {
		return sumOfMoney;
	}

	public void setSumOfMoney(int sumOfMoney) {
		this.sumOfMoney = sumOfMoney;
	}

	public void setSumOfLottoMoney(int sumOfLottoMoney) {
		this.sumOfLottoMoney = sumOfLottoMoney;
	}

	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	public int getCountOfMatchLotto(int count) {
		Match match  = Match.getMatch(count);
		for(int i=0; i<results.size(); i++) {
			if(results.get(i).checkMatch(match))
				return results.get(i).getCountOfMatchLotto();
		}
		
		throw new NumberFormatException("잘못된 값이 입력되었습니다.");
	}
	private static class MatchingResult{
		private int countOfMatchLotto = 0;
		private Match match;
		public MatchingResult(Match match){
			this.match = match;
		}
		
		public int getCountOfMatchLotto() {
			return countOfMatchLotto;
		}

		private boolean checkMatch(Match match) {
			return this.match == match;
		}
		private void updateMatcingResult() {
			this.countOfMatchLotto++;
		}
	}
	

}
