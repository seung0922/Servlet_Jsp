package com.codechobo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserFortune {
	HashMap<String, Integer> userMap = new HashMap();	// 유저데이터(key)와 랜덤한 수(value) 저장할 map
	
	ArrayList<String> fortuneList() {
		ArrayList<String> resultList = new ArrayList<>();	// 운세 저장할 배열
		try {
			// 1) BuffredReader와 FileReader를 이용해서 지정된 파일을 읽는다.
			// ( txt파일에 저장된 운세 가져오기)
			FileReader fr = new FileReader("C:\\fortune.txt");
			BufferedReader br = new BufferedReader(fr);
			
			// 2) 라인별로 읽어서 fortuneList에 저장한다.
			String line = "";
			while((line=br.readLine())!=null) {
				resultList.add(line);
			}
			
		} catch(IOException e) {
			System.exit(0);
		}
		return resultList;
	}
	
}

