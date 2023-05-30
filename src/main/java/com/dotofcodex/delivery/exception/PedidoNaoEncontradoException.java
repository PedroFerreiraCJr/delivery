package com.dotofcodex.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PedidoNaoEncontradoException(Long cidadeId) {
		this(String.format("Pedido de código %d não encontrado", cidadeId));
	}
}
