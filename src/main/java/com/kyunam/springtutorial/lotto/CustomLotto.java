package com.kyunam.springtutorial.lotto;

import java.util.ArrayList;
import java.util.List;

public class CustomLotto {
	private List<Lotto> customLottoList;
	
	public CustomLotto(String custom) {
		customLottoList = new ArrayList<Lotto>();
		String[] lines = custom.split(System.getProperty("line.separator"));
		for(int i=0; i<lines.length; i++) {
			customLottoList.add(parseCustomString(lines[i]));
		}
	}
	
	public Lotto parseCustomString(String row) {
		String[] lottoNos = row.split(",");
		List<Integer> lotto = new ArrayList<Integer>();
		for(int i=0; i<lottoNos.length; i++) {
			lotto.add(Integer.parseInt(lottoNos[i]));
		}
		return new Lotto(lotto);
	}

	public List<Lotto> getCustomLottoList() {
		return customLottoList;
	}

	public void setCustomLottoList(List<Lotto> customLottoList) {
		this.customLottoList = customLottoList;
	}
	
	
}
