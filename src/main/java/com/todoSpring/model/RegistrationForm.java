package com.todoSpring.model;

public class RegistrationForm {

    private int userId;
    private String fisrtName;
	private String lastName;
	private String email;
	private String userName;
	private String password;

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "RegistrationForm [fisrtName=" + fisrtName + ", lastName="
				+ lastName + ", email=" + email + ", userName=" + userName
				+ ", password=" + password + "]";
	}

}
