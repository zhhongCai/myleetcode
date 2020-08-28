package com.theonecai.algorithms;

import java.util.Random;

public class RandomStringUtil {

//	private static final String AB = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String AB = "abcdefghijklmnopqrstuvwxyz";
	private static Random rnd = new Random();

	public static String randomString(int len )
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ )
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}

}
