package com.kyunam.springtutorial.lotto;

import java.util.ArrayList;
import java.util.List;

public class NonAutoLottos {
	private List<Lotto> customLottoList;
	private int customLottoCount;
	private int customLottoMoney;
	
	public NonAutoLottos() {
		customLottoList = new ArrayList<Lotto>();
	}
	
	public NonAutoLottos(String custom) {
		customLottoList = new ArrayList<Lotto>();
		try {
			String[] lines = custom.split(System.getProperty("line.separator"));
			
			this.customLottoCount = lines.length;
			this.customLottoMoney = customLottoCount * 1000;
			for(int i=0; i<customLottoCount; i++) {
				System.out.println(lines[i]);
				customLottoList.add(parseCustomString(lines[i]));
			}
		}catch (NullPointerException e){
			e.printStackTrace();
		}
	}
	
	public Lotto parseCustomString(String row) {
		String[] lottoNos = row.split(",");
		List<Integer> lotto = new ArrayList<Integer>();
		try {
			for(int i=0; i<lottoNos.length; i++) {
				//line.separator issue
				if(lottoNos[i] != "")
					lotto.add(Integer.parseInt(lottoNos[i]));
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		return new Lotto(lotto);
	}

	public List<Lotto> getCustomLottoList() {
		return customLottoList;
	}

	public void setCustomLottoList(List<Lotto> customLottoList) {
		this.customLottoList = customLottoList;
	}

	public int getCustomLottoCount() {
		return customLottoCount;
	}

	public void setCustomLottoCount(int customLottoCount) {
		this.customLottoCount = customLottoCount;
	}

	public int getCustomLottoMoney() {
		return customLottoMoney;
	}

	public void setCustomLottoMoney(int customLottoMoney) {
		this.customLottoMoney = customLottoMoney;
	}
	
}
