package com.example.Alertsystem.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utility {
	public static List<Integer> exclude = new ArrayList<Integer>();

	public static Random rand = new Random();

	public static  int getTheRandomNumber(int start, int end) {
		int random = start + rand.nextInt(end - start + 1 - exclude.size());
		for (int ex : exclude) {
			if (random < ex) {
				break;
			}
			random++;
		}
		return random;
	}

}
