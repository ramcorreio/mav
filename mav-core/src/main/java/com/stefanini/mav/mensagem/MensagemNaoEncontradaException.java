package com.stefanini.mav.mensagem;

public class MensagemNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 6991623147337235771L;

	public MensagemNaoEncontradaException() {
		super();
	}

	public MensagemNaoEncontradaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MensagemNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MensagemNaoEncontradaException(String message) {
		super(message);
	}

	public MensagemNaoEncontradaException(Throwable cause) {
		super(cause);
	}
}
