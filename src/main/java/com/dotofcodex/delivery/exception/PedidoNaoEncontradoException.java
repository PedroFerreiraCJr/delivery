package com.dotofcodex.delivery.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PedidoNaoEncontradoException(Long cidadeId) {
		this(String.format("Cliente de código %d não encontrado", cidadeId));
	}
}
