package com.elevateHome.model;

/**
 * @author Riya Bhatta
 * 
 * The UserModel class represents a user with attributes such as user ID, first name, last name,
 * password, role, email, phone number, and address. It provides methods for getting and setting these attributes.
 * - Getter: Returns the current value of the attribute. 
 * - Setter: Sets a new value for the attribute.
 */
public class UserModel {
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userRole;
    private String userEmail;
    private String phoneNumber;
    private String address;
    private String profileImage;
    
    /**
     * Constructor to initialize a UserModel object with the provided details.
     * 
     * @param userId The unique identifier for the user.
     * @param userFirstName The first name of the user.
     * @param userLastName The last name of the user.
     * @param userEmail The email address of the user.
     * @param userRole The role of the user (e.g., "admin", "user").
     * @param userPassword The password of the user.
     * @param phoneNumber The phone number of the user.
     * @param address The address of the user.
     */

    public UserModel(int userId, String userFirstName,String userLastName,  String userEmail ,String userRole,String userPassword, String phoneNumber, String address, String profileImage   ) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userEmail = userEmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profileImage = profileImage;
     
    }

    /**
     * Getter and Setter methods for all user attributes 
     */
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserFirstName() {
		return userFirstName;
	}


	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}


	public String getUserLastName() {
		return userLastName;
	}


	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
    public void setPhoneNumber(String phoneNumber) { 
    	this.phoneNumber = phoneNumber;
    }
    public String getAddress() { 
    	return address; 
    }
    public void setAddress(String address) { 
    	this.address = address; 
    }
    public String getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
	

	
}
