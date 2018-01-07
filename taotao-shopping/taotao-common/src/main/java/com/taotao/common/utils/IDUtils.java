package com.taotao.common.utils;

import java.util.Random;

public class IDUtils {
	
	/**
	 * 图片名生成
	 * @return
	 */
	public static String getImageName() {
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end3 = random.nextInt(999);
		String str = millis + String.format("%03d", end3);
		return str;
	}
	/**
	 * 商品id生成
	 * @return
	 */
	public static long getItemId() {
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end2 = random.nextInt(99);
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
	
	public static void main(String[] args) {
		for(int i=0; i < 100; i++) {
			System.out.println(getItemId());
			System.out.println(getImageName());
		}
	}

}
