package com.brandex.model;

import java.util.LinkedList;
import java.util.List;

import com.brandex.util.FileManager;
import com.brandex.util.PasswordManager;
import com.brandex.util.UserType;
import com.brandex.util.Validator;

public class Customer extends User {
	private String[] passwordHistory;
	private List<Order> orderHistory;
	private ShoppingCart shoppingCart;

	/**
	 * This constructor is for new customer registration
	 * Fields are set when register() method is executed
	 * Starts with null/empty values */
	
	public Customer() {
		super();
		this.passwordHistory = new String[2];
		this.orderHistory = new LinkedList<Order>();
		this.shoppingCart = null;
	}
	
	/**
	 * Constructor for loading existing customer from file
	 * All details are already known from the file */
	
	public Customer(int userID, String firstName, String lastName,
			String email, String hashedPassword, UserType userType, 
			String[] passwordHistory,LinkedList<Order> orderHistory, 
			ShoppingCart shoppingCart) {
		
		super();
		this.userID = userID;
	    setFirstName(firstName);
	    setLastName(lastName);
	    setEmail(email);
	    this.hashedPassword = hashedPassword;
	    this.userType = UserType.CUSTOMER;
	    this.passwordHistory = passwordHistory;
	    this.orderHistory = orderHistory;
	    this.shoppingCart = shoppingCart;
	}
	
	// Methods
	public boolean register(String firstName, String lastName,
			String email, String password) {
		/**
		 * Make sure all fields are valid */
		
		if(!Validator.isValidName(firstName)) {
			System.out.println("ERROR: Invalid first name entered");
			return false;
		}
		
		if(!Validator.isValidName(lastName)) {
            System.out.println("ERROR: Invalid last name entered");
            return false;
		}
		
		if (!Validator.isValidEmail(email)) {
			System.out.println("ERROR: Invalid email format");
			return false;
		}
		
		if (!Validator.isValidPassword(password)) {
            System.out.println("ERROR: Password does not meet " +
                               "requirements:\n" +
                               "- Minimum of 8 characters\n" +
                               "- At least one uppercase letter\n" +
                               "- At least one number\n" +
                               "- At least one special character" +
                               "(!@#$%^&*)");
            return false;
        }
		
		// Check if the email already exists
		if (FileManager.emailExists(email)) {
			System.out.println("ERROR: Email already registered");
			return false;
		}
		
		/**
		 * Assign unique ID from FileManager
		 * FileManager counts existing users and returns the next ID */
		this.userID = FileManager.generateUserID();
		
		/**
		 * Set validated customer details
		 * Though setters will validate again
		 * They will also handle trimming and formatting */
		
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		
		// Hash and store password
		String hashedString = PasswordManager.hashPassword(password);
		this.hashedPassword = hashedString;
		
		// Here we initialize password history
		this.passwordHistory[0] = hashedString;
	 	this.passwordHistory[1] = null;
	 
		// Each Customer owns a shopping cart
	 	this.shoppingCart = new ShoppingCart();
		 
		// Save Customer to users file
		if (!FileManager.saveUser(this)) {
            System.out.println("ERROR: Registration failed - " +
                               "could not save to file");
            return false;
        }
		
		// Generate and send OTP
		/*
		OTPGenerator.generateOTP(this.email);
        System.out.println("Registration successful!\n" +
                           "An OTP has been sent to: " + 
                           this.email + "\n" +
                           "Please check your email to continue.");
		*/
		return true;
	}
	
	
	
	@Override
	public void display() {
		System.out.println(this.toString());
	}

	// Accessors
	public String[] getPasswordHistory() {
		return passwordHistory;
	}

	public List<Order> getOrderHistory() {
		return orderHistory;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	// Mutators
	public void setOrderHistory(List<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}

	public void setPasswordHistory(String[] passwordHistory) {
		this.passwordHistory = passwordHistory;
	}
	
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Customer Details:\n" +
				"-----------------\n" +
				"%s\n" +
				"Orders Placed: %d",
				super.toString(),
				orderHistory != null ? orderHistory.size() : 0
				);
	}
}
