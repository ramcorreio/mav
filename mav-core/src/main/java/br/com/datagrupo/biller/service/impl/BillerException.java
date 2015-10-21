package br.com.datagrupo.biller.service.impl;

public class BillerException extends RuntimeException {

	private static final long serialVersionUID = 3833033114612723334L;

	public BillerException() {
		super();
	}

	public BillerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BillerException(String message, Throwable cause) {
		super(message, cause);
	}

	public BillerException(String message) {
		super(message);
	}

	public BillerException(Throwable cause) {
		super(cause);
	}
}
