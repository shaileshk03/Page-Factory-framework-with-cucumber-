package com.abc.utility;

import java.util.Random;

public class RandomGenerator {

	private static final String char_list="a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,w,x,w,z";
	private static final int char_length= 10;
	
	public static String GenerateRandomString(){
		StringBuffer randStr = new StringBuffer();
		for(int i=0; i< char_length; i++){
			int number = getRandomNumber();
			char ch = char_list.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}
	
	public static int getRandomNumber(){
		int randomInt =0;
		Random randGen = new Random();
		randomInt = randGen.nextInt(char_list.length());
	if(randomInt -1 == -1){
		return randomInt -1;
	 
	} else{
		return randomInt;
	}
	   
	}
	
}
