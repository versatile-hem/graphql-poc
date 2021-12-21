package com.chegg.fish.pet.exceptions;

/**
 * 
 * @author hem
 *
 */
public enum FishExceptionCode {

	SOMETHING_WENT_WRONG(1001, "something.went.wrong"),
	
	FISH_NOT_PRESENT(4124, "Fish is not in acquarium"),
	
	INVALID_PARAM(4114, "Invalid parameters"), 
	CAN_NOT_FIT_FISH(4114, "Can not fit fish in this acquariom"), 
	
	INSUFFICENT_FISH(4114, "Invalid removal request for fish"), 
	
	CAN_NOT_STAY_WITH_FISHES(4333, "Can not stay with fishes in acqa"), 
	
	;

	/** The exception code. */
	private final Integer exceptionCode;

	/** The exception description. */
	private final String exceptionDescription;

	/**
	 * Instantiates a new exception codes.
	 *
	 * @param exceptionCode        the exception code
	 * @param exceptionDescription the exception description
	 */
	private FishExceptionCode(int exceptionCode, String exceptionDescription) {
		this.exceptionCode = exceptionCode;
		this.exceptionDescription = exceptionDescription;
	}

	/**
	 * Gets the exception code.
	 *
	 * @return the exception code
	 */
	public Integer getExceptionCode() {
		return this.exceptionCode;
	}

	/**
	 * Gets the exception description.
	 *
	 * @return the exception description
	 */
	public String getExceptionDescription() {
		return exceptionDescription;
	}
}
