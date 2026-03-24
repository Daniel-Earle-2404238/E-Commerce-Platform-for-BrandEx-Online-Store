package com.brandex.model;

import com.brandex.util.PasswordManager;
import com.brandex.util.UserType;
import com.brandex.util.Validator;

public abstract class User {

	/**
	 * Track currently logged in user
	 * null means nobody is logged in 
	 */
	private static User currentUser = null;
	
	protected int userID;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String hashedPassword;
	protected UserType userType;
	
	/**
	 * Creates a blank User object with empty fields
	 * Used when a new Customer is being created
	 * Before register() fills in the real details
	 * Like a blank form to be filled out */
	
	public User() {
		this.userID = 0;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.hashedPassword = "";
		this.userType = UserType.CUSTOMER;
	}

	/**
	 * Creates a fully initialized user in one go
	 * Used when we already have all the details up front
	 * Like when Admin is created with known credentials */
	
	public User(int userID, String firstName, String lastName, 
			String email, String password, UserType userType) {
		this.userID = userID;
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setHashedPassword(PasswordManager.hashPassword(password));
		this.userType = userType;
	}
	/**
	 * Used when loading an existing customer from file
	 * Or you have an existing User obj, and you want to copy without rehashing password 
	 * Currently redundant since FileManager can use Constructor 2 directly*/
	public User(User user) {
		this.userID = user.userID;
		setFirstName(user.firstName);
		setLastName(user.lastName);
		setEmail(user.email);
		this.hashedPassword = user.hashedPassword;
		this.userType = user.userType;
	}
	

	// Methods
	public abstract boolean login(String credentials, String password);
	
	public void logout() {
		currentUser = null;
	}
	
	public boolean changePassword(String oldPassword, String newPassword) {
		// verify old password matches stored hash
		if (!PasswordManager.verifyPassword(oldPassword, this.hashedPassword)) {
			System.out.println("ERROR: Incorrect current password");
			return false;
		}
		
		// make sure new password meets requirements
		 if (!Validator.isValidPassword(newPassword)) {
		        System.out.println("ERROR: New password does not meet requirements\n" +
		                           "Minimum of 8 Characters\n" +
		        				   "At least one uppercase letter\n" + 
		                           "At least one number\n" +
		        				   "At least one special character(!@#$%^&*)");
		        return false;
		 }
		 this.setHashedPassword(PasswordManager.hashPassword(newPassword));
		 return true;
	}
	
	public void display() {
		System.out.println(this.toString());
	}
	
	// Accessors
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public int getUserID() {
		return userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	
	// Mutators
	public static void setCurrentUser(User user) {
		currentUser = user;
	}
	
	public void setFirstName(String firstName) { 
		
		if(!Validator.isValidName(firstName)) { 
			System.out.println("ERROR: Invalid First Name Entered");
			return;
		}
		this.firstName = firstName.trim();
	}

	public void setLastName(String lastName) {
		if(!Validator.isValidName(lastName)) {
			System.out.println("ERROR: Invalid Last Name");
			return;
		}
		this.lastName = lastName.trim();
	}

	public void setEmail(String email) {
		/**
		 * Defensive Programming
		 * Though we validate again in Customer class
		 * This prevents direct setter calls using User's setter
		 * Without it customer.setEmail("notanemail") would succeed */
		
		if(!Validator.isValidEmail(email)) {
			System.out.println("ERROR: Invalid Email Format");
			return;
		}
		this.email = email.trim().toLowerCase();
	}

	public void setHashedPassword(String hashedPassword) {
		if (hashedPassword == null || hashedPassword.isEmpty()) {
			System.out.println("ERROR: Invalid password hash");
			return;
		}
		
		/**
		 *SHA-256 hash is always exactly 64 characters
		 *If the length isn't 64 something went wrong in hashing 
		 */
		
		if (hashedPassword.length() != 64) {
			System.out.println("ERROR: Invalid hash format");
			return;
		}
		this.hashedPassword = hashedPassword;
	}

	@Override
	public String toString() {
		return String.format("User ID: %d\n" + 
							 "First Name: %s\n" + 
							 "Last Name: %s\n" + 
							 "Email: %s\n" + 
							 "User Type: %s",
							 userID,
							 firstName, 
							 lastName,
							 email,
							 userType.name() // JVM would automatically call this method
							 ); 	
		}
}
