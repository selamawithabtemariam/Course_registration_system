package edu.mum.se.mumsched.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
	private static Map<Integer, String> months = new HashMap<>();
	static {
		months.put(1, "January");
		months.put(2, "February");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");
	}
	public static String getMonth(int month) {
		return months.getOrDefault(month, "Unknown");
	}
}
