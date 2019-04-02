package com.inn.project1.project1.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

//public class Xyz {
//
//	public static void main(String[] args) {
//		
//		String a = "abcba";
//		String b = "acab";
//		
//		String comparingStr = b;
//		
//		String shortestString = Stream.of(a,b)
//			.min((x1,x2) -> x1.length() > x2.length() ? 1 : -1)
//			.get();
//		
//		System.out.println("shor=="+shortestString);
//		
//		List<Integer> str = new ArrayList<>();
//		for(int i=0;i<=shortestString.length();i++) {
//			for(int j=i;j<=shortestString.length();j++) {
//				String sub = shortestString.substring(i,j);
//				if(comparingStr.contains(sub)) {
//					str.add(sub.length());
//				}
//			}
//		}
//		
//		System.out.println("List str==="+str);
//		int max = str.stream()
//			.max((x1,x2) -> x1 > x2 ? 1 : -1)
//			.get();
//		
//		
//		
//		System.out.println("Maz===="+max);
//		//System.out.println("Length==="+max.length());
//	}
//
//}

/* A Naive recursive implementation of LCS problem in java*/
public class LongestCommonSubsequence 
{ 

/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
int lcs( char[] X, char[] Y, int m, int n ) 
{ 
	if (m == 0 || n == 0) 
	return 0; 
	if (X[m-1] == Y[n-1]) 
	return 1 + lcs(X, Y, m-1, n-1); 
	else
	return max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n)); 
} 

/* Utility function to get max of 2 integers */
int max(int a, int b) 
{ 
	return (a > b)? a : b; 
} 

public static void main(String[] args) 
{ 
	LongestCommonSubsequence lcs = new LongestCommonSubsequence(); 
	String s1 = "AGGTAB"; 
	String s2 = "GXTXAYB"; 

	char[] X=s1.toCharArray(); 
	char[] Y=s2.toCharArray(); 
	int m = X.length; 
	int n = Y.length; 

	System.out.println("Length of LCS is" + " " + 
								lcs.lcs( X, Y, m, n ) ); 
} 

} 

// This Code is Contributed by Saket Kumar 

