package com.bozpower.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
	public void test() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(new Date());
		System.out.println(str.substring(0, 13));
		String[] strToInt = str.split("-");
		int[] timeInt = new int[strToInt.length];
		for (int i = 0; i < strToInt.length; i++) {
			timeInt[i] = Integer.parseInt(strToInt[i]);
			System.out.println(timeInt[i]);
		}
//		String time = "2019-07-23";
//		String time = "21:17:50";
//		time = time.replace(":", "-");
//		time = time.replace(" ", "-");
//		String [] strToInt = time.split("-");
//		int [] timeInt = new int[strToInt.length];
//		for(int i = 0; i < strToInt.length; i ++) {
//			timeInt[i] = Integer.parseInt(strToInt[i]);
//			System.out.println(timeInt[i]);
//		}
//		System.out.println(timeInt.length);
//		
//		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
//		String time = simple.format(new Date());
//		System.out.println(time);
		
//		String [] strToInt = time.split(":");
//		int [] timeInt = new int[strToInt.length];
//		for(int i = 0; i < strToInt.length; i ++) {
//			timeInt[i] = Integer.parseInt(strToInt[i]);
//			System.out.println(timeInt[i]);
//		}
	}
}
