package com.dotofcodex.delivery.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ClienteNaoEncontradoException(Long cidadeId) {
		this(String.format("Cliente de código %d não encontrado", cidadeId));
	}
}
