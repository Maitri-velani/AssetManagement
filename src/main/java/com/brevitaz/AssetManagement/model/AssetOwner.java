package com.brevitaz.AssetManagement.model;

public class AssetOwner {
	
	private int abc;
	private String ownerId;
	private String firstName;
	private String lastName;
	
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLasttName() {
		return lastName;
	}
	public void setLastName(String lasttName) {
		this.lastName = lasttName;
	}
	
	@Override
	public String toString() {
		return "AssetOwner [ownerId=" + ownerId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
