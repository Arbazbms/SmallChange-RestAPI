package com.fidelity.smallchange.models;

public class Instrument {
	private String instrumentId;
	private String description;
	private String externalIdType;
	private String externalId;
	private String categoryId;
	private int minQuantity;
	private int maxQuantity;
	
	
	
	public Instrument(String instrumentId, String description, String externalIdType, String externalId,
			String categoryId, int minQuantity, int maxQuantity) {
		super();
		this.instrumentId = instrumentId;
		this.description = description;
		this.externalIdType = externalIdType;
		this.externalId = externalId;
		this.categoryId = categoryId;
		
		if( maxQuantity <= minQuantity ) {
			throw new IllegalArgumentException("Max quantity to be traded cannot be lesser than min quanitty");
		}
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
	}
	public String getInstrumentId() {
		return instrumentId;
	}
	public String getDescription() {
		return description;
	}
	public String getExternalIdType() {
		return externalIdType;
	}
	public String getExternalId() {
		return externalId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public int getMinQuantity() {
		return minQuantity;
	}
	public int getMaxQuantity() {
		return maxQuantity;
	}
	
	

}
