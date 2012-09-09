package com.rcji.familystyle;

import java.util.ArrayList;

import com.rcji.familystyle.models.Table;

public class Tables {
	private static ArrayList<Table> tables = new ArrayList<Table>();
	
	public static void generateTables() {
		Table a = new Table(2, "Holy Grill", 0.0, 0.0, "Virtue Holy Salad", "San Francisco", new String[] {"Jennifer To"});
		Table b = new Table(2, "Henry's Hunan Restaurant", 0.0, 0.0, "Hunan Spare Ribs", "San Francisco", new String[] {"Carrie Chow"});
		tables.add(a);
		tables.add(b);
	}
	
	public static ArrayList<Table> getTable() {
		return tables;
	}
}
