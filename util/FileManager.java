package com.brandex.util;

import java.io.File;

public class FileManager {
	
	// ============== FILE PATHS ===============
	private static final String USERS_FILE = "data/users.txt";
	private static final String PRODUCTS_FILE = "data/products.txt";
	private static final String ORDERS_FILE = "data/users.txt";
	private static final String  PASWORD_HISTORY_FILE = "data/password_history.txt";
	
	// Delimiter Used in Field Separation
	private static final String DELIMITER = "|";
	private FileManager() {} // This class won't be used to create objects
	
	// ============== Utility Methods ===============
	private static void validDirectory() {
		File directory = new File("data");
		if(!directory.exists()) { 
			directory.mkdirs(); 
		}
	}
	
	
}
