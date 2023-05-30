package com.dotofcodex.delivery.exception;

public class PedidoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoInvalidoException(String mensagem) {
		super(mensagem);
	}

	public PedidoInvalidoException(Long cidadeId) {
		this(String.format("Cliente de código %d não encontrado", cidadeId));
	}
}
