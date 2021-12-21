package com.chegg.fish.pet.exceptions;

/**
 * 
 * @author a15mrzcc
 *
 */
public class FishException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final FishExceptionCode exceptionCodes;

	public FishException(FishExceptionCode exceptionCodes) {
		this.exceptionCodes = exceptionCodes;
	}

	public FishExceptionCode getExceptionCodes() {
		return exceptionCodes;
	}

}
