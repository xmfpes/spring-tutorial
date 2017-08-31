package com.kyunam.springtutorial.web;

import com.kyunam.springtutorial.lotto.Result;

public class ResultDto {
	private int profits;
	private int sumOfLottoMoney;
	private int countOfLotto;
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
		resultDto.countOfMatchBonus = result.getCountOfMatchBonus();
		resultDto.countOfMatch3 = result.getCountOfMatch3();
		resultDto.countOfMatch4 = result.getCountOfMatch4();
		resultDto.countOfMatch5 = result.getCountOfMatch5();
		resultDto.countOfMatch6 = result.getCountOfMatch6();
		resultDto.sumOfLottoMoney = result.getSumOfLottoMoney();
		resultDto.calculateRate(result.getSumOfLottoMoney());
		
		return resultDto;
	}
	
	public void calculateRate(int money) {
		
		this.countOfMatch3Rate = (int)(((double)(countOfMatch3 * 5000)  / (money))* 100);
		this.countOfMatch4Rate = (int)(((double)(countOfMatch4 * 50000) / (money)) * 100);
		this.countOfMatch5Rate = (int)(((double)(countOfMatch5 * 1500000) / (money)) * 100);
		this.countOfMatch6Rate = (int)(((double)(countOfMatch6 * 1000000000) / (money)) * 100);
		this.countOfMatchBonusRate = (int)(((double)(countOfMatchBonus * 100000000) / (money)) * 100);
	}
	

	public int getProfits() {
		return profits;
	}

	public void setProfits(int profits) {
		this.profits = profits;
	}

	public int getCountOfLotto() {
		return countOfLotto;
	}

	public void setCountOfLotto(int countOfLotto) {
		this.countOfLotto = countOfLotto;
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

	public int getSumOfLottoMoney() {
		return sumOfLottoMoney;
	}

	public void setSumOfLottoMoney(int sumOfLottoMoney) {
		this.sumOfLottoMoney = sumOfLottoMoney;
	}
	
	
}
