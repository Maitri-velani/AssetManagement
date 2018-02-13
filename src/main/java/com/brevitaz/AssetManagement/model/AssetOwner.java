package com.brevitaz.AssetManagement.model;

import java.util.List;

public class AssetOwner {
	
	private String ownerId;
	private String firstName;
	private String lastName;
	private List<Asset> assetList;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	@Override
	public String toString() {
		return "AssetOwner{" +
				"ownerId='" + ownerId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", assetList=" + assetList +
				'}';
	}
}
