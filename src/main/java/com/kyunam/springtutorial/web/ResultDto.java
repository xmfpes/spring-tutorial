package com.kyunam.springtutorial.web;

import com.kyunam.springtutorial.lotto.Result;
import com.kyunam.springtutorial.lotto.Result.Match;

public class ResultDto {
	private int sumOfMoney;
	private int profits;
	private int sumOfLottoMoney;
	private int countOfMatch3;
	private int countOfMatch4;
	private int countOfMatch5;
	private int countOfMatch6;
	private int countOfMatchBonus;

	private int countOfMatch3Rate;
	private int countOfMatch4Rate;
	private int countOfMatch5Rate;
	private int countOfMatch6Rate;
	private int countOfMatchBonusRate;

	public static ResultDto fromResult(Result result) {
		ResultDto resultDto = new ResultDto();
		resultDto.profits = result.getProfit();
		resultDto.countOfMatchBonus = result.getCountOfMatchLotto(Match.MATCHBonus.getCount());
		resultDto.countOfMatch3 = result.getCountOfMatchLotto(Match.MATCH3.getCount());
		resultDto.countOfMatch4 = result.getCountOfMatchLotto(Match.MATCH4.getCount());
		resultDto.countOfMatch5 = result.getCountOfMatchLotto(Match.MATCH5.getCount());
		resultDto.countOfMatch6 = result.getCountOfMatchLotto(Match.MATCH6.getCount());
		resultDto.sumOfLottoMoney = result.getSumOfLottoMoney();
		resultDto.calculateRates(result.getSumOfLottoMoney());
		resultDto.sumOfMoney = result.getSumOfMoney();
		
		return resultDto;
	}
	
	public void calculateRates(int money) {
		this.countOfMatch3Rate = calculateRate(Match.MATCH3, countOfMatch3, money);
		this.countOfMatch4Rate = calculateRate(Match.MATCH4, countOfMatch4, money);
		this.countOfMatch5Rate = calculateRate(Match.MATCH5, countOfMatch5, money);
		this.countOfMatch6Rate = calculateRate(Match.MATCH6, countOfMatch6, money);
		this.countOfMatchBonusRate = calculateRate(Match.MATCHBonus, countOfMatchBonus, money);
	}
	
	public int calculateRate(Match match, int countOfMatch, int money) {
		return (int)(((double)(countOfMatch * match.getMoney())  / (money))* 100);
	}

	public int getProfits() {
		return profits;
	}

	public void setProfits(int profits) {
		this.profits = profits;
	}

	public int getSumOfLottoMoney() {
		return sumOfLottoMoney;
	}

	public void setSumOfLottoMoney(int sumOfLottoMoney) {
		this.sumOfLottoMoney = sumOfLottoMoney;
	}

	public int getCountOfMatch3() {
		return countOfMatch3;
	}

	public void setCountOfMatch3(int countOfMatch3) {
		this.countOfMatch3 = countOfMatch3;
	}

	public int getCountOfMatch4() {
		return countOfMatch4;
	}

	public void setCountOfMatch4(int countOfMatch4) {
		this.countOfMatch4 = countOfMatch4;
	}

	public int getCountOfMatch5() {
		return countOfMatch5;
	}

	public void setCountOfMatch5(int countOfMatch5) {
		this.countOfMatch5 = countOfMatch5;
	}

	public int getCountOfMatch6() {
		return countOfMatch6;
	}

	public void setCountOfMatch6(int countOfMatch6) {
		this.countOfMatch6 = countOfMatch6;
	}

	public int getCountOfMatchBonus() {
		return countOfMatchBonus;
	}

	public void setCountOfMatchBonus(int countOfMatchBonus) {
		this.countOfMatchBonus = countOfMatchBonus;
	}

	public int getCountOfMatch3Rate() {
		return countOfMatch3Rate;
	}

	public void setCountOfMatch3Rate(int countOfMatch3Rate) {
		this.countOfMatch3Rate = countOfMatch3Rate;
	}

	public int getCountOfMatch4Rate() {
		return countOfMatch4Rate;
	}

	public void setCountOfMatch4Rate(int countOfMatch4Rate) {
		this.countOfMatch4Rate = countOfMatch4Rate;
	}

	public int getCountOfMatch5Rate() {
		return countOfMatch5Rate;
	}

	public void setCountOfMatch5Rate(int countOfMatch5Rate) {
		this.countOfMatch5Rate = countOfMatch5Rate;
	}

	public int getCountOfMatch6Rate() {
		return countOfMatch6Rate;
	}

	public void setCountOfMatch6Rate(int countOfMatch6Rate) {
		this.countOfMatch6Rate = countOfMatch6Rate;
	}

	public int getCountOfMatchBonusRate() {
		return countOfMatchBonusRate;
	}

	public void setCountOfMatchBonusRate(int countOfMatchBonusRate) {
		this.countOfMatchBonusRate = countOfMatchBonusRate;
	}

	public int getSumOfMoney() {
		return sumOfMoney;
	}

	public void setSumOfMoney(int sumOfMoney) {
		this.sumOfMoney = sumOfMoney;
	}

}
